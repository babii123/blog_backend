package com.blog.controller;

import com.blog.vo.Code;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping
    public Result getTypeList(){
        Result result = new Result();
        result.setData(typeService.getTypeList());
        result.setMsg(Msg.Request_Success);
        result.setCode(200);
        return result;
    }
}
