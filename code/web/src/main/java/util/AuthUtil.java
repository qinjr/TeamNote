package util;


/**
 * Created by qjr on 2017/6/28.
 */
public interface AuthUtil {

    public String encrypt(String rawPassword);

    public boolean match(String rawPassword, String password);

}

