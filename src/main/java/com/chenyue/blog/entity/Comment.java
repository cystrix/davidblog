package com.chenyue.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Data
public class Comment {
    private Integer commentId;
    private Integer commentPid;
    private String commentPname;
    private String commentAuthorName;
    private String commentAuthorEmail;
    private String commentAuthorUrl;
    private String commentAuthorAvatar;
    private String commentContent;
    private String commentAgent;
    private String commentIp;
    private LocalDateTime commentCreateTime;
    private Integer commentRole;
}
