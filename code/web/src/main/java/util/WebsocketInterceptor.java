package util;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import service.UserBasicService;

import java.util.Map;

/**
 * Created by lxh on 2017/7/6.
 */
public interface WebsocketInterceptor {
    public void setUserBasicService(UserBasicService userBasicService);

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception;

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex);

}
