package com.chenyue.blog.conf.exception;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.exception.BusinessException;
import com.chenyue.blog.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response defaultExceptionHandle(Exception ex) {
        log.error(ex.getMessage());
        Response response = Response.builder()
                .code(CodeEnum.INTERNAL_SERVER_ERROR.code)
                .msg(CodeEnum.INTERNAL_SERVER_ERROR.message)
                .data(ex.getClass().getCanonicalName() +": "+ ex.getMessage())
                .build();
        return response;
    }

    @ExceptionHandler(value = {BusinessException.class})
    public Response businessExceptionHandle(Exception ex) {
        log.error(ex.getMessage());
        Response response = Response.builder()
                .code(CodeEnum.INTERNAL_SERVER_ERROR.code)
                .msg("业务错误，回滚")
                .build();
        return response;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response parameterErrorHandle(IllegalArgumentException ex) {
        log.warn(ex.getMessage());
        Response response = Response.builder()
                .code(CodeEnum.BAD_REQUEST_PARAMETER.code)
                .msg(CodeEnum.BAD_REQUEST_PARAMETER.message)
                .data(ex.getMessage())
                .build();
        return response;
    }

    @ExceptionHandler(value = {JWTVerificationException.class, JWTDecodeException.class})
    public Response tokenVerifyExceptionHandle(Exception ex){
        String qualifiedExceptionName = ex.getClass().getCanonicalName();
        String exceptionMsg = ex.getMessage();
        log.error(qualifiedExceptionName+":"+exceptionMsg);
        Response response = Response.builder()
                .code(CodeEnum.BAD_REQUEST_PARAMETER.code)
                .msg("token验证失败")
                .build();
        return response;
    }
}
