package util;

import dao.mysqlDao.UserInfoDao;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;

import java.util.Map;

/**
 * Created by lxh on 2017/7/13.
 */
public interface WebsocketInterceptor {
    public void setUserInfoDao(UserInfoDao userInfoDao);

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception;

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex);
}
