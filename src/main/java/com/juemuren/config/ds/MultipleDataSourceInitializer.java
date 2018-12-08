package com.juemuren.config.ds;

/**
 * @Author liubin
 * @Date 2017/10/21 16:12
 */
public interface MultipleDataSourceInitializer {

    MultipleDataSourceInitializer DEFAULT = new Default();

    String BEAN_NAME = "multipleDataSourceInitializer";


    class Default implements MultipleDataSourceInitializer {

    }


}
