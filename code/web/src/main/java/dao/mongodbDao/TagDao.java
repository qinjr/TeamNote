package dao.mongodbDao;

import model.mongodb.Tag;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface TagDao {
    void addTag(Tag tag);
    void deleteTag(Tag tag);
    void updateTag(Tag tag);
    Tag getTagById(int tagId);
    ArrayList<Tag> getAllTags();
}
