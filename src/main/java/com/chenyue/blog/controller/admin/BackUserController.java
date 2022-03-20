package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.User;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.exception.PasswordErrorException;
import com.chenyue.blog.exception.UsernameNotExistsException;
import com.chenyue.blog.query.LoginQuery;
import com.chenyue.blog.service.UserService;
import com.chenyue.blog.util.Md5Utils;
import com.chenyue.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String insertView() {
        return "Admin/User/insert";
    }

    @RequestMapping(value = "/checkUserName")
    @ResponseBody
    public Response checkUserName(@RequestParam String username) {
        if (userService.isUserExisted(username)) {
            return Response.builder().code(CodeEnum.OK.code).msg("用户存在").data(true).build();
        }
        return Response.builder().code(CodeEnum.OK.code).msg("用户不存在").data(false).build();
    }

    @RequestMapping(value = "/checkUserEmail")
    @ResponseBody
    public Response checkUserEmail(@RequestParam Integer userId, @RequestParam String email) {
        if (userService.isUserEmailExisted(email)) {
            return Response.builder().code(CodeEnum.OK.code).msg("用户存在").data(true).build();
        }
        return Response.builder().code(CodeEnum.OK.code).msg("用户不存在").data(false).build();
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response signin(@RequestBody LoginQuery account) {
        if (checkUsernameAndPassword(account)) {
            return Response.builder().code(CodeEnum.OK.code).msg("登录成功").build();
        }
        return Response.builder().code(CodeEnum.BAD_REQUEST_PARAMETER.code).msg("登录失败，用户名密码错误").build();
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signup(HttpServletRequest request) {
        User newUser = new User();
        newUser.setUserName(request.getParameter("userName"));
        newUser.setUserPass(request.getParameter("userPass"));
        newUser.setUserNickname(request.getParameter("userNickname"));
        newUser.setUserEmail(request.getParameter("userEmail"));
        newUser.setUserUrl(request.getParameter("userUrl"));
        Assert.notNull(newUser.getUserName(), "username cannot be null");
        Assert.notNull(newUser.getUserPass(), "password cannot be null");
        userService.insert(newUser);
        return "Admin/index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/admin/user";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(@RequestBody  User user) {
        userService.update(user);
        return "redirect:/admin/user";
    }


    @RequestMapping("/profile")
    public ModelAndView userProfileView(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User sessionUser = (User) session.getAttribute("user");
        User user = userService.getUserById(sessionUser.getUserId());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/User/profile");
        return modelAndView;
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
