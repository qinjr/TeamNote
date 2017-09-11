package mysqlDaoTest;

import dao.mysqlDao.NotebookInfoDao;
import model.mysql.NotebookInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by qjr on 2017/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class NotebookInfoDaoImplTest {
    @Autowired
    private NotebookInfoDao notebookInfoDao;

    @Test
    @Transactional
    public void testAddNotebookInfo() {
        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);

        List<NotebookInfo> notebookInfos = notebookInfoDao.getAllNotebookInfos();
        Assert.assertEquals(notebookInfos.size(), 1);
    }

    @Test
    @Transactional
    public void testDeleteNotebookInfo() {
        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);

        notebookInfo = notebookInfoDao.getAllNotebookInfos().get(0);
        notebookInfoDao.deleteNotebookInfo(notebookInfo);

        Assert.assertEquals(notebookInfoDao.getAllNotebookInfos().size(), 0);
    }

    @Test
    @Transactional
    public void testGetNotebookById() {
        NotebookInfo notebookInfo = new NotebookInfo();
        notebookInfoDao.addNotebookInfo(notebookInfo);
        int notebookId = notebookInfoDao.getAllNotebookInfos().get(0).getNotebookId();

        Assert.assertEquals(notebookInfoDao.getNotebookInfoById(notebookId).getNotebookId(), notebookId);
    }
}
