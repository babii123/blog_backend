package com.blog.service.impl;

import com.blog.dao.FramerDao;
import com.blog.domain.Framer;
import com.blog.exception.BusinessException;
import com.blog.service.LoginService;
import com.blog.utils.JWTUtils;
import com.blog.vo.Code;
import com.blog.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private FramerDao framerDao;

    @Override
    public LoginInfoVo login(String nameOrEmailOrPhone, String password) {
        LoginInfoVo loginInfoVo = framerDao.getIdByPassAndNEP(nameOrEmailOrPhone,password);
        if (loginInfoVo == null){
            throw new BusinessException(Code.GET_ERR,"用户名或密码错误！！！");
        }else{
            loginInfoVo.setToken(JWTUtils.sign(nameOrEmailOrPhone,loginInfoVo.getFramerId()));
            System.out.println("userId="+JWTUtils.getUserId(loginInfoVo.getToken()));
        }
        return loginInfoVo;
    }

    @Override
    public boolean checkLogin(String token) {
        return JWTUtils.verify(token);
    }
}
