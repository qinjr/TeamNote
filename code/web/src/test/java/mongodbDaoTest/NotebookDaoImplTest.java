package mongodbDaoTest;

import dao.mongodbDao.NotebookDao;
import model.mongodb.Notebook;
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
public class NotebookDaoImplTest {
    @Autowired
    private NotebookDao notebookDao;

    @Test
    public void testAddNotebookTest() {
        ArrayList<Integer> collaborators = new ArrayList<Integer>();
        ArrayList<Integer> contributors = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        collaborators.add(1);
        contributors.add(1);
        notes.add(1);
        Notebook notebook = new Notebook("new notebook", "about machine learning",
                1, 1, 23, 33, 789, collaborators,
                contributors, notes, new Date());

        notebookDao.addNotebook(notebook);

        Assert.assertNotNull(notebookDao.getAllNotebooks().get(0));
        Assert.assertEquals(notebookDao.getAllNotebooks().get(0).getClickCount(), 789);

        notebookDao.deleteNotebook(notebook);
    }

    @Test
    public void testDeleteNotebookTest() {
        ArrayList<Integer> collaborators = new ArrayList<Integer>();
        ArrayList<Integer> contributors = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        collaborators.add(1);
        contributors.add(1);
        notes.add(1);
        Notebook notebook = new Notebook("new notebook", "about machine learning",
                1, 1, 23, 33, 789, collaborators,
                contributors, notes, new Date());

        notebookDao.addNotebook(notebook);

        notebook = notebookDao.getAllNotebooks().get(0);
        notebookDao.deleteNotebook(notebook);

        Assert.assertEquals(notebookDao.getAllNotebooks().size(), 0);
    }

    @Test
    public void testUpdateNotebookTest() {
        ArrayList<Integer> collaborators = new ArrayList<Integer>();
        ArrayList<Integer> contributors = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        collaborators.add(1);
        contributors.add(1);
        notes.add(1);
        Notebook notebook = new Notebook("new notebook", "about machine learning",
                1, 1, 23, 33, 789, collaborators,
                contributors, notes, new Date());

        notebookDao.addNotebook(notebook);

        notebook = notebookDao.getAllNotebooks().get(0);
        notebook.setClickCount(898);
        notebookDao.updateNotebook(notebook);

        Assert.assertEquals(notebookDao.getAllNotebooks().get(0).getClickCount(), 898);

        notebookDao.deleteNotebook(notebook);
    }

    @Test
    public void testGetNotebookById() {
        ArrayList<Integer> collaborators = new ArrayList<Integer>();
        ArrayList<Integer> contributors = new ArrayList<Integer>();
        ArrayList<Integer> notes = new ArrayList<Integer>();
        collaborators.add(1);
        contributors.add(1);
        notes.add(1);
        Notebook notebook = new Notebook("new notebook", "about machine learning",
                1, 1, 23, 33, 789, collaborators,
                contributors, notes, new Date());

        notebookDao.addNotebook(notebook);

        notebook = notebookDao.getAllNotebooks().get(0);
        int notebookId = notebook.getNotebookId();

        Assert.assertEquals(notebookDao.getNotebookById(notebookId).getClickCount(), notebook.getClickCount());

        notebookDao.deleteNotebook(notebook);
    }
}
