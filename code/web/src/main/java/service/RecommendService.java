package service;

import model.mongodb.Notebook;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface RecommendService {
    /**
     * getRecommendNotebooks
     * @param userId 发起首页浏览请求的用户Id（先登录，才能浏览首页）
     * @return 推荐系统选出的笔记本，以Json List的形式返回
     */
    String getRecommendNotebooks(int userId);


    /**
     * getRecommendTags
     * @param userId 发起请求的Id
     * @return 推荐系统选出的tag
     */
    String getRecommendTags(int userId);

    /**
     * getBooksOfTag
     * @param tagId tagId
     * @param userId login userId
     * @return 这个tag的书
     */
    String getBooksOfTag(int tagId, int userId);
}
