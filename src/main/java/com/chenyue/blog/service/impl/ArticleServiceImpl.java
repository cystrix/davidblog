package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.ArticleCategoryDao;
import com.chenyue.blog.dao.ArticleDao;
import com.chenyue.blog.dao.ArticleTagDao;
import com.chenyue.blog.entity.*;
import com.chenyue.blog.enums.ArticleCommentStatus;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.query.ArticleQuery;
import com.chenyue.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleCategoryDao articleCategoryDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTagDao articleTagDao;


    @Override
    public Integer countArticle(Integer status) {
        return articleDao.countArticle(status);
    }

    @Override
    public Integer countArticleComment() {
        return articleDao.countArticleComment();
    }

    @Override
    public Integer countArticleView() {
        return articleDao.countArticleView();
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        return articleCategoryDao.countArticleByCategoryId(categoryId);
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return articleTagDao.countArticleByTagId(tagId);
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return articleDao.findAll(criteria);
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        return articleDao.listArticleByLimit(limit);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleDao.update(article);
        if (article.getTagList() != null) {
            articleTagDao.deleteByArticleId(article.getArticleId());
            for (int i = 0; i < article.getTagList().size(); i++) {
                ArticleTag articleTag = new ArticleTag(article.getArticleId(), article.getTagList().get(i).getTagId());
                articleTagDao.insert(articleTag);
            }
        }

        if (article.getCategoryList() != null) {
            articleCategoryDao.deleteByArticleId(article.getArticleId());
            for (int i = 0; i < article.getCategoryList().size(); i++) {
                ArticleCategory articleCategory = new ArticleCategory(article.getArticleId(), article.getCategoryList().get(i).getCategoryId());
                articleCategoryDao.insert(articleCategory);
            }
        }
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        articleDao.deleteBatch(ids);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleCategoryDao.deleteByArticleId(id);
        articleTagDao.deleteByArticleId(id);
        articleDao.deleteById(id);
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        Article article = articleDao.getArticleByStatusAndId(status, id);
        if (article != null) {
            List<Category> categories = articleCategoryDao.listCategoryByArticleId(id);
            List<Tag> tags = articleTagDao.listTagByArticleId(id);
            article.setCategoryList(categories);
            article.setTagList(tags);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleDao.listArticleByViewCount(limit);
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return articleDao.getAfterArticle(id);
    }

    @Override
    public Article getPreArticle(Integer id) {
        return articleDao.getPreviousArticle(id);
    }

    // todo 2022/3/20 HashMap 该改为query
    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleDao.findAll(criteria);
        for (Article article: articleList) {
            List<Category> categories = articleCategoryDao.listCategoryByArticleId(article.getArticleId());
            if (categories == null || categories.size() == 0) {
                categories = new ArrayList<>();
                categories.add(Category.defaultCategory());
            }
            article.setCategoryList(categories);
        }
        return new PageInfo<>(articleList);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleDao.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleDao.listArticleByCommentCount(limit);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void insertArticle(Article article) {
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.value);
        article.setArticleViewCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleStatus(ArticleStatus.PUBLISH.value);
        articleDao.insert(article);
        //分类关联
        for(int i = 0; i < article.getCategoryList().size(); i++) {
            insertArticleCategory(new ArticleCategory(article.getArticleId(), article.getCategoryList().get(i).getCategoryId()));
        }

        for(int i = 0; i < article.getTagList().size(); i++) {
            insertArticleTag(new ArticleTag(article.getArticleId(), article.getTagList().get(i).getTagId()));
        }


    }

    @Override
    public void insertArticleTag(ArticleTag articleTag) {
        articleTagDao.insert(articleTag);
    }

    @Override
    public void insertArticleCategory(ArticleCategory articleCategory) {
        articleCategoryDao.insert(articleCategory);
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        articleDao.updateCommentCount(articleId);
    }

    @Override
    public Article getLastUpdateArticle() {
        return articleDao.getLastUpdateArticle();
    }

    @Override
    public Article listArticleByCategoryId(Integer categoryId, Integer limit) {
        return articleDao.getArticleByCategoryId(categoryId, limit);
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit) {
        return articleDao.getArticleByCategoryIds(categoryIds, limit);
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleCategoryDao.listCategoryIdByArticleId(articleId);
    }
}
