package com.chenyue.blog.service;

import com.chenyue.blog.entity.Notice;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface NoticeService {
    void insertNotice(Notice notice);
    Notice getNoticeById(Integer id);
}
