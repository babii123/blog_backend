package com.blog.controller;

import com.blog.exception.BusinessException;
import com.blog.exception.SystemException;
import com.blog.vo.Code;
import com.blog.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doException(SystemException ex){
        //记录日志
        //通知运维
        //给开发发邮件
        return new Result(ex.getCode(),ex.getMessage(),null);
    }

    @ExceptionHandler(BusinessException.class)
    public Result doException(BusinessException ex){
        return new Result(ex.getCode(),ex.getMessage(),null);
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        System.out.println(ex);
        return new Result(Code.SYSTEM_UNKNOW_ERR,"系统繁忙，请稍后再试！",null);
    }
}
