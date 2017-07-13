package util.impl;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import util.QualityUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;

/**
 * Created by qjr on 2017/7/3.
 */

public class QualityUtilImpl implements QualityUtil {
    public int checkTextContent(int userId, String content) throws IOException {
        HashSet<String> sensitiveWords = new HashSet<String>();
        String source = "/Users/rudeigerc/Developer/TeamNote/code/web/out/artifacts/web_war_exploded/sensitive/政治类.txt";

        InputStream fis = new FileInputStream(source);
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null)
            sensitiveWords.add(line.substring(0, line.length() - 1));


        Result result = ToAnalysis.parse(Jsoup.clean(content, Whitelist.none()));
        List<Term> termList = result.getTerms();
        for (Term term : termList) {
            if (sensitiveWords.contains(term.getName()))
                return 0;
        }
        return 1;
    }
}
