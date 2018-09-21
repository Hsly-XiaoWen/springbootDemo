package com.juemuren.shiro.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by 肖文 on 2018/6/20
 */
public interface UserDAO {

    String getPasswordByUserName(@Param("userName") String userName);
}
