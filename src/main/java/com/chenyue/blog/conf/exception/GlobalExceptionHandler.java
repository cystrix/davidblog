package com.chenyue.blog.conf.exception;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.exception.BusinessException;
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
        return new ResponseVo<>(CodeEnum.INTERNAL_SERVER_ERROR.code, CodeEnum.INTERNAL_SERVER_ERROR.message,ex.getClass().getCanonicalName() +": "+ ex.getMessage());
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseVo businessExceptionHandle(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseVo<>(CodeEnum.INTERNAL_SERVER_ERROR.code, "业务错误，回滚", null);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseVo parameterErrorHandle(IllegalArgumentException ex) {
        log.warn(ex.getMessage());
        return new ResponseVo<>(CodeEnum.BAD_REQUEST_PARAMETER.code, CodeEnum.BAD_REQUEST_PARAMETER.message, ex.getMessage());
    }

    @ExceptionHandler(value = {JWTVerificationException.class, JWTDecodeException.class})
    public ResponseVo tokenVerifyExceptionHandle(Exception ex){
        String qualifiedExceptionName = ex.getClass().getCanonicalName();
        String exceptionMsg = ex.getMessage();
        log.error(qualifiedExceptionName+":"+exceptionMsg);
        return new ResponseVo<>(CodeEnum.BAD_REQUEST_PARAMETER.code, "token验证失败", null);
    }
}
