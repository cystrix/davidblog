package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.OptionsDao;
import com.chenyue.blog.entity.Options;
import com.chenyue.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsDao optionsDao;

    @Override
    public Options getOptions() {
        return optionsDao.getOptions();
    }

    @Override
    public void insertOptions(Options options) {
        optionsDao.insert(options);
    }

    @Override
    public void updateOptions(Options options) {
        optionsDao.update(options);
    }
}
