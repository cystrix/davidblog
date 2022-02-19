package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Category;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface CategoryDao {

    int insert(Category record);
    int update(Category record);
    Category getCategoryById(Integer id);
    Category getCategoryByName(String name);
    int deleteCategoryById(Integer id);
    Integer countCategory();
    List<Category> listCategory();

    /**
     * 根据父分类id查找子父类
     * @param pid
     * @return
     */
    List<Category> listChildCategory(Integer pid);
}
