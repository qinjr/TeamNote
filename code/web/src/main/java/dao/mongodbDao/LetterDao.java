package dao.mongodbDao;

import model.mongodb.Letter;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface LetterDao {
    void addLetter(Letter letter);
    void deleteLetter(Letter letter);
    void updateLetter(Letter letter);
    Letter getLetterById(int letterId);
    ArrayList<Letter> getAllLetters();
}
