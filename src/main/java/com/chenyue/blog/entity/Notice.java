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
public class Notice {
    private Integer noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeCreateTime;
    private Date noticeUpdateTime;
    private Integer noticeOrder;
    private Integer noticeStatus;
}
