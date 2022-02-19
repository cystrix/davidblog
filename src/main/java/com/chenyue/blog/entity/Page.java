package com.chenyue.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
@Data
public class Page {
    private Integer pageId;
    private String pageKey;
    private String pageTitle;
    private String pageContent;
    private LocalDateTime pageCreateTime;
    private LocalDateTime pageUpdateTime;
    private Integer pageViewCount;
    private Integer pageCountCount;
    private Integer pageCommentCount;
    private Integer pageStatus;
}
