package com.juemuren.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.juemuren.config.spring.CustomRequestMappingHandlerMapping;
import com.juemuren.interceptor.SpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 肖文 on 2018/7/8
 * WebMvcConfigurerAdapter配置类其实是Spring内部的一种配置方式，
 * 采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制。
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {//WebMvcConfigurerAdapter


    /**
     * SpringBoot 路径匹配RequestMappingHandlerMapping优先查找参数变量然后是路径变量
     * SpringBoot允许自定义方法实现路径匹配规则
     * @return
     */
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new CustomRequestMappingHandlerMapping();
    }

    /**
     * 注册拦截器
     * 相当于在spring-mvc.xml文件添加<mvc:interceptor>标签配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringInterceptor());
    }


    /**
     * 最经常用到的就是"/"、"/index"路径请求时不通过@RequestMapping配置，
     * 而是直接通过配置文件映射指定请求路径到指定View页面
     * 相当于在配置文件中定义一个映射路径
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/webMvc").setViewName("Index");
    }


    /**
     * 设置SpringBoot消息转换机制，默认是使用JackJson
     * @param converters
     * WriteMapNullValue 是否输出值为null的字段,默认为false
     * DisableCircularReferenceDetect 消除对同一对象循环引用的问题
     * PrettyFormat 结果是否格式化,默认为false
     * WriteNullBooleanAsFalse Boolean字段如果为null,输出为false,而非null
     * WriteNullNumberAsZero 数值字段如果为null,输出为0,而非null
     * WriteNullStringAsEmpty 字符类型字段如果为null,输出为”“,而非null
     * WriteNullListAsEmpty List字段如果为null,输出为[],而非null
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //修改配置返回内容的过滤
        config.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty);
        fastJsonHttpMessageConverter.setFastJsonConfig(config);

        //中文乱码问题处理
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(fastJsonHttpMessageConverter);
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("application"
                , "json", Charset.forName("UTF-8"))));
        converters.add(stringHttpMessageConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
