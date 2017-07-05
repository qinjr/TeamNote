package service.impl;

import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.UserDao;
import dao.mysqlDao.NotebookInfoDao;
import dao.mysqlDao.UserInfoDao;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.User;
import model.mysql.NotebookInfo;
import model.mysql.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import service.CreateNoteService;
import sun.net.httpserver.HttpsServerImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lxh on 2017/7/4.
 */
public class CreateNoteServiceImpl implements CreateNoteService{

    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private NotebookInfoDao notebookInfoDao;
    private UserDao userDao;

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setNotebookInfoDao(NotebookInfoDao notebookInfoDao) {
        this.notebookInfoDao = notebookInfoDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * createNotebook
     * @param userId 创建者Id
     * @param notebookName 笔记本名称
     * @return 1为成功，0为失败
     */
    public int createNotebook(int userId, String notebookName) {
        NotebookInfo notebookInfo= new NotebookInfo();
        ArrayList<Integer> collaborators = new ArrayList<Integer>();
        ArrayList<Integer> contributors = new ArrayList<Integer>();
        collaborators.add(userId);
        contributors.add(userId);
        Date createTime = new Date();
        Notebook notebook = new Notebook(notebookName, new String(), userId, userId, 0, 0, 0,
                collaborators, contributors, new ArrayList<Integer>(), createTime, "default_notecover.png",
                new ArrayList<Integer>());
        int notebookId = notebookInfoDao.addNotebookInfo(notebookInfo);
        notebook.setNotebookId(notebookId);
        notebookDao.addNotebook(notebook);

        User user = userDao.getUserById(userId);
        ArrayList<Integer> notebooks = user.getNotebooks();
        notebooks.add(notebookId);
        user.setNotebooks(notebooks);
        userDao.updateUser(user);
        return 1;
    }

    /**
     * uploadFileNote
     * @param userId 上传者Id
     * @param notebookId 笔记本Id
     * @param content 上传的文件内容（二进制文件）
     * @param datetime 上传时间
     * @return 1为成功，0为失败
     */
    public int uploadFileNote(int userId, int notebookId, File content, Date datetime) {
        return 1;
    }


    /**
     * uploadTextNote
     * @param userId 上传者userId
     * @param notebookId 笔记本Id
     * @param content 上传的文件内容（文本文件）
     * @param datetime 上传时间
     * @return 1为成功，0为失败
     */
    public int uploadTextNote(int userId, int notebookId, String noteTitle, String content, Date datetime) {

        String firstEdition = "{\"editTime\":" + datetime.toString() + ",\"message\":\"First edition of this note\",\"content\":"
                                + content + ",\"editor\":" + userId + "}";
        ArrayList<String> history = new ArrayList<String>();
        history.add(firstEdition);
        Note note = new Note(notebookId, noteTitle, history, new ArrayList<String>(), new ArrayList<Integer>(),
                                new ArrayList<Integer>(), 0, 1, 0);
        int noteId = noteDao.addNote(note);
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        ArrayList<Integer> notes = notebook.getNotes();
        notes.add(noteId);
        notebook.setNotes(notes);
        notebookDao.updateNotebook(notebook);
        return 1;
    }
}
