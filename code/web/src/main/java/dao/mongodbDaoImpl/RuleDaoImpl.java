package dao.mongodbDaoImpl;

import dao.mongodbDao.RuleDao;
import model.mongodb.Rule;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by lxh on 2017/7/1.
 */
public class RuleDaoImpl implements RuleDao {
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addRule(Rule rule){

        //新建rule的id不在此指定，而是在mysql中被创建时确定
        mongoTemplate.insert(rule, "Rule");
    }

    public void deleteRule(Rule rule){
        Query query = new Query();
        query.addCriteria(new Criteria("ruleId").is(rule.getRuleId()));
        mongoTemplate.findAndRemove(query, Rule.class,"Rule");
    }

    public void updateRule(Rule rule){
        Query query = new Query();
        query.addCriteria(new Criteria("ruleId").is(rule.getRuleId()));
        Update update = new Update();
        update.set("rule", rule.getRule());
        mongoTemplate.updateFirst(query, update, Rule.class,"Rule");
    }

    public Rule getRuleById(int ruleId){
        Query query = new Query();
        query.addCriteria(new Criteria("ruleId").is(ruleId));
        return mongoTemplate.findOne(query, Rule.class,"Rule");
    }

    public List<Rule> getAllRules(){
        return mongoTemplate.findAll(Rule.class, "Rule");
    }
}
