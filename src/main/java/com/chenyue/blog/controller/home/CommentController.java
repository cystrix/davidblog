package com.chenyue.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.chenyue.blog.entity.Article;
import com.chenyue.blog.entity.Comment;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.enums.UserRole;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CommentService;
import com.chenyue.blog.util.NetUtils;
import com.chenyue.blog.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * @author chenyue7@foxmail.com
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    public JsonResult insertComment(HttpServletRequest request, Comment comment){
        comment.setCommentCreateTime(LocalDateTime.now());
        comment.setCommentIp(NetUtils.getIpAddr(request));
        if (request.getSession().getAttribute("user") != null){
            comment.setCommentRole(UserRole.OWNER.getValue());
        } else {
            comment.setCommentRole(UserRole.VISITOR.getValue());
        }
        comment.setCommentAuthorAvatar(NetUtils.getGravatar(comment.getCommentAuthorEmail()));

        //过滤字符,防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        try {
            commentService.insert(comment);
            Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
            articleService.updateCommentCount(article.getArticleId());
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult().failed();
        }

        return new JsonResult().success();
    }
}
