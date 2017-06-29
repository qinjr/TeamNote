package dao.mongodbDao;

import model.mongodb.Notebook;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NotebookDao {
    void addNotebook(Notebook notebook);
    void deleteNotebook(Notebook notebook);
    void updateNotebook(Notebook notebook);
    Notebook getNotebookById(int notebookId);
    ArrayList<Notebook> getAllNotebooks();
}
