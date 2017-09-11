import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.QualityUtil;

import java.io.*;

/**
 * Created by qjr on 2017/7/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class QualityUtilTest {
    @Autowired
    private QualityUtil qualityUtil;

    @Test
    public void test() throws IOException {
        System.out.println(qualityUtil.checkTextContent(1, "<h1>江泽民的三件小事</h1>\n\n<div style=\"background:#eeeeee; border:1px solid #cccccc; padding:5px 10px\">1、邓小平理论<br />\n2、三个代表<br />\n3、军队一律不得经商</div>\n"));
    }
}
