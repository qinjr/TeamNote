package dao.mongodbDao.impl;

import dao.mongodbDao.VerifyDao;
import model.mongodb.Verify;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    public int addVerify(Verify verify){
        List<Verify> verifies = getAllVerifies();
        int id = 0;
        if(verifies.size() == 0) {
            verify.setVerifyId(0);
        } else {
            Verify maxVerify = verifies.get(0);
            for (Verify entry : verifies) {
                if (entry.getVerifyId() > maxVerify.getVerifyId()) {
                    maxVerify = entry;
                }
            }
            verify.setVerifyId(maxVerify.getVerifyId() + 1);
            id = maxVerify.getVerifyId() + 1;
        }
        mongoTemplate.insert(verify, "Verify");
        return id;
    }

    public void deleteVerify(Verify verify){
        Query query = new Query();
        query.addCriteria(new Criteria("verifyId").is(verify.getVerifyId()));
        mongoTemplate.findAndRemove(query, Verify.class,"Verify");
    }

    public void updateVerify(Verify verify){
        Query query = new Query();
        query.addCriteria(new Criteria("verifyId").is(verify.getVerifyId()));
        Update update = new Update();
        update.set("date", verify.getDate());
        update.set("type", verify.getType());
        update.set("targetId", verify.getTargetId());
        update.set("reason", verify.getReason());
        mongoTemplate.updateFirst(query, update, Verify.class,"Verify");
    }

    public Verify getVerifyById(int verifyId){
        Query query = new Query();
        query.addCriteria(new Criteria("verifyId").is(verifyId));
        return mongoTemplate.findOne(query, Verify.class,"Verify");
    }

    public List<Verify> getAllVerifies(){
        return mongoTemplate.findAll(Verify.class, "Verify");
    }

    public List<Verify> getUncheckedVerifies() {
        Query query = new Query();
        query.addCriteria(new Criteria("checked").is(0));
        return mongoTemplate.find(query, Verify.class, "Verify");
    }
}
