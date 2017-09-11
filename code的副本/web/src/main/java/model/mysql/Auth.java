package model.mysql;

/**
 * Created by qjr on 2017/6/27.
 */
public class Auth {
    private int authId;
    private int userId;
    private int notebookId;
    private String auth;

    public Auth() {}

    public Auth(int userId, int notebookId, String auth) {
        this.userId = userId;
        this.notebookId = notebookId;
        this.auth = auth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }
}
