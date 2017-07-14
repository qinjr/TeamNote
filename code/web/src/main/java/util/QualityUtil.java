package util;

import java.io.IOException;

/**
 * Created by qjr on 2017/6/28.
 */
public interface QualityUtil {
    /**
     * checkTextContent
     * @param userId 上传者Id
     * @param content 上传内容
     * @return 机器过滤结果（通过、不通过原因）
     */
    public int checkTextContent(int userId, String content) throws IOException;
}
