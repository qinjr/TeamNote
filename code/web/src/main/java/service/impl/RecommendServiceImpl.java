package service.impl;

import com.google.gson.Gson;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.TagDao;
import dao.mongodbDao.UserDao;
import model.mongodb.Notebook;
import model.mongodb.Tag;
import model.mongodb.User;
import service.RecommendService;

import java.util.ArrayList;
import java.util.HashMap;

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
        ArrayList<Notebook> notebooks = new ArrayList<Notebook>();
        for (Integer notebookId : notebookIds) {
            if (candidates.get(notebookId) >= threshold) {
                notebooks.add(notebookDao.getNotebookById(notebookId));
            }
        }
        return new Gson().toJson(notebooks);
    }
}
