package dao.mongodbDaoImpl;

import dao.mongodbDao.CommentDao;
import model.mongodb.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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

    public void addComment(Comment comment) {
        List<Comment> comments = getAllComments();
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
    }

    public void deleteComment(Comment comment) {
        Query query = new Query();
        query.addCriteria(new Criteria("commentId").is(comment.getCommentId()));
        mongoTemplate.remove(query, Comment.class,"Comment");
    }

    public void updateComment(Comment comment) {
        mongoTemplate.save(comment, "Comment");
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
