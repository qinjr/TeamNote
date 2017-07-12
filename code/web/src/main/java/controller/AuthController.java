package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @RequestMapping("/auth")
    public String auth() {
        return "auth";
    }

    @RequestMapping("/register")
    public String register(@RequestParam(value = "_username") String username, @RequestParam(value = "_password") String password,
                           @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email) {

        if (userBasicService.register(username, password, phone, email, "", "/image/avatar/default_avatar.png", new ArrayList<Integer>()) == 1) {
            return "auth";
        } else {
            return "auth";
        }
    }

    @RequestMapping("/validate")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ResponseEntity<?> validate(@RequestParam(value = "_username") String username) {
        if (userBasicService.usernameValidate(username)) {
            return ResponseEntity.status(200).body(null);
        }
        else return ResponseEntity.status(406).body(null);
    }
}
