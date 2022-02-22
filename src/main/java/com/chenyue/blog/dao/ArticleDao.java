package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Article;
import com.chenyue.blog.query.ArticleQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ArticleDao {
    Integer insert(Article article);
    Integer deleteById(Integer id);
    Integer update(Article article);

    List<Article> findAll(ArticleQuery query);
    List<Article> listAllNotWithContent();
    Integer countArticle(Integer status);

    Integer countArticleComment();
    Integer countArticleView();
    List<Article> listArticle();
    Article getArticleByStatusAndId(@Param("status") Integer status, @Param("id") Integer id);

    /**
     * 获得访问次数最多的文章
     * @param limit 文章数量
     * @return
     */
    List<Article> listArticleByViewCount(Integer limit);

    Article getPreviousArticle(Integer id);
    Article getAfterArticle(Integer id);

    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得热评文章
     * @param limit 文章数量
     * @return
     */
    List<Article> listArticleByCommentCount(Integer limit);

    void updateCommentCount(Integer articleId);

    /**
     * 获得最新更新的文章
     */
    Article getLastUpdateArticle();

    /**
     * 获得用户的文章数量
     * @param userId
     * @return
     */
    Integer countArticleByUserId(Integer userId);

    Article getArticleByCategoryId(@Param("categoryId") Integer categoryId,@Param("limit") Integer limit);
    List<Article> getArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds, @Param("limit") Integer limit);
    /**
     * 获得最新文章
     * @param limit
     * @return
     */
    List<Article> listArticleByLimit(Integer limit);

    /**
     * 批量删除文章
     * @param ids
     * @return
     */
    Integer deleteBatch(@Param("ids") List<Integer> ids);

}
