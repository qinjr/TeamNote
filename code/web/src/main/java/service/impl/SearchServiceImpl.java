package service.impl;

import com.google.gson.Gson;
import dao.mongodbDao.NotebookDao;
import dao.mongodbDao.UserDao;
import model.mongodb.Notebook;
import model.mongodb.User;
import service.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService{
    private NotebookDao notebookDao;
    private UserDao userDao;

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String search(String type, String keyWord){
        String result;
        if (keyWord.equals("") || keyWord.equals(" ")) return "[]";
        if(type.equals("notebook")) {
            List<Notebook> notebooks = notebookDao.getNotebooksByKey(keyWord);
            result = new Gson().toJson(notebooks);
        } else {
            List<User> users = userDao.getUsersByKey(keyWord);
            result = new Gson().toJson(users);
        }
        return result;
    }
}
