package model.mongodb;

import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public class Letter {
    private int letterId;
    private int senderId;
    private int receiverId;
    private String content;
    private Date sentTime;

    public Letter() {}

    public Letter(int senderId, int receiverId, String content, Date sentTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.sentTime = sentTime;
    }

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
}
