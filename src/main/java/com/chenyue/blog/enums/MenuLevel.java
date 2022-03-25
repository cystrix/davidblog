package com.chenyue.blog.enums;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/22
 * @description:
 */
public enum MenuLevel {
    TOP_MENU(1, "顶部菜单"),
    MAIN_MENU(2, "主体菜单");

    public final Integer value;
    public final String message;

    MenuLevel(Integer value, String message) {
        this.value = value;
        this.message = message;
    }
}
