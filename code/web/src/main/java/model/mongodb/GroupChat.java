package model.mongodb;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public class GroupChat {
    private int notebookId;
    private ArrayList<JsonObject> contents;

    public GroupChat() {}

    public GroupChat(int notebookId, ArrayList<JsonObject> contents) {
        this.notebookId = notebookId;
        this.contents = contents;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public ArrayList<JsonObject> getContents() {
        return contents;
    }

    public void setContents(ArrayList<JsonObject> contents) {
        this.contents = contents;
    }
}
