package com.chenyue.blog.enums;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/20
 * @description:
 */
public enum ArticleStatus {
    PUBLISH(1, "已发布"),
    DRAFT(0, "草稿");

    public final Integer value;
    public final String message;

    ArticleStatus(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
