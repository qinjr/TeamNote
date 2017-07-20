package dao.mongodbDao.impl;

import dao.mongodbDao.UserBehaviorDao;
import model.mongodb.UserBehavior;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by qjr on 2017/7/20.
 */
public class UserBehaviorDaoImpl implements UserBehaviorDao {
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addUserBehavior(UserBehavior userBehavior) {
        mongoTemplate.insert(userBehavior, "UserBehavior");
    }

    public void deleteUserBehavior(UserBehavior userBehavior) {
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(userBehavior.getUserId()));
        mongoTemplate.findAndRemove(query, UserBehavior.class, "UserBehavior");
    }

    public void updateUserBehavior(UserBehavior userBehavior) {
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(userBehavior.getUserId()));
        Update update = new Update();
        update.set("behaviors", userBehavior.getBehaviors());
        mongoTemplate.updateFirst(query, update, UserBehavior.class, "UserBehavior");
    }

    public UserBehavior getUserBehaviorById(int userId) {
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(userId));
        return mongoTemplate.findOne(query, UserBehavior.class, "UserBehavior");
    }

    public List<UserBehavior> getUserBehaviors() {
        return mongoTemplate.findAll(UserBehavior.class, "UserBehavior");
    }
}
