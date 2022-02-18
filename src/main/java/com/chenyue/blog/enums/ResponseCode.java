package com.chenyue.blog.enums;

public enum ResponseCode {
    INTER_SERVER_UNAVAILABLE(503,"服务器繁忙"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_REQUEST_PARAMETER(400, "请求参数错误"),
    USER_UNAUTHORIZED(401, "用户未登录"),
    FORBIDDEN_ACCESS(403, "没有权限，禁止访问"),
    OK(200, "一切正常");

    public final Integer code;
    public final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseCode OK(){
        return ResponseCode.OK;
    }
}
