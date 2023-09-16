package com.blog.controller;

import com.blog.domain.Framer;
import com.blog.vo.Code;
import com.blog.vo.Result;
import com.blog.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping
    public Result register(@RequestBody Framer framer){
        System.out.println(framer);
        Result result = new Result();
        result.setData(registerService.register(framer));
        result.setCode(Code.SAVE_OK);
        result.setMsg("注册成功");
        return result;
    }
}
