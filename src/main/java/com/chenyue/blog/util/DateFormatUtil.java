package com.chenyue.blog.util;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/19
 * @description:
 */
public final class DateFormatUtil {
    public DateFormatUtil(){}

    public String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
