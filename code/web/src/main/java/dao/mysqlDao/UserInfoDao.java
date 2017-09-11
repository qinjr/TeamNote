package dao.mysqlDao;

import model.mysql.UserInfo;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface UserInfoDao {
    Integer addUserInfo(UserInfo userInfo);
    void deleteUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
    UserInfo getUserInfoById(int userId);
    UserInfo getUserInfoByUsername(String username);
    ArrayList<UserInfo> getAllUserInfos();
}
