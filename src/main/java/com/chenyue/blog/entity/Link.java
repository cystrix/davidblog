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
public class Link {
    private Integer linkId;
    private String linkUrl;
    private String linkName;
    private String linkImage;
    private String linkDescription;
    private String linkOwnerNickname;
    private String linkOwnerContact;
    private LocalDateTime linkCreateTime;
    private LocalDateTime linkUpdateTime;
    private Integer linkOrder;
    private Integer linkStatus;
}
