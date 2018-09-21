package com.juemuren.restTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by 肖文 on 2018/5/3
 */
public class RestTemplateUtils {

    public static HttpEntity<Object> httpHeader(Object params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(params, headers);
    }
}
