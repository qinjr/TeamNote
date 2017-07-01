package mongodbDaoTest;

import dao.mongodbDao.LetterDao;
import model.mongodb.Letter;
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
public class LetterDaoImplTest {
    @Autowired
    private LetterDao letterDao;

    @Test
    public void testAddLetter() {
        Letter letter = new Letter(1, 2, "I love you", new Date());
        letterDao.addLetter(letter);
        Assert.assertNotNull(letterDao.getAllLetters().get(0));
        Assert.assertEquals(letterDao.getAllLetters().get(0).getContent(), "I love you");

        letterDao.deleteLetter(letter);
    }

    @Test
    public void testDeleteLetter() {
        Letter letter = new Letter(1, 2, "I love you", new Date());
        letterDao.addLetter(letter);
        letter = letterDao.getAllLetters().get(0);
        letterDao.deleteLetter(letter);

        Assert.assertEquals(letterDao.getAllLetters().size(), 0);

    }

    @Test
    public void testUpdateLetter() {
        Letter letter = new Letter(1, 2, "I love you", new Date());
        letterDao.addLetter(letter);
        letter = letterDao.getAllLetters().get(0);
        letter.setContent("I hate you");
        letterDao.updateLetter(letter);

        Assert.assertEquals(letterDao.getAllLetters().get(0).getContent(), "I hate you");

        letterDao.deleteLetter(letter);
    }

    @Test
    public void testGetLetterById() {
        Letter letter = new Letter(1, 2, "I love you", new Date());
        letterDao.addLetter(letter);
        letter = letterDao.getAllLetters().get(0);
        int letterId = letter.getLetterId();

        Assert.assertEquals(letterDao.getLetterById(letterId).getContent(), letter.getContent());

        letterDao.deleteLetter(letter);
    }

}
