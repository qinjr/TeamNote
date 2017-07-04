package controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.mongodb.Notebook;
import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CooperateService;
import service.NoteManageService;
import service.UserBasicService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/3.
 */
@Controller
public class CooperateController {
    private final CooperateService cooperateService;
    private final UserBasicService userBasicService;
    private final NoteManageService noteManageService;

    @Autowired
    public CooperateController(CooperateService cooperateService, UserBasicService userBasicService,
                               NoteManageService noteManageService) {
        this.cooperateService = cooperateService;
        this.userBasicService = userBasicService;
        this.noteManageService = noteManageService;
    }



    @RequestMapping("/cooperate/giveownership")
    @ResponseBody
    public String giveOwnership(@RequestParam(value = "newOwnerId")int newOwnerId,
                                @RequestParam(value = "notebookId")int notebookId) {
        //get username from spring security
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int oldOwnerId = userInfo.getUserId();

        //change ownership and send massage to frontend
        JsonObject json = new JsonObject();
        if (cooperateService.giveOwnership(oldOwnerId, newOwnerId, notebookId) == 1) {
            json.addProperty("message", "successful");
        } else {
            json.addProperty("message", "fail");
        }

        return json.toString();
    }

    @RequestMapping("/cooperate/allnotebooks")
    @ResponseBody
    public String allNotebooks() throws UnsupportedEncodingException {
        //get username from spring security
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        //get notebooks
        ArrayList<Notebook> notebooks = noteManageService.getAllNotebooks();
        System.out.println(notebooks.get(0).getTitle());
        Gson gson = new Gson();
        String response = gson.toJson(notebooks);
        return response;
    }
}
