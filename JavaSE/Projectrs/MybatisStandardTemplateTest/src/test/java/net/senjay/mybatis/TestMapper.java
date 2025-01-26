package net.senjay.mybatis;

import net.senjay.mybatis.mappers.UserMapper;
import net.senjay.mybatis.pojo.User;
import net.senjay.mybatis.utils.SqlsessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMapper { // 类名不能是Test 这样会与关键字冲突
    @Test
    public void testUserMapper() throws IOException {

        SqlSession session = new SqlsessionUtils().getSqlSession();// 通用        // 获取接口的实现类
        //ctrl + alt + l 格式化代码
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println(mapper.selectUserById(1).toString());
        System.out.println(mapper.selectUserByNameAndId("Attavoy",1).toString());
        session.close();



    }
}
