package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

    //@Autowired
    //public SocketController(SocketService socketService) {
        //this.socketService = socketService;
    //}

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String sendMessage(String message) throws Exception {
        Thread.sleep(1000);
        return message;
    }

}
