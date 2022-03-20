package com.chenyue.blog.util;

import com.chenyue.blog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/20
 * @description:
 */
@Component
public class Md5Utils {
    private final String salt =  "1jsivcaxz23.*J%^21?!_#+";

    @Autowired
    private UserService userService;

    public String createPassword(String originPass) {
        String strWithSalt = originPass + salt;
        return DigestUtils.md5Hex(strWithSalt.getBytes(StandardCharsets.UTF_8));
    }

    public Boolean verifyPassword(String originPass, String dbPass) {
        String pwd = originPass + salt;
        Assert.notNull(originPass, "account cannot be null");
        pwd = DigestUtils.md5Hex(pwd.getBytes(StandardCharsets.UTF_8));
        return pwd.equals(dbPass);
    }
}
