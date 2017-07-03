package service;

import java.io.File;

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
     * report
     * @param userId 举报人Id
     * @param noteId 被举报笔记Id
     * @return 1为举报已被记录，0为发生错误
     */
    int reportNote(int userId, int noteId);


    /**
     *
     * @param userId 举报人Id
     * @param commentId 被举报评论Id
     * @return 1为举报已记录，0为发生错误
     */
    int reportComment(int userId, int commentId);


    /**
     * reward
     * @param userId 打赏者Id
     * @param noteId 要打赏的笔记Id
     * @return 打赏对象的支付宝二维码
     */
    File reward(int userId, int noteId);

    /**
     * comment
     * @param userId 评论者Id
     * @param comment 评论内容
     * @return 1为成功评论，0为出错
     */
    int comment(int userId, String comment);
}
