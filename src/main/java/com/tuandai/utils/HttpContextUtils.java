package com.tuandai.utils;

import eu.bitwalker.useragentutils.Browser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 肖文 on 2018/8/25
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getBrowerInfo(HttpServletRequest req) {
        String userAgent = req.getHeader(HttpHeaders.USER_AGENT);
        if (StringUtils.isNotBlank(userAgent)) {
            userAgent = "-" + Browser.parseUserAgentString(userAgent).getName();
        }
        return userAgent;
    }
}

