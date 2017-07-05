package service.impl;

import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.TagDao;
import dao.mongodbDao.UserDao;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.Tag;
import model.mongodb.User;
import service.NoteManageService;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/4.
 */
public class NoteManageServiceImpl implements NoteManageService {
    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private UserDao userDao;
    private TagDao tagDao;

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Notebook getNotebookById(int notebookId) {
        return notebookDao.getNotebookById(notebookId);
    }

    public ArrayList<Notebook> getAllNotebooksByUserId(int userId) {
        User user = userDao.getUserById(userId);
        ArrayList<Integer> notebookIds = user.getNotebooks();
        ArrayList<Notebook> notebooks = new ArrayList<Notebook>();
        for (int notebookId : notebookIds) {
            notebooks.add(notebookDao.getNotebookById(notebookId));
        }
        return notebooks;
    }

    public Note getNoteById(int noteId) {
        return noteDao.getNoteById(noteId);
    }


    public int deleteNote(int noteId) {
        Note note = noteDao.getNoteById(noteId);
        noteDao.deleteNote(note);
        return 1;
    }

    public int deleteNotebook(int notebookId) {
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        notebookDao.deleteNotebook(notebook);
        return 1;
    }

    public ArrayList<ArrayList<Tag>> getTagsByNotebooks(ArrayList<Notebook> notebooks) {
        ArrayList<ArrayList<Tag>> tagsList = new ArrayList<ArrayList<Tag>>();
        for (Notebook notebook : notebooks) {
            ArrayList<Tag> tags = new ArrayList<Tag>();
            for (int tagId : notebook.getTags()) {
                tags.add(tagDao.getTagById(tagId));
            }
            tagsList.add(tags);
        }
        return tagsList;
    }

    public ArrayList<User> getOwnersByNotebooks(ArrayList<Notebook> notebooks) {
        ArrayList<User> owners = new ArrayList<User>();
        for (Notebook notebook : notebooks) {
            owners.add(userDao.getUserById(notebook.getOwner()));
        }
        return owners;
    }

    public ArrayList<ArrayList<User>> getCollaboratorsByNotebooks(ArrayList<Notebook> notebooks) {
        ArrayList<ArrayList<User>> usersList = new ArrayList<ArrayList<User>>();
        for (Notebook notebook : notebooks) {
            ArrayList<User> users = new ArrayList<User>();
            for (int userId : notebook.getCollaborators()) {
                users.add(userDao.getUserById(userId));
            }
            usersList.add(users);
        }
        return usersList;
    }

    public ArrayList<User> getCreatorsByNotebooks(ArrayList<Notebook> notebooks) {
        ArrayList<User> creators = new ArrayList<User>();
        for (Notebook notebook : notebooks) {
            creators.add(userDao.getUserById(notebook.getCreator()));
        }
        return creators;
    }

    public void updateNoteVersion(int noteId, int versionPoint) {
        Note note = noteDao.getNoteById(noteId);
        note.setVersionPointer(versionPoint);
        noteDao.updateNote(note);
    }
}
