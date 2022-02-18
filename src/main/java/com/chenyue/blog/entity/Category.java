package com.chenyue.blog.entity;

import lombok.Data;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Data
public class Category {
    private Integer categoryId;
    private Integer categoryPid;
    private String categoryName;
    private String categoryDescription;
    private String categoryOrder;
    private String categoryIcon;
}
