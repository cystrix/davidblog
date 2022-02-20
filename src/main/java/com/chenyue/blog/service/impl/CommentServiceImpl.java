package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.ArticleDao;
import com.chenyue.blog.dao.CommentDao;
import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Comment;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArticleDao articleDao;


    @Override
    public void insert(Comment comment) {
        commentDao.insert(comment);
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        return commentDao.listCommentByArticleId(articleId);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public List<Comment> listComment() {
        return commentDao.listComment();
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentDao.deleteById(id);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public Integer countComment() {
        return commentDao.countComment();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList = null;
        commentList = commentDao.listRecentComment(limit);
        for (Comment comment : commentList) {
            Article article = articleDao.getArticleByStatusAndId(ArticleStatus.PUBLISH.value, comment.getCommentArticleId());
            comment.setArticle(article);
        }
        return commentList;
    }

    @Override
    public List<Comment> listChildComment(Integer id) {
        return commentDao.listChildComment(id);
    }
}
