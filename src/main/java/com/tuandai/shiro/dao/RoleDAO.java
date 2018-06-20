package com.tuandai.shiro.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * Created by 肖文 on 2018/6/20
 */
public interface RoleDAO {

    Set<String> getRolesByName(@Param("userName") String userName);
}
