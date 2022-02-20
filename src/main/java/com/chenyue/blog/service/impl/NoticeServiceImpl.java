package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.NoticeDao;
import com.chenyue.blog.entity.Notice;
import com.chenyue.blog.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Notice> listNotice(Integer status) {
        return noticeDao.listNotice(status);
    }

    @Override
    public void insertNotice(Notice notice) {
        noticeDao.insert(notice);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeDao.update(notice);
    }

    @Override
    public void deleteNotice(Integer id) {
        noticeDao.deleteById(id);
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeDao.getNoticeById(id);
    }
}
