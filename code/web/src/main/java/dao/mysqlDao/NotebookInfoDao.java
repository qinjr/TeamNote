package dao.mysqlDao;

import model.mysql.NotebookInfo;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NotebookInfoDao {
    Integer addNotebookInfo(NotebookInfo notebookInfo);
    void deleteNotebookInfo(NotebookInfo notebookInfo);
    void updateNotebookInfo(NotebookInfo notebookInfo);
    NotebookInfo getNotebookInfoById(int notebookId);
    ArrayList<NotebookInfo> getAllNotebookInfos();

}
