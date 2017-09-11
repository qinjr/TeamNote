package service;



import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface DownloadService {
    /**
     * downloadNote
     * @param noteId 要下载的笔记Id
     * @param type 导出的格式（PDF，markdown，png/jpeg）
     * @return 以文件形式返回笔记内容
     */
    File downloadNote(int noteId, String type, String leftPath) throws IOException, DocumentException;

    /**
     * genHttpHeaders
     * @param id 要下载的笔记或笔记本Id
     * @return 生成的Http响应头
     */
    HttpHeaders genHttpHeaders(int id, String type, String noteOrNotebook) throws IOException;

    /**
     * downloadNotebook
     * @param notebookId 笔记本Id
     * @param type 导出格式
     * @return 笔记本中的所有笔记
     */
    File downloadNotebook(int notebookId, String type, String leftPath)throws IOException, DocumentException;
}
