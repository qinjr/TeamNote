package model.mongodb;


import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public class Notice {
    private int userId;
    private ArrayList<String> notices;

    public Notice() {}

    public Notice(int userId, ArrayList<String> notices) {
        this.userId = userId;
        this.notices = notices;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<String> getNotices() {
        return notices;
    }

    public void setNotices(ArrayList<String> notices) {
        this.notices = notices;
    }
}
