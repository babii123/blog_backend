package com.blog.service;

import com.blog.domain.Framer;
import com.blog.vo.LoginInfoVo;

public interface LoginService {
    /**
     * 登录，返回用户id，用于检查登录
     * @return
     */
    public LoginInfoVo login(String nameOrEmailOrPhone, String password);

    /**
     * 判断登录，让前端可以正确的跳转一些页面
     * @return
     */
    public boolean checkLogin(String token);
}
