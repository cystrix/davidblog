package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface PageDao {
    int insert(Page page);
    int update(Page page);
    int deleteById(Integer id);

    Page getPageById(Integer id);

    List<Page> listPage(@Param(value = "status") Integer status);


    Page getPageByKey(@Param(value = "status") Integer status,
                      @Param(value = "key") String key);
}
