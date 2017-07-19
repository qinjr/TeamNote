package service;

import model.mongodb.User;
import model.mysql.UserInfo;
import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface UserBasicService {
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
    int register(String username, String password, String phone, String email, String ps, String avatar, ArrayList<Integer> feeds);

    boolean usernameValidate(String username);

    int deleteAccount(int userId);
    int updateUsername(int userId, String newUsername);
    int updatePassword(int userId, String newPassword);
    int updatePhone(int userId, String newPhone);
    int updateEmail(int userId, String newEmail);
    int updatePs(int userId, String newPs);

    /**
     * manageFeeds
     * @param userId 用户
     * @param tagId 标签
     * @param manageType 增删等操作类型
     * @return 1为成功，0为失败
     */
    int manageFeeds(int userId, int tagId, String manageType);


    /**
     * getUserInfoByUserName
     * @param username 用户名
     * @return UserInfo
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * getUserById
     * @param userId 用户Id
     * @return User
     */
    User getUserById(int userId);

    void deleteUser(int userId);

    void updateUserProfile(int userId, String email, String phone, String ps);

    void updateavatar(int userId, String avatar, String path);

    int updatePassword(int userId, String originalRawPassword, String newRawPassword);

    void updateQrcode(int userId, String qrcode, String path);

    void deleteQrcode(int userId, String path);

    String getFollowers(int userId);

    String getFollowings(int userId);

    String getTagsOfUser(int userId);
}