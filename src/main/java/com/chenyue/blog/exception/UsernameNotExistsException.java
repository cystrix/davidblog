package com.chenyue.blog.exception;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/20
 * @description:
 */
public class UsernameNotExistsException extends BusinessException{
    public UsernameNotExistsException(){
        super();
    }
    public UsernameNotExistsException(String msg) {
        super(msg);
    }
}
