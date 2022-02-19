package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Options;
import org.springframework.stereotype.Repository;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface OptionsDao {
    int insert(Options options);
    int update(Options options);
    int deleteById(Integer id);

    Options getOptionsById(Integer id);

    Options getOptions();
}
