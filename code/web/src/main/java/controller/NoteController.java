package controller;

import com.google.gson.JsonObject;
import model.mongodb.Note;
import model.mysql.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CreateNoteService;
import service.NoteManageService;
import service.UserBasicService;

import java.util.Date;

/**
 * Created by lxh on 2017/7/4.
 */

@Controller
public class NoteController {
    private CreateNoteService createNoteService;
    private UserBasicService userBasicService;
    private NoteManageService noteManageService;

    @Autowired
    public NoteController(CreateNoteService createNoteService, UserBasicService userBasicService,
                          NoteManageService noteManageService) {
        this.createNoteService = createNoteService;
        this.userBasicService = userBasicService;
        this.noteManageService = noteManageService;
    }

    @RequestMapping("/createNotebook")
    @ResponseBody
    public String createNotebook(@RequestParam(value = "notebookName") String notebookName) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();
        createNoteService.createNotebook(userId, notebookName);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping("/saveFirstEdition")
    @ResponseBody
    public String saveFirstEdition(@RequestParam(value = "notebookId") int noteBookId, @RequestParam(value = "noteTitle") String noteTitle,
                                   @RequestParam(value = "content") String content) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();
        Date datetime = new Date();
        createNoteService.newTextNote(userId, noteBookId, noteTitle, content, datetime);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping("/updateNote")
    @ResponseBody
    public String updateNote(@RequestParam(value = "noteId") int noteId, @RequestParam(value = "content") String content,
                             @RequestParam(value = "message") String message) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        int userId = userInfo.getUserId();
        Date datetime = new Date();
        noteManageService.updateNote(noteId, userId, datetime, content, message);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/getNote")
    @ResponseBody
    public String getNote(@RequestParam(value = "noteId") int noteId) {
        Note note = noteManageService.getNoteById(noteId);
        return note.getHistory().get(note.getVersionPointer());
    }

    @RequestMapping(value = "/updateNoteTitle")
    @ResponseBody
    public String updateNoteTitle(@RequestParam(value = "noteId") int noteId, @RequestParam(value = "newNoteTitle") String newNoteTitle) {
        noteManageService.updateNoteTitle(noteId, newNoteTitle);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/deleteNote")
    @ResponseBody
    public String deleteNote(@RequestParam(value = "noteId") int noteId) {
        noteManageService.deleteNote(noteId);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/getHistory")
    @ResponseBody
    public String getHistory(@RequestParam(value = "noteId") int noteId) {
        String history = noteManageService.getHistory(noteId);
        JsonObject json = new JsonObject();
        json.addProperty("history", history);
        return json.toString();
    }

    @RequestMapping(value = "/updateNoteVersion")
    @ResponseBody
    public String updateNoteVersion(@RequestParam(value = "noteId") int noteId, @RequestParam(value = "newVersion") int newVersion) {
        noteManageService.updateNoteVersion(noteId, newVersion);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/getNotebookDetail")
    @ResponseBody
    public String getNotebookDetail(@RequestParam(value = "notebookId") int notebookId) {
        return noteManageService.getNotebookDetail(notebookId);
    }

    @RequestMapping(value = "/updateNotebookDetail")
    @ResponseBody
    public String updateNotebookDetail(@RequestParam(value = "notebookId") int notebookId, @RequestParam(value = "newTitle") String newTitle,
                                       @RequestParam(value = "newDescription") String newDescription) {
        noteManageService.updateNotebookDetail(notebookId, newTitle, newDescription);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }
}
