package model.mongodb;

/**
 * Created by qjr on 2017/6/29.
 */
public class Rule {
    private int ruleId;
    private String rule;

    public Rule() {}

    public Rule(String rule) {
        this.rule = rule;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
