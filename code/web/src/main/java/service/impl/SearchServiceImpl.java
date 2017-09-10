package service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.TagDao;
import dao.mongodbDao.UserDao;
import model.mongodb.Notebook;
import model.mongodb.User;
import service.SearchService;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService{
    private NotebookDao notebookDao;
    private UserDao userDao;
    private TagDao tagDao;

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String search(String type, String keyWord, int userId) {
        User user = userDao.getUserById(userId);
        String result;
        if (keyWord.equals("") || keyWord.equals(" ")) return "[]";
        if(type.equals("notebook")) {
            List<Notebook> notebooks = notebookDao.getNotebooksByKey(keyWord);
            ArrayList<JsonObject> notebooksRet = new ArrayList<JsonObject>();
            for (Notebook notebook : notebooks) {
                JsonObject json = new JsonObject();
                json.addProperty("owner", userDao.getUserById(notebook.getOwner()).getUsername());
                json.addProperty("ownerId", userDao.getUserById(notebook.getOwner()).getUserId());
                json.addProperty("creator", userDao.getUserById(notebook.getCreator()).getUsername());
                json.addProperty("creatorId", userDao.getUserById(notebook.getCreator()).getUserId());
                json.addProperty("title", notebook.getTitle());
                json.addProperty("description", notebook.getDescription());
                json.addProperty("createTime", notebook.getCreateTime().toString());
                json.addProperty("star", notebook.getStar());
                json.addProperty("cover", notebook.getCover());
                json.addProperty("notebookId", notebook.getNotebookId());

                if (notebook.getStarers().contains(userId)) {
                    json.addProperty("stared", 1);
                } else {
                    json.addProperty("stared", 0);
                }

                if (user.getCollections().contains(notebook.getNotebookId())) {
                    json.addProperty("collected", 1);
                } else {
                    json.addProperty("collected", 0);
                }

                ArrayList<String> tagsOfBook = new ArrayList<String>();
                for (Integer tagId : notebook.getTags()) {
                    tagsOfBook.add(tagDao.getTagById(tagId).getTagName());
                }
                json.addProperty("tags", new Gson().toJson(tagsOfBook));
                notebooksRet.add(json);
            }
            result = new Gson().toJson(notebooksRet);
        } else {
            List<User> users = userDao.getUsersByKey(keyWord);
            result = new Gson().toJson(users);
        }
        return result;
    }
}
