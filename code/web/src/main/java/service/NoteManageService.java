package service;

import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.Tag;
import model.mongodb.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NoteManageService {
    /**
     * getNotebookById
     * @param notebookId 需要的笔记本Id
     * @return 笔记本详细信息，笔记本内包含的所有笔记
     */
    Notebook getNotebookById(int notebookId);


    /**
     * @param userId 用户
     * getAllNotebooks
     * @return 用户所有的所有笔记本
     */
    ArrayList<Notebook> getAllNotebooksByUserId(int userId);


    /**
     * getNoteById
     * @param noteId 笔记Id
     * @return 笔记详细信息
     */
    Note getNoteById(int noteId);


    /**
     * deleteNote
     * @param noteId 要删除的note的Id
     * @return 1为成功删除，0为出错
     */
    int deleteNote(int noteId);

    /**
     * deleteNotebook
     * @param notebookId 要删除的笔记本Id
     * @return 1为成功删除，0为出错
     */
    int deleteNotebook(int notebookId);

    ArrayList<ArrayList<Tag>> getTagsByNotebooks(ArrayList<Notebook> notebooks);
    ArrayList<User> getOwnersByNotebooks(ArrayList<Notebook> notebooks);
    ArrayList<ArrayList<User>> getCollaboratorsByNotebooks(ArrayList<Notebook> notebooks);
    ArrayList<User> getCreatorsByNotebooks(ArrayList<Notebook> notebooks);
    /**
     * getNotebooksDetailsByUserId
     * @param userId 用户
     * @return 这个用户所有工作组的笔记本信息，上面的四个函数都被这个函数调用
     */
    String getNotebooksDetailsByUserId(int userId);

    void updateNote(int noteId, int userId, Date datetime, String content, String message);
    void updateNoteVersion(int noteId, int versionPoint);
    void updateNoteTitle(int noteId, String newNoteTitle);
    String getHistory(int noteId);
    void changeVersion(int noteId, int versionPointer);


    String getNotebookDetail(int notebookId);
    void updateNotebookDetail(int notebookId, String newNotebookTitle, String newDescription);
    String getCollaborators(int notebookId);


}
