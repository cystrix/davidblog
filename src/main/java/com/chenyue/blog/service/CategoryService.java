package com.chenyue.blog.service;

import com.chenyue.blog.entity.Category;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface CategoryService {
    Integer countCategory();
    List<Category> listCategory();

    /**
     * 返回category并统计文章数量
     * @return
     */
    List<Category> listCategoryWithCount();
    void deleteCategory(Integer categoryId);
    Category getCategoryById(Integer id);
    Category insertCategory(Category category);
    void updateCategory(Category category);
    Category getCategoryByName(String name);
}
