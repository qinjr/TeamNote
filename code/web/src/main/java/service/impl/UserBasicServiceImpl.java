package service.impl;

import com.google.gson.Gson;
import dao.mongodbDao.TagDao;
import dao.mongodbDao.UserDao;
import dao.mysqlDao.UserInfoDao;
import model.mongodb.Tag;
import model.mongodb.User;
import model.mysql.UserInfo;
import org.springframework.web.multipart.MultipartFile;
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
    private TagDao tagDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void setUserInfoDao(UserInfoDao userInfoDao) { this.userInfoDao = userInfoDao; }
    public void setAuthUtil(AuthUtil authUtil) { this.authUtil = authUtil; }
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /**
     * register
     * @param username 用户名
     * @param password 密码
     * @param phone 电话
     * @param email 邮箱
     * @param ps 个性签名
     * @param avatar 头像
     * @param feeds 关注的tags组成的list
     * @return 1为成功注册，0为失败
     */
    public int register(String username, String password, String phone, String email, String ps, String avatar, ArrayList<Integer> feeds) {
        try {
            String finalPassword = authUtil.encrypt(password);
            UserInfo userInfo = new UserInfo(username, finalPassword, phone, email, "ROLE_USER");
            User user = new User(username, ps, new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), feeds, avatar, new ArrayList<Integer>(),
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
    public int updateavatar(int userId, File newavatar) {
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

    public void deleteUser(int userId) {
        //TODO
    }

    public void updateUserProfile(int userId, String email, String phone, String ps) {
        UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfoDao.updateUserInfo(userInfo);

        User user = userDao.getUserById(userId);
        user.setPersonalStatus(ps);
        userDao.updateUser(user);
    }

    public void updateavatar(int userId, String avatar, String path) {
        User user = userDao.getUserById(userId);
        if (!user.getAvatar().equals("/image/avatar/default_avatar.png")) {
            File oldavatar = new File(path + user.getAvatar());
            if (oldavatar.delete()) {
                user.setAvatar(avatar);
                userDao.updateUser(user);
            }
        } else {
            user.setAvatar(avatar);
            userDao.updateUser(user);
        }
    }

    public int updatePassword(int userId, String originalRawPassword, String newRawPassword) {
        UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        if (authUtil.match(originalRawPassword, userInfo.getPassword())) {
            String newPassword = authUtil.encrypt(newRawPassword);
            userInfo.setPassword(newPassword);
            userInfoDao.updateUserInfo(userInfo);
            return 1;
        }
        else
            return 0;
    }

    public void updateQrcode(int userId, String qrcode, String path) {
        User user = userDao.getUserById(userId);
        if (user.getQrcode().length() != 0) {
            File oldQrcode = new File(path + user.getQrcode());
            if (oldQrcode.delete()) {
                user.setQrcode(qrcode);
                userDao.updateUser(user);
            }
        } else {
            user.setQrcode(qrcode);
            userDao.updateUser(user);
        }
    }

    public void deleteQrcode(int userId, String path) {
        User user = userDao.getUserById(userId);
        if (!user.getQrcode().equals("")) {
            File oldQrcode = new File(path + user.getQrcode());
            if (oldQrcode.delete()) {
                user.setQrcode("");
                userDao.updateUser(user);
            }
        }
    }

    public String getFollowers(int userId) {
        User user = userDao.getUserById(userId);
        ArrayList<Integer> followersIds = user.getFollowers();
        ArrayList<User> followers = new ArrayList<User>();
        for (Integer followerId : followersIds) {
            User follower = userDao.getUserById(followerId);
            followers.add(follower);
        }
        return new Gson().toJson(followers);
    }

    public String getFollowings(int userId) {
        User user = userDao.getUserById(userId);
        ArrayList<Integer> followingsIds = user.getFollowings();
        ArrayList<User> followings = new ArrayList<User>();
        for (Integer followingId : followingsIds) {
            User following = userDao.getUserById(followingId);
            followings.add(following);
        }
        return new Gson().toJson(followings);
    }

    public String getTagsOfUser(int userId) {
        User user = userDao.getUserById(userId);
        ArrayList<Integer> tagIds = user.getTags();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        for (Integer tagId : tagIds) {
            tags.add(tagDao.getTagById(tagId));
        }
        return new Gson().toJson(tags);
    }
}
