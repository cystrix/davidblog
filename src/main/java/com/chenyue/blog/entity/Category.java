package com.chenyue.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer categoryId;
    private Integer categoryPid;
    private String categoryName;
    private String categoryDescription;
    private Integer categoryOrder;
    private String categoryIcon;

    /*non-database field*/
    private Integer articleCount;

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public static Category defaultCategory() {
        return new Category(100000000, "未分类");
    }
}
