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
}
