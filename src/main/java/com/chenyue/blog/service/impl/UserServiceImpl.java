package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.TagDao;
import com.chenyue.blog.dao.UserDao;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.exception.BusinessException;
import com.chenyue.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    TagDao tagDao;


    @Transactional(rollbackFor = {BusinessException.class})
    public void testTransactional() {
        System.out.println("开始执行业务代码");
        Tag tag = new Tag(null, "C--", "C--描述");
        tagDao.insert(tag);
        // throw new BusinessException("业务代码从错误");  /*测试回滚*/
    }
}
