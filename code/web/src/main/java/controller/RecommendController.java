package controller;

import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RecommendService;
import service.UserBasicService;

/**
 * Created by qjr on 2017/7/11.
 */
@Controller
public class RecommendController {
    private RecommendService recommendService;
    private UserBasicService userBasicService;

    @Autowired
    public RecommendController(RecommendService recommendService, UserBasicService userBasicService) {
        this.recommendService = recommendService;
        this.userBasicService = userBasicService;
    }

    private int getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        return userInfo.getUserId();
    }


    @RequestMapping("/recommend")
    @ResponseBody
    public String getRecommendNotebooks() {
        int userId = getUserId();
        return recommendService.getRecommendNotebooks(userId);
    }
}
