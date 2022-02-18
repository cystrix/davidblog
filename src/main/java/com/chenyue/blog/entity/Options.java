package com.chenyue.blog.entity;

import lombok.Data;
/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Data
public class Options {
    private Integer optionId;
    private String optionSiteTitle;
    private String optionSiteDescription;
    private String optionMetaDescription;
    private String optionAboutsiteAvatar;
    private String optionAboutsiteTitle;
    private String optionAboutsiteContent;
    private String optionAboutsiteWechat;
    private String optionAboutsiteQq;
    private String optionAboutsiteWeibo;
    private String optionAboutsiteGithub;
    private String optionAboutsiteTongji;
}
