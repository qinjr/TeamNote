package dao.mongodbDaoImpl;

import dao.mongodbDao.UserDao;
import model.mongodb.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class UserDaoImpl implements UserDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addUser(User user){

        //新建user的id不在此指定，而是在mysql中被创建时确定
        mongoTemplate.insert(user, "User");
    }

    public void deleteUser(User user){
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(user.getUserId()));
        mongoTemplate.findAndRemove(query, User.class,"User");
    }

    public void updateUser(User user){
        Query query = new Query();
        query.addCriteria(new Criteria("UserId").is(user.getUserId()));
        Update update = new Update();
        update.set("username", user.getUsername());
        update.set("personalStatus", user.getPersonalStatus());
        update.set("notebooks", user.getNotebooks());
        update.set("followers", user.getFollowers());
        update.set("followings", user.getFollowings());
        update.set("tags", user.getTags());
        update.set("avator", user.getAvator());
        update.set("collections", user.getCollections());
        update.set("valid", user.getValid());
        update.set("deleteCount", user.getDeleteTime());
        update.set("reputation", user.getReputation());
        update.set("reward", user.getReward());
        mongoTemplate.updateFirst(query, update, User.class,"User");
    }

    public User getUserById(int userId){
        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(userId));
        return mongoTemplate.findOne(query, User.class,"User");
    }

    public List<User> getAllUsers(){
        return mongoTemplate.findAll(User.class, "User");
    }
}
