package mongodbDaoTest;

import dao.mongodbDao.VerifyDao;
import model.mongodb.Verify;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class VerifyDaoImplTest {
    @Autowired
    private VerifyDao verifyDao;

    @Test
    public void testAddVerify() {
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        comments.add(1);
        notes.add(2);
        Verify verify = new Verify(new Date(), comments, notes);

        verifyDao.addVerify(verify);

        Assert.assertNotNull(verifyDao.getAllVerifies().get(0));
        Assert.assertEquals(verifyDao.getAllVerifies().get(0).getComments(), verify.getComments());

        verifyDao.deleteVerify(verify);
    }

    @Test
    public void testDeleteVerify() {
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        comments.add(1);
        notes.add(2);
        Verify verify = new Verify(new Date(), comments, notes);

        verifyDao.addVerify(verify);
        verify = verifyDao.getAllVerifies().get(0);
        verifyDao.deleteVerify(verify);

        Assert.assertEquals(verifyDao.getAllVerifies().size(), 0);
    }

    @Test
    public void testUpdateVerify() {
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        comments.add(1);
        notes.add(2);
        Verify verify = new Verify(new Date(), comments, notes);

        verifyDao.addVerify(verify);
        verify = verifyDao.getAllVerifies().get(0);
        verify.getComments().set(0, 4);
        verifyDao.updateVerify(verify);

        Assert.assertEquals(verifyDao.getAllVerifies().get(0).getComments(), verify.getComments());

        verifyDao.deleteVerify(verify);
    }

    @Test
    public void testGetVerifyByDate() {
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        comments.add(1);
        notes.add(2);
        Verify verify = new Verify(new Date(), comments, notes);

        verifyDao.addVerify(verify);
        verify = verifyDao.getAllVerifies().get(0);
        Date date = verify.getDate();

        Assert.assertEquals(verifyDao.getVerifyByDate(date).getComments(), verify.getComments());

        verifyDao.deleteVerify(verify);
    }
}
