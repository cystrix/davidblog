package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Comment;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CommentService;
import com.chenyue.blog.service.UserService;
import com.chenyue.blog.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.List;

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

    @RequestMapping("/admin")
    public String index(Model model){
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList", articleList);
        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("comment", commentList);
        return "Admin/index"; // default: redirect method
    }
}
