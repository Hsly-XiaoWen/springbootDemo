package com.tuandai.dao;

import com.tuandai.entiy.Person;
import com.tuandai.entiy.PersonDTO;
import com.tuandai.entiy.PersonExcelDTO;

import java.util.List;

/**
 * Created by 肖文 on 2018/4/27
 */

public interface PersonDAO {

    /**
     * 添加用户
     * @param person
     * @return
     */
    int insertPerson(Person person);

    /**
     * 添加用户,返回主键id
     * @param person
     * @return
     */
    void insertPersons(Person person);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    Person queryById(int id);

    /**
     * 查询所有用户
     * @return
     */
    List<Person> queryPerson();

    /**
     * 查询所有用户
     * @return
     */
    List<PersonDTO> queryPersonDTO();

    /**
     * 更新用户
     * @param person
     */
    int updatePerson(Person person);

    /**
     * 通过名字查找用户
     * @param userName
     */
    void queryByUserName(String userName);

    /**
     * 查询所有用户
     * @return
     */
    List<PersonExcelDTO> queryPersonExcelDTO();
}
