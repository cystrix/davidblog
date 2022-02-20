package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.ArticleTagDao;
import com.chenyue.blog.dao.TagDao;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class TagServiceImpl implements TagService {
    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    @Autowired
    private TagDao tagDao;
    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    public Integer countTag() {
        return tagDao.countTag();
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    @Override
    public List<Tag> listTagWithCount() {
        List<Tag> tagList = null;
        try {
            tagList = tagDao.listTag();
            for (int i = 0; i < tagList.size(); i++) {
                Integer count = articleTagDao.countArticleByTagId(tagList.get(i).getTagId());
                tagList.get(i).setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获得所有标签失败, cause:{}", e);
        }
        return tagList;
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagDao.getTagById(id);
    }

    @Override
    public int insert(Tag tag) {
        return tagDao.insert(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.update(tag);
    }

    @Override
    public void deleteById(Integer tagId) {
        tagDao.deleteById(tagId);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        return articleTagDao.listTagByArticleId(articleId);
    }
}
