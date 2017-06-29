package service;


import java.io.File;
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
    File downloadNote(int noteId, String type);


    /**
     * downloadNotebook
     * @param notebookId 笔记本Id
     * @param type 导出格式
     * @return 笔记本中的所有笔记
     */
    ArrayList<File> downloadNotebook(int notebookId, String type);
}
