package dao.mongodbDaoImpl;

import dao.mongodbDao.LetterDao;
import model.mongodb.Letter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Date;

/**
 * Created by lxh on 2017/6/30.
 */
public class LetterDaoImpl implements LetterDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addLetter(Letter letter){
        List<Letter> letters = getAllLetters();
        if(letters.size() == 0) {
            letter.setLetterId(0);
        } else {
            Letter maxLetter = letters.get(0);
            for (Letter entry : letters) {
                if (entry.getLetterId() > maxLetter.getLetterId()) {
                    maxLetter = entry;
                }
            }
            letter.setLetterId(maxLetter.getLetterId() + 1);
        }
        letter.setSentTime(new Date());
        mongoTemplate.insert(letter, "Letter");
    }
    public void deleteLetter(Letter letter){
        Query query = new Query();
        query.addCriteria(new Criteria("letterId").is(letter.getLetterId()));
        mongoTemplate.findAndRemove(query, Letter.class,"Letter");
    }
    public void updateLetter(Letter letter){
        Query query = new Query();
        query.addCriteria(new Criteria("letterId").is(letter.getLetterId()));
        Update update = new Update();
        update.set("senderId", letter.getSenderId());
        update.set("receiverId", letter.getReceiverId());
        update.set("content", letter.getContent());
        update.set("sentTime", letter.getSentTime());
        mongoTemplate.updateFirst(query, update, Letter.class,"Letter");
    }
    public Letter getLetterById(int letterId){
        Query query = new Query();
        query.addCriteria(new Criteria("letterId").is(letterId));
        return mongoTemplate.findOne(query, Letter.class,"Letter");
    }
    public List<Letter> getAllLetters(){
        return mongoTemplate.findAll(Letter.class, "Letter");
    }
}
