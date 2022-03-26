package com.chenyue.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Date pageCreateTime;
    private Date pageUpdateTime;
    private Integer pageViewCount;
    private Integer pageCommentCount;
    private Integer pageStatus;
}
