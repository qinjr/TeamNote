package service.impl;

import dao.mongodbDao.*;
import model.mongodb.*;
import service.EvaluateService;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/7/14.
 */
public class EvaluateServiceImpl implements EvaluateService {
    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private VerifyDao verifyDao;
    private UserDao userDao;
    private CommentDao commentDao;

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public VerifyDao getVerifyDao() {
        return verifyDao;
    }

    public void setVerifyDao(VerifyDao verifyDao) {

        this.verifyDao = verifyDao;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public NotebookDao getNotebookDao() {
        return notebookDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public int upvote(int userId, int noteId) {
        Note note = noteDao.getNoteById(noteId);
        ArrayList<Integer> upvoters = note.getUpvoters();
        upvoters.add(userId);
        note.setUpvoters(upvoters);
        noteDao.updateNote(note);
        return 1;
    }

    public int downvote(int userId, int noteId) {
        Note note = noteDao.getNoteById(noteId);
        ArrayList<Integer> downvoters = note.getDownvoters();
        downvoters.add(userId);
        note.setDownvoters(downvoters);
        noteDao.updateNote(note);
        return 1;
    }

    public int reportNote(int userId, int noteId, String reason) {
        Verify verify = new Verify(new Date(), 1, noteId, reason, 0, userId);
        verifyDao.addVerify(verify);
        Note note = noteDao.getNoteById(noteId);
        note.setReportCount(note.getReportCount() + 1);
        noteDao.updateNote(note);
        return 1;
    }

    public int reportComment(int userId, int commentId, String reason) {
        Verify verify = new Verify(new Date(), 0, commentId, reason, 0, userId);
        verifyDao.addVerify(verify);
        Comment comment = commentDao.getCommentById(commentId);
        comment.setReportCount(comment.getReportCount() + 1);
        commentDao.updateComment(comment);
        return 1;
    }

    public String reward(int noteId) {
        Note note = noteDao.getNoteById(noteId);
        Notebook notebook = notebookDao.getNotebookById(note.getNotebookId());
        User user = userDao.getUserById(notebook.getOwner());
        return user.getQrcode();
    }

    public int newComment(int userId, String content, int noteId) {
        Comment comment = new Comment(userId, new Date(), content, 0, 1);
        int commentId = commentDao.addComment(comment);

        Note note = noteDao.getNoteById(noteId);
        ArrayList<Integer> comments = note.getComments();
        comments.add(commentId);
        note.setComments(comments);
        noteDao.updateNote(note);
        return 1;
    }

    public ArrayList<Comment> getCommentsByNote(int noteId) {
        Note note = noteDao.getNoteById(noteId);
        ArrayList<Integer> commentsIds = note.getComments();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        for (Integer commentId : commentsIds) {
            comments.add(commentDao.getCommentById(commentId));
        }
        return comments;
    }
}
