package com.chenyue.blog.controller.home;

import com.alibaba.fastjson.JSON;
import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Comment;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.entity.User;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 */
@Controller
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable("articleId") Integer articleId, Model model) {

        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            return "Home/Error/404";
        }

        User user = userService.getUserById(article.getArticleId());
        article.setUser(user);
        model.addAttribute("article", article);


        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList", commentList);

        //相关文章
        List<Integer> categoryIds = articleService.listCategoryIdByArticleId(articleId);
        List<Article> similarArticleList = null;
        if(categoryIds.size()!=0) {
            similarArticleList = articleService.listArticleByCategoryIds(categoryIds, 5);
        }

        model.addAttribute("similarArticleList", similarArticleList);

        //猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        model.addAttribute("mostViewArticleList", mostViewArticleList);

        //获得下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);
        model.addAttribute("afterArticle", afterArticle);

        //获得下一篇文章
        Article preArticle = articleService.getPreArticle(articleId);
        model.addAttribute("preArticle", preArticle);

        //测边栏
        //标签列表
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("randomCommentArticleList",  mostCommentArticleList);

        return "Home/Page/articleDetail";
    }

    /**
     * 增加点赞数
     * @param id articleId
     * @return
     */
    @PostMapping("/article/like/{id}")
    public String increaseLikeCount(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
        Integer articleLikeCount = article.getArticleLikeCount() + 1;
        article.setArticleLikeCount(articleLikeCount);
        articleService.update(article);
        return JSON.toJSONString(articleLikeCount);
    }

    /**
     * 增加文章观看数
     * @param id articleId
     * @return
     */
    @PostMapping("/article/view/{id}")
    public String increaseArticleViewCount(@PathVariable("id") Integer id){
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), id);
        Integer articleViewCount = article.getArticleViewCount() + 1;
        article.setArticleViewCount(articleViewCount);
        articleService.update(article);
        return JSON.toJSONString(articleViewCount);
    }

}
