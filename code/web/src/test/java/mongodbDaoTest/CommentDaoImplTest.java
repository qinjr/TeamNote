package mongodbDaoTest;

import dao.mongodbDao.CommentDao;
import model.mongodb.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by qjr on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentDaoImplTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    public void testAddComment() {
        Comment comment = new Comment(1, new Date(), "this is good", 0, 1);
        commentDao.addComment(comment);
        Assert.assertNotNull(commentDao.getAllComments().get(0));
        Assert.assertEquals(commentDao.getAllComments().get(0).getContent(), "this is good");

        //transaction rollback, mongodb doesn't support transaction roll back using spring data
        commentDao.deleteComment(comment);
    }

    @Test
    public void testDeleteComment() {
        Comment comment = new Comment(1, new Date(), "this is good", 0, 1);
        commentDao.addComment(comment);
        comment = commentDao.getAllComments().get(0);
        commentDao.deleteComment(comment);
        Assert.assertEquals(commentDao.getAllComments().size(), 0);
    }

    @Test
    public void testUpdateComment() {
        Comment comment = new Comment(1, new Date(), "this is good", 0, 1);
        commentDao.addComment(comment);
        comment = commentDao.getAllComments().get(0);
        comment.setContent("hhhhh");
        commentDao.updateComment(comment);
        Assert.assertEquals(commentDao.getAllComments().get(1).getContent(), "hhhhh");

        commentDao.deleteComment(comment);
    }

    @Test
    public void testGetCommentById() {
        Comment comment = new Comment(1, new Date(), "this is good", 0, 1);
        commentDao.addComment(comment);
        comment = commentDao.getAllComments().get(0);
        int commentId = comment.getCommentId();
        Assert.assertEquals(commentDao.getCommentById(commentId).getContent(), "this is good");

        commentDao.deleteComment(comment);
    }
}
