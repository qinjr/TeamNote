package util.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.mongodbDao.UserDao;
import model.temp.Message;
import util.ConvertUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lxh on 2017/7/17.
 */
public class ConvertUtilImpl implements ConvertUtil {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String formMessage(String content) {
        try {
            JsonObject contentObj = new JsonParser().parse(content).getAsJsonObject();
            int userId = contentObj.get("uid").getAsInt();
            String datetime = contentObj.get("datetime").getAsString();
            SimpleDateFormat insdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            SimpleDateFormat outsdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String parsedDatetime = outsdf.format(insdf.parse(datetime));
            String chatContent = contentObj.get("content").getAsString();
            String username = userDao.getUserById(userId).getUsername();
            String avatar = userDao.getUserById(userId).getAvatar();

            JsonObject messageJson = new JsonObject();
            messageJson.addProperty("uid", userId);
            messageJson.addProperty("username", username);
            messageJson.addProperty("datetime", parsedDatetime);
            messageJson.addProperty("avatar", avatar);
            messageJson.addProperty("content", chatContent);
            return messageJson.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
