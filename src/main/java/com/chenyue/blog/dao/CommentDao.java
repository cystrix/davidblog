package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface CommentDao {
    int insert(Comment comment);
    int deleteById(Integer id);
    int update(Comment comment);

    Comment getCommentById(Integer id);
    List<Comment> listCommentByArticleId(Integer id);
    List<Comment> listComment();
    Integer countComment();
    List<Comment> listRecentComment(Integer limit);
    List<Comment> listChildComment(Integer id);
}
