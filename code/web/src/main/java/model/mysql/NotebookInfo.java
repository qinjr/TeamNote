package model.mysql;

/**
 * Created by qjr on 2017/6/27.
 */
public class NotebookInfo {
    private int notebookId;

    public NotebookInfo() {}

    public NotebookInfo(int notebookId) {
        this.notebookId = notebookId;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }
}
