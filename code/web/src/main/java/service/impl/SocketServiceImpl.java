package service.impl;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import service.SocketService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lxh on 2017/7/6.
 */
public class SocketServiceImpl extends TextWebSocketHandler implements SocketService {
    //在线用户列表
    private static final Map<Integer, WebSocketSession> users;

    static {
        users = new HashMap<Integer, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功建立连接");
        Integer userId = getUserId(session);
        System.out.println(userId);
        if (userId != null) {
            users.put(userId, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
            System.out.println(userId);
            System.out.println(session);
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message.getPayload());

        WebSocketMessage message1 = new TextMessage("server:"+message);
        try {
            session.sendMessage(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessageToUser(Integer userId, TextMessage message) {
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


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("连接出错");
        users.remove(getUserId(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("连接已关闭：" + status);
        users.remove(getUserId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public Integer getUserId(WebSocketSession session) {
        try {
            Integer userId = (Integer) session.getAttributes().get("userId");   //map 中的属性
            return userId;
        } catch (Exception e) {
            return null;
        }
    }
}
