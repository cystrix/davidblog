package com.chenyue.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer articleId;
    private Integer articleUserId;
    private String articleTitle;
    private String articleContent;
    private String articleSummary;
    private Integer articleCommentCount;
    private Integer articleViewCount;
    private Integer articleLikeCount;
    private Integer articleIsComment;
    private Integer articleOrder;
    private LocalDateTime articleCreateTime;
    private LocalDateTime articleUpdateTime;
    private Integer articleStatus;

    /*non-database field*/
    private User user;
    private List<Category> categoryList;
    private List<Tag> tagList;
}
