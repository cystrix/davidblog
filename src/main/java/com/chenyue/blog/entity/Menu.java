package com.chenyue.blog.entity;

import lombok.Data;

/**
 * @author chenyue7@foxmail.com
 * @date 2020/2/18
 * @description:
 */
@Data
public class Menu {
    private Integer menuId;
    private String menuUrl;
    private Integer menuLevel;
    private String menuIcon;
    private Integer menuOrder;
}
