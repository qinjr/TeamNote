package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AdminService;

/**
 * Created by rudeigerc on 2017/7/12.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/getAllNotebooks")
    @ResponseBody
    public String getAllNotebooks() {
        return new Gson().toJson(adminService.RNotebooks());
    }

    @RequestMapping("/getAllUsers")
    @ResponseBody
    public String getAllUsers() {
        return new Gson().toJson(adminService.RUser());
    }

    @RequestMapping("/getNotes")
    @ResponseBody
    public String getNotesOfNotebook(@RequestParam("notebookId") int notebookId) {
        return new Gson().toJson(adminService.RNoteOfNotebook(notebookId));
    }

    @RequestMapping("/getVerifyNotes")
    @ResponseBody
    public String getVerifyNotes() {
        return adminService.verifyNoteList().toString();
    }

    @RequestMapping("/banNote")
    @ResponseBody
    public String banNote(@RequestParam("verifyId") int verifyId) {
        adminService.banNote(verifyId);
        JsonObject obj = new JsonObject();
        obj.addProperty("result","success");
        return new Gson().toJson(obj);
    }
}
