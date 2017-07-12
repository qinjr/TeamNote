package service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import dao.mongodbDao.NoteDao;
import model.mongodb.Note;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import service.DownloadService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lxh on 2017/7/11.
 */
public class DownloadServiceImpl implements DownloadService {
    private NoteDao noteDao;

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public File downloadNote(int noteId, String type)throws IOException{
        Note note = noteDao.getNoteById(noteId);
        String currentVersion = note.getHistory().get(note.getVersionPointer());
        JsonObject obj = new JsonParser().parse(currentVersion).getAsJsonObject();
        String content = obj.get("content").getAsString();
        String path = "D:\\test\\temp.";
        path += "html";
        File file = new File(path);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
        if(type.equals("pdf")) {

        }

        return file;

    }

    public HttpHeaders genHttpHeaders(int noteId, String type) throws IOException{
        String filename = new String(noteDao.getNoteById(noteId).getTitle().getBytes("UTF-8"), "iso8859-1") + "." + type;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);
        return headers;
    }

    public ArrayList<File> downloadNotebook(int notebookId, String type) {
        return null;
    }
}
