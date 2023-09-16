package com.blog.vo;

import lombok.Data;

@Data
public class Result {
    //描述统一格式中的数据
    private Object data;
    //描述统一格式中的编码，用于区分操作，可以简化配置0或1表示成功失败
    private Integer code;
    //描述统一格式中的消息，可选属性
    private String msg;

    public Result() {
    }

    public Result(Integer code, String msg,Object data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
