package service;

import model.mongodb.Notebook;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface SearchService {
    /**
     * search
     * @param type 搜索类型
     * @param keyWord 搜索关键词
     * @return 返回搜索结果JSONArray
     */
    String search(String type, String keyWord, int userId);
}
