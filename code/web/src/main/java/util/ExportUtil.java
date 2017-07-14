package util;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;

/**
 * Created by lxh on 2017/7/12.
 */
public interface ExportUtil {
    /**
     *  htmlToPdf
     * @param htmlPath
     * @param pdfFile
     * @throws IOException
     */
    public void htmlToPdf(String htmlPath, File pdfFile) throws IOException, DocumentException;
}
