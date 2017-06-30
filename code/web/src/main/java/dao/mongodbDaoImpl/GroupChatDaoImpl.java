package dao.mongodbDaoImpl;

import dao.mongodbDao.GroupChatDao;
import model.mongodb.GroupChat;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class GroupChatDaoImpl implements GroupChatDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addGroupChat(GroupChat groupChat){
        mongoTemplate.insert(groupChat, "GroupChat");
    }

    public void deleteGroupChat(GroupChat groupChat){
        Query query = new Query();
        query.addCriteria(new Criteria("notebookId").is(groupChat.getNotebookId()));
        mongoTemplate.remove(query, GroupChat.class,"GroupChat");
    }

    public void updateGroupChat(GroupChat groupChat){
        mongoTemplate.save(groupChat, "GroupChat");
    }

    public GroupChat getGroupChatById(int notebookId){
        Query query = new Query();
        query.addCriteria(new Criteria("notebookId").is(notebookId));
        return mongoTemplate.findOne(query, GroupChat.class,"GroupChat");
    }

    public List<GroupChat> getAllGroupChats(){
        return mongoTemplate.findAll(GroupChat.class, "GroupChat");
    }
}
