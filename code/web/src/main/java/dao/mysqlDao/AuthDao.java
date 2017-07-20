package dao.mysqlDao;

import model.mysql.Auth;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface AuthDao {
    Integer addAuth(Auth auth);
    void deleteAuth(Auth auth);
    void updateAuth(Auth auth);
    Auth getAuthById(int authId);
    Auth getAuthByUserAndNotebook(int userId, int notebookId);
    ArrayList<Auth> getAllAuths();
}
