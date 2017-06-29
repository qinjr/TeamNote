package model.mongodb;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public class Notice {
    private int userId;
    private ArrayList<JsonObject> notices;

    public Notice() {}

    public Notice(int userId, ArrayList<JsonObject> notices) {
        this.userId = userId;
        this.notices = notices;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<JsonObject> getNotices() {
        return notices;
    }

    public void setNotices(ArrayList<JsonObject> notices) {
        this.notices = notices;
    }
}
