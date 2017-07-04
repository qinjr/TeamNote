package controller;

import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CreateNoteService;
import service.UserBasicService;

import java.util.Date;

/**
 * Created by lxh on 2017/7/4.
 */

@Controller
public class EditNoteController {
        private CreateNoteService createNoteService;
        private UserBasicService userBasicService;

        @Autowired
        public EditNoteController(CreateNoteService createNoteService, UserBasicService userBasicService) {
            this.createNoteService = createNoteService;
            this.userBasicService = userBasicService;
        }

        @RequestMapping("/createNotebook")
        public String createNotebook(@RequestParam(value = "notebookName") String notebookName) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
            int userId = userInfo.getUserId();
            createNoteService.createNotebook(userId, notebookName);
            return "workgroup";
        }


        @RequestMapping("/saveFirstEdition")
        public String uploadTextNote(@RequestParam(value = "notebookId") int noteBookId, @RequestParam(value = "noteTitle") String noteTitle, @RequestParam(value = "content") String content) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
            int userId = userInfo.getUserId();
            Date datetime = new Date();
            createNoteService.uploadTextNote(userId, noteBookId, noteTitle, content, datetime);
            return "workgroup";
        }
}
