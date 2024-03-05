package com.hykang.management.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 解决跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }

    /**
     * 异步请求配置
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    /**
     * 配置拦截器、拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/managers/login");  //登录
        excludePath.add("/swagger-resources/**");     //swagger
        excludePath.add("/webjars/**");     //swagger
        excludePath.add("/v2/**");     //swagger
        excludePath.add("/v3/**");     //swagger
        excludePath.add("/swagger-ui/**");     //swagger
        excludePath.add("/api");     //swagger
        excludePath.add("/api-docs/**");     //swagger
        excludePath.add("/doc.html/**");     //swagger
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")   //拦截所有路径
                .excludePathPatterns(excludePath);  //排除指定路径
        WebMvcConfigurer.super.addInterceptors(registry);

    }
}