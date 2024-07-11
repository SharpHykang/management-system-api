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

    // @Autowired
    // private Interceptor interceptor;

    /**
     * 解决跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
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
        // 原生Swagger3需要请求的路径/资源
        excludePath.add("/v3/**");     //swagger
        excludePath.add("/swagger-resources/**");     //swagger
        excludePath.add("/swagger-ui/**");     //swagger
        // knief4j美化需要请求的路径/资源。
        excludePath.add("/webjars/**");     //swagger
        excludePath.add("/favicon.ico/**");     //swagger
        excludePath.add("/doc.html/**");     //swagger
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")   //拦截所有路径
                .excludePathPatterns(excludePath);  //排除指定路径
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}