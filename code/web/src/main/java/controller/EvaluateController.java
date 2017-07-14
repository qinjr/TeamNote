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
import service.EvaluateService;
import service.UserBasicService;

/**
 * Created by qjr on 2017/7/14.
 */
@Controller
public class EvaluateController {
    private final EvaluateService evaluateService;
    private final UserBasicService userBasicService;

    @Autowired
    public EvaluateController(EvaluateService evaluateService, UserBasicService userBasicService) {
        this.evaluateService = evaluateService;
        this.userBasicService = userBasicService;
    }

    @RequestMapping("/evaluate/upvoteNote")
    @ResponseBody
    public String upvoteNote(@RequestParam("noteId") int noteId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        JsonObject json = new JsonObject();
        if (evaluateService.upvote(userId, noteId) == 1) {
            json.addProperty("result", "success");
        } else {
            json.addProperty("result", "failed");
        }
        return json.toString();
    }

    @RequestMapping("/evaluate/downvoteNote")
    @ResponseBody
    public String downvoteNote(@RequestParam("noteId") int noteId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        JsonObject json = new JsonObject();
        if (evaluateService.downvote(userId, noteId) == 1) {
            json.addProperty("result", "success");
        } else {
            json.addProperty("result", "failed");
        }
        return json.toString();
    }

    @RequestMapping("/evaluate/reportNote")
    @ResponseBody
    public String reportNote(@RequestParam("noteId") int noteId, @RequestParam("reason") String reason) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        JsonObject json = new JsonObject();
        if (evaluateService.reportNote(userId, noteId, reason) == 1) {
            json.addProperty("result", "success");
        } else {
            json.addProperty("result", "failed");
        }
        return json.toString();
    }

    @RequestMapping("/evaluate/reportComment")
    @ResponseBody
    public String reportComment(@RequestParam("commentId") int commentId, @RequestParam("reason") String reason) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        JsonObject json = new JsonObject();
        if (evaluateService.reportComment(userId, commentId, reason) == 1) {
            json.addProperty("result", "success");
        } else {
            json.addProperty("result", "failed");
        }
        return json.toString();
    }

}
