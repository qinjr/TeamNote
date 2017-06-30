package dao.mongodbDaoImpl;

import dao.mongodbDao.VerifyDao;
import model.mongodb.Verify;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class VerifyDaoImpl implements VerifyDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addVerify(Verify verify){

        //新建verify的id不在此指定，而是在mysql中被创建时确定
        mongoTemplate.insert(verify, "Verify");
    }

    public void deleteVerify(Verify verify){
        Query query = new Query();
        query.addCriteria(new Criteria("date").is(verify.getDate()));
        mongoTemplate.remove(query, Verify.class,"Verify");
    }

    public void updateVerify(Verify verify){
        mongoTemplate.save(verify, "Verify");
    }

    public Verify getVerifyByDate(Date date){
        Query query = new Query();
        query.addCriteria(new Criteria("date").is(date));
        return mongoTemplate.findOne(query, Verify.class,"Verify");
    }

    public List<Verify> getAllVerifies(){
        return mongoTemplate.findAll(Verify.class, "Verify");
    }
}
