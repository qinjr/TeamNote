package service;

import com.google.gson.JsonObject;
import model.mongodb.Comment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface EvaluateService {
    /**
     * upvote
     * @param userId 点赞用户Id
     * @param noteId 被点赞笔记Id
     * @return 1为点赞成功，0为发生错误
     */
    int upvote(int userId, int noteId);

    /**
     * downvote
     * @param userId 踩者Id
     * @param noteId 被踩笔记Id
     * @return 1为成功，0为发生错误
     */
    int downvote(int userId, int noteId);

    /**
     * star
     * @param userId 用户
     * @param notebookId 笔记本
     * @return 1为成功，0为错误
     */
    int star(int userId, int notebookId);

    int unstar(int userId, int notebookId);


    /**
     * report
     * @param userId 举报人Id
     * @param noteId 被举报笔记Id
     * @param reason 举报原因
     * @return 1为举报已被记录，0为发生错误
     */
    int reportNote(int userId, int noteId, String reason);

    int reportNotebook(int userId, int notebookId, String reason);

    /**
     *
     * @param userId 举报人Id
     * @param commentId 被举报评论Id
     * @param reason 举报原因
     * @return 1为举报已记录，0为发生错误
     */
    int reportComment(int userId, int commentId, String reason);


    /**
     * reward
     * @param noteId 要打赏的笔记Id
     * @return 打赏对象的支付宝二维码图片路径
     */
    String reward(int noteId);

    /**
     * comment
     * @param userId 评论者Id
     * @param content 评论内容
     * @param noteId 评论的笔记Id
     * @return 1为成功评论，0为出错
     */
    int newComment(int userId, String content, int noteId);

    /**
     * getCommentsByNote
     * @param noteId noteId
     * @return 这个note所有的comments
     */
    ArrayList<JsonObject> getCommentsByNote(int noteId);
}
