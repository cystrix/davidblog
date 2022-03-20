package com.chenyue.blog.service;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.query.ArticleQuery;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface ArticleService {

    Integer countArticle(Integer status);
    Integer countArticleComment();

    Integer countArticleView();

    Integer countArticleByCategoryId(Integer categoryId);

    Integer countArticleByTagId(Integer tagId);

    List<Article> listArticle(HashMap<String, Object> criteria);

    List<Article> listRecentArticle(Integer limit);

    /**
     * 文章简单信息修改
     * @param article
     */
    void update(Article article);

    /**
     * 文章内容分类修该
     * @param article
     */
    void updateDetail(Article article);

    void deleteArticleBatch(List<Integer> ids);

    void deleteArticle(Integer id);

    Article getArticleByStatusAndId(Integer status, Integer id);

    List<Article> listArticleByViewCount(Integer limit);

    Article getAfterArticle(Integer id);

    Article getPreArticle(Integer id);

    // TODO 22/2/20 mybatis 分页插件
    PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

    List<Article> listRandomArticle(Integer limit);

    List<Article> listArticleByCommentCount(Integer limit);

    void insertArticle(Article article);

    void updateCommentCount(Integer articleId);

    Article getLastUpdateArticle();

    Article listArticleByCategoryId(Integer categoryId, Integer limit);

    List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit);

    List<Integer> listCategoryIdByArticleId(Integer articleId);
}
