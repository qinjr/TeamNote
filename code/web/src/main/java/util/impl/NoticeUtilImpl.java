package util.impl;

import com.google.gson.JsonObject;
import dao.mongodbDao.NoticeDao;
import model.mongodb.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import util.NoticeUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/7/3.
 */
public class NoticeUtilImpl implements NoticeUtil {
    private NoticeDao noticeDao;

    public NoticeDao getNoticeDao() {
        return noticeDao;
    }

    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    public int sendNotice(int receiverId, String content, Date datetime) {
        //construct new notice item string
        JsonObject json = new JsonObject();
        json.addProperty("read", 0);
        json.addProperty("content", content);
        json.addProperty("datetime", datetime.toString());

        //add it to the notice of the receiver
        Notice notice = noticeDao.getNoticeById(receiverId);
        if (notice == null) {
            ArrayList<String> notices = new ArrayList<String>();
            notices.add(json.toString());
            notice = new Notice(receiverId, notices);
            noticeDao.addNotice(notice);
        } else {
            notice.getNotices().add(json.toString());
            noticeDao.updateNotice(notice);
        }
        return 1;
    }
}
