package com.chenyue.blog.dao;

import com.chenyue.blog.entity.ArticleTag;
import com.chenyue.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface ArticleTagDao {
    int insert(ArticleTag record);
    int deleteByTagId(Integer tagId);
    int deleteByArticleId(Integer articleId);
    int countArticleByTagId(Integer tagId);
    List<Tag> listTagByArticleId(Integer articleId);
}
