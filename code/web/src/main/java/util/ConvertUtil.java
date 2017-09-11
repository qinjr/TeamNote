package util;

/**
 * Created by lxh on 2017/7/17.
 */
public interface ConvertUtil {
    /**
     * formMessage
     * @param content
     * @return 由数据库中的一条groupChat的JSON字符串生成对应的message的JSON字符串
     */

    String formMessage(String content);
}
