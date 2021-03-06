package com.chenyue.blog.controller.home;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Category;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CategoryService;
import com.chenyue.blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 */
@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/category/{categoryId}")
    public String getArticleListByCategory(@PathVariable("categoryId") Integer categoryId,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                           Model model){

        Category category = categoryService.getCategoryById(categoryId);
        if (category == null){
            return "redirect:/404";
        }
        model.addAttribute("category", category);

        //文章列表
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("categoryId", categoryId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);

        //热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        model.addAttribute("pageUrlPrefix", "/category/"+pageIndex+"?pageIndex");
        return "Home/Page/articleListByCategory";

    }

}
