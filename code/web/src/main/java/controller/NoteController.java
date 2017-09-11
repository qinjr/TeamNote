package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.itextpdf.text.DocumentException;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mysql.UserInfo;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.CreateNoteService;
import service.DownloadService;
import service.NoteManageService;
import service.UserBasicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lxh on 2017/7/4.
 */

@Controller
public class NoteController {
    private CreateNoteService createNoteService;
    private UserBasicService userBasicService;
    private NoteManageService noteManageService;
    private DownloadService downloadService;

    @Autowired
    public NoteController(CreateNoteService createNoteService, UserBasicService userBasicService,
                          NoteManageService noteManageService, DownloadService downloadService) {
        this.createNoteService = createNoteService;
        this.userBasicService = userBasicService;
        this.noteManageService = noteManageService;
        this.downloadService = downloadService;
    }

    private int getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserInfo userInfo = userBasicService.getUserInfoByUsername(username);
        return userInfo.getUserId();
    }

    //Notebook operations:
    @RequestMapping("/createNotebook")
    @ResponseBody
    public String createNotebook(@RequestParam(value = "notebookTitle") String notebookTitle, @RequestParam(value = "description") String description,
                                 @RequestParam(value = "tag") String tag, @RequestParam(value = "notebookCover") MultipartFile notebookCover, HttpSession session) throws IOException{
        int userId = getUserId();
        int notebookId = createNoteService.createNotebook(userId, notebookTitle);

        String coverName = Long.toString(System.currentTimeMillis()) + notebookCover.getOriginalFilename();
        String destPath = session.getServletContext().getRealPath("/image/cover/") + coverName;
        notebookCover.transferTo(new File(destPath));

        noteManageService.updateNotebookDetail(notebookId, notebookTitle, description, "image/cover/" + coverName, tag);

        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/getNotebookDetail")
    @ResponseBody
    public String getNotebookDetail(@RequestParam(value = "notebookId") int notebookId) {
        return noteManageService.getNotebookDetail(notebookId);
    }

    @RequestMapping(value = "/notebook")
    public String enterNotebook(@RequestParam(value = "notebookId") int notebookId, HttpServletRequest request) {
        Notebook notebook = noteManageService.getNotebookById(notebookId);
        ArrayList<Note> notes = new ArrayList<Note>();
        for (Integer noteId : notebook.getNotes()) {
            notes.add(noteManageService.getNoteById(noteId));
        }

        String relation = noteManageService.relation(getUserId(), notebookId);
        if (relation.equals("owner")) {
            request.setAttribute("relation", "owner");
        } else if (relation.equals("collaborator")) {
            request.setAttribute("relation", "collaborator");
        } else {
            request.setAttribute("relation", "visitor");
        }
        request.setAttribute("notebook", notebook);
        request.setAttribute("notes", notes);

        return "notebook";
    }

    @RequestMapping(value = "/updateNotebookDetail")
    @ResponseBody
    public String updateNotebookDetail(@RequestParam(value = "notebookId") int notebookId, @RequestParam(value = "newTitle") String newTitle,
                                       @RequestParam(value = "newDescription") String newDescription, @RequestParam("newCover") String newCover, @RequestParam("newTags") String newTags) {
        noteManageService.updateNotebookDetail(notebookId, newTitle, newDescription, newCover, newTags);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }


    //Note operations:
    @RequestMapping("/saveFirstEdition")
    @ResponseBody
    public String saveFirstEdition(@RequestParam(value = "notebookId") int noteBookId, @RequestParam(value = "noteTitle") String noteTitle,
                                   @RequestParam(value = "content") String content) throws IOException {
        int userId = getUserId();
        Date datetime = new Date();
        JsonObject json = new JsonObject();
        if (createNoteService.newTextNote(userId, noteBookId, noteTitle, content, datetime) == 0) {
            json.addProperty("result", "sensitive");
        } else {
            json.addProperty("result", "success");
        }
        return json.toString();
    }

    @RequestMapping("/updateNote")
    @ResponseBody
    public String updateNote(@RequestParam(value = "noteId") int noteId, @RequestParam(value = "content") String content,
                             @RequestParam(value = "message") String message) {
        int userId = getUserId();
        Date datetime = new Date();
        noteManageService.updateNote(noteId, userId, datetime, content, message);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/updateNoteTitle")
    @ResponseBody
    public String updateNoteTitle(@RequestParam(value = "noteId") int noteId, @RequestParam(value = "newNoteTitle") String newNoteTitle) {
        noteManageService.updateNoteTitle(noteId, newNoteTitle);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
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

    @RequestMapping(value = "/getNote")
    @ResponseBody
    public String getNote(@RequestParam(value = "noteId") int noteId) {
        int userId = getUserId();
        return noteManageService.getNoteDetail(noteId, userId);
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

    @RequestMapping(value = "/changeVersion")
    @ResponseBody
    public String changeVersion(@RequestParam(value = "noteId") int noteId, @RequestParam(value = "versionPointer") int versionPointer) {
        noteManageService.changeVersion(noteId, versionPointer);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/exportNote")
    public ResponseEntity<byte[]> downloadNote(@RequestParam(value = "type") String type, @RequestParam(value = "noteId") int noteId, HttpSession session) throws IOException, DocumentException{
        String leftPath = session.getServletContext().getRealPath("/temp/");
        //在服务器端生成html文件
        File file = downloadService.downloadNote(noteId, type, leftPath);
        //将文件返回给用户
        HttpHeaders headers = downloadService.genHttpHeaders(noteId, type, "note");
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        file.delete();
        return result;
    }

    @RequestMapping(value = "/exportNotebook")
    public ResponseEntity<byte[]> downloadNotebook(@RequestParam(value = "type") String type, @RequestParam(value = "notebookId") int notebookId, HttpSession session) throws IOException, DocumentException{
        String leftPath = session.getServletContext().getRealPath("/temp/");
        //在服务器端生成zip文件
        File file = downloadService.downloadNotebook(notebookId, type, leftPath);
        //将文件返回给用户
        HttpHeaders headers = downloadService.genHttpHeaders(notebookId, type, "notebook");
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        file.delete();
        return result;
    }

    @RequestMapping(value = "/uploadNote")
    @ResponseBody
    public String uploadNote(@RequestParam(value = "uploadFile") MultipartFile uploadFile, @RequestParam(value = "notebookId") int notebookId, HttpSession session) throws IOException  {
         int userId = getUserId();
         JsonObject json = new JsonObject();

         if (uploadFile.getSize() > 0) {
             String leftPath = session.getServletContext().getRealPath("/temp/");
             String filename = uploadFile.getOriginalFilename();
             if (filename.endsWith("html")) {
                 File file = new File(leftPath, filename);
                 uploadFile.transferTo(file);
                 createNoteService.uploadFileNote(userId, notebookId, file, new Date());
                 file.delete();
                 json.addProperty("result", "success");
             } else {
                 json.addProperty("result", "wrongType");
             }
         } else {
             json.addProperty("result", "noFile");
         }
        return json.toString();
    }

    @RequestMapping(value = "/getNotebooksOfUser")
    @ResponseBody
    public String getNotebooksOfUser(@RequestParam("userId") int userId) {
        String notebooks = noteManageService.getNotebooksOfUser(userId);
        return notebooks;
    }

    @RequestMapping(value = "/collectNotebook")
    @ResponseBody
    public String collectNotebook(@RequestParam("notebookId") int notebookId) {
        int userId = getUserId();
        noteManageService.collectNotebook(userId, notebookId);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/uncollectNotebook")
    @ResponseBody
    public String uncollectNotebook(@RequestParam("notebookId") int notebookId) {
        int userId = getUserId();
        noteManageService.uncollectNotebook(userId, notebookId);
        JsonObject json = new JsonObject();
        json.addProperty("result", "success");
        return json.toString();
    }

    @RequestMapping(value = "/getCollectedNotebooks")
    @ResponseBody
    public String getCollectedNotebooks(@RequestParam("userId") int userId) {
        String collectedNotebooks = noteManageService.getCollectedNotebooks(userId);
        return collectedNotebooks;
    }
}
