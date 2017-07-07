package service.impl;

import dao.mongodbDao.UserDao;
import dao.mysqlDao.UserInfoDao;
import model.mongodb.User;
import model.mysql.UserInfo;
import service.UserBasicService;
import util.AuthUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lxh on 2017/7/3.
 */
public class UserBasicServiceImpl implements UserBasicService {
    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private AuthUtil authUtil;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void setUserInfoDao(UserInfoDao userInfoDao) { this.userInfoDao = userInfoDao; }
    public void setAuthUtil(AuthUtil authUtil) { this.authUtil = authUtil; }

    /**
     * register
     * @param username 用户名
     * @param password 密码
     * @param phone 电话
     * @param email 邮箱
     * @param ps 个性签名
     * @param avator 头像
     * @param feeds 关注的tags组成的list
     * @return 1为成功注册，0为失败
     */
    public int register(String username, String password, String phone, String email, String ps, String avator, ArrayList<Integer> feeds) {
        try {
            String finalPassword = authUtil.encrypt(password);
            UserInfo userInfo = new UserInfo(username, finalPassword, phone, email, "ROLE_USER");
            User user = new User(username, ps, new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), feeds, avator, new ArrayList<Integer>(),
                    1, 0, 0, "{ \"valid\": 1, \"qrcode\": \"null\"}");
            int userId = userInfoDao.addUserInfo(userInfo);
            user.setUserId(userId);
            userDao.addUser(user);
            return 1;
        } catch(Exception e) {
            return 0;
        }
    }

    public boolean usernameValidate(String username) {
        return (userInfoDao.getUserInfoByUsername(username) == null);
    }

    public UserInfo getUserInfoByUsername(String username) {
        return userInfoDao.getUserInfoByUsername(username);
    }


    //public int login(String )
    //not sure
    /*public int login(String username, String password) {
    }
    public int logout();*/


    public int deleteAccount(int userId) {
        return 1;
    }
    public int updateUsername(int userId, String newUsername) {
        return 1;
    }
    public int updatePassword(int userId, String newPassword) {
        return 1;
    }
    public int updatePhone(int userId, String newPhone) {
        return 1;
    }
    public int updateEmail(int userId, String newEmail) {
        return 1;
    }
    public int updatePs(int userId, String newPs) {
        return 1;
    }
    public int updateAvator(int userId, File newAvator) {
        return 1;
    }

    /**
     * manageFeeds
     * @param userId 用户
     * @param tagId 标签
     * @param manageType 增删等操作类型
     * @return 1为成功，0为失败
     */
    public int manageFeeds(int userId, int tagId, String manageType) {
        return 1;
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }
}
