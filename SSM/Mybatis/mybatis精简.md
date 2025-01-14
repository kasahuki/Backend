## **映射文件命名规则**：

- 表 `t_user` 对应实体类 `User`，映射文件为 `UserMapper.xml`。
- **一个映射文件对应一个实体类，对应一张表的操作**。

##  **实体类与SQL字段映射**：

- 实体类属性与SQL字段一一对应，属性名映射字段名。

- **`resultType`**：

  - 用于简单映射，字段名与属性名一致时使用。

  - 自动映射，无需额外配置。

  - 示例：

    xml

    复制

    ```sql
    <select id="selectUserById" resultType="User">
        select * from test2 where id='${id}';
    </select>
    ```

    

    运行 HTML

- **`resultMap`**：

  - 用于复杂映射，字段名与属性名不一致时使用。

  - 需要手动定义映射关系。

  - 示例：

    xml

    复制

    ```sql
    <resultMap id="UserResultMap" type="User">
        <id property="id" column="user_id"/>
        <result property="name" column="user_name"/>
        <result property="age" column="user_age"/>
    </resultMap>
    
    <select id="selectUserById" resultMap="UserResultMap">
        select user_id, user_name, user_age from test2 where user_id='${id}';
    </select>
    ```

    

## **实体类与表数据的关系**：

- **每个实体类对象代表表中的一行数据**。

## **Mapper接口与XML文件**：

- 在 `UserMapper.xml` 中编写SQL语句。
- 在Java代码中调用Mapper接口方法，相当于执行XML中的SQL语句。
-  MyBatis 的 SQL 映射文件（如 `UserMapper.xml`）中，`resultType` 是一个非常重要的属性，用于指定 SQL 查询结果的映射类型。它的作用是告诉 MyBatis 将查询结果映射到哪个 **Java 类型（通常是实体类）。**

---



可创建模板template文件 将配置文件和mapper xml文件作为模板

~~~xml
<!--User CheckLoginByParam(@Param("username") String username, @Param("password") String password);-->
    <select id="CheckLoginByParam" resultType="User">
        select * from t_user where username = #{username} and password = #{password}
    </select>
~~~

其中Param(“别名”) 数据类型 参数名



**resultType 的值就是查询的每一行数据都会被封装成这个类型**

**总的返回值就是接口的方法返回类型**



resultType = _integer /实体类/map

使用不同的数据结构封装这些数据就可以利用这些数据结构的方法操作这些在数据库中查询到的数据了





## **多对一和一对多**

![image-20250112145001620](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250112145001620.png)



![image-20250112144747567](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250112144747567.png)



外键是一种约束，它用来建立两个表之间的关联关系。在关系数据库中，外键通常指向另一个表中的主键，用来保持数据的完整性和一致性。

具体来说，在上面的例子中：

- 在 `orders` 表中，`user_id` 列被定义为外键。这意味着 `orders` 表中的每一行可以包含一个指向 `users` 表中某一行的值。
- 外键的值就是指向另一张表中主键列的值。在这里，`orders` 表中的 `user_id` 值将与 `users` 表中的 `user_id` 值对应，表示哪个用户拥有这个订单。

![image-20250112145627059](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250112145627059.png)

## 动态sql

### if （sql多加恒成立条件 1==1 ）

### where （只可去前面多余的and/or）

- where和if一般结合使用：
 - 若where标签中的if条件都不满足，则where标签没有任何功能，即不会添加where关键字  
   - 若where标签中的if条件满足，则where标签会自动添加where关键字，并将条件最前方多余的and/or去掉  

### trim

- trim用于去掉或添加标签中的内容  
- 常用属性
- prefix：在trim标签中的内容的前面==添加==某些内容  
- suffix：在trim标签中的内容的后面==添加==某些内容 
  - prefixOverrides：在trim标签中的内容的前面==去掉==某些内容  
  - suffixOverrides：在trim标签中的内容的后面==去掉==某些内容
- 若trim中的标签==**都不满足条件**==，则trim标签没有任何效果，也就是只剩下`select * from t_emp`

### choose、when、otherwise

外面要套一层的where

`<where>` 标签的主要功能是：

1. **自动生成 `WHERE` 关键字**：
   - 如果 `<where>` 标签内有内容，MyBatis 会自动生成 `WHERE` 关键字。
   - 如果 `<where>` 标签内没有内容，则不会生成 `WHERE` 关键字。
2. **自动去除多余的 `AND` 或 `OR`**：
   - 如果 `<where>` 标签内的内容以 `AND` 或 `OR` 开头，MyBatis 会自动去除这些多余的连接词。

**然后再套一层choose 之后就是when（else if） 和otherwise（else）**

**相当于else if 和else 只能有一个满足** 

### foreach

- 属性：  

  collection：设置要循环的数组或集合  

  item：表示集合或数组中的每一个数据  

  separator：设置循环体之间的分隔符，分隔符前后默认有一个空格，如` , `

  open：设置foreach标签中的内容的开始符  

  close：设置foreach标签中的内容的结束符

  **（separator  open close 都是自己定义的 根据sql语句的语法格式）**

- 批量删除

- foreach就是批量遍历处理厂数据罢了





---





## MyBatis 支持两种方式来定义 SQL 语句和映射关系：

1. **XML 映射文件**（传统方式）
2. **注解**（简化方式）

### **XML 映射文件的方式**

这是 MyBatis 最经典的使用方式。你需要编写一个 XML 文件来定义 SQL 语句和结果映射。

#### （1）Mapper 接口



```java
public interface UserMapper {
    User findById(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
}
```

#### （2）XML 映射文件（UserMapper.xml）



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.UserMapper">
    <!-- 根据 ID 查询用户 -->
    <select id="findById" resultType="com.example.User">
        SELECT * FROM users WHERE id = #{id}
    </select>

    <!-- 插入用户 -->
    <insert id="save">
        INSERT INTO users (name, email) VALUES (#{name}, #{email})
    </insert>

    <!-- 更新用户 -->
    <update id="update">
        UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}
    </update>

    <!-- 删除用户 -->
    <delete id="delete">
        DELETE FROM users WHERE id = #{id}
    </delete>
</mapper>
```





#### （3）MyBatis 配置文件

在 MyBatis 的全局配置文件中，加载 Mapper 的 XML 文件：



```xml
<configuration>
    <mappers>
        <mapper resource="com/example/UserMapper.xml"/>
    </mappers>
</configuration>
```





------

###  **注解的方式**

MyBatis 也支持通过注解直接在 Mapper 接口中定义 SQL 语句，而不需要额外的 XML 文件。

#### （1）Mapper 接口（使用注解）



```java
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(int id);

    @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")
    void save(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(int id);
}
```

#### （2）MyBatis 配置文件

在 MyBatis 的全局配置文件中，直接加载 Mapper 接口：



```xml
<configuration>
    <mappers>
        <mapper class="com.example.UserMapper"/>
    </mappers>
</configuration>
```