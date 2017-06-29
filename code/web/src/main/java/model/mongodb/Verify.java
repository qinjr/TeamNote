package model.mongodb;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public class Verify {
    private Date date;
    private ArrayList<Integer> comments;
    private ArrayList<Integer> notes;

    public Verify() {}

    public Verify(Date date, ArrayList<Integer> comments, ArrayList<Integer> notes) {
        this.date = date;
        this.comments = comments;
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Integer> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
    }

    public ArrayList<Integer> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Integer> notes) {
        this.notes = notes;
    }
}
