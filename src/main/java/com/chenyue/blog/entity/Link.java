package com.chenyue.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Data
public class Link {
    private Integer linkId;
    private String linkName;
    private String linkImage;
    private String linkDescription;
    private String linkOwnerNickname;
    private String linkOwnerContact;
    private Date linkCreateTime;
    private Date linkUpdateTime;
    private Integer linkOrder;
    private Integer linkStatus;
}
