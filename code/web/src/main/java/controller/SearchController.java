package controller;

import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SearchService;
import service.UserBasicService;

@Controller
public class SearchController {
    private SearchService searchService;
    private UserBasicService userBasicService;

    private int getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        return userInfo.getUserId();
    }

    @Autowired
    public SearchController(SearchService searchService, UserBasicService userBasicService) {
        this.searchService = searchService;
        this.userBasicService = userBasicService;
    }

    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam("type") String type, @RequestParam("keyWord") String keyWord) {
        String result = searchService.search(type, keyWord, getUserId());
        return result;
    }
}
