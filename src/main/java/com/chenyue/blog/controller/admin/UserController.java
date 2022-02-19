package com.chenyue.blog.controller.admin;

import com.chenyue.blog.conf.jwt.JwtUtils;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.query.LoginQuery;
import com.chenyue.blog.vo.ResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo signin(@RequestBody LoginQuery account) {

        //test
        if("chenyue".equals(account.getUsername()) &&
        "123".equals(account.getPassword())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("role", "admin");
            map.put("userId", 1);
            Calendar expireTime = Calendar.getInstance();
            expireTime.add(Calendar.HOUR, 1);
            String token = JwtUtils.createToken(map, expireTime.getTime());
            return new ResponseVo<>(CodeEnum.OK.code, "登录成功", token);
        }
        return new ResponseVo<>(CodeEnum.OK.code, "登录失败，用户名密码错误");
    }

    @ResponseBody
    @RequestMapping("/interceptor")
    public ResponseVo interceptor(){
        return new ResponseVo(CodeEnum.OK.code, CodeEnum.OK.message);
    }
}
