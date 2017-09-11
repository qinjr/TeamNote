package service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import dao.mongodbDao.NoteDao;
import dao.mongodbDao.NotebookDao;
import model.mongodb.Note;
import model.mongodb.Notebook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import service.DownloadService;
import util.ExportUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lxh on 2017/7/11.
 * competition might exist
 */
public class DownloadServiceImpl implements DownloadService {
    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private ExportUtil exportUtil;

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setExportUtil(ExportUtil exportUtil) {
        this.exportUtil = exportUtil;
    }

    public File downloadNote(int noteId, String type, String leftPath)throws IOException, DocumentException {
        Note note = noteDao.getNoteById(noteId);
        String currentVersion = note.getHistory().get(note.getVersionPointer());
        JsonObject obj = new JsonParser().parse(currentVersion).getAsJsonObject();
        String content = obj.get("content").getAsString();
        String htmlPath = leftPath + "htmlTemp.html";
        File file = new File(htmlPath);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("<body>" + content + "</body>");
        writer.close();
        if(type.equals("pdf")) {
            String pdfPath = leftPath + "pdfTemp.pdf";
            File pdfFile = new File(pdfPath);
            exportUtil.htmlToPdf(htmlPath, pdfFile);
            file.delete();
            file = pdfFile;
        }
        //default html
        return file;

    }

    public HttpHeaders genHttpHeaders(int id, String type, String noteOrNotebook) throws IOException{
        String filename = "";
        if(noteOrNotebook.equals("note")) {
            filename = new String(noteDao.getNoteById(id).getTitle().getBytes("UTF-8"), "iso8859-1") + "." + type;
        } else {
            filename = new String(notebookDao.getNotebookById(id).getTitle().getBytes("UTF-8"), "iso8859-1") + ".zip";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);
        return headers;
    }

    public File downloadNotebook(int notebookId, String type, String leftPath) throws IOException,DocumentException{
        File tempDir = new File(leftPath + "tempDir");
        String tempPath = leftPath + "tempDir/";
        tempDir.mkdirs();
        ArrayList<Integer> notes = notebookDao.getNotebookById(notebookId).getNotes();
        for(int noteId : notes){
            Note note = noteDao.getNoteById(noteId);
            String currentVersion = note.getHistory().get(note.getVersionPointer());
            JsonObject obj = new JsonParser().parse(currentVersion).getAsJsonObject();
            String content = obj.get("content").getAsString();
            String htmlPath = tempPath + noteId +".html";
            File file = new File(htmlPath);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("<body>" + content + "</body>");
            writer.close();
            if(type.equals("pdf")) {
                String pdfPath = tempPath + noteId +".pdf";
                File pdfFile = new File(pdfPath);
                exportUtil.htmlToPdf(htmlPath, pdfFile);
                file.delete();
            }
        }
        return exportUtil.compressExe(tempDir, leftPath + "temp.zip");
    }
}
