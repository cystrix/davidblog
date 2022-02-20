package com.chenyue.blog.exception;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/20
 * @description:
 */
public class PasswordErrorException extends BusinessException{
    public PasswordErrorException(){
        super();
    }
    public PasswordErrorException(String msg) {
        super(msg);
    }
}
