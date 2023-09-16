package com.blog.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan({"com.blog.service"})
@PropertySources({
        @PropertySource(value = "classpath:jdbc.properties"),
        @PropertySource(value = "classpath:application.properties")
})
@Import({JdbcConfig.class,MyBatisConfig.class})
//@ComponentScan(value = "com.blog",
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.ANNOTATION,
//                classes = Controller.class
//        )
//)
public class SpringConfig {
}
