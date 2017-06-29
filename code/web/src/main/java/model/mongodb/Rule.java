package model.mongodb;

import com.google.gson.JsonObject;

/**
 * Created by qjr on 2017/6/29.
 */
public class Rule {
    private int ruleId;
    private JsonObject rule;

    public Rule() {}

    public Rule(JsonObject rule) {
        this.rule = rule;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public JsonObject getRule() {
        return rule;
    }

    public void setRule(JsonObject rule) {
        this.rule = rule;
    }
}
