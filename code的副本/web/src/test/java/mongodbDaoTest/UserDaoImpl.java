package mongodbDaoTest;

import dao.mongodbDao.UserDao;
import model.mongodb.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoImpl {
    @Autowired
    private UserDao userDao;

    @Test
    public void testAddUser() {
        ArrayList<Integer> notebooks = new ArrayList<Integer>();
        ArrayList<Integer> followers = new ArrayList<Integer>();
        ArrayList<Integer> followings = new ArrayList<Integer>();
        ArrayList<Integer> tags = new ArrayList<Integer>();
        ArrayList<Integer> collections = new ArrayList<Integer>();
        notebooks.add(1);
        followers.add(3);
        followings.add(2);
        tags.add(4);
        collections.add(5);

        User user = new User("qjr", "haha", notebooks, followers, followings,
                tags, null, collections, 1, 0 , 0, "haha");
        userDao.addUser(user);

        Assert.assertNotNull(userDao.getAllUsers().get(0));
        Assert.assertEquals(userDao.getAllUsers().get(0).getPersonalStatus(), "haha");

        userDao.deleteUser(user);
    }

    @Test
    public void testDeleteUser() {
        ArrayList<Integer> notebooks = new ArrayList<Integer>();
        ArrayList<Integer> followers = new ArrayList<Integer>();
        ArrayList<Integer> followings = new ArrayList<Integer>();
        ArrayList<Integer> tags = new ArrayList<Integer>();
        ArrayList<Integer> collections = new ArrayList<Integer>();
        notebooks.add(1);
        followers.add(3);
        followings.add(2);
        tags.add(4);
        collections.add(5);

        User user = new User("qjr", "haha", notebooks, followers, followings,
                tags, null, collections, 1, 0 , 0, "haha");
        userDao.addUser(user);
        user = userDao.getAllUsers().get(0);
        userDao.deleteUser(user);

        Assert.assertEquals(userDao.getAllUsers().size(), 0);
    }

    @Test
    public void testUpdateUser() {
        ArrayList<Integer> notebooks = new ArrayList<Integer>();
        ArrayList<Integer> followers = new ArrayList<Integer>();
        ArrayList<Integer> followings = new ArrayList<Integer>();
        ArrayList<Integer> tags = new ArrayList<Integer>();
        ArrayList<Integer> collections = new ArrayList<Integer>();
        notebooks.add(1);
        followers.add(3);
        followings.add(2);
        tags.add(4);
        collections.add(5);

        User user = new User("qjr", "haha", notebooks, followers, followings,
                tags, null, collections, 1, 0 , 0, "haha");
        userDao.addUser(user);
        user = userDao.getAllUsers().get(0);
        user.setPersonalStatus("heihei");
        userDao.updateUser(user);

        Assert.assertEquals(userDao.getAllUsers().get(0).getPersonalStatus(), "heihei");

        userDao.deleteUser(user);
    }

    @Test
    public void testGetUserById() {
        ArrayList<Integer> notebooks = new ArrayList<Integer>();
        ArrayList<Integer> followers = new ArrayList<Integer>();
        ArrayList<Integer> followings = new ArrayList<Integer>();
        ArrayList<Integer> tags = new ArrayList<Integer>();
        ArrayList<Integer> collections = new ArrayList<Integer>();
        notebooks.add(1);
        followers.add(3);
        followings.add(2);
        tags.add(4);
        collections.add(5);

        User user = new User("qjr", "haha", notebooks, followers, followings,
                tags, null, collections, 1, 0 , 0, "haha");
        userDao.addUser(user);
        user = userDao.getAllUsers().get(0);
        int userId = user.getUserId();

        Assert.assertEquals(userDao.getUserById(userId).getPersonalStatus(), user.getPersonalStatus());

        userDao.deleteUser(user);
    }
}
