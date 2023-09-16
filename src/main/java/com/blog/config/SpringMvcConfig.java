package com.blog.config;

import com.blog.controller.interceptor.ProjectInterceptor;
import com.blog.controller.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan({"com.blog.controller", "com.blog.config","com.blog.cache","com.blog.handler"})
//开启json数据转对象
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer{
    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Autowired
    private TokenInterceptor TokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(TokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login","/checkLogin","/");
    }

    /**
     * 配置跨域
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")// 根据实际情况配置映射路径
//                .allowedOrigins("*") // 允许跨域访问的域名
//                .allowedMethods("*")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
}
