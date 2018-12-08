package com.juemuren.service;

import com.github.pagehelper.PageHelper;
import com.juemuren.config.ds.DataSourceName;
import com.juemuren.config.ds.DataSourceNames;
import com.juemuren.dao.PersonDAO;
import com.juemuren.entiy.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 肖文 on 2018/4/27
 * Person缓存服务
 */
@Service
public class PersonCacheService {

    private static final Logger logger = LoggerFactory.getLogger(PersonCacheService.class);

    @Autowired
    private PersonDAO personDAO;

    /**
     * 当本地缓存里没有数据时执行该方法，有的话直接从缓存获取数据
     * @param id
     * @return
     */
    @Cacheable(value = "person", key = "T(String).valueOf(#id)")
    public Person findPerson(int id) {
        logger.info("执行了该方法");
        Person person = this.personDAO.queryById(id);
        return person;
    }

    /**
     * 分页显示数据
     * @return
     */
    public List<Person> personList() {
        PageHelper.startPage(2, 5);
        List<Person> data = this.personDAO.queryPerson();
        return data;
    }
    /**
     * 
     * @param person
     * @return
     */
    @CachePut(value = "person",key ="T(String).valueOf(#person.id)")
    public Person updatePerson(Person person){
        logger.info("执行了该方法");
        return person;
    }

    @CacheEvict(value = "person",key = "T(String).valueOf(#person.id)")
    public void deletePerson(Person person){
        logger.info("执行了该方法{}",person.toString());
    }


    /********
     * 注解切库
      * @return
     */
    @DataSourceName(value = DataSourceNames.myBatisDB)
    public Person query(){
        return this.personDAO.queryById(1);
    }

    public Person findPerson(){
        return this.personDAO.queryById(1);
    }

    @DataSourceName(value = DataSourceNames.crmDB)
    public Person querys(){
        return this.personDAO.queryById(1);
    }


}
