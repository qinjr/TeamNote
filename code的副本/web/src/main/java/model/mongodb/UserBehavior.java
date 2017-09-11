package model.mongodb;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/20.
 */
public class UserBehavior {
    private int userId;
    private ArrayList<String> behaviors;

    public UserBehavior() {}

    public UserBehavior(int userId, ArrayList<String> behaviors) {
        this.userId = userId;
        this.behaviors = behaviors;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<String> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(ArrayList<String> behaviors) {
        this.behaviors = behaviors;
    }
}
