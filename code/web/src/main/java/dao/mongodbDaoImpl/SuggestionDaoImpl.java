package dao.mongodbDaoImpl;

import dao.mongodbDao.SuggestionDao;
import model.mongodb.Suggestion;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Date;

/**
 * Created by lxh on 2017/6/30.
 */
public class SuggestionDaoImpl implements SuggestionDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addSuggestion(Suggestion suggestion){
        List<Suggestion> suggestions = getAllSuggestions();
        if(suggestions.size() == 0) {
            suggestion.setSuggestionId(0);
        } else {
            Suggestion maxSuggestion = suggestions.get(0);
            for (Suggestion entry : suggestions) {
                if (entry.getSuggestionId() > maxSuggestion.getSuggestionId()) {
                    maxSuggestion = entry;
                }
            }
            suggestion.setSuggestionId(maxSuggestion.getSuggestionId() + 1);
        }
        suggestion.setRaiseTime(new Date());
        mongoTemplate.insert(suggestion, "Suggestion");
    }

    public void deleteSuggestion(Suggestion suggestion){
        Query query = new Query();
        query.addCriteria(new Criteria("suggestionId").is(suggestion.getSuggestionId()));
        mongoTemplate.findAndRemove(query, Suggestion.class,"Suggestion");
    }

    public void updateSuggestion(Suggestion suggestion){
        Query query = new Query();
        query.addCriteria(new Criteria("SuggestionId").is(suggestion.getSuggestionId()));
        Update update = new Update();
        update.set("userId", suggestion.getUserId());
        update.set("noteId", suggestion.getNoteId());
        update.set("notebookId", suggestion.getNotebookId());
        update.set("content", suggestion.getContent());
        update.set("issue", suggestion.getIssue());
        update.set("raiseTime", suggestion.getRaiseTime());
        update.set("status", suggestion.getStatus());
        mongoTemplate.updateFirst(query, update, Suggestion.class,"Suggestion");
    }

    public Suggestion getSuggestionById(int suggestionId){
        Query query = new Query();
        query.addCriteria(new Criteria("suggestionId").is(suggestionId));
        return mongoTemplate.findOne(query, Suggestion.class,"Suggestion");
    }

    public List<Suggestion> getAllSuggestions(){
        return mongoTemplate.findAll(Suggestion.class, "Suggestion");
    }
}
