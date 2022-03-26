package com.chenyue.blog.enums;

/**
 * @author chenyue7@foxmail.com
 */
public enum UserRole {
    OWNER(1, "博主"),
    VISITOR(2, "游客");

    private final Integer value;
    private final String message;

    UserRole(Integer value, String message){
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
