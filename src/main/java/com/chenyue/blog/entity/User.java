package com.chenyue.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String userName;
    private String userPass;
    private String userNickname;
    private String userEmail;
    private String userUrl;
    private String userAvatar;
    private String userLastLoginIp;
    private LocalDateTime userRegisterTime;
    private LocalDateTime userLastLoginTime;
    private Integer userStatus;

    /*非数据库字段*/
    private Integer articleCount;
}
