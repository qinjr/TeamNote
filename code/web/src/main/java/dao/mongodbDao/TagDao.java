package dao.mongodbDao;

import model.mongodb.Tag;

import java.util.List;

/**
 * Created by qjr on 2017/6/27.
 */
public interface TagDao {
    int addTag(Tag tag);
    void deleteTag(Tag tag);
    void updateTag(Tag tag);
    Tag getTagById(int tagId);
    List<Tag> getAllTags();
}
