package util.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import util.ExportUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;



/**
 * Created by lxh on 2017/7/12.
 */
public class ExportUtilImpl implements ExportUtil {
    public void htmlToPdf(String htmlPath, File pdfFile) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath), Charset.forName("UTF-8"));
        document.close();
    }

    public File compressExe(File srcDir, String destZipPath) {
        Project prj = new Project();
        Zip zip = new Zip();
        File dest = new File(destZipPath);
        zip.setProject(prj);
        zip.setDestFile(dest);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcDir);
        zip.addFileset(fileSet);
        zip.execute();

        File[] files = srcDir.listFiles();
        for (int i = 0;i < files.length;i++) {
            files[i].delete();
        }
        srcDir.delete();

        return dest;
    }
}
