package util;

import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NoticeUtil {
    /**
     * sendNotice
     * @param receiverId 接收者
     * @param content 内容
     * @param datetime 发送时间
     * @return 1为成功，0为失败
     */
    int sendNotice(int receiverId, String content, Date datetime);
}
