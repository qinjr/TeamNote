package dao.mysqlDao.impl;

import dao.mysqlDao.AuthDao;
import model.mysql.Auth;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjr on 2017/6/30.
 */
public class AuthDaoImpl extends HibernateDaoSupport implements AuthDao {
    public Integer addAuth(Auth auth) {
        getHibernateTemplate().save(auth);
        List<Auth> auths = (List<Auth>) getHibernateTemplate().find(
                "from Auth as a where a.authId=(select max(authId) from Auth)");
        return auths.size() > 0 ? auths.get(0).getAuthId() : -1;
    }

    public void deleteAuth(Auth auth) {
        getHibernateTemplate().delete(auth);
    }

    public void updateAuth(Auth auth) {
        getHibernateTemplate().merge(auth);
    }

    public Auth getAuthById(int authId) {
        List<Auth> auths = (List<Auth>) getHibernateTemplate().find("from Auth as a where a.authId=?", authId);
        return auths.size() > 0 ? auths.get(0) : null;
    }

    public ArrayList<Auth> getAllAuths() {
        ArrayList<Auth> auths = (ArrayList<Auth>) getHibernateTemplate().find("from Auth");
        return auths;
    }
}
