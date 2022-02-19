package com.chenyue.blog.exception;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
public class BusinessException extends RuntimeException{
    public BusinessException(){
        super("业务错误");
    }
    public BusinessException(String msg){
        super(msg);
    }
}
