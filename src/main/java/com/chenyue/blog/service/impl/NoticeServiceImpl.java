package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.NoticeDao;
import com.chenyue.blog.entity.Notice;
import com.chenyue.blog.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    NoticeDao noticeDao;

    @Override
    public void insertNotice(Notice notice) {
        noticeDao.insert(notice);
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeDao.getNoticeById(id);
    }
}
