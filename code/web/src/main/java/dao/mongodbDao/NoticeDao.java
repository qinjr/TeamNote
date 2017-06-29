package dao.mongodbDao;

import model.mongodb.Notice;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface NoticeDao {
    void addNotice(Notice notice);
    void deleteNotice(Notice notice);
    void updateNotice(Notice notice);
    Notice getNoticeById(int noticeId);
    ArrayList<Notice> getAllNotices();
}
