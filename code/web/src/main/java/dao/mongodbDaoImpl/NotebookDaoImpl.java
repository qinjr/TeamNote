package dao.mongodbDaoImpl;

import dao.mongodbDao.NotebookDao;
import model.mongodb.Notebook;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class NotebookDaoImpl implements NotebookDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addNotebook(Notebook notebook){

        //新建notebook的id不在此指定，而是在mysql中被创建时确定
        notebook.setCreateTime(new Date());
        mongoTemplate.insert(notebook, "Notebook");
    }

    public void deleteNotebook(Notebook notebook){
        Query query = new Query();
        query.addCriteria(new Criteria("notebookId").is(notebook.getNotebookId()));
        mongoTemplate.findAndRemove(query, Notebook.class,"Notebook");
    }

    public void updateNotebook(Notebook notebook){
        Query query = new Query();
        query.addCriteria(new Criteria("NotebookId").is(notebook.getNotebookId()));
        Update update = new Update();
        update.set("title", notebook.getTitle());
        update.set("description", notebook.getDescription());
        update.set("creator", notebook.getCreator());
        update.set("owner", notebook.getOwner());
        update.set("star", notebook.getStar());
        update.set("collected", notebook.getCollected());
        update.set("clickCount", notebook.getClickCount());
        update.set("collaborators", notebook.getCollaborators());
        update.set("contributors", notebook.getContributors());
        update.set("notes", notebook.getNotes());
        update.set("createTime", notebook.getCreateTime());
        mongoTemplate.updateFirst(query, update, Notebook.class,"Notebook");
    }

    public Notebook getNotebookById(int notebookId){
        Query query = new Query();
        query.addCriteria(new Criteria("notebookId").is(notebookId));
        return mongoTemplate.findOne(query, Notebook.class,"Notebook");
    }

    public List<Notebook> getAllNotebooks(){
        return mongoTemplate.findAll(Notebook.class, "Notebook");
    }
}
