package com.chenyue.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer articleId;
    private String articleUserId;
    private String articleTitle;
    private String articleContent;
    private String articleSummary;
    private Integer articleCommentCount;
    private Integer articleLikeCount;
    private Integer articleIsComment;
    private Integer articleOrder;
    private Date articleCreateTime;
    private Date articleUpdateTime;
    private Integer articleStatus;
}