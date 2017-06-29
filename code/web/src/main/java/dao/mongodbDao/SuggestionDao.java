package dao.mongodbDao;

import model.mongodb.Suggestion;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface SuggestionDao {
    void addSuggestion(Suggestion suggestion);
    void deleteSuggestion(Suggestion suggestion);
    void updateSuggestion(Suggestion suggestion);
    Suggestion getSuggestionById(int suggestionId);
    ArrayList<Suggestion> getAllSuggestions();
}
