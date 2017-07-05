package dao.mongodbDao.impl;

import dao.mongodbDao.TagDao;
import model.mongodb.Tag;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by lxh on 2017/6/30.
 */
public class TagDaoImpl implements TagDao{
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public int addTag(Tag tag){
        List<Tag> tags = getAllTags();
        int id = 0;
        if(tags.size() == 0) {
            tag.setTagId(0);
        } else {
            Tag maxTag = tags.get(0);
            for (Tag entry : tags) {
                if (entry.getTagId() > maxTag.getTagId()) {
                    maxTag = entry;
                }
            }
            tag.setTagId(maxTag.getTagId() + 1);
        }
        mongoTemplate.insert(tag, "Tag");
        return id;
    }

    public void deleteTag(Tag tag){
        Query query = new Query();
        query.addCriteria(new Criteria("tagId").is(tag.getTagId()));
        mongoTemplate.findAndRemove(query, Tag.class,"Tag");
    }

    public void updateTag(Tag tag){
        Query query = new Query();
        query.addCriteria(new Criteria("TagId").is(tag.getTagId()));
        Update update = new Update();
        update.set("tagName", tag.getTagName());
        update.set("booksOfTag", tag.getBooksOfTag());
        mongoTemplate.updateFirst(query, update, Tag.class,"Tag");
    }

    public Tag getTagById(int tagId){
        Query query = new Query();
        query.addCriteria(new Criteria("tagId").is(tagId));
        return mongoTemplate.findOne(query, Tag.class,"Tag");
    }

    public List<Tag> getAllTags(){
        return mongoTemplate.findAll(Tag.class, "Tag");
    }
}
