package com.chenyue.blog.controller.home;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Page;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CategoryService;
import com.chenyue.blog.service.PageService;
import com.chenyue.blog.service.TagService;
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
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/{key}")
    public String pageDetail(@PathVariable("key") String key, Model model){
        Page page = pageService.getPageByKey(1, key);
        if (page == null){
            return "redirect:/404";
        }
        model.addAttribute("page", page);
        //侧边栏
        //热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/page";
    }
}
