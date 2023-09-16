package com.blog.config;

import com.blog.controller.interceptor.ProjectInterceptor;
import com.blog.controller.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Autowired
    private com.blog.controller.interceptor.TokenInterceptor TokenInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        当访问/pages/？？？？时，走/pages目录下的内容
        registry.addResourceHandler("/pages/**","/pages/");
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedOrigins("*");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截所有，判断是否登录成功，根据token是否为null
         */
//        registry.addInterceptor(TokenInterceptor).addPathPatterns("/");
//        registry.addInterceptor(projectInterceptor).addPathPatterns("/type");
    }
}
