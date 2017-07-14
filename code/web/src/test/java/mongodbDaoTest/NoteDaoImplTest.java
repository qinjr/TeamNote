package mongodbDaoTest;

import dao.mongodbDao.NoteDao;
import model.mongodb.Note;
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
public class NoteDaoImplTest {
    @Autowired
    private NoteDao noteDao;

    @Test
    public void testAddNote() {
        ArrayList<String> history = new ArrayList<String>();
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> upvoters = new ArrayList<Integer>();
        ArrayList<Integer> downvoters = new ArrayList<Integer>();
        history.add("haha");
        comments.add(1);
        upvoters.add(2);
        downvoters.add(3);

        Note note = new Note(1, "CS229-lecture1", history, comments, upvoters, downvoters, 0, 1, 0);
        noteDao.addNote(note);

        Assert.assertNotNull(noteDao.getAllNotes().get(0));
        Assert.assertEquals(noteDao.getAllNotes().get(0).getTitle(), "CS229-lecture1");

        noteDao.deleteNote(note);
    }

    @Test
    public void testDeleteNote() {
        ArrayList<String> history = new ArrayList<String>();
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> upvoters = new ArrayList<Integer>();
        ArrayList<Integer> downvoters = new ArrayList<Integer>();
        history.add("haha");
        comments.add(1);
        upvoters.add(2);
        downvoters.add(3);

        Note note = new Note(1, "CS229-lecture1", history, comments, upvoters, downvoters, 0, 1, 0);
        noteDao.addNote(note);
        note = noteDao.getAllNotes().get(0);
        noteDao.deleteNote(note);

        Assert.assertEquals(noteDao.getAllNotes().size(), 0);
    }

    @Test
    public void testUpdateNote() {
        ArrayList<String> history = new ArrayList<String>();
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> upvoters = new ArrayList<Integer>();
        ArrayList<Integer> downvoters = new ArrayList<Integer>();
        history.add("haha");
        comments.add(1);
        upvoters.add(2);
        downvoters.add(3);

        Note note = new Note(1, "CS229-lecture1", history, comments, upvoters, downvoters, 0, 1, 0);
        noteDao.addNote(note);
        note = noteDao.getAllNotes().get(0);
        note.setTitle("CS231n-lec1");
        noteDao.updateNote(note);

        Assert.assertEquals(noteDao.getAllNotes().get(0).getTitle(), "CS231n-lec1");

        noteDao.deleteNote(note);
    }

    @Test
    public void testGetNoteById() {
        ArrayList<String> history = new ArrayList<String>();
        ArrayList<Integer> comments = new ArrayList<Integer>();
        ArrayList<Integer> upvoters = new ArrayList<Integer>();
        ArrayList<Integer> downvoters = new ArrayList<Integer>();
        history.add("haha");
        comments.add(1);
        upvoters.add(2);
        downvoters.add(3);

        Note note = new Note(1, "CS229-lecture1", history, comments, upvoters, downvoters, 0, 1, 0);
        noteDao.addNote(note);
        note = noteDao.getAllNotes().get(0);
        int noteId = note.getNoteId();

        Assert.assertEquals(noteDao.getNoteById(noteId).getTitle(), note.getTitle());

        noteDao.deleteNote(note);
    }
}
