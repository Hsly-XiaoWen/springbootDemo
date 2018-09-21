package com.juemuren.shiro.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * Created by 肖文 on 2018/6/20
 */
public interface PermissionDAO {

    Set<String> getPermissionsByName(@Param("name") String name);
}
