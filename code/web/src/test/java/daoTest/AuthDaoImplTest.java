package daoTest;

import dao.mysqlDao.AuthDao;
import dao.mysqlDao.NotebookInfoDao;
import dao.mysqlDao.UserInfoDao;
import model.mysql.Auth;
import model.mysql.NotebookInfo;
import model.mysql.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by qjr on 2017/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AuthDaoImplTest {
    @Autowired
    private AuthDao authDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private NotebookInfoDao notebookInfoDao;

    @Test
    @Transactional
    public void testAddAuth() {
        UserInfo userInfo = new UserInfo("haha", "haha", "123456788901", "haha@haha.com", "user");
        userInfoDao.addUserInfo(userInfo);
        userInfo = userInfoDao.getAllUserInfos().get(0);

        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);
        notebookInfo = notebookInfoDao.getAllNotebookInfos().get(0);

        Auth auth = new Auth(userInfo.getUserId(), notebookInfo.getNotebookId(), "owner");
        authDao.addAuth(auth);
        Assert.assertEquals(authDao.getAllAuths().get(0).getAuth(), "owner");
    }

    @Test
    @Transactional
    public void testDeleteAuth() {
        UserInfo userInfo = new UserInfo("haha", "haha", "123456788901", "haha@haha.com", "user");
        userInfoDao.addUserInfo(userInfo);
        userInfo = userInfoDao.getAllUserInfos().get(0);

        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);
        notebookInfo = notebookInfoDao.getAllNotebookInfos().get(0);

        Auth auth = new Auth(userInfo.getUserId(), notebookInfo.getNotebookId(), "owner");
        authDao.addAuth(auth);
        auth = authDao.getAllAuths().get(0);
        authDao.deleteAuth(auth);
        Assert.assertEquals(authDao.getAllAuths().size(), 0);
    }

    @Test
    @Transactional
    public void testUpdateAuth() {
        UserInfo userInfo = new UserInfo("haha", "haha", "123456788901", "haha@haha.com", "user");
        userInfoDao.addUserInfo(userInfo);
        userInfo = userInfoDao.getAllUserInfos().get(0);

        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);
        notebookInfo = notebookInfoDao.getAllNotebookInfos().get(0);

        Auth auth = new Auth(userInfo.getUserId(), notebookInfo.getNotebookId(), "owner");
        authDao.addAuth(auth);

        auth = authDao.getAllAuths().get(0);
        auth.setAuth("contributor");
        authDao.updateAuth(auth);
        Assert.assertEquals(authDao.getAllAuths().get(0).getAuth(), "contributor");
    }

    @Test
    @Transactional
    public void testGetAuthById() {
        UserInfo userInfo = new UserInfo("haha", "haha", "123456788901", "haha@haha.com", "user");
        userInfoDao.addUserInfo(userInfo);
        userInfo = userInfoDao.getAllUserInfos().get(0);

        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);
        notebookInfo = notebookInfoDao.getAllNotebookInfos().get(0);

        Auth auth = new Auth(userInfo.getUserId(), notebookInfo.getNotebookId(), "owner");
        authDao.addAuth(auth);
        auth = authDao.getAllAuths().get(0);
        int authId = auth.getAuthId();
        Assert.assertEquals(authDao.getAuthById(authId).getAuth(), "owner");
    }
}
