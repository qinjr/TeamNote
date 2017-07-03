package dao.mysqlDao.impl;

import dao.mysqlDao.UserInfoDao;
import model.mysql.UserInfo;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjr on 2017/6/30.
 */
public class UserInfoDaoImpl extends HibernateDaoSupport implements UserInfoDao{
    public Integer addUserInfo(UserInfo userInfo) {
        getHibernateTemplate().save(userInfo);
        List<UserInfo> userInfos = (List<UserInfo>) getHibernateTemplate().find(
                "from UserInfo as u where u.username=?", userInfo.getUsername());
        return userInfos.size() > 0 ? userInfos.get(0).getUserId() : -1;
    }

    public void deleteUserInfo(UserInfo userInfo) {
        getHibernateTemplate().delete(userInfo);
    }

    public void updateUserInfo(UserInfo userInfo) {
        getHibernateTemplate().merge(userInfo);
    }

    public UserInfo getUserInfoById(int userId) {
        List<UserInfo> userInfos = (List<UserInfo>) getHibernateTemplate().find(
                "from UserInfo as u where u.userId=?", userId);
        return userInfos.size() > 0 ? userInfos.get(0) : null;
    }

    public UserInfo getUserInfoByUsername(String username) {
        List<UserInfo> userInfos = (List<UserInfo>) getHibernateTemplate().find(
                "from UserInfo as u where u.username=?", username);
        return userInfos.size() > 0 ? userInfos.get(0) : null;
    }

    public ArrayList<UserInfo> getAllUserInfos() {
        ArrayList<UserInfo> userInfos = (ArrayList<UserInfo>) getHibernateTemplate().find(
                "from UserInfo");
        return userInfos;
    }

}
