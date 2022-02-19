package com.chenyue.blog.dao;

import com.chenyue.blog.entity.ArticleCategory;
import com.chenyue.blog.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface ArticleCategoryDao {
    int insert(ArticleCategory record);
    int deleteByCategoryId(Integer categoryId);
    int deleteByArticleId(Integer articleId);
    int countArticleByCategoryId(Integer categoryId);
    List<Integer> listCategoryIdByArticleId(Integer articleId);
    List<Integer> listArticleIdByCategoryId(Integer categoryId);
    List<Category> listCategoryByArticleId(Integer articleId);
}
