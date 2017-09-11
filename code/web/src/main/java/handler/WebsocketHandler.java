package handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.mongodbDao.NotebookDao;
import dao.mysqlDao.UserInfoDao;
import model.mysql.UserInfo;
import model.temp.Message;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by lxh on 2017/7/13.
 */
public class WebsocketHandler implements WebSocketHandler {
    //在线用户列表
    private static final Map<Integer, WebSocketSession> users;
    private NotebookDao notebookDao;
    private UserInfoDao userInfoDao;

    static {
        users = new HashMap<Integer, WebSocketSession>();
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * 建立连接后
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("websocket connected");
        int userId = getUserId(session);
        if (users.get(userId) == null) {
            users.put(userId, session);
        }
    }

    /**
     * 消息处理，js客户端send()的消息会到这里
     * 不用
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message.getPayloadLength()==0)return;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userInfoDao.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        Message msg=new Gson().fromJson(message.getPayload().toString(),Message.class);
        msg.setFrom(userId);
        msg.setFromName(username);
        msg.setDate(new Date());

        sendMessageToGroup(userId, msg.getNotebookId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }


    public boolean sendMessageToGroup(int sender, int notebookId, TextMessage message) {
        ArrayList<Integer> groupMembers = notebookDao.getNotebookById(notebookId).getCollaborators();
        for (int member : groupMembers) {
            if(member != sender) {
                sendMessageToUser(member, message);
            }
        }
        return true;
    }

    public boolean sendMessageToUser(int userId, TextMessage message) {
        if (users.get(userId) == null) return false;
        WebSocketSession session = users.get(userId);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()) return false;
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<Integer> userIds = users.keySet();
        WebSocketSession session = null;
        for (Integer userId : userIds) {
            try {
                session = users.get(userId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }


    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("connection error");
        users.remove(getUserId(session));
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed：" + status);
        users.remove(getUserId(session));
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    public int getUserId(WebSocketSession session) {
        try {
            int userId = (Integer) session.getAttributes().get("userId");   //map 中的属性
            return userId;
        } catch (Exception e) {
            return -1;
        }
    }
}
