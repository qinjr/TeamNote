package service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by lxh on 2017/7/6.
 */
public interface SocketService {
    /**
     *
     * @param session
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception;


    public void handleTextMessage(WebSocketSession session, TextMessage message);

    /**
     * 发送信息给指定用户
     * @param userId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(Integer userId, TextMessage message);

    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message);


    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception;


    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception;


    public boolean supportsPartialMessages();
    /**
     * 获取用户标识
     * @param session
     * @return
     */
     public Integer getUserId(WebSocketSession session);
}
