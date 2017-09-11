package mongodbDaoTest;

import dao.mongodbDao.TagDao;
import model.mongodb.Tag;
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
public class TagDaoImplTest {
    @Autowired
    private TagDao tagDao;

    @Test
    public void testAddTag() {
        ArrayList<Integer> booksOfTag = new ArrayList<Integer>();
        booksOfTag.add(1);
        Tag tag = new Tag("machine forget", booksOfTag);

        tagDao.addTag(tag);

        Assert.assertNotNull(tagDao.getAllTags().get(0));
        Assert.assertEquals(tagDao.getAllTags().get(0).getTagName(), "machine forget");

        tagDao.deleteTag(tag);
    }

    @Test
    public void testDeleteTag() {
        ArrayList<Integer> booksOfTag = new ArrayList<Integer>();
        booksOfTag.add(1);
        Tag tag = new Tag("machine forget", booksOfTag);

        tagDao.addTag(tag);
        tag = tagDao.getAllTags().get(0);
        tagDao.deleteTag(tag);

        Assert.assertEquals(tagDao.getAllTags().size(), 0);
    }

    @Test
    public void testUpdateTag() {
        ArrayList<Integer> booksOfTag = new ArrayList<Integer>();
        booksOfTag.add(1);
        Tag tag = new Tag("machine forget", booksOfTag);

        tagDao.addTag(tag);
        tag = tagDao.getAllTags().get(0);
        tag.setTagName("artificial fools");
        tagDao.updateTag(tag);

        Assert.assertEquals(tagDao.getAllTags().get(0).getTagName(), "artificial fools");

        tagDao.deleteTag(tag);
    }

    @Test
    public void testGetTagById() {
        ArrayList<Integer> booksOfTag = new ArrayList<Integer>();
        booksOfTag.add(1);
        Tag tag = new Tag("machine forget", booksOfTag);

        tagDao.addTag(tag);
        tag = tagDao.getAllTags().get(0);
        int tagId = tag.getTagId();

        Assert.assertEquals(tagDao.getTagById(tagId).getTagName(), tag.getTagName());

        tagDao.deleteTag(tag);
    }
}
