package com.chenyue.blog.enums;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
public enum NoticeStatus {
    NORMAL(1,"显示"),
    HIDDEN(0,"隐藏");
    private final Integer value;
    private final String message;
    NoticeStatus(Integer value, String message) {
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
