package com.chenyue.blog.query;

import lombok.Data;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/20
 * @description:
 */
@Data
public class ArticleQuery {
    private Integer status;
    private String keywords;
    private Integer userId;
    private Integer categoryId;
    private Integer tagId;
}
