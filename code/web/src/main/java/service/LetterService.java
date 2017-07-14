package service;

import model.mongodb.Letter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface LetterService {
    /**
     * sendLetter
     * @param senderId 发送者Id
     * @param receiverId 接受者Id
     * @param content 私信内容
     * @return 1为成功记录入db并且提醒，0为出错
     */
    int sendLetter(int senderId, int receiverId, String content);


    /**
     * readLetter
     * @param letterId 信Id
     * @return 信的内容
     */
    String readLetter(int letterId);


    /**
     * getAllReceivedLetters
     * @param userId 用户Id
     * @return 所有这个用户收到的信
     */
    String getAllReceivedLetters(int userId);


    /**
     * getAllSentLetters
     * @param userId 用户Id
     * @return 所有这个用户发出的信
     */
    String getAllSentLetters(int userId);


}
