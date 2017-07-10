package dao.mongodbDao;

import model.mongodb.Suggestion;

import java.util.List;

/**
 * Created by qjr on 2017/6/27.
 */
public interface SuggestionDao {
    int addSuggestion(Suggestion suggestion);
    void deleteSuggestion(Suggestion suggestion);
    void updateSuggestion(Suggestion suggestion);
    Suggestion getSuggestionById(int suggestionId);
    List<Suggestion> getAllSuggestions();
    List<Suggestion> getPendingSuggestionsByNoteId(int userId);
}
