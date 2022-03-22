package com.chenyue.blog.enums;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/22
 * @description:
 */
public enum LinkStatus {
    NORMAL(1, "显示"),
    HIDDEN(0, "隐藏");

    public final Integer value;
    public final String message;

    LinkStatus(Integer value, String message) {
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
