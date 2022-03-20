package com.chenyue.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Category;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.entity.User;
import com.chenyue.blog.query.ArticleParam;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CategoryService;
import com.chenyue.blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/20
 * @description:
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status,
                        Model model) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        if(status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        }else {
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status="+status+"&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "Admin/Article/index";
    }


    @RequestMapping("/insert")
    public String insertArticleView(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        return "Admin/Article/insert";
    }

    @RequestMapping("/insertSubmit")
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();

        User user = (User) session.getAttribute("user");
        if(user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());

        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        }else  {
            article.setArticleSummary(summaryText);
        }

        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());

        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null){
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }


}
