package controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.Tag;
import model.mongodb.User;
import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.CooperateService;
import service.NoteManageService;
import service.UserBasicService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        String response = noteManageService.getNotebooksDetailsByUserId(userId);
        return response;
    }

    @RequestMapping("/cooperate/workgroup")
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

    @RequestMapping("/cooperate/allversions")
    @ResponseBody
    public String allVersions(@RequestParam(value = "noteId")int noteId) {
        Note note = noteManageService.getNoteById(noteId);
        ArrayList<String> history = note.getHistory();
        return (new Gson()).toJson(history);
    }

    @RequestMapping("/cooperate/changeversion")
    public void changeVersion(@RequestParam(value = "noteId")int noteId, @RequestParam(value = "versionPoint")int versionPoint) {
        noteManageService.updateNoteVersion(noteId, versionPoint);
    }
}
