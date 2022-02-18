package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.DemoDao;
import com.chenyue.blog.entity.Student;
import com.chenyue.blog.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    DemoDao demoDao;

    @Override
    public void insert(Student student) {
        if (demoDao.insert(student) != 0){
            log.info("学生表插入成功: "+ student.toString());
        }
    }

    @Override
    public List<Student> getAllUsers() {
        return demoDao.queryAllUsers();
    }
}
