package com.juemuren.restTemplate;

import com.juemuren.utils.JsonUtils;
import okhttp3.OkHttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 肖文 on 2018/5/3
 */
public class RestTemplateUtils {

    public static HttpEntity<Object> httpHeader(Object params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(params, headers);
    }

    public static RestTemplate CustomRestTemplate(OkHttpConfig applicationConstant, long timeOut) {
        applicationConstant.setOkHttpConnectTimeout(timeOut);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(JsonUtils.OBJECT_MAPPER);
        OkHttpClient okHttpClient = OkHttpUtils.okHttpClientBuilder(applicationConstant).build();
        return EnhancedRestTemplate.assembleRestTemplate(mappingJackson2HttpMessageConverter,okHttpClient);
    }

    public static HttpEntity<Object> applicationFormUrlencoded(Object params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(params, headers);
    }

    public static HttpEntity<Object> applicationJsonUtf8(Object params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(JsonUtils.object2Json(params), headers);
    }
}
