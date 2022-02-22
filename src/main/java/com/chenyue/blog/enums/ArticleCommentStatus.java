package com.chenyue.blog.enums;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/22
 * @description:
 */
public enum ArticleCommentStatus {
    ALLOW(1,"允许"),
    FORBIDDEN(0,"不允许");

    public final Integer value;
    public final String message;

    ArticleCommentStatus(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

}
