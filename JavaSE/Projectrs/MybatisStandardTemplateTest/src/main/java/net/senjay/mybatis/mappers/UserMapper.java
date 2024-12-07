package net.senjay.mybatis.mappers;

import net.senjay.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectUserById(@Param("id") int id);
    User selectUserByNameAndId(@Param("name") String name,@Param("id") int id);
    // 在接口中设置参数
    List<User> selectAllUser();
    // 传参
}
