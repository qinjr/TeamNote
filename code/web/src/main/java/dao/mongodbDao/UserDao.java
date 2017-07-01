package dao.mongodbDao;

import model.mongodb.User;

import java.util.List;

/**
 * Created by qjr on 2017/6/27.
 */
public interface UserDao {
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
}
