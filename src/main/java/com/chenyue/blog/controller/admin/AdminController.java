package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Comment;
import com.chenyue.blog.entity.User;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.query.LoginQuery;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CommentService;
import com.chenyue.blog.service.UserService;

import com.chenyue.blog.util.Md5Utils;
import com.chenyue.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.chenyue.blog.util.NetUtils;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/19
 * @description:
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private Md5Utils md5Utils;

    @RequestMapping("/admin")
    public String index(Model model){
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList", articleList);
        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("comment", commentList);
        return "Admin/index";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "Admin/login";
    }

    @ResponseBody
    @PostMapping("/loginVerify")
    public Response loginVerify(HttpServletRequest request, HttpServletResponse httpServletResponse){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        Response response = null;
        if(user == null) {
            response = Response.builder().code(CodeEnum.BAD_REQUEST_PARAMETER.code).msg("用户名错误").build();
        }else if (!md5Utils.verifyPassword(password, user.getUserPass())) { // todo 2020/3/19 密码应该md5加密对比 done
            response = Response.builder().code(CodeEnum.BAD_REQUEST_PARAMETER.code).msg("密码错误").build();
        }else {
            response = Response.builder().code(CodeEnum.OK.code).msg("登陆成功").build();

            request.getSession().setAttribute("user", user);
            //添加cookie
            if(rememberMe != null){
                Cookie nameCookie = new Cookie("username", username);
                nameCookie.setMaxAge(60 * 60 * 24 * 3); // 三天
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                httpServletResponse.addCookie(nameCookie);
                httpServletResponse.addCookie(pwdCookie);

                user.setUserLastLoginTime(new Date());
                user.setUserLastLoginIp(NetUtils.getIpAddr(request));
                userService.update(user);
            }
        }
        return response;
    }

   @RequestMapping("/admin/logout")
    public String logout(HttpSession session) {
       session.removeAttribute("user");
       session.invalidate();
       return "redirect:/login";
   }
}
