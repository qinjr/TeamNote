package service;

import model.mongodb.Note;
import model.mongodb.Notebook;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NoteManageService {
    /**
     * pushUpdate
     * @param userId ownerId
     * @param noteId 笔记本Id
     * @param content 修改后内容
     * @param datetime 修改时间
     * @return 1为修改成功，0为出错
     */
    int pushUpdate(int userId, int noteId, String content, Date datetime);


    /**
     * getNotebookById
     * @param notebookId 需要的笔记本Id
     * @return 笔记本详细信息，笔记本内包含的所有笔记
     */
    ArrayList<Note> getNotebookById(int notebookId);


    /**
     * getAllNotebooks
     * @return 所有笔记本
     */
    ArrayList<Notebook> getAllNotebooks();


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
}
