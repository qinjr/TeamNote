package service;

import model.mongodb.Notebook;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface SearchService {
    /**
     * search
     * @param userId 发起搜索的用户
     * @param keyWord 搜索关键词
     * @return 返回搜索结果
     */
    ArrayList<Notebook> search(int userId, String keyWord);
}
