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
    ArrayList<Notebook> getRecommendNotebooks(int userId);
}
