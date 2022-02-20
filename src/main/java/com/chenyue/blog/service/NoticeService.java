package com.chenyue.blog.service;

import com.chenyue.blog.entity.Notice;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface NoticeService {
    List<Notice> listNotice(Integer status);
    void insertNotice(Notice notice);
    void updateNotice(Notice notice);
    void deleteNotice(Integer id);
    Notice getNoticeById(Integer id);
}
