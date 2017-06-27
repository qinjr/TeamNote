package service;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NoticeService {
    /**
     * sendNotice
     * @param userId 通知接受者Id
     * @param notice 通知
     * @return 1为发送成功，0为失败
     */
    public int sendNotice(int userId, String notice);
}
