package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.mongodb.User;
import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserBasicService;

/**
 * Created by qjr on 2017/7/7.
 */
@Controller
public class UserController {
    private final UserBasicService userBasicService;
    @Autowired

    public UserController(UserBasicService userBasicService) {
        this.userBasicService = userBasicService;
    }

    @RequestMapping("/userdetail")
    @ResponseBody
    public String getUserDetail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();
        User user = userBasicService.getUserById(userId);
        Gson gson = new Gson();
        String userInfoString = gson.toJson(userInfo);
        String userString = gson.toJson(user);

        JsonObject json = new JsonObject();
        json.addProperty("userInfo", userInfoString);
        json.addProperty("user", userString);

        return json.toString();
    }
}
