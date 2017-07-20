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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.UserBasicService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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

    private int getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        return userInfo.getUserId();
    }

    private int checkSelf(int userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int loginUserId = userInfo.getUserId();
        if (userId == loginUserId)
            return 1;
        else
            return 0;
    }

    @RequestMapping("/settings")
    public String settings() {
        return "settings";
    }

    @RequestMapping("/userdetail")
    @ResponseBody
    public String getUserDetail(@RequestParam("userId") int userId) {
        User user = userBasicService.getUserById(userId);
        UserInfo userInfo = userBasicService.getUserInfoByUsername(user.getUsername());
        Gson gson = new Gson();
        String userInfoString = gson.toJson(userInfo);
        String userString = gson.toJson(user);

        JsonObject json = new JsonObject();
        json.addProperty("userInfo", userInfoString);
        json.addProperty("user", userString);
        if (checkSelf(userId) == 1) {
            json.addProperty("self", true);
        } else {
            json.addProperty("self", false);
        }
        return json.toString();
    }

    @RequestMapping("/loginUserDetail")
    @ResponseBody
    public String loginUserDetail() {
        int userId = getUserId();
        User user = userBasicService.getUserById(userId);
        UserInfo userInfo = userBasicService.getUserInfoByUsername(user.getUsername());
        String userString = new Gson().toJson(user);
        String userInfoString = new Gson().toJson(userInfo);

        JsonObject json = new JsonObject();
        json.addProperty("userInfo", userInfoString);
        json.addProperty("user", userString);
        return json.toString();
    }

    @RequestMapping("/updateUserProfile")
    @ResponseBody
    public String updateUserProfile(@RequestParam("email") String email, @RequestParam("phone") String phone,
                                    @RequestParam("ps") String ps) {
        int userId = getUserId();
        userBasicService.updateUserProfile(userId, email, phone, ps);

        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping("/updateUserPassword")
    @ResponseBody
    public String updateUserPassword(@RequestParam("originalRawPassword") String originalRawPassword,
                                     @RequestParam("newRawPassword") String newRawPassword) {
        int userId = getUserId();

        JsonObject json = new JsonObject();
        if (userBasicService.updatePassword(userId, originalRawPassword, newRawPassword) == 1) {
            json.addProperty("result", "success");
        } else {
            json.addProperty("result", "origin password wrong");
        }
        return json.toString();
    }

    @RequestMapping("/uploadAvatar")
    public String uploadavatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        ServletContext servletContext = request.getServletContext();
        String avatarName = Long.toString(System.currentTimeMillis()) + file.getOriginalFilename();
        String destPath = servletContext.getRealPath("/image/avatar/") + avatarName;
        file.transferTo(new File(destPath));

        int userId = getUserId();

        userBasicService.updateavatar(userId, "/image/avatar/" + avatarName, servletContext.getRealPath("/"));
        return "redirect:/settings";
    }

    @RequestMapping("/uploadQrcode")
    public String uploadQrcode(@RequestParam("qrcode") MultipartFile qrcode, HttpServletRequest request) throws IOException {
        ServletContext servletContext = request.getServletContext();
        String qrcodeName = Long.toString(System.currentTimeMillis()) + qrcode.getOriginalFilename();
        String destPath = servletContext.getRealPath("/image/qrcode/") + qrcodeName;
        qrcode.transferTo(new File(destPath));

        int userId = getUserId();

        userBasicService.updateQrcode(userId, "/image/qrcode/" + qrcodeName, servletContext.getRealPath("/"));
        return "redirect:/settings";
    }

    @RequestMapping("/deleteQrcode")
    public String deleteQrcode(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        int userId = getUserId();

        userBasicService.deleteQrcode(userId, servletContext.getRealPath("/"));
        return "redirect:/settings";
    }

    @RequestMapping("/getFollowers")
    @ResponseBody
    public String getFollowers(@RequestParam("userId") int userId) {
        JsonObject json = new JsonObject();
        String followers = userBasicService.getFollowers(userId);
        json.addProperty("followers", followers);
        if (checkSelf(userId) == 1) {
            json.addProperty("self", true);
        } else {
            json.addProperty("self", false);
        }
        return json.toString();
    }

    @RequestMapping("/getFollowings")
    @ResponseBody
    public String getFollowings(@RequestParam("userId") int userId) {
        JsonObject json = new JsonObject();
        String followings = userBasicService.getFollowings(userId);
        json.addProperty("followings", followings);
        if (checkSelf(userId) == 1) {
            json.addProperty("self", true);
        } else {
            json.addProperty("self", false);
        }
        return json.toString();
    }

    @RequestMapping("/getTagsOfUser")
    @ResponseBody
    public String getTagsOfUser(@RequestParam("userId") int userId) {
        String tags = userBasicService.getTagsOfUser(userId);
        return tags;
    }

    @RequestMapping("/follow")
    @ResponseBody
    public String follow(@RequestParam("userId") int userId) {
        int loginUserId = getUserId();
        return userBasicService.follow(loginUserId, userId);
    }

    @RequestMapping("/unfollow")
    @ResponseBody
    public String unfollow(@RequestParam("userId") int userId) {
        int loginUserId = getUserId();
        userBasicService.unfollow(loginUserId, userId);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }
}