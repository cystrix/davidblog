package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.ArticleDao;
import com.chenyue.blog.dao.TagDao;
import com.chenyue.blog.dao.UserDao;
import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.entity.User;
import com.chenyue.blog.exception.BusinessException;
import com.chenyue.blog.service.UserService;
import com.chenyue.blog.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private Md5Utils md5Utils;


    @Override
    public List<User> listUser() {
        List<User> users = userDao.listUser();
        if (users != null && users.size() > 0) {
            for (User user : users) {
                user.setArticleCount(null);
            }
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public int insert(User user) {
        user.setUserRegisterTime(LocalDateTime.now());
        String pwd = md5Utils.createPassword(user.getUserName(), user.getUserPass());
        user.setUserPass(pwd);
        return userDao.insert(user);
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        return userDao.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional(rollbackFor = {BusinessException.class})
    public void testTransactional() {
      /*  System.out.println("开始执行业务代码");
        Tag tag = new Tag(null, "C--", "C--描述");
        tagDao.insert(tag);*/
        // throw new BusinessException("业务代码从错误");  /*测试回滚*/
    }

    @Override
    public boolean isUserExisted(String username) {
        if (userDao.getUserByName(username) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserEmailExisted(String email) {
        if (userDao.getUserByEmail(email) != null) {
            return true;
        }
        return false;
    }
}
