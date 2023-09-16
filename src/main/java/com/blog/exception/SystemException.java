package com.blog.exception;

public class SystemException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SystemException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
    public SystemException(Integer code,String msg,Throwable cause){
        super(msg,cause);
        this.code = code;
    }
}
