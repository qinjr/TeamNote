package model.mongodb;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public class Notebook {
    private int notebookId;
    private String title;
    private String description;
    private int creator;
    private int owner;
    private int star;
    private int collected;
    private int clickCount;
    private ArrayList<Integer> collaborators;
    private ArrayList<Integer> contributors;
    private ArrayList<Integer> notes;
    private Date createTime;

    public Notebook() {}

    public Notebook(String title, String description, int creator, int owner,
                    int star, int collected, int clickCount, ArrayList<Integer> collaborators,
                    ArrayList<Integer> contributors, ArrayList<Integer> notes, Date createTime) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.owner = owner;
        this.star = star;
        this.collected = collected;
        this.clickCount = clickCount;
        this.collaborators = collaborators;
        this.contributors = contributors;
        this.notes = notes;
        this.createTime = createTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCollected() {
        return collected;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public ArrayList<Integer> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<Integer> collaborators) {
        this.collaborators = collaborators;
    }

    public ArrayList<Integer> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<Integer> contributors) {
        this.contributors = contributors;
    }

    public ArrayList<Integer> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Integer> notes) {
        this.notes = notes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
