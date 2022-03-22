package com.chenyue.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Category;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.entity.User;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.query.ArticleParam;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CategoryService;
import com.chenyue.blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        if(user != null) { //todo 2022/3/20 必须有userId,更改article表设计
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

    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editArticleView(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Article  article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.value, id);
        modelAndView.addObject("article", article);
        List<Category>  categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);

        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList", tagList);

        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam) {
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(article.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if(summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        List<Category> categorys = new ArrayList<>();
        if(articleParam.getArticleChildCategoryId() != null) {
            categorys.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        if(articleParam.getArticleParentCategoryId() != null) {
            categorys.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        article.setCategoryList(categorys);
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateDetail(article);
        return "redirect:/admin/article";
    }
}
