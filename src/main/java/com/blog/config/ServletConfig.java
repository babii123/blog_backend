package com.blog.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.annotation.processing.Filer;
import javax.servlet.Filter;

/**
 * AbstractDispatcherServletInitializer类是SpringMVC提供的快速初始化Web3.0容器的抽象类
 * 1. createServletApplicationContext()方法，创建Servlet容器时，加载SpringMVC对应的bean并放入
 * WebApplicationContext对象范围中，而WebApplicationContext的作用范围为ServletContext范围
 * 即整个web容器范围
 * 2. getServletMappings()方法，设定SpringMVC对应的请求映射路径，设置为/表示拦截所有请求，任意请求都将
 * 转入到SpringMVC进行处理
 * 3. createRootApplicationContext()方法，如果创建servlet容器时需要加载非SpringMVC对应的bean，使用
 * 当前方法进行，使用方式同createServletApplicationContext()
 */
//public class ServletConfig extends AbstractDispatcherServletInitializer {
////    加载配置类
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(SpringMvcConfig.class);
//        return (WebApplicationContext) ctx;
//    }
////    哪些类通过SpringMVC处理
//    @Override
//    protected String[] getServletMappings() {
////        所有
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(SpringConfig.class);
//        return (WebApplicationContext) ctx;
//    }
//}
public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    POST请求中文处理乱码
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}