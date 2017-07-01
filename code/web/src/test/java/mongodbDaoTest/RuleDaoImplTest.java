package mongodbDaoTest;

import dao.mongodbDao.RuleDao;
import model.mongodb.Rule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by qjr on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RuleDaoImplTest {
    @Autowired
    private RuleDao ruleDao;

    @Test
    public void testAddRule() {
        Rule rule = new Rule("haha");
        ruleDao.addRule(rule);

        Assert.assertNotNull(ruleDao.getAllRules().get(0));
        Assert.assertEquals(ruleDao.getAllRules().get(0).getRule(), "haha");

        ruleDao.deleteRule(rule);
    }

    @Test
    public void testDeleteRule() {
        Rule rule = new Rule("haha");
        ruleDao.addRule(rule);

        rule = ruleDao.getAllRules().get(0);
        ruleDao.deleteRule(rule);

        Assert.assertEquals(ruleDao.getAllRules().size(), 0);
    }

    @Test
    public void testUpdateRule() {
        Rule rule = new Rule("haha");
        ruleDao.addRule(rule);

        rule = ruleDao.getAllRules().get(0);
        rule.setRule("heihei");
        ruleDao.updateRule(rule);

        Assert.assertEquals(ruleDao.getAllRules().get(0).getRule(), "heihei");

        ruleDao.deleteRule(rule);
    }

    @Test
    public void testGetRuleById() {
        Rule rule = new Rule("haha");
        ruleDao.addRule(rule);

        rule = ruleDao.getAllRules().get(0);
        int ruleId = rule.getRuleId();

        Assert.assertEquals(ruleDao.getRuleById(ruleId).getRule(), rule.getRule());

        ruleDao.deleteRule(rule);
    }
}
