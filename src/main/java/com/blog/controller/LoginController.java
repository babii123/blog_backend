package com.blog.controller;

import com.blog.exception.BusinessException;
import com.blog.utils.JWTUtils;
import com.blog.vo.Code;
import com.blog.vo.LoginInfoVo;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import com.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 登录
     * @param map
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map map){
        String nameOrEmailOrPhone = map.get("nameOrEmailOrPhone").toString();
        String password = map.get("password").toString();
        Result result = new Result();
        result.setData(loginService.login(nameOrEmailOrPhone,password));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Login_Success);
        return result;
    }

    /**
     * 判断登录，返回用户信息
     * @return
     */
    @GetMapping("/checkLogin")
    public Result checkLogin(@RequestHeader("token") String token){
        Result result = new Result();
        if (token.length() == 0){
            result.setCode(200);
            result.setMsg("未登录");
            result.setData(false);
            return result;
        }
        if (loginService.checkLogin(token)){
            result.setCode(200);
            result.setMsg("已登录");
            result.setData(true);
        } else{
            result.setCode(200);
            result.setMsg("登录过期");
            result.setData(false);
        }
        return result;
    }

}
