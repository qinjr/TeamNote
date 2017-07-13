package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import handler.WebsocketHandler;
import model.mysql.UserInfo;
import model.temp.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import service.UserBasicService;

import java.util.Date;

/**
 * Created by lxh on 2017/7/13.
 */
@Controller
public class SocketController {
    private UserBasicService userBasicService;
    private WebsocketHandler websocketHandler;

    @Autowired
    public SocketController(UserBasicService userBasicService, WebsocketHandler websocketHandler) {
        this.userBasicService = userBasicService;
        this.websocketHandler = websocketHandler;
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    String sendMsg(@RequestParam("notebookId") int notebookId, @RequestParam("text") String text) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        Message msg = new Message(userId, username, notebookId, text, new Date());
        websocketHandler.sendMessageToGroup(userId, notebookId, new TextMessage(new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create().toJson(msg)));

        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        json.addProperty("sender", username);
        return json.toString();
    }
}
