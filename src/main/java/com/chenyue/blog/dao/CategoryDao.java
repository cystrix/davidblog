package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Category;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface CategoryDao {

    Category queryCategoryById(Integer id);
}
