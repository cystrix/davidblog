package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Link;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface LinkDao {
    int insert(Link link);
    int update(Link link);
    int deleteById(Integer id);

    Link getLinkById(Integer id);
    Integer countLink(@Param("status") Integer status);
    List<Link> listLink(@Param("status") Integer status);
}
