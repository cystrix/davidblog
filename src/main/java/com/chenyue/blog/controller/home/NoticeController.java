package com.chenyue.blog.controller.home;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Notice;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/notice/{noticeId}")
    public String noticeDetailView(@PathVariable("noticeId") Integer noticeId, Model model){
        Notice notice = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice", notice);


        //侧边栏
        //热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/noticeDetail";
    }
}
