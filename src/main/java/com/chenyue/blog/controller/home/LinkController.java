package com.chenyue.blog.controller.home;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Link;
import com.chenyue.blog.enums.LinkStatus;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 */
@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/applyLink")
    public String applyLinkView(Model model){
        //侧边栏
        //热评文章
        List<Article>  mostCommentArticle = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCountArticle", mostCommentArticle);
        return "Home/Page/applyLink";
    }

    @PostMapping("/applyLinkSubmit")
    @ResponseBody
    public void applyLinkSubmit(Link link){
        link.setLinkStatus(LinkStatus.HIDDEN.getValue());
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkService.insertLink(link);
    }
}
