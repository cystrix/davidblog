package com.chenyue.blog.controller.admin;

import com.chenyue.blog.conf.jwt.JwtUtils;
import com.chenyue.blog.entity.User;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.exception.PasswordErrorException;
import com.chenyue.blog.exception.UsernameNotExistsException;
import com.chenyue.blog.query.LoginQuery;
import com.chenyue.blog.service.UserService;
import com.chenyue.blog.util.Md5Utils;
import com.chenyue.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @Autowired
    private UserService userService;
    @Autowired
    private Md5Utils md5Utils;

    @RequestMapping(value = "")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.listUser();

        modelAndView.addObject("userList", users);
        modelAndView.setViewName("Admin/User/index");
        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insertView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin/User/insert");
        return modelAndView;
    }

    @RequestMapping(value = "/checkUserName")
    @ResponseBody
    public ResponseVo checkUserName(@RequestParam String username) {
        if (userService.isUserExisted(username)) {
            return new ResponseVo<>(CodeEnum.OK.code, "用户存在", true);
        }
        return new ResponseVo<>(CodeEnum.OK.code, "用户不存在", false);
    }

    @RequestMapping(value = "/checkUserEmail")
    @ResponseBody
    public ResponseVo checkUserEmail(@RequestParam Integer userId, @RequestParam String email) {
        if (userService.isUserEmailExisted(email)) {
            return new ResponseVo<>(CodeEnum.OK.code, "用户存在", true);
        }
        return new ResponseVo<>(CodeEnum.OK.code, "用户不存在", false);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo signin(@RequestBody LoginQuery account) {
        if (checkUsernameAndPassword(account)) {
            return new ResponseVo<>(CodeEnum.OK.code, "登录成功");
        }
        return new ResponseVo<>(CodeEnum.OK.code, "登录失败，用户名密码错误");
    }


    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseVo signup(@RequestBody User newUser) {
        Assert.notNull(newUser.getUserName(), "username cannot be null");
        Assert.notNull(newUser.getUserPass(), "password cannot be null");
        userService.insert(newUser);
        return new ResponseVo<>(CodeEnum.OK.code, "注册成功");
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


    private Boolean checkUsernameAndPassword(LoginQuery query) throws UsernameNotExistsException, PasswordErrorException {
        User user = userService.getUserByNameOrEmail(query.getAccount());
        if (user == null) {
            throw new UsernameNotExistsException("user does not exist");
        }
        if (query.getPassword() == null) {
            throw new PasswordErrorException("password does not exist");
        }
        return md5Utils.verifyPassword(query.getAccount(), query.getPassword());
    }
}
