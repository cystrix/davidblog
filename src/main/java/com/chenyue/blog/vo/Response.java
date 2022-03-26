package com.chenyue.blog.vo;

import com.chenyue.blog.enums.CodeEnum;

public class Response{
    private Integer code;
    private String msg;
    private Object data;


    public Response(Integer code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static Response OK(){
        return new Response(CodeEnum.OK.code, CodeEnum.OK.message, null);
    }

    public  static Response success(){
        return new Response(1, "操作成功", null);
    }

    public static Response failed(){
        return new Response(0, "操作失败", null);
    }
    public static Response failed(String data){
        return new Response(0, "操作失败", data);
    }
    public static Response success(Object data){
        return new Response(0, "操作成功", data);
    }


    public static Builder builder(){
        return new Builder();
    }

    // 静态内部类
    public static class Builder{
        private Integer code;
        private String msg;
        private Object data;

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(Object data){
            this.data = data;
            return this;
        }

        public Response build(){
            return new Response(code, msg, data);
        }
    }
}
