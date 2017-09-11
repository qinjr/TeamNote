package dao.mongodbDao;

import model.mongodb.Note;

import java.util.List;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NoteDao {
    int addNote(Note note);
    void deleteNote(Note note);
    void updateNote(Note note);
    Note getNoteById(int noteId);
    List<Note> getAllNotes();
}
