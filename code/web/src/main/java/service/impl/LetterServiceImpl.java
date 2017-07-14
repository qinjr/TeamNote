package service.impl;

import com.google.gson.Gson;
import dao.mongodbDao.LetterDao;
import model.mongodb.Letter;
import service.LetterService;

import java.util.Date;
import java.util.List;

/**
 * Created by qjr on 2017/7/14.
 */
public class LetterServiceImpl implements LetterService {
    private LetterDao letterDao;

    public void setLetterDao(LetterDao letterDao) {
        this.letterDao = letterDao;
    }

    public int sendLetter(int senderId, int receiverId, String content) {
        Letter letter = new Letter(senderId, receiverId, content, new Date(), 0);
        letterDao.addLetter(letter);
        return 1;
    }

    public String readLetter(int letterId) {
        Letter letter = letterDao.getLetterById(letterId);
        return new Gson().toJson(letter);
    }

    public String getAllReceivedLetters(int userId) {
        List<Letter> letters = letterDao.getLettersByReceiverId(userId);
        return new Gson().toJson(letters);
    }

    public String getAllSentLetters(int userId) {
        List<Letter> letters = letterDao.getLettersBySenderId(userId);
        return new Gson().toJson(letters);
    }
}
