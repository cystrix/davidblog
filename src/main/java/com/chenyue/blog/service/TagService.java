package com.chenyue.blog.service;

import com.chenyue.blog.entity.Tag;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface TagService {
    Integer countTag();
    List<Tag> listTag();
    List<Tag> listTagWithCount();
    Tag getTagById(Integer id);
    int insert(Tag tag);
    void updateTag(Tag tag);
    void deleteById(Integer tagId);
    Tag getTagByName(String name);
    List<Tag> listTagByArticleId(Integer articleId);
}
