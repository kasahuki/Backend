package net.senjay.mybatis.mappers;

import net.senjay.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    User selectUserById();

    List<User> selectAllUser();
    // 传参
}
