package com.tuandai.config.spring;

import com.tuandai.utils.CommonUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 肖文 on 2018/7/10
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    // 匹配版本
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("(\\d{1,2}.\\d{1,2}.\\d{1,2})");

    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition apiVersionCondition) {
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher match = VERSION_PREFIX_PATTERN.matcher(request.getHeader("version"));
        if (match.find()) {
            //如果请求的版本号大于配置版本号， 则满足
            if (CommonUtils.completeVersion(match.group(1)) >= this.apiVersion) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        //优先匹配最新版本
        return apiVersionCondition.getApiVersion()-this.apiVersion;
    }
}
