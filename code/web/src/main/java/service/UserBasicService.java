package service;

import java.io.File;
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
     * @param avator 头像
     * @param feeds 关注的tags组成的list
     * @return 1为成功注册，0为失败
     */
    int register(String username, String password, String phone, String email, String ps, Byte[] avator, ArrayList<Integer> feeds);


    //not sure
    //int login(String username, String password);
    //int logout();


    int deleteAccount(int userId);
    int updateUsername(int userId, String newUsername);
    int updatePassword(int userId, String newPassword);
    int updatePhone(int userId, String newPhone);
    int updateEmail(int userId, String newEmail);
    int updatePs(int userId, String newPs);
    int updateAvator(int userId, File newAvator);


    /**
     * manageFeeds
     * @param userId 用户
     * @param tagId 标签
     * @param manageType 增删等操作类型
     * @return 1为成功，0为失败
     */
    int manageFeeds(int userId, int tagId, String manageType);
}