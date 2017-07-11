package service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.TagDao;
import dao.mongodbDao.UserDao;
import dao.mysqlDao.UserInfoDao;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.Tag;
import model.mongodb.User;
import model.mysql.UserInfo;
import service.NoteManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by qjr on 2017/7/4.
 */
public class NoteManageServiceImpl implements NoteManageService {
    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private UserDao userDao;
    private TagDao tagDao;
    private UserInfoDao userInfoDao;

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

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
        Notebook notebook = notebookDao.getNotebookById(note.getNotebookId());
        ArrayList<Integer> notes = notebook.getNotes();
        for (Integer i : notes) {
            if (i == noteId) {
                notes.remove(i);
                notebook.setNotes(notes);
                notebookDao.updateNotebook(notebook);
                break;
            }
        }
        return 1;
    }

    public int deleteNotebook(int notebookId) {
        Notebook notebook = notebookDao.getNotebookById(notebookId);

        //delete all notes of the notebook
        for (int noteId : notebook.getNotes()) {
            Note note = noteDao.getNoteById(noteId);
            noteDao.deleteNote(note);
        }

        //delete notebook
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

    public String getNotebooksDetailsByUserId(int userId) {
        ArrayList<Notebook> notebooks = getAllNotebooksByUserId(userId);
        ArrayList<ArrayList<Tag>> tagsList = getTagsByNotebooks(notebooks);
        ArrayList<User> creators = getCreatorsByNotebooks(notebooks);
        ArrayList<User> owners = getOwnersByNotebooks(notebooks);
        ArrayList<ArrayList<User>> collaboratorsList = getCollaboratorsByNotebooks(notebooks);

        ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < notebooks.size(); ++ i) {
            HashMap<String, Object> detailItem = new HashMap<String, Object>();
            detailItem.put("notebook", notebooks.get(i));
            detailItem.put("tags", tagsList.get(i));
            detailItem.put("owner", owners.get(i));
            detailItem.put("creator", creators.get(i));
            detailItem.put("collaborators", collaboratorsList.get(i));

            result.add(detailItem);
        }

        Gson gson = new Gson();
        return gson.toJson(result);
    }

    public void updateNote(int noteId, int userId, Date datetime, String content, String message) {
        String username = userInfoDao.getUserInfoById(userId).getUsername();

        Note note = noteDao.getNoteById(noteId);
        ArrayList<String> history = note.getHistory();
        if (note.getVersionPointer() + 1 < history.size()) {
            for (int i = note.getVersionPointer() + 1; i < history.size(); ++ i) {
                history.remove(i);
            }
        }

        JsonObject json = new JsonObject();
        json.addProperty("editTime", datetime.toString());
        json.addProperty("message", message);
        json.addProperty("content", content);
        json.addProperty("editor", username);

        history.add(json.toString());
        note.setHistory(history);
        note.setVersionPointer(note.getVersionPointer() + 1);
        noteDao.updateNote(note);
    }

    public void updateNoteTitle(int noteId, String newNoteTitle) {
        Note note = noteDao.getNoteById(noteId);
        note.setTitle(newNoteTitle);
        noteDao.updateNote(note);
    }

    public void updateNotebookDetail(int notebookId, String newNotebookTitle, String newDescription) {
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        notebook.setTitle(newNotebookTitle);
        notebook.setDescription(newDescription);
        notebookDao.updateNotebook(notebook);
    }

    public String getHistory(int noteId) {
        Note note = noteDao.getNoteById(noteId);
        String versions = new Gson().toJson(note.getHistory());
        return versions;
    }

    public String getNotebookDetail(int notebookId) {
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        ArrayList<String> tagNames = new ArrayList<String>();
        for (int tagId : notebook.getTags()) {
            tagNames.add(tagDao.getTagById(tagId).getTagName());
        }
        String tagNamesString = new Gson().toJson(tagNames);
        JsonObject json = new JsonObject();
        json.addProperty("title", notebook.getTitle());
        json.addProperty("description", notebook.getDescription());
        json.addProperty("tags", tagNamesString);
        return json.toString();
    }

    public void changeVersion(int noteId, int versionPointer) {
        Note note = noteDao.getNoteById(noteId);
        note.setVersionPointer(versionPointer);
        noteDao.updateNote(note);
    }
}
