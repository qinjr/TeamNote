package service.impl;

import com.google.gson.JsonObject;
import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.UserDao;
import dao.mysqlDao.AuthDao;
import dao.mysqlDao.NotebookInfoDao;
import dao.mysqlDao.UserInfoDao;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.User;
import model.mysql.Auth;
import model.mysql.NotebookInfo;
import model.mysql.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import service.CreateNoteService;
import sun.net.httpserver.HttpsServerImpl;
import util.QualityUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lxh on 2017/7/4.
 */
public class CreateNoteServiceImpl implements CreateNoteService{

    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private NotebookInfoDao notebookInfoDao;
    private AuthDao authDao;
    private UserDao userDao;
    private UserInfoDao userInfoDao;

    private QualityUtil qualityUtil;


    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setNotebookInfoDao(NotebookInfoDao notebookInfoDao) {
        this.notebookInfoDao = notebookInfoDao;
    }

    public void setAuthDao(AuthDao authDao) {
        this.authDao = authDao;
    }

    public void setQualityUtil(QualityUtil qualityUtil) {
        this.qualityUtil = qualityUtil;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;

    }

    public int createNotebook(int userId, String notebookName) {
        NotebookInfo notebookInfo= new NotebookInfo();
        ArrayList<Integer> collaborators = new ArrayList<Integer>();
        ArrayList<Integer> contributors = new ArrayList<Integer>();
        collaborators.add(userId);
        contributors.add(userId);
        Date createTime = new Date();
        Notebook notebook = new Notebook(notebookName, new String(), userId, userId, 0, 0, 0,
                collaborators, contributors, new ArrayList<Integer>(), createTime, "default_notecover.png",
                new ArrayList<Integer>(), new ArrayList<Integer>());//TODO tags, cover, description
        int notebookId = notebookInfoDao.addNotebookInfo(notebookInfo);
        Auth auth = new Auth(userId, notebookId, "owner");
        authDao.addAuth(auth);
        notebook.setNotebookId(notebookId);
        notebookDao.addNotebook(notebook);

        User user = userDao.getUserById(userId);
        ArrayList<Integer> notebooks = user.getNotebooks();
        notebooks.add(notebookId);
        user.setNotebooks(notebooks);
        userDao.updateUser(user);
        return notebookId;
    }

    /**
     * uploadFileNote
     * @param userId 上传者Id
     * @param notebookId 笔记本Id
     * @param content 上传的文件内容（二进制文件）
     * @param datetime 上传时间
     * @return 1为成功，0为失败
     */
    public int uploadFileNote(int userId, int notebookId, File content, Date datetime) throws IOException{
        FileReader reader = new FileReader(content);
        BufferedReader bReader = new BufferedReader(reader);
        String contentStr = "";
        String temp = "";
        while((temp = bReader.readLine())!= null) {
            contentStr += temp;
        }
        bReader.close();
        reader.close();
        newTextNote(userId, notebookId, "导入的笔记", contentStr, datetime);
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
    public int newTextNote(int userId, int notebookId, String noteTitle, String content, Date datetime) throws IOException {
        //check where have sensitive word
        /*
        if (qualityUtil.checkTextContent(userId, content) == 0)
            return 0;
        */

        String username = userInfoDao.getUserInfoById(userId).getUsername();
        JsonObject firstEdition = new JsonObject();
        firstEdition.addProperty("editTime", datetime.toString());
        firstEdition.addProperty("Message", "First edition");
        firstEdition.addProperty("content", content);
        firstEdition.addProperty("editor", username);
        ArrayList<String> history = new ArrayList<String>();
        history.add(firstEdition.toString());
        Note note = new Note(notebookId, noteTitle, history, new ArrayList<Integer>(), new ArrayList<Integer>(),
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
