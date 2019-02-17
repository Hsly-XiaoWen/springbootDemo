package com.juemuren.spring;

import com.juemuren.spring.bean.BeanDemo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by 肖文 on 2019/1/21.
 */
public class SpringFactory {

    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        BeanDemo demo = beanFactory.getBean("beanDemo",BeanDemo.class);
        System.out.println(demo.toString());
    }
}
