package com.blog.controller.interceptor;

import com.blog.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此处拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 拦截器和过滤器的区别
     * 1.拦截器针对访问控制器进行拦截
     * 及 @RequestMapping(value = {"/test"})
     * 简而言说就是访问方法的url
     * 应用：可以作为权限的判断，
     * 2.过滤器则是针对全局的请求
     * 包括：css/js/html/jpg/png/git/...
     * 及静态文件
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("token拦截...");
        response.setCharacterEncoding("utf-8");
        // 获取请求头
        String token = request.getHeader("token");
        System.out.println(token);
        //如果已经登录，不拦截
        if (null != token) {
            //验证token是否正确
            boolean result = JWTUtils.verify(token);
            System.out.println("是否通过拦截器:" + result);
            if (!result) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
