package dao.mongodbDaoImpl;

import dao.mongodbDao.UserDao;
import model.mongodb.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
        mongoTemplate.remove(query, User.class,"User");
    }

    public void updateUser(User user){
        mongoTemplate.save(user, "User");
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
