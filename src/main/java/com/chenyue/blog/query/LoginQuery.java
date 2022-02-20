package com.chenyue.blog.query;

import lombok.Data;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
@Data
public class LoginQuery {
    private String account; // username or email
    private String password;
}
