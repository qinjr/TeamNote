package service.impl;

import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import model.mongodb.Note;
import model.mongodb.Notebook;
import service.NoteManageService;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/4.
 */
public class NoteManageServiceImpl implements NoteManageService {
    private NoteDao noteDao;
    private NotebookDao notebookDao;

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public Notebook getNotebookById(int notebookId) {
        return notebookDao.getNotebookById(notebookId);
    }


    /**
     * getAllNotebooks
     * @return 所有笔记本
     */
    public ArrayList<Notebook> getAllNotebooks() {
        return (ArrayList<Notebook>)notebookDao.getAllNotebooks();
    }


    /**
     * getNoteById
     * @param noteId 笔记Id
     * @return 笔记详细信息
     */
    public Note getNoteById(int noteId) {
        return noteDao.getNoteById(noteId);
    }


    /**
     * deleteNote
     * @param noteId 要删除的note的Id
     * @return 1为成功删除，0为出错
     */
    public int deleteNote(int noteId) {
        Note note = noteDao.getNoteById(noteId);
        noteDao.deleteNote(note);
        return 1;
    }

    /**
     * deleteNotebook
     * @param notebookId 要删除的笔记本Id
     * @return 1为成功删除，0为出错
     */
    public int deleteNotebook(int notebookId) {
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        notebookDao.deleteNotebook(notebook);
        return 1;
    }
}
