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

    @Autowired
    private UserService userService;

    public String createPassword(String account, String origin) {
        String strWithSalt = origin + account;
        return DigestUtils.md5Hex(strWithSalt.getBytes(StandardCharsets.UTF_8));
    }

    public Boolean verifyPassword(String account, String origin) {
        String pwd = null;
        Assert.notNull(account, "account cannot be null");
        pwd = userService.getUserByNameOrEmail(account).getUserPass();
        String strWithSalt =  origin + account;
        return pwd.equals(DigestUtils.md5Hex(strWithSalt.getBytes(StandardCharsets.UTF_8)));
    }
}
