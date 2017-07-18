package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qjr on 2017/7/13.
 */
@Controller
public class ViewController {
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping(path = {"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    @RequestMapping("/notebook")
    public String notebook() {
        return "notebook";
    }
}
