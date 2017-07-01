package model.mongodb;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public class Note {
    private int noteId;
    private int notebookId;
    private String title;
    private ArrayList<String> history;
    private ArrayList<String> comments;
    private ArrayList<Integer> upvoters;
    private ArrayList<Integer> downvoters;
    private int reportCount;
    private int valid;

    public Note() {}

    public Note(int notebookId, String title, ArrayList<String> history,
                ArrayList<String> comments, ArrayList<Integer> upvoters,
                ArrayList<Integer> downvoters, int reportCount, int valid) {
        this.notebookId = notebookId;
        this.title = title;
        this.history = history;
        this.comments = comments;
        this.upvoters = upvoters;
        this.downvoters = downvoters;
        this.reportCount = reportCount;
        this.valid = valid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public ArrayList<Integer> getUpvoters() {
        return upvoters;
    }

    public void setUpvoters(ArrayList<Integer> upvoters) {
        this.upvoters = upvoters;
    }

    public ArrayList<Integer> getDownvoters() {
        return downvoters;
    }

    public void setDownvoters(ArrayList<Integer> downvoters) {
        this.downvoters = downvoters;
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
