package model.temp;

import java.util.Date;

/**
 * Created by lxh on 2017/7/13.
 */
public class Message {
    //发送者id
    public int from;
    //发送者名称
    public String fromName;
    //接收群组
    public int notebookId;
    //发送的文本
    public String text;
    //发送日期
    public Date date;

    public String avatar;

    public Message(int from, String fromName, String avatar, int notebookId, String text, Date date) {
        this.from = from;
        this.fromName = fromName;
        this.avatar = avatar;
        this.notebookId = notebookId;
        this.text = text;
        this.date = date;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
