package dao.mongodbDaoImpl;

import dao.mongodbDao.NoteDao;
import model.mongodb.Note;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class NoteDaoImpl implements NoteDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addNote(Note note){
        List<Note> notes = getAllNotes();
        if(notes.size() == 0) {
            note.setNoteId(0);
        } else {
            Note maxNote = notes.get(0);
            for (Note entry : notes) {
                if (entry.getNoteId() > maxNote.getNoteId()) {
                    maxNote = entry;
                }
            }
            note.setNoteId(maxNote.getNoteId() + 1);
        }
        mongoTemplate.insert(note, "Note");
    }

    public void deleteNote(Note note){
        Query query = new Query();
        query.addCriteria(new Criteria("noteId").is(note.getNoteId()));
        mongoTemplate.remove(query, Note.class,"Note");
    }

    public void updateNote(Note note){
        mongoTemplate.save(note, "Note");
    }

    public Note getNoteById(int noteId){
        Query query = new Query();
        query.addCriteria(new Criteria("noteId").is(noteId));
        return mongoTemplate.findOne(query, Note.class,"Note");
    }

    public List<Note> getAllNotes(){
        return mongoTemplate.findAll(Note.class, "Note");
    }
}
