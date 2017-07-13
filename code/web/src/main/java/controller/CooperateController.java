package controller;
import com.google.gson.JsonObject;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.User;
import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.CooperateService;
import service.NoteManageService;
import service.UserBasicService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by qjr on 2017/7/3.
 */
@Controller
@RequestMapping("/cooperate")
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

    @RequestMapping("/getCollaborators")
    @ResponseBody
    public String getCollaborators(@RequestParam(value = "notebookId") int notebookId) {
        return noteManageService.getCollaborators(notebookId);
    }


    @RequestMapping("/giveownership")
    @ResponseBody
    public String giveOwnership(@RequestParam(value = "newOwnerName")String newOwnerName,
                                @RequestParam(value = "notebookId")int notebookId) {
        //get username from spring security
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int oldOwnerId = userInfo.getUserId();

        //get new owner userId
        UserInfo newOwnerInfo = userBasicService.getUserInfoByUsername(newOwnerName);
        int newOwnerId = newOwnerInfo.getUserId();

        //change ownership and send massage to frontend
        JsonObject json = new JsonObject();
        if (cooperateService.giveOwnership(oldOwnerId, newOwnerId, notebookId) == 1) {
            json.addProperty("result", "success");
            json.addProperty("newOwner", newOwnerName);
        } else {
            json.addProperty("result", "fail");
            json.addProperty("Message", "you are not the owner of the notebook");
        }
        return json.toString();
    }

    @RequestMapping("/allworkgroups")
    @ResponseBody
    public String allworkgroups() {
        //get username from spring security
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        //get notebooks
        String response = noteManageService.getNotebooksDetailsByUserId(userId);
        return response;
    }

    @RequestMapping("/workgroup")
    public String enterWorkGroup(@RequestParam(value = "notebookId")int notebookId,
                                 HttpServletRequest request) {
        Notebook notebook = noteManageService.getNotebookById(notebookId);
        ArrayList<Note> notes = new ArrayList<Note>();
        for (int noteId : notebook.getNotes()) {
            notes.add(noteManageService.getNoteById(noteId));
        }
        ArrayList<User> collaborators = new ArrayList<User>();
        for (int userId : notebook.getCollaborators()) {
            collaborators.add(userBasicService.getUserById(userId));
        }
        request.setAttribute("notebook", notebook);
        request.setAttribute("notes", notes);
        request.setAttribute("collaborators", collaborators);
        return "workgroup";
    }

    @RequestMapping("/invite")
    @ResponseBody
    public String inviteCollaborator(@RequestParam(value = "inviteUsername") String inviteUsername,
                                     @RequestParam(value = "notebookId") int notebookId,
                                     @RequestParam("inviteDescription") String description) {
        //get invitor from spring security
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();

        UserInfo target = userBasicService.getUserInfoByUsername(inviteUsername);
        int targetId = target.getUserId();

        cooperateService.inviteCooperator(userId, targetId, notebookId, description);

        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping("/inviteValidate")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ResponseEntity<?> validate(@RequestParam(value = "inviteUsername") String username) {
        if (!userBasicService.usernameValidate(username)) {
            return ResponseEntity.status(200).body(null);
        }
        else return ResponseEntity.status(406).body(null);
    }

    @RequestMapping("/getSuggestions")
    @ResponseBody
    public String getSuggestions(@RequestParam(value = "noteId") int noteId) {
        return cooperateService.getSuggestions(noteId);
    }

    @RequestMapping("/mergeSuggestion")
    @ResponseBody
    public String mergeSuggestion(@RequestParam(value = "suggestionId") int suggestionId, @RequestParam(value = "noteId") int noteId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();
        cooperateService.mergeSuggestion(userId, noteId, suggestionId);

        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }
}
