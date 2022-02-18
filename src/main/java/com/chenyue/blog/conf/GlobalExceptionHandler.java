package com.chenyue.blog.conf;


import com.chenyue.blog.enums.ResponseCode;
import com.chenyue.blog.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseVo defaultExceptionHandle(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseVo<>(ResponseCode.INTERNAL_SERVER_ERROR.code, ResponseCode.INTERNAL_SERVER_ERROR.message, ex.getMessage());
    }
}
