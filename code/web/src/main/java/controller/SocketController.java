package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import service.SocketService;

/**
 * Created by lxh on 2017/7/6.
 */
@Controller
public class SocketController {
    SocketService socketService;

    @Autowired
    public SocketController(SocketService socketService) {
        this.socketService = socketService;
    }

    @RequestMapping("/message")
    @ResponseBody
    public String sendMessage() {
        boolean hasSend = socketService.sendMessageToUser(4, new TextMessage("a message"));
        System.out.println(hasSend);
        return "message";
    }
}
