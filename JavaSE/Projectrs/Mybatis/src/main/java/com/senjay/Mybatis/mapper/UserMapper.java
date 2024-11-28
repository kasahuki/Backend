package com.senjay.Mybatis.mapper;

import com.senjay.Mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    int insertUser();// 如何进行传参到mapper映射文件当中动态查询数据库中表

    void deleteUser();

    void updateUser();

    User selectUser();
    // User实体类的作用就是返回数据库的查询字段而已
    List<User> selectAllUser();

}
