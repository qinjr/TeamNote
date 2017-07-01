package dao.mongodbDao;

import model.mongodb.Comment;

import java.util.List;

/**
 * Created by qjr on 2017/6/27.
 */
public interface CommentDao {
    void addComment(Comment comment);
    void deleteComment(Comment comment);
    void updateComment(Comment comment);
    Comment getCommentById(int commentId);
    List<Comment> getAllComments();
}
