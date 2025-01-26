package com.senjay.Mybatis;

import com.senjay.Mybatis.mapper.UserMapper;
import com.senjay.Mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//juit测试类 有什么用  可以测试方法 测试和非测试有什么区别？

public class test {
    @Test
    public void MybatisTest() throws IOException {
      InputStream inputStream = Resources.getResourceAsStream("Mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession session = factory.openSession();
        // 获取接口的实现类
        UserMapper userMapper = session.getMapper(UserMapper.class);
//        userMapper.insertUser();
//        userMapper.deleteUser();
//        userMapper.updateUser(); 提供了接口方法使用sql语句操作数据库
//        User User = userMapper.selectUser();
//        System.out.println(User.toString());
        List<User> list = userMapper.selectAllUser();
        list.forEach(user -> System.out.println(user.toString()));// lambda表达式 user表示的是list集合中的每一个元素

        session.commit();// 事务要进行提交

        session.close();// 要记得关闭资源



    }
}
