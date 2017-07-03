package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserBasicService;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/7/3.
 */

@Controller
public class AuthController {

    private UserBasicService userBasicService;

    @Autowired
    public AuthController(UserBasicService userBasicService) {
        this.userBasicService = userBasicService;
    }

    @RequestMapping("/register")
    public String Register(@RequestParam(value = "_username") String username, @RequestParam(value = "_password") String password,
                           @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email) {
        userBasicService.register(username, password, phone, email, "", null, new ArrayList<Integer>());
        return "auth";
    }
}
