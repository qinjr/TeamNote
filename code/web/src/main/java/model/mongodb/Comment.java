package model.mongodb;

import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public class Comment {
    private int commentId;
    private int userId;
    private Date sentTime;
    private String content;
    private int reportCount;
    private int valid;

    public Comment() {}

    public Comment(int userId, Date sentTime, String content, int reportCount, int valid) {
        this.userId = userId;
        this.sentTime = sentTime;
        this.content = content;
        this.reportCount = reportCount;
        this.valid = valid;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
