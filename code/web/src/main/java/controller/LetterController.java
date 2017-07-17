package controller;

import com.google.gson.JsonObject;
import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LetterService;
import service.UserBasicService;

/**
 * Created by qjr on 2017/7/14.
 */

@Controller
public class LetterController {
    private final LetterService letterService;
    private final UserBasicService userBasicService;

    @Autowired
    public LetterController(LetterService letterService, UserBasicService userBasicService) {
        this.letterService = letterService;
        this.userBasicService = userBasicService;
    }

    private int getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        return userInfo.getUserId();
    }

    @RequestMapping("/letter/sendLetter")
    @ResponseBody
    public String sendLetter(@RequestParam("receiverId") int receiverId, @RequestParam("content") String content) {
        int senderId = getUserId();
        JsonObject json = new JsonObject();
        if (letterService.sendLetter(senderId, receiverId, content) == 1) {
            json.addProperty("result", "success");
        } else {
            json.addProperty("result", "failed");
        }
        return json.toString();
    }

    @RequestMapping("/letter/getReceivedLetters")
    @ResponseBody
    public String getReceivedLetters() {
        int userId = getUserId();
        JsonObject json = new JsonObject();
        json.addProperty("letterReceived", letterService.getAllReceivedLetters(userId));
        return json.toString();
    }

    @RequestMapping("/letter/getSentLetters")
    @ResponseBody
    public String getSentLetters() {
        int userId = getUserId();
        JsonObject json = new JsonObject();
        json.addProperty("letterSent", letterService.getAllSentLetters(userId));
        return json.toString();
    }

    @RequestMapping("/letter/readLetter")
    @ResponseBody
    public String readLetter(@RequestParam("letterId") int letterId) {
        JsonObject json = new JsonObject();
        json.addProperty("letter", letterService.readLetter(letterId));
        return json.toString();
    }
}
