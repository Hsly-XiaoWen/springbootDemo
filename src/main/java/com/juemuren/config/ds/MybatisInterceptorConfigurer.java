package com.juemuren.config.ds;

import org.apache.ibatis.plugin.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 肖文 on 2018/11/30.
 */
public interface MybatisInterceptorConfigurer {
    List<Interceptor> supplyInterceptors();


    class Default implements MybatisInterceptorConfigurer {

        @Override
        public List<Interceptor> supplyInterceptors() {
            return new ArrayList<>();
        }
    }
}
