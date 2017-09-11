package mongodbDaoTest;

import dao.mongodbDao.SuggestionDao;
import model.mongodb.Suggestion;
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
public class SuggestionDaoImplTest {
    @Autowired
    private SuggestionDao suggestionDao;

    @Test
    public void testAddSuggestion() {
        Suggestion suggestion = new Suggestion(1, 1, 1, "new content",
                "not good", new Date(), "accepted", "qinjr");
        suggestionDao.addSuggestion(suggestion);

        Assert.assertNotNull(suggestionDao.getAllSuggestions().get(0));
        Assert.assertEquals(suggestionDao.getAllSuggestions().get(0).getContent(), "new content");

        suggestionDao.deleteSuggestion(suggestion);
    }

    @Test
    public void testDeleteSuggestion() {
        Suggestion suggestion = new Suggestion(1, 1, 1, "new content",
                "not good", new Date(), "accepted", "qinjr");
        suggestionDao.addSuggestion(suggestion);

        suggestion = suggestionDao.getAllSuggestions().get(0);
        suggestionDao.deleteSuggestion(suggestion);

        Assert.assertEquals(suggestionDao.getAllSuggestions().size(), 0);
    }

    @Test
    public void testUpdateSuggestion() {
        Suggestion suggestion = new Suggestion(1, 1, 1, "new content",
                "not good", new Date(), "accepted", "qinjr");
        suggestionDao.addSuggestion(suggestion);

        suggestion = suggestionDao.getAllSuggestions().get(0);
        suggestion.setContent("heihei");
        suggestionDao.updateSuggestion(suggestion);

        Assert.assertEquals(suggestionDao.getAllSuggestions().get(0).getContent(), "heihei");

        suggestionDao.deleteSuggestion(suggestion);
    }

    @Test
    public void testGetSuggestionById() {
        Suggestion suggestion = new Suggestion(1, 1, 1, "new content",
                "not good", new Date(), "accepted", "qinjr");
        suggestionDao.addSuggestion(suggestion);

        suggestion = suggestionDao.getAllSuggestions().get(0);
        int suggestionId = suggestion.getSuggestionId();

        Assert.assertEquals(suggestionDao.getSuggestionById(suggestionId).getContent(), suggestion.getContent());

        suggestionDao.deleteSuggestion(suggestion);
    }
}
