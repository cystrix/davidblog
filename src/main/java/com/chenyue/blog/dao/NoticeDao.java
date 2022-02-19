package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface NoticeDao {
    int insert(Notice notice);
    int update(Notice notice);
    int deleteById(Integer id);

    Notice getNoticeById(Integer id);
    Integer countNotice(@Param("status") Integer status);
    List<Notice> listNotice(@Param("status") Integer status);
}
