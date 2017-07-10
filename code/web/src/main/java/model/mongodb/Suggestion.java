package model.mongodb;

import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public class Suggestion {
    private int suggestionId;
    private int userId;
    private int noteId;
    private int notebookId;
    private String content;
    private String issue;
    private Date raiseTime;
    private String status;
    private String username;

    public Suggestion() {}

    public Suggestion(int userId, int noteId, int notebookId, String content, String issue,
                      Date raiseTime, String status, String username) {
        this.userId = userId;
        this.noteId = noteId;
        this.notebookId = notebookId;
        this.content = content;
        this.issue = issue;
        this.raiseTime = raiseTime;
        this.status = status;
        this.username = username;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Date getRaiseTime() {
        return raiseTime;
    }

    public void setRaiseTime(Date raiseTime) {
        this.raiseTime = raiseTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
