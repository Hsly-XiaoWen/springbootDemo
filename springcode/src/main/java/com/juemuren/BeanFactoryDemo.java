package com.juemuren;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by 肖文 on 2018/9/25
 */
public class BeanFactoryDemo {

    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource(""));
        String name= (String) bf.getBean("hello");
    }
}
