package com.chenyue.blog.service.impl;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleCategoryImpl.class);

    @Override
    public Integer countArticle(Integer status) {
        return null;
    }

    @Override
    public Integer countArticleComment() {
        return null;
    }

    @Override
    public Integer countArticleView() {
        return null;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return null;
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return null;
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        return null;
    }

    @Override
    public void update(Article article) {

    }

    @Override
    public void updateDetail(Article article) {

    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {

    }

    @Override
    public void deleteArticle(Integer id) {

    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        return null;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return null;
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return null;
    }

    @Override
    public Article getPreArticle(Integer id) {
        return null;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return null;
    }

    @Override
    public void insertArticle(Article article) {

    }

    @Override
    public void updateCommentCount(Integer articleId) {

    }

    @Override
    public Article getLastUpdateArticle() {
        return null;
    }

    @Override
    public Article listArticleByCategoryId(Integer categoryIds, Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit) {
        return null;
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return null;
    }
}
