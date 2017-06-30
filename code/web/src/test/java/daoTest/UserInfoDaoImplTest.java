package daoTest;

import dao.mysqlDao.UserInfoDao;
import model.mysql.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class UserInfoDaoImplTest {
    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    @Transactional
    public void testAddUserInfo() {
        UserInfo userInfo = new UserInfo("admin", "admin", "13456567788", "admin@teamnote.com", "admin");
        userInfoDao.addUserInfo(userInfo);
        ArrayList<UserInfo> userInfos = userInfoDao.getAllUserInfos();
        Assert.assertEquals(userInfos.get(0).getUsername(), "admin");
    }
}
