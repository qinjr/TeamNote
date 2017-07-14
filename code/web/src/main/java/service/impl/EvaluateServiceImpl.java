package service.impl;

import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import model.mongodb.Note;
import service.EvaluateService;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/14.
 */
public class EvaluateServiceImpl implements EvaluateService {
    private NoteDao noteDao;
    private NotebookDao notebookDao;

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


    public int reportNote(int userId, int noteId) {
        return 0;
    }

    public int reportComment(int userId, int commentId) {
        return 0;
    }

    public String reward(int userId, int noteId) {
        return "";
    }

    public int comment(int userId, String comment) {
        return 0;
    }
}
