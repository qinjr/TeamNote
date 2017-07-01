package model.mongodb;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public class GroupChat {
    private int notebookId;
    private ArrayList<String> contents;

    public GroupChat() {}

    public GroupChat(int notebookId, ArrayList<String> contents) {
        this.notebookId = notebookId;
        this.contents = contents;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }
}
