package service;

import com.google.gson.JsonObject;

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
    ArrayList<JsonObject> search(int userId, String keyWord);
}
