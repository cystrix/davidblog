package com.chenyue.blog.entity;

import lombok.Data;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Data
public class ArticleTag {
    private Integer id;
    private Integer articleId;
    private Integer tagId;
}
