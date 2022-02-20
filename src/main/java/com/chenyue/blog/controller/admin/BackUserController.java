package com.chenyue.blog.controller.admin;

import com.chenyue.blog.conf.jwt.JwtUtils;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.query.LoginQuery;
import com.chenyue.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController {
    @Autowired
    HttpServletRequest request;

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
        }else if("zhumingyao".equals(account.getUsername()) &&
                "123".equals(account.getPassword())){
            HashMap<String, Object> map = new HashMap<>();
            map.put("role", "user");
            map.put("userId", 2);
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

    @ResponseBody
    @RequestMapping("/userId")
    public ResponseVo currentUserId(){
       /* System.out.println("ip:" + request.getHeader("x-forwarded-for"));
        Iterator<String> i = request.getHeaderNames().asIterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }*/
        return new ResponseVo<>(CodeEnum.OK.code, CodeEnum.OK.message, "当前用户id: "+ request.getAttribute("userId"));
    }
}
