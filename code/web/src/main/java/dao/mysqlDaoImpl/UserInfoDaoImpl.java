package dao.mysqlDaoImpl;

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
        return (Integer) getHibernateTemplate().save(userInfo);
    }

    public void deleteUserInfo(UserInfo userInfo) {
        getHibernateTemplate().save(userInfo);
    }

    public void updateUserInfo(UserInfo userInfo) {
        getHibernateTemplate().merge(userInfo);
    }

    public UserInfo getUserInfoById(int userId) {
        List<UserInfo> userInfos = (List<UserInfo>) getHibernateTemplate().find(
                "from UserInfo as u where u.userId=?", userId);
        return userInfos.size() > 0 ? userInfos.get(0) : null;
    }

    public ArrayList<UserInfo> getAllUserInfos() {
        ArrayList<UserInfo> userInfos = (ArrayList<UserInfo>) getHibernateTemplate().find(
                "from UserInfo");
        return userInfos;
    }

}
