package dao.mongodbDao;


import model.mongodb.Rule;

import java.util.List;

/**
 * Created by lxh on 2017/7/1.
 */
public interface RuleDao {
    void addRule(Rule rule);
    void deleteRule(Rule rule);
    void updateRule(Rule rule);
    Rule getRuleById(int userId);
    List<Rule> getAllRules();
}
