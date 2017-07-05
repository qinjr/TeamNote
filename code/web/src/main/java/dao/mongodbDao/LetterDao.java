package dao.mongodbDao;

import model.mongodb.Letter;

import java.util.List;

/**
 * Created by qjr on 2017/6/27.
 */
public interface LetterDao {
    int addLetter(Letter letter);
    void deleteLetter(Letter letter);
    void updateLetter(Letter letter);
    Letter getLetterById(int letterId);
    List<Letter> getAllLetters();
}
