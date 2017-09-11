package mongodbDaoTest;

import dao.mongodbDao.NoticeDao;
import model.mongodb.Notice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class NoticeDaoImplTest {
    @Autowired
    private NoticeDao noticeDao;

    @Test
    public void testAddNotice() {
        ArrayList<String> notices = new ArrayList<String>();
        notices.add("haha");
        Notice notice = new Notice(1, notices);
        noticeDao.addNotice(notice);

        Assert.assertNotNull(noticeDao.getAllNotices().get(0));
        Assert.assertEquals(noticeDao.getAllNotices().get(0).getUserId(), 1);

        noticeDao.deleteNotice(notice);
    }

    @Test
    public void testDeleteNotice() {
        ArrayList<String> notices = new ArrayList<String>();
        notices.add("haha");
        Notice notice = new Notice(1, notices);
        noticeDao.addNotice(notice);

        notice = noticeDao.getAllNotices().get(0);
        noticeDao.deleteNotice(notice);

        Assert.assertEquals(noticeDao.getAllNotices().size(), 0);
    }

    @Test
    public void testUpdateNotice() {
        ArrayList<String> notices = new ArrayList<String>();
        notices.add("haha");
        Notice notice = new Notice(1, notices);
        noticeDao.addNotice(notice);

        notice = noticeDao.getAllNotices().get(0);
        notices = notice.getNotices();
        notices.set(0, "heihei");
        notice.setNotices(notices);
        noticeDao.updateNotice(notice);

        Assert.assertEquals(noticeDao.getAllNotices().get(0).getNotices(), notice.getNotices());

        noticeDao.deleteNotice(notice);
    }

    @Test
    public void testGetNoticeById() {
        ArrayList<String> notices = new ArrayList<String>();
        notices.add("haha");
        Notice notice = new Notice(1, notices);
        noticeDao.addNotice(notice);

        notice = noticeDao.getAllNotices().get(0);
        int userId = notice.getUserId();

        Assert.assertEquals(noticeDao.getNoticeById(userId).getNotices(), notice.getNotices());

        noticeDao.deleteNotice(notice);
    }
}
