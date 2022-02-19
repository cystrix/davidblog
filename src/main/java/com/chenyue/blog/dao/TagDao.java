package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface TagDao {
    int insert(Tag tag);
    int update(Tag tag);
    int deleteById(Integer id);

    Tag getTagById(Integer id);
    Tag getTagByName(String name);
    Integer countTag();
    List<Tag> listTag();
}
