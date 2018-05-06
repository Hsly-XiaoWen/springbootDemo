package com.tuandai.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 肖文 on 2018/5/2
 * 自定义拦截器
 */
public class SpringInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SpringInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        // 请求调用之前进行处理
        logger.info("请求信息是{}",httpServletRequest.getRequestURL());
        logger.info("请求信息是{}", JSON.toJSON(httpServletRequest.getParameterMap()));
        return true;//只有返回true才会继续向下执行，若其返回值为FALSE，就中断请求目标方法了
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        // 请求处理之后，在页面渲染之前执行。postHandle 是在目标方法执行完之后执行的
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
