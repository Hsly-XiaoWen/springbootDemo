package com.tuandai.dao;

import com.tuandai.entiy.Person;
import com.tuandai.service.PersonCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 肖文 on 2018/4/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(PersonDAOTest.class);

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private PersonCacheService personCacheService;

    /**
     * 测试添加数据
     * @throws Exception
     */
    @Test
    public void insertPerson() throws Exception {
        Person p = new Person(10012, "test", 2, "1352451556");
        int result=this.personDAO.insertPerson(p);
        this.personDAO.insertPersons(p);
        logger.info("返回的数据是{}",result);
        logger.info("返回的数据是{}",p.getId());
    }

    /**
     * 根据Id查找用户
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        Person person=personDAO.queryById(10000);
        logger.info("返回数据是{}",person.toString());
    }

    @Test
    public void queryPerson() throws Exception {
        List<Person> data = this.personCacheService.personList();
        data.forEach(x->System.out.println(x.toString()));
    }

    @Test
    public void queryByUserName() throws Exception {
    }

}