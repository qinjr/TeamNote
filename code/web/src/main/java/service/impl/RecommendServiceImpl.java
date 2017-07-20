package service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.TagDao;
import dao.mongodbDao.UserDao;
import model.mongodb.Notebook;
import model.mongodb.Tag;
import model.mongodb.User;
import service.RecommendService;

import java.util.*;

/**
 * Created by qjr on 2017/7/11.
 */
public class RecommendServiceImpl implements RecommendService {
    private UserDao userDao;
    private TagDao tagDao;
    private NotebookDao notebookDao;

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    private double tagValue = 100;
    private double followingValue = 80;
    private double threshold = 50;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getRecommendNotebooks(int userId) {
        HashMap<Integer, Double> candidates = new HashMap<Integer, Double>();

        User user = userDao.getUserById(userId);
        ArrayList<Integer> tags = user.getTags();

        //candidates according to tags
        for (Integer tagId : tags) {
            Tag tag = tagDao.getTagById(tagId);
            for (Integer notebookId : tag.getBooksOfTag()) {
                candidates.put(notebookId, tagValue);
            }
        }

        //get final result
        ArrayList<Integer> notebookIds = new ArrayList<Integer>(candidates.keySet());
        ArrayList<JsonObject> notebooks = new ArrayList<JsonObject>();
        for (Integer notebookId : notebookIds) {
            if (candidates.get(notebookId) >= threshold) {
                JsonObject json = new JsonObject();
                Notebook notebook = notebookDao.getNotebookById(notebookId);
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
                notebooks.add(json);
            }
        }

        //cold start
        if (notebooks.size() == 0) {
            ArrayList<Tag> allTags = (ArrayList<Tag>) tagDao.getAllTags();
            Set<Integer> notebookIdSet = new HashSet<Integer>();
            for (Tag tag : allTags) {
                int notebookId = tag.getBooksOfTag().get(0);
                notebookIdSet.add(notebookId);
            }
            for (Object aNotebookIdSet : notebookIdSet) {
                JsonObject json = new JsonObject();
                Notebook notebook = notebookDao.getNotebookById((Integer) aNotebookIdSet);
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
                notebooks.add(json);
            }
        }
        return new Gson().toJson(notebooks);
    }

    public String getRecommendTags(int userId) {
        ArrayList<Tag> tags = (ArrayList<Tag>) tagDao.getAllTags();
        ArrayList<Integer> userTags = userDao.getUserById(userId).getTags();
        ArrayList<Tag> recommendTags = new ArrayList<Tag>();
        for (Tag tag : tags) {
            if (!userTags.contains(tag.getTagId())) {
                recommendTags.add(tag);
            }
        }
        return new Gson().toJson(recommendTags);
    }

    public String getBooksOfTag(int tagId, int userId) {
        User user = userDao.getUserById(userId);
        Tag tag = tagDao.getTagById(tagId);
        ArrayList<JsonObject> booksOfTag = new ArrayList<JsonObject>();

        for (Integer notebookId : tag.getBooksOfTag()) {
            Notebook notebook = notebookDao.getNotebookById(notebookId);
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
            for (Integer tagIdOfBook : notebook.getTags()) {
                tagsOfBook.add(tagDao.getTagById(tagIdOfBook).getTagName());
            }
            json.addProperty("tags", new Gson().toJson(tagsOfBook));
            booksOfTag.add(json);
        }
        return new Gson().toJson(booksOfTag);
    }
}
