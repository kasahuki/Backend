package net.senjay.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlsessionUtils {
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("Mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 注意异常
    // TODO : 待复习

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession();
        return sqlSession;
    }
}
