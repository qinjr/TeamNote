package dao.mongodbDao.impl;

import dao.mongodbDao.CommentDao;
import model.mongodb.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Date;

/**
 * Created by lxh on 2017/6/30.
 */
public class CommentDaoImpl implements CommentDao {
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public int addComment(Comment comment) {
        List<Comment> comments = getAllComments();
        int id = 0;
        if(comments.size() == 0) {
            comment.setCommentId(0);
        } else {
            Comment maxComment = comments.get(0);
            for (Comment entry : comments) {
                if (entry.getCommentId() > maxComment.getCommentId()) {
                    maxComment = entry;
                }
            }
            comment.setCommentId(maxComment.getCommentId() + 1);
        }
        comment.setSentTime(new Date());
        mongoTemplate.insert(comment, "Comment");
        return id;
    }

    public void deleteComment(Comment comment) {
        Query query = new Query();
        query.addCriteria(new Criteria("commentId").is(comment.getCommentId()));
        mongoTemplate.findAndRemove(query, Comment.class,"Comment");
    }

    public void updateComment(Comment comment) {
        Query query = new Query();
        query.addCriteria(new Criteria("CommentId").is(comment.getCommentId()));
        Update update = new Update();
        update.set("userId", comment.getUserId());
        update.set("sentTime", comment.getSentTime());
        update.set("content", comment.getContent());
        update.set("reportCount", comment.getReportCount());
        update.set("valid", comment.getValid());
        mongoTemplate.updateFirst(query, update, Comment.class,"Comment");
    }

    public Comment getCommentById(int commentId) {
        Query query = new Query();
        query.addCriteria(new Criteria("commentId").is(commentId));
        return mongoTemplate.findOne(query, Comment.class,"Comment");
    }

    public List<Comment> getAllComments() {
        return mongoTemplate.findAll(Comment.class, "Comment");
    }

}
