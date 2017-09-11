package dao.mongodbDao.impl;

import dao.mongodbDao.NoteDao;
import model.mongodb.Note;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    public int addNote(Note note){
        List<Note> notes = getAllNotes();
        int id = 0;
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
            id = maxNote.getNoteId() + 1;
        }
        mongoTemplate.insert(note, "Note");
        return id;
    }

    public void deleteNote(Note note){
        Query query = new Query();
        query.addCriteria(new Criteria("noteId").is(note.getNoteId()));
        mongoTemplate.findAndRemove(query, Note.class,"Note");
    }

    public void updateNote(Note note){
        Query query = new Query();
        query.addCriteria(new Criteria("NoteId").is(note.getNoteId()));
        Update update = new Update();
        update.set("notebookId", note.getNotebookId());
        update.set("title", note.getTitle());
        update.set("history", note.getHistory());
        update.set("comments", note.getComments());
        update.set("upvoters", note.getUpvoters());
        update.set("downvoters", note.getDownvoters());
        update.set("reportCount", note.getReportCount());
        update.set("valid", note.getValid());
        update.set("versionPointer", note.getVersionPointer());
        mongoTemplate.updateFirst(query, update, Note.class,"Note");
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
