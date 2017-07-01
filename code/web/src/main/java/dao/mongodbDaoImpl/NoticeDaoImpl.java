package dao.mongodbDaoImpl;

import dao.mongodbDao.NoticeDao;
import model.mongodb.Notice;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class NoticeDaoImpl implements NoticeDao {
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addNotice(Notice notice){
        mongoTemplate.insert(notice, "Notice");
    }

    public void deleteNotice(Notice notice){
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(notice.getUserId()));
        mongoTemplate.remove(query, Notice.class,"Notice");
    }

    public void updateNotice(Notice notice){
        mongoTemplate.save(notice, "Notice");
    }

    public Notice getNoticeById(int userId){
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(userId));
        return mongoTemplate.findOne(query, Notice.class,"Notice");
    }

    public List<Notice> getAllNotices(){
        return mongoTemplate.findAll(Notice.class, "Notice");
    }
}
