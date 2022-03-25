package com.chenyue.blog.service;

import com.chenyue.blog.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface CommentService {

    void insert(Comment comment);
    List<Comment> listCommentByArticleId(Integer articleId);
    Comment getCommentById(Integer id);
    // TODO 22/2/20 安装mybatis page插件
    PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize);
    List<Comment> listComment();

    void deleteCommentById(Integer id);
    void update(Comment comment);
    Integer countComment();
    List<Comment> listRecentComment(Integer limit);
    List<Comment> listChildComment(Integer id);
}
