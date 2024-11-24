package com.senjay.Mybatis;

import com.senjay.Mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
        userMapper.insertUser();
        session.commit();

    }
}
