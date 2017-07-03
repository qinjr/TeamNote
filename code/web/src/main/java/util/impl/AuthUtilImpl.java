package util.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import util.AuthUtil;

/**
 * Created by lxh on 2017/7/3.
 */
public class AuthUtilImpl implements AuthUtil {
    private static final PasswordEncoder encoder = new StandardPasswordEncoder("my-secret-key");

    public String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean match(String rawPassword, String password) {
        return encoder.matches(rawPassword, password);
    }
}

