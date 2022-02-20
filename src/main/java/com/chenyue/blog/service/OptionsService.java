package com.chenyue.blog.service;

import com.chenyue.blog.entity.Options;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface OptionsService {
    Options getOptions();
    void insertOptions(Options options);
    void updateOptions(Options options);
}
