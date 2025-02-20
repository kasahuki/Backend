



系统环境变量（如 `JAVA_HOME` 和 `PATH`）主要用于：

- 命令行工具（如 `java`、`javac`）。
- 其他依赖 `JAVA_HOME` 的应用程序（如 Maven、Gradle、Tomcat 等）。

如果你在命令行中运行 `java -version` 或 `javac`，系统会使用环境变量中配置的 JDK 8。

# maven配置相关



Maven 的 `<parent>` 标签用于指定当前项目的父 POM。父 POM 可以继承一些默认配置（如依赖管理、插件配置等）。如果你没有使用 `<parent>` 标签，说明你的项目是一个独立的项目，没有继承任何父 POM。

- Spring Boot 是一个模块化的框架，不同的功能由不同的模块提供。
- 例如，`spring-boot-starter-web` 提供了 Web 开发相关的依赖，而 `spring-boot-starter-data-jpa` 提供了数据库访问相关的依赖。
- 你只需要引入你实际需要的模块，避免引入不必要的依赖。

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-project</artifactId>
    <version>1.0.0</version>

    <!-- 继承 Spring Boot 父工程 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.2</version>
    </parent>

    <dependencies>
        <!-- 添加 Spring Boot 依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!--这样也无需指定版本号 继承父工程-->
        </dependency>
    </dependencies>
</project>
```

 <font size=5 color =red >有些内容和springMVC&spring中一样或是简化了 注意（主要是知道有什么功能 语法不要死记硬背）</font>

如果ide中没有提供springboot的骨架快速构建 就自己手动构建工程 先maven 引入依赖

最后提供启动类

# Springboot基本文件结构



![image-20250116105700553](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116105700553.png)

~~~java
package com.senjay.springboot_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestApplication.class, args);
    }

}
// springboot启动类 会启动内嵌的tomcat

~~~

不用properties 后缀用yml/yaml （层级清晰 减少冗余 重复内容）

## yml配置信息的书写和获取

![image-20250116115937770](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116115937770.png)

![image-20250116120528426](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116120528426.png)

**注解更轻松**

![image-20250116120944821](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116120944821.png)

**key:**

Spring Boot 允许你将配置信息外部化，使得配置与代码分离。这样做的优点包括：

- **灵活性**：可以在不重新编译代码的情况下修改配置。
- **安全性**：敏感信息（如数据库密码）可以存储在配置文件中，而不是硬编码在代码中。
- **可维护性**：配置集中管理，便于维护和更新。

### springboot 整合mybatis



Spring Boot 自动发现并注册 MyBatis 相关的 Bean（如 `SqlSessionFactory`、`SqlSessionTemplate`、`Mapper` 接口等），而无需手动配置。

1. **自动创建 `SqlSessionFactory`**：
   - `SqlSessionFactory` 是 MyBatis 的核心组件，用于创建 `SqlSession`。
   - Spring Boot 会根据配置的数据源自动创建 `SqlSessionFactory`。
2. **自动创建 `SqlSessionTemplate`**：
   - `SqlSessionTemplate` 是 MyBatis 与 Spring 集成的核心组件，用于管理 `SqlSession` 的生命周期。
3. **自动扫描 Mapper 接口**：
   - Spring Boot 会自动扫描带有 `@Mapper` 注解的接口，并将其注册为 Spring Bean。
4. **自动配置事务管理**：
   - Spring Boot 会自动配置 MyBatis 的事务管理器，确保事务的正确管理。

~~~xml
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
~~~

**自动配置 MyBatis 与 Spring Boot 的集成。**

**自动扫描 Mapper 接口并注册为 Spring Bean。**

**提供默认的 MyBatis 配置（如 SqlSessionFactory 和 SqlSessionTemplate）。**

**但它 不包含数据库驱动（如 MySQL、PostgreSQL 等），因此你需要手动引入数据库驱动的依赖**

---





**根据DDL数据库语句生成实体类对象（在pojo包下）**

**然后在mapper包下创建对应表的mapper接口和注解（在上面写sql语句，不用在映射文件中写了）**

#### 1.user实体类对象

~~~java
package com.senjay.springboot_test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Integer id;      // 编号
    private String age;      // 年龄
    private String name;     // 姓名
    private Character gender; // 性别
}

~~~

####  2.userMapper接口

~~~java
package com.senjay.springboot_test.mappers;

import com.senjay.springboot_test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from test1 where id = #{id}")
    public User getUserById(Integer id);


}


~~~



#### 3.yml配置文件



~~~yml
server:
  port: 8080
  servlet:
    context-path: /myartifact

spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8 # 配置数据库地址并且指定编码格式
    username: root
    password: 123456
~~~



####  4.userService接口

~~~java
package com.senjay.springboot_test.services;

import com.senjay.springboot_test.pojo.User;

public interface userService {
    public User getUserById(Integer id);
}


~~~



####  5.userServiceImpl 实现类

~~~java
package com.senjay.springboot_test.services.impl;

import com.senjay.springboot_test.mappers.UserMapper;
import com.senjay.springboot_test.pojo.User;
import com.senjay.springboot_test.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}

~~~



####  6userController 控制器类

~~~java
package com.senjay.springboot_test.controller;

import com.senjay.springboot_test.pojo.User;
import com.senjay.springboot_test.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtroller {
    @Autowired
    private userService userService;

    @RequestMapping("/findId")
    public User getUserById(Integer id)
    {
        return userService.getUserById(id); // RestController 返回json字符串
    }

}

~~~







---

`UserMapper` 是 **数据访问层（DAO 层）** 的组件，负责与数据库进行交互。

`UserService` 是 **服务层（Service 层）** 的组件，负责处理业务逻辑。它的主要职责包括：

`Controller` 是 **表现层（Presentation Layer）** 的组件，负责处理 HTTP 请求和响应。它是客户端（如浏览器、移动端）与服务器之间的桥梁，主要职责包括：

`Controller` 的作用

**（1）接收 HTTP 请求**

- `Controller` 接收客户端发送的 HTTP 请求（如 GET、POST、PUT、DELETE 等）。
- 它通过注解（如 `@GetMapping`、`@PostMapping`）将请求映射到具体的方法。

**（2）调用服务层**

- `Controller` 调用 `Service` 层的方法来处理业务逻辑。
- 它不直接处理业务逻辑，而是将请求委托给 `Service` 层。

**（3）返回 HTTP 响应**

- `Controller` 将 `Service` 层返回的结果封装成 HTTP 响应，返回给客户端。
- 响应可以是 JSON、XML、HTML 等格式。

- 调用 `UserMapper` 执行数据库操作。
- 实现业务规则和逻辑（如数据校验、计算、事务管理等）。
- 为控制器层（Controller）提供统一的接口。

---



**数据库字段可能为 `NULL`**

在数据库中，字段的值可能为 `NULL`。如果使用 `int`，无法表示 `NULL`，因为 `int` 的默认值是 `0`。这会导致以下问题：

- 如果数据库中的字段值为 `NULL`，映射到 `int` 时会变成 `0`，这可能与业务逻辑不符。
- 使用 `Integer` 可以正确表示 `NULL`，避免数据歧义。



---

# Bean扫描与注册

也可以自己配置扫描注解

![image-20250116131227032](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116131227032.png)

![image-20250116131258210](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116131258210.png)

**==SpringBoot默认扫描启动类所在的包及其子包==**

如何将第三方类库中的类加入到bean容器中：

![image-20250116131728664](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116131728664.png)

## 1.@Bean（不推荐使用写在启动类上）

![image-20250116132206359](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116132206359.png)

**写在配置类中（推荐）** 各种配置类写在imports中

**然后配置类中写一些要注册ioc容器的** 

 ![image-20250116132431513](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116132431513.png)

这样的方法 所创建的bean对象的名字==默认==就是==方法名== 

**也可指定**

![image-20250116132644505](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116132644505.png)

## 2.@Import

首先要明白 **==SpringBoot默认扫描启动类所在的包及其子包==**

![image-20250116134355560](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116134355560.png)

先在启动类中书写注解 @Import  导入==ImportSelector实现类的字节码文件==

![image-20250116134007322](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116134007322.png)

要在**resources文件夹**中创建**imports后缀文件**里面写**要导入类（一般就导入配置类（配置类见上面@Bean））的全类名（按行写）**

ImportSelector实现类就获取**imports后缀文件** 中的类然后通过import导入到启动类

![image-20250116133606651](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116133606651.png)

~~~java
public class CommonImportSelector implements ImportSelector {
// 实现接口的方法要求String[] （返回值）
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取配置文件的内容
        List<String> imports = new ArrayList<>();
        
        InputStream is = CommonImportSelector.class.getClassLoader().getResourceAsStream("common.imports");
      // 用集合方便增删改查  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String line = null;

        try {
            while ((line = br.readLine()) != null) {
                imports.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return imports.toArray(new String[0]); //List<String> 转换为 String[] 数组
        // 因为返回值是String[]
    }
}
~~~

**就是利用输入流读取文件中的全类名**

## 注册条件

tips ： idea中 new User().var **自动添加变量接收这个值**

![image-20250116140310062](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116140310062.png)

![image-20250116140339545](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116140339545.png)

**可在配置文件中自定义**

![image-20250116141230802](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250116141230802.png)

**创建（注册/声明）bean** 

![image-20250116142630343](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116142630343.png)

![image-20250116143236184](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116143236184.png)

# Springboot原理篇 



 <font size=8 color = red>**起步依赖（starter） 和 自动装配**(自动配置)</font> 



## 自动配置原理

涉及源码分析 -->**面试八股文**

**bean 就是ioc容器存放的对象**

![image-20250116203613652](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116203613652.png)



![image-20250116203814130](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116203814130.png)

利用ApplicationContext类 也就是IOC容器获取容器中的bean对象

**bean没有指定名字默认就是类名**



![ ](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116204055615.png)



如果是多例 prototype

![image-20250116204222211](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116204222211.png)

![image-20250116204257270](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116204257270.png)



**报错要从下往上看**

![image-20250116223216412](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116223216412.png)

**定位当前打开的文件所在的资源路径 定位源码神器**

或者两下shift 搜索这个类/方法（针对源码/或是自己工程中的）



![image-20250116224254566](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116224254566.png)



`AnnotationMetadata annotationMetadata` 是 Spring 框架中的一个重要接口，用于表示被注解标记的类或方法的元数据。在 `selectImports` 方法中，`AnnotationMetadata` 提供了关于被 `@EnableAutoConfiguration` 注解标记的类的信息

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117095706216.png)

`getAutoConfigurationEntry(AnnotationMetadata annotationMetadata)` 是 `AutoConfigurationImportSelector` 类中的一个核心方法，负责加载、处理和过滤自动配置类。它的作用是返回一个 `AutoConfigurationEntry` 对象，包含需要加载的自动配置类和需要排除的类



![image-20250117100008682](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117100008682.png)





![image-20250117095927506](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117095927506.png)



![image-20250117100128888](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117100128888.png)

---



**`Class<?> annotation` 参数**

**作用**

- 指定需要加载的注解类型。
- 用于生成 `imports` 文件的路径。

**为什么需要这个参数？**

（1）**确定 `imports` 文件的路径**

- `ImportCandidates.load` 方法会根据传入的注解类型生成 `imports` 文件的路径。

- 文件路径的格式为：

  ```ceylon
  META-INF/spring/<注解全限定名>.imports
  ```

  例如，如果传入的注解是 `AutoConfiguration.class`，则文件路径为：

  ```ceylon
  META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
  ```

（2）**支持多种注解类型**

- `ImportCandidates.load` 是一个通用的工具方法，不仅可以加载自动配置类，还可以加载其他类型的配置类。
- 通过传入不同的注解类型，可以加载不同的 `imports` 文件。例如：
  - `AutoConfiguration.class`：加载自动配置类。
  - `EnableConfigurationProperties.class`：加载配置属性类。

（3）**扩展性**

- 如果需要支持新的注解类型，只需传入相应的注解类即可，无需修改 `ImportCandidates.load` 的实现。

---



`ImportCandidates.load` 方法会**遍历类加载器的类路径**，查找所有 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件，并加载其中的自动配置类。

**classpath：类路径是 JVM 用来查找类和资源文件的路径**

`ClassLoader.getResources` 是 Java 标准库中的一个方法，用于查找类路径下所有匹配的资源文件。它的作用是：

- 遍历类加载器的类路径，查找所有符合条件的资源文件。
- 返回一个 `Enumeration<URL>`，包含所有找到的资源文件的 URL。

在 `ImportCandidates.load` 方法中，遍历类路径的逻辑主要体现在以下代码中：



```java
Enumeration<URL> urls = findUrlsInClasspath(classLoaderToUse, location);
```

（1）**`findUrlsInClasspath` 方法**

`findUrlsInClasspath` 是一个工具方法，用于调用 `ClassLoader.getResources` 并查找所有匹配的 `imports` 文件。



```java
private static Enumeration<URL> findUrlsInClasspath(ClassLoader classLoader, String location) {
    try {
        return classLoader.getResources(location);
    } catch (IOException ex) {
        throw new IllegalArgumentException("Unable to load configurations from location [" + location + "]", ex);
    }
}
```

- **`classLoader.getResources(location)`**：
  - 调用类加载器的 `getResources` 方法，查找类路径下所有匹配的 `imports` 文件。
  - 返回一个 `Enumeration<URL>`，包含所有找到的资源文件的 URL。

（2）**遍历 `Enumeration<URL>`**

`ImportCandidates.load` 方法会遍历 `Enumeration<URL>`，读取每个 `imports` 文件的内容：



- `classLoader.getResources(location)` 会遍历**类加载器的类路径**，查找所有匹配的资源文件。
- - 系统类加载器（ClassLoader）的类路径包括：
    - 主模块的 `classes` 和 `resources` 目录。
    - 子模块的 `classes` 和 `resources` 目录。
    - 所有依赖的第三方库（JAR 文件）。







![image-20250116224617153](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116224617153.png)

### 自动装配具体含义

![image-20250116205345350](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116205345350.png)



## 自定义starter 

功能：提供==起步依赖==和==自动配置==

在 Spring Boot 中，**Starter** 是一种特殊的 Maven 或 Gradle 依赖，它简化了项目的依赖管理和配置。Starter 的目的是通过提供一组预定义的依赖和默认配置，让开发者能够快速集成和启用特定功能，而无需手动添加多个依赖或编写复杂的配置。

![image-20250116225600599](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116225600599.png)

在 Spring Boot 项目中，使用阿里云 OSS（对象存储服务）时，**自己配置 Starter** 和 **直接导入依赖** 是两种不同的方式，它们的主要区别在于 **配置的复杂度** 和 **代码的复用性**。下面通过 `aliyun-oss-spring-boot-starter` 的例子详细说明这两种方式的区别。

---

### 1. **直接导入依赖**
直接导入依赖是指手动引入阿里云 OSS 的 SDK 依赖（如 `aliyun-sdk-oss`），然后自己编写配置类、工具类等。

#### 实现步骤：
1. **引入依赖**：
   在 `pom.xml` 中引入阿里云 OSS 的 SDK 依赖：
   ```xml
   <dependency>
       <groupId>com.aliyun.oss</groupId>
       <artifactId>aliyun-sdk-oss</artifactId>
       <version>3.15.1</version>
   </dependency>
   ```

2. **手动配置**：
   在 `application.yml` 或 `application.properties` 中配置 OSS 的相关参数：
   ```yaml
   aliyun:
     oss:
       endpoint: your-oss-endpoint
       access-key-id: your-access-key-id
       access-key-secret: your-access-key-secret
       bucket-name: your-bucket-name
   ```

3. **编写配置类**：
   创建一个配置类，读取配置文件中的参数，并初始化 `OSSClient`：
   ```java
   @Configuration
   public class AliyunOSSConfig {
   
       @Value("${aliyun.oss.endpoint}")
       private String endpoint;
   
       @Value("${aliyun.oss.access-key-id}")
       private String accessKeyId;
   
       @Value("${aliyun.oss.access-key-secret}")
       private String accessKeySecret;
   
       @Bean
       public OSS ossClient() {
           return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
       }
   }
   ```

4. **使用 OSSClient**：
   在需要的地方注入 `OSSClient` 并使用：
   ```java
   @Service
   public class OSSService {
   
       @Autowired
       private OSS ossClient;
   
       public void uploadFile(String bucketName, String objectName, InputStream inputStream) {
           ossClient.putObject(bucketName, objectName, inputStream);
       }
   }
   ```

#### 特点：
- **优点**：
  - 完全控制配置和初始化过程，灵活性高。
  - 适合需要对 OSS 进行深度定制的场景。
- **缺点**：
  - 需要手动编写配置类，代码量较多。
  - 重复劳动，如果多个项目都需要使用 OSS，每个项目都要写一遍类似的代码。

---

### 2. **使用 Starter**
Starter 是 Spring Boot 提供的一种依赖管理方式，它将常用的配置和依赖打包成一个模块，开箱即用。对于阿里云 OSS，可以使用官方或社区提供的 `aliyun-oss-spring-boot-starter`。

#### 实现步骤：
1. **引入 Starter 依赖**：
   在 `pom.xml` 中引入 Starter 依赖：
   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>aliyun-oss-spring-boot-starter</artifactId>
       <version>2.2.0</version>
   </dependency>
   ```

2. **配置参数**：
   在 `application.yml` 或 `application.properties` 中配置 OSS 的相关参数：
   ```yaml
   aliyun:
     oss:
       endpoint: your-oss-endpoint
       access-key-id: your-access-key-id
       access-key-secret: your-access-key-secret
       bucket-name: your-bucket-name
   ```

3. **直接使用**：
   Starter 已经自动配置好了 `OSSClient`，可以直接注入使用：
   ```java
   @Service
   public class OSSService {
   
       @Autowired
       private OSS ossClient;
   
       public void uploadFile(String bucketName, String objectName, InputStream inputStream) {
           ossClient.putObject(bucketName, objectName, inputStream);
       }
   }
   ```

#### 特点：
- **优点**：
  - 开箱即用，无需手动编写配置类。
  - 代码简洁，减少重复劳动。
  - 适合快速集成，提高开发效率。
- **缺点**：
  - 灵活性较低，如果需要对 OSS 进行深度定制，可能需要修改 Starter 的源码或自己实现。

---

### 3. **两种方式的对比**

| 特性           | 直接导入依赖                   | 使用 Starter                    |
| -------------- | ------------------------------ | ------------------------------- |
| **配置复杂度** | 需要手动编写配置类，复杂度较高 | 开箱即用，配置简单              |
| **代码量**     | 代码量较多，重复劳动           | 代码量少，简洁                  |
| **灵活性**     | 灵活性高，适合深度定制         | 灵活性较低，适合标准场景        |
| **适用场景**   | 需要对 OSS 进行深度定制的项目  | 快速集成，标准化的项目          |
| **维护性**     | 每个项目都需要维护配置代码     | 依赖 Starter 维护，减少维护成本 |

---

### 4. **如何选择？**
- 如果你需要快速集成阿里云 OSS，并且不需要深度定制，建议使用 **Starter**。
- 如果你需要对 OSS 进行深度定制，或者 Starter 的功能无法满足需求，可以选择 **直接导入依赖**，并手动编写配置。

---

### **为什么需要配置类？**

**因为对于第三方类**

配置类的作用是 **定义和初始化 Bean**。在没有 Starter 的情况下，Spring Boot 无法自动完成以下工作：

1. **读取配置文件**：
   你需要通过 `@Value` 或 `@ConfigurationProperties` 从 `application.yml` 或 `application.properties` 中读取配置参数。
2. **初始化 Bean**：
   你需要使用 `@Bean` 注解，手动创建 `OSSClient` 实例，并将其注册到 Spring 容器中。
3. **依赖注入**：
   通过 `@Autowired`，将 `OSSClient` 注入到需要的地方。

sum：starter就是减少配置等重复操作 直接自动配置

### 操作步骤



![image-20250116233628530](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116233628530.png)

![image-20250116233826991](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116233826991.png)

starter就是进行依赖管理 所以这个模块**只需要留一个pom.xml**

而autoconfigure才是进行自动配置的 

**starter 模块：**





![image-20250116235422341](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250116235422341.png)



**autoConfigure模块:**





![image-20250116235732299](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250116235732299.png)

**定义自动配置文件 可以学习源码的格式！！**



![image-20250117001541821](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117001541821.png)

![image-20250117002015494](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117002015494.png)



Spring Boot 的自动配置机制会在应用启动时扫描所有依赖中的 `AutoConfiguration.imports` 文件，并加载其中声明的自动配置类。

**（注意和上面在启动类中自己书写的import语句做区分）**

import配置类 然后在配置类中写bean注解 添加要加入ioc容器的类

---



假设你开发了一个 HTTP 客户端工具库 `http-client`，其他项目可以通过导入这个工程来使用它。

**封装自己的maven工程**

#### 实现步骤：

1. **创建 `http-client` 工程**：

   - 在 `http-client` 的 `pom.xml` 中定义坐标：

     ```xml
     <groupId>com.example</groupId>
     <artifactId>http-client</artifactId>
     <version>1.0.0</version>
     ```

     

   - 编写 HTTP 客户端工具类。

2. **安装 `http-client` 到本地仓库**：
   在 `http-client` 目录下运行：

   

   ```maven
   mvn install
   ```

3. **在其他项目中导入 `http-client`**：
   在其他项目的 `pom.xml` 中添加依赖：

   ```xml
   <dependency>
       <groupId>com.example</groupId>
       <artifactId>http-client</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

   

   

4. **使用 `http-client`**：
   在其他项目中可以直接使用这个工具库：

   

   ```java
   import com.example.httpclient.HttpClient;
   
   public class Main {
       public static void main(String[] args) {
           HttpClient client = new HttpClient();
           String response = client.get("https://example.com");
           System.out.println(response);
       }
   }
   ```

#### 好处：

- **工具复用**：避免重复造轮子，提高开发效率。
- **统一维护**：工具库的更新和修复只需在一个地方进行。



---



# 实战篇

## 配置基础



对于父工程

**依赖管理（Dependency Management）**

- 父工程可以统一管理子模块的依赖版本，==避免版本冲突。==
- 例如，Spring Boot 的父工程 `spring-boot-starter-parent` 定义了大量常用依赖的版本号，子模块无需单独指定版本。

- **父工程可以强制所有子模块遵循统一的标准和规范，例如：**

  - **统一的依赖版本。**
  - **统一的构建配置。**
  - **统一的代码风格和质量检查。**

  

```yml
# 配置数据源信息
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306
    username: root
    password: 123456

# java实体类驼峰 数据库下划线 映射
mybatis:
  configuration:
    map-underscore-to-camel-case: true


```

**创建包结构 和实体类**

![image-20250117183618053](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117183618053.png)

```java
// 配置项目启动类
@SpringBootApplication
public class WSJApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(WSJApplication.class, args);

    }
}
```

## 开发接口

![image-20250117201615948](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117201615948.png)

![image-20250117202658573](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117202658573.png)

**传入的参数不是什么都要提交到数据库的 还要做请求参数的校验 参数要合法才行**

e.g. 对注册接口的参数进行校验（使用正则表达式）

### 1. **Controller 的职责**

Controller 是**表现层**的一部分，主要负责：

- 接收 HTTP 请求（如 GET、POST 等）。
- 解析请求参数（如路径参数、查询参数、请求体等）。
- 调用 Service 层处理业务逻辑。
- 返回 HTTP 响应（如 JSON、视图等）。

Controller 的代码应该尽量简单，只关注与 HTTP 相关的逻辑，而不应该包含具体的业务逻辑。

------

### 2. **Service 的职责**

Service 是**业务逻辑层**的一部分，主要负责：

- 实现具体的业务逻辑（如用户注册、订单处理等）。
- 调用数据访问层（如 DAO 或 Repository）进行数据操作。
- 处理事务管理（如使用 `@Transactional` 注解）。
- 封装业务规则和流程。

Service 层的代码应该专注于业务逻辑，确保逻辑清晰、可复用。



![image-20250117215929744](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117215929744.png)

**对于不符合正则会抛出异常**

**以下是统一异常处理类**

==最重要的是有可能会导错包！！！！==

![image-20250117222422180](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117222422180.png)

![image-20250117222227937](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250117222227937.png)

~~~java

// 注册和登录逻辑 

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    // 主次接口
    // 这里message具体值也可以交给前端进行校验
    public Result register(@Pattern(regexp = "^\\S{5,16}$", message = "用户名必须是5到16个非空白字符")  String username, @Pattern(regexp = "^\\S{5,16}$",  message = "密码必须是5到16个非空白字符")  String password) {
        if(userService.findUserByUsername(username) != null) // 说明用户名已被注册
             return Result.error("用户名已被注册");
        else
        {
            userService.register(username, password);
            return Result.success();
        }
    }
    @PostMapping("/login")
    public Result login(String username, String password) {
        User user = userService.findUserByUsername(username);
        if(user == null)
            return Result.error("用户不存在");
        else
        {
            if(Md5Util.checkPassword(password, user.getPassword()))
                // 此时对象里存的是加密过的密码
                return Result.success("登录成功");

                return Result.error("密码错误或者用户名错误，请重新输入");
        }
    }
}



~~~

密码加密工具类：

开发思路：

**要知道可以导入别人的模块使用别人封装好的方法实现自己的逻辑 工具类的使用**



## 有关泛型

~~~java
package com.senjay.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@Data
@NoArgsConstructor
@AllArgsConstructor
// 泛型问题？
public class Result<T> {
    private Integer code;//业务状态码  0-成功  1-失败
    private String message;//提示信息
    private T data;//响应数据  这样就可以返回任意类型的数据了


    //快速返回操作成功响应结果(带响应数据)
    // <E>这是 泛型类型参数声明，表示该方法使用一个类型参数 E。
    // Result<E>  这是方法的 返回类型

    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }
//     <> 表示编译器会根据方法的返回类型 Result<E> 自动推断泛型类型为 E。

    // 不带响应数据
    public static Result success() {
    //快速返回操作成功响应结果
        return new Result(0, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
// 成功和失败响应结果

~~~

### **静态方法与泛型**

静态方法是属于类的，而不是类的实例。因此，静态方法不能直接使用类的泛型参数（如 `T`）。如果静态方法需要使用泛型，必须单独声明泛型参数。

**（1）`public static Result success()`**

这个方法没有返回 `data` 字段，因此不需要使用泛型。它的返回值是 `Result`，而不是 `Result<T>`。此时，`Result` 是一个原始类型（Raw Type），`data` 字段的类型是 `Object`。

**（2）`public static <E> Result<E> success(E data)`**

这个方法返回一个带有 `data` 字段的 `Result` 对象。由于 `data` 的类型是动态的，因此需要单独声明一个泛型参数 `E`。`E` 表示 `data` 的类型，并且与类的泛型参数 `T` 无关。

 <font size=7 color =blue >**思考：**</font>

 <font size=3 color =red>**如果将E改为T** </font>

 <font size=3 color =red>在静态方法  public static <T> Result<T> success(T data)中：</font>



- `<T>` 是==静态==**方法的泛型参数**，与类**的泛型参数 `T`** 是 **不同的**。
- 方法的泛型参数 `T` 会 **遮蔽（Shadow）** 类的泛型参数 `T`。
- 编译器会将方法的泛型参数 `T` 视为一个新的类型参数，与类的泛型参数 `T` 无关

**如果方法 不是静态方法（即实例方法），那么方法的泛型参数和类的泛型参数 可以是同一个 `T`，因为实例方法可以直接使用类的泛型参数**



## 登录认证 token(JWT Token)

![image-20250118095724930](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250118095724930.png)

![image-20250118164430028](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250118164430028.png)

无需会写 直接调用工具类就行（会有人封装好 不用重复造无意义的轮子）

~~~java
package com.senjay;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JWTTest {
    @Test // 加个test才可以不用开启服务跑这个方法
    public void testGen() {
        // 创建一个Map对象，用于存储JWT的声明（claims），键为String类型，值为Object类型
        Map<String, Object> claims = new HashMap<>();

// 向claims中添加一个键值对，键为"id"，值为1
        claims.put("id", 1); // ctrl + D 复制当行内容到下行

// 向claims中添加一个键值对，键为"name"，值为"Senjay"
        claims.put("name", "Senjay");

// 使用JWT库创建一个JWT（JSON Web Token）
        String token = JWT.create()
                // 添加一个名为"user"的声明，值为之前创建的claims Map
                .withClaim("user", claims)
                // 设置JWT的过期时间，当前时间加上7天（1000毫秒 * 60秒 * 60分钟 * 24小时 * 7天）
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                // 使用HMAC256算法对JWT进行签名，密钥为"senjay"
                .sign(Algorithm.HMAC256("senjay"));

// 打印生成的JWT
        System.out.println(token);
    }
    @Test
    public void testParse() {
        // 模拟用户传过来的token
        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"+
                ".eyJ1c2VyIjp7Im5hbWUiOiJTZW5qYXkiLCJpZCI6MX0sImV4cCI6MTczNzc5NjIzOX0" +
                ".kifI-advSkajlXiuwVjK0QI4WAVegiIIQKtoggVyrhU";
        JWTVerifier jwtVerifier = JWT.require( Algorithm.HMAC256("senjay")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token); // 解码
        Map<String, Claim> claims = decodedJWT.getClaims(); // 获取声明 一个Map对象，键为String类型，值为Claim类型
        System.out.println(claims.get("user"));

    }

}

~~~

##  验证工具类

~~~java

package com.senjay.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "itheima"; // 密钥
	
	//接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}

~~~

 **JWT（JSON Web Token）** 的身份验证机制，它既不是基于 **Session**，也不是基于 **Cookie**，而是一种无状态的、基于令牌（Token）的身份验证方式。

---

### JWT 的原理

#### 1. **JWT 的结构**
JWT 由三部分组成：
- **Header**：包含令牌类型和签名算法。
- **Payload**：包含声明（claims），例如用户信息、过期时间等。
- **Signature**：对 Header 和 Payload 的签名，用于验证令牌的完整性。

#### 2. **JWT 的工作流程**
1. **生成 Token**：
   - 用户登录成功后，服务器使用密钥（`KEY`）生成一个 JWT，并将用户信息（`claims`）存储在 Payload 中。
   - 生成的 JWT 返回给客户端（通常是前端）。

2. **客户端存储 Token**：
   - 客户端将 JWT 存储在本地（通常是 `localStorage` 或 `sessionStorage`）。

3. **发送 Token**：
   - 客户端在每次请求时，将 JWT 放在请求头（通常是 `Authorization` 头）中发送给服务器。

4. **验证 Token**：
   - 服务器接收到请求后，使用相同的密钥验证 JWT 的签名，并解析 Payload 中的用户信息。
   - 如果验证通过，服务器处理请求；否则，返回错误。

---

- **浏览器会根据服务器的响应自动处理一些状态码，例如：**
  - **`200 OK`：请求成功。**
  - **`404 Not Found`：资源未找到。**
  - **`500 Internal Server Error`：服务器内部错误。**
- **但是，浏览器并不会自动生成与业务逻辑相关的状态码（如 `401 Unauthorized`），这些状态码需要服务器明确设置。**

| 特性         | `@RestController`                                        | `@Controller`                                        |
| :----------- | :------------------------------------------------------- | :--------------------------------------------------- |
| **默认行为** | 所有方法的返回值直接写入响应体（无需 `@ResponseBody`）。 | 需要显式使用 `@ResponseBody` 或将返回值解析为视图。  |
| **适用场景** | 适合构建 RESTful API，返回 JSON/XML 数据。               | 适合传统的 MVC 应用，返回视图（如 JSP、Thymeleaf）。 |
| **注解组合** | `@Controller` + `@ResponseBody`。                        | 单独使用，通常与 `@ResponseBody` 或视图解析器配合。  |

### 前后端代码协同入门

~~~js
<template>
  <div>
    <button @click="getUserProfile">获取用户信息</button>
  </div>
</template>

<script> 
import axios from 'axios';

export default {
  methods: {
    getUserProfile() {
      const token = localStorage.getItem('token'); // 从本地存储获取 token
      axios.get('/api/user/profile', {
        headers: {
          'Authorization': `Bearer ${token}` // 手动添加 Authorization 头
        }
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
    }
  }
};
</script>
~~~

## 拦截器写法

![image-20250119000929994](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250119000929994.png)

**webconfig** 为了让拦截器生效，你需要在 Spring Boot 中配置拦截器，并指定它拦截哪些请求





- 拦截器的拦截范围取决于配置中的 `addPathPatterns` 和 `excludePathPatterns`。
- 如果你希望拦截器只拦截 **部分请求**，可以通过配置实现。

~~~java
package com.senjay.config;

import com.senjay.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不能拦截所有的请求
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register"); // 排除一些登录、注册接口

    }
}

~~~

**拦截器：**作用就是如果**都在controller里**==权限判断==的话每次都要写==重复的代码==违反了设计模式

~~~java
package com.senjay.interceptors;

import com.senjay.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token= request.getHeader("Authorization");

        try {
            Map<String,Object> claims= JwtUtil.parseToken(token); // 解析token
            return true;
        } catch (Exception e) {
            // 不放行
            response.setStatus(401);
            return false;
        }

    }
}

~~~

### **拦截器**

**Spring Boot 的拦截器（`HandlerInterceptor`）** 的工作原理是 **针对控制器（Controller）的请求** 进行拦截和处理

- 如果你配置了多个拦截器，它们会按照 **配置的顺序** 依次执行。

  

  ```java
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(interceptor1).addPathPatterns("/**");
      registry.addInterceptor(interceptor2).addPathPatterns("/**");
  }
  ```

  在这种情况下，`interceptor1` 会先执行，然后是 `interceptor2`。

  ---

  

| **方法**              | **执行时机**                                        | **用途**                                 |
| :-------------------- | :-------------------------------------------------- | :--------------------------------------- |
| **`preHandle`**       | 在请求到达控制器 **之前** 执行。                    | 通常用于身份验证、日志记录、权限检查等。 |
| **`postHandle`**      | 在控制器处理请求 **之后**，视图渲染 **之前** 执行。 | 通常用于修改模型数据或视图。             |
| **`afterCompletion`** | 在请求完成 **之后**（即视图渲染完成之后）执行。     | 通常用于资源清理、日志记录等。           |

- 拦截器只会拦截 **控制器（Controller）的请求**，不会拦截静态资源（如 CSS、JS、图片等）。
- 如果你希望拦截所有请求（包括静态资源），需要使用 **过滤器（Filter）**。

**---> 这里就能体现拦截器和过滤器的区别了**

**`addPathPatterns`**

- **`/**`**：匹配所有路径。
- **`/api/**`**：匹配 `/api` 路径下的所有请求。
- **`/public/*`**：匹配 `/public` 路径下的单层路径（如 `/public/data`，但不包括 `/public/data/1`）。
- **`/admin/**`**：匹配 `/admin` 路径下的所有请求（包括多层路径）。

==如果只有excludePathPatterns，就会拦截所有路径，除了 `excludePathPatterns` 中指定的路径。==



## ThreadLocal

小tips ： **name.sout** 更快打印  **name.var** 快速前置赋值声明 

**ctrl alt T**   快速if 异常代码块



---



![image-20250119133633306](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250119133633306.png)

### **1. 内存泄露（Memory Leak）**

内存泄露是指程序在运行过程中，未能正确释放不再使用的内存，导致内存占用不断增加，最终可能耗尽系统内存，影响程序性能甚至崩溃。

#### **内存泄露的原因**

- **未释放动态分配的内存**：例如在 C/C++ 中，使用 `malloc` 或 `new` 分配内存后，未调用 `free` 或 `delete` 释放。
- **对象引用未清除**：例如在 Java 或 Python 等有垃圾回收机制的语言中，对象被意外持有（如静态集合类、监听器等），导致垃圾回收器无法回收。
- **缓存未清理**：缓存中的数据长期未使用，但未设置过期时间或清理机制，导致内存占用不断增加。

#### **内存泄露的影响**

- 内存占用持续增长，最终可能导致 `OutOfMemoryError`（内存溢出）。
- 程序性能下降，甚至崩溃。

------

### **2. 清空缓存**

清空缓存是指主动删除缓存中的数据，以释放内存或**确保数据一致性**。

#### **清空缓存的场景**

- **内存不足**：当系统内存紧张时，**清空缓存**可以释放内存供其他程序使用。
- **数据过期**：缓存中的数据已过期或失效，需要清理。
- **数据一致性**：**缓存中的数据与源数据不一致**，需要清空缓存并重新加载。



---



**每个 HTTP 请求由一个独立的线程处理，`ThreadLocal` 可以用于存储当前请求的用户信息，避免在多线程环境下出现数据混乱。**

`ThreadLocal` 可以避免在**方法之间频繁传递参数**，简化代码逻辑  

~~~java
public class LogContext {
    private static final ThreadLocal<String> requestId = new ThreadLocal<>();

    public static void setRequestId(String id) {
        requestId.set(id);
    }

    public static String getRequestId() {
        return requestId.get();
    }

    public static void clear() {
        requestId.remove();
    }
}
~~~



~~~java
/**
 * ThreadLocal 工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    //提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal(); // 创建唯一全局变量（所有实例公用）

    //根据键获取值
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
	
    //存储键值对
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }


    //清除ThreadLocal 防止内存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
~~~

![image-20250119140610757](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250119140610757.png)

~~~java
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token= request.getHeader("Authorization");// Authorization 来自请求头

        try {
            Map<String,Object> claims= JwtUtil.parseToken(token); // 解析token
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            // 不放行
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}




  @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name="Authorization") String token) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        return Result.success(userService.findUserByUsername(username));
        // 这里的token是前端传过来的
    }
~~~

注意在前端调用这个接口的时候不需要传递参数 why：
**因为我已经在前端拦截器中定义了全局的请求头token参数 所以不需要额外传递**



## 更新数据



```ABAP
@RequestBody 参数类型 参数名
@RequestBody 用于将 HTTP 请求体中的数据绑定到方法参数上
绑定：HTTP 请求体中的 JSON 数据映射到一个自定义的 Java 对象（POJO）
```

| POST     | PUT            |                |
| :------- | :------------- | -------------- |
| **语义** | 创建或提交数据 | 更新或替换资源 |



为什么不能这样

问题：1.首先不是post请求 

2.写业务逻辑尽量写在service层不是controller层 controller 是调用封装好的方法的！！

3.这样子更新只是更新了对象数据库中的数据都没有更改

~~~java
    @Override
    public void update(User user) {
        if(userMapper.findUserById(user.getId())!=null)
        userMapper.update(user);
        else
            throw new RuntimeException("用户不存在");
    }
}
~~~

**通过抛出异常处理特殊情况**

~~~java
    @PostMapping("/update")
    public Result update(@RequestBody User user ) {
        User currentUser = userService.findUserByUsername(user.getUsername());
        currentUser.setNickname(user.getNickname());
        currentUser.setEmail(user.getEmail());
        return Result.success();

    }
~~~

**注意事项；**

==在 `#{}` 中，可以直接写属性名（如 `#{nickname}`），MyBatis 会自动调用对应的 getter 方法（如 `getNickname()`）。==

```java
@Update("update user set nickname= #{user.getNickname()}, update_time = now(), email = #{user.getEmail()} where id = #{id}")
void update(User user);
```

- **`user` 是方法的唯一参数，MyBatis 会将其作为根对象。**
- `#{user.getNickname()}` 和 `#{user.getEmail()}` 中的 `user` 是方法参数 `User user`，MyBatis 会自动解析。
- 因此，不需要写 `User.`，直接写 `user.getNickname()` 即可。

**如果方法有多个参数**

如果方法有多个参数，需要使用==@Param 注解==明确指定参数名。例如：



```java
void update(@Param("user") User user, @Param("id") int id);
```

对应的 SQL 注解：

```java
@Update("update user set nickname= #{user.nickname}, update_time = now(), email = #{user.email} where id = #{id}")
void update(@Param("user") User user, @Param("id") int id);
```

## 其中参数校验

![image-20250119151728474](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250119151728474.png)



| 特性         | `@NotNull`             | `@NotEmpty`                                  |
| :----------- | :--------------------- | :------------------------------------------- |
| **校验内容** | 只检查是否为 `null`    | 检查是否为 `null` 或空（如空字符串、空集合） |
| **适用类型** | 所有类型               | 字符串、集合、数组等                         |
| **空字符串** | 通过（不检查空字符串） | 不通过（空字符串会失败）                     |
| **空集合**   | 通过（不检查空集合）   | 不通过（空集合会失败）                       |

~~~java
    @JsonIgnore //当将一个 Java 对象转换为 JSON 时，标记了 @JsonIgnore 的字段会被忽略，不会出现在生成的 JSON 数据中。
    private String password;//密码


    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user ) {
        userService.update(user);
        return Result.success();
    }

// 要添加 @Validated 这个实体类的成员变量检验才会生效
~~~

**校验的核心：是前端往后端参数传数据 后端接收的时候对这些数据的判断看看要不要接收**

- **触发时机**：与其他注解（如 `@NotNull`）一样，在==请求参数绑定==（接收阶段）或手动调用校验时触发。
- **校验失败**：如果校验失败，会抛出相应的异常。**就不会绑定也就不会执行后端代码后面的逻辑了**

## Put 和Patch 以及url参数验证

### **`PUT` 和 `PATCH` 的语义区别**

- **`PUT`**：
  - 语义是**替换整个资源**。
  - 客户端应该提供资源的完整表示，服务器会用请求体中的数据完全替换现有资源。
  - 如果客户端没有提供某些字段，服务器通常会将这些字段设置为 `null` 或默认值。
- **`PATCH`**：
  - 语义是**部分更新资源**。
  - 客户端只需要提供需要更新的字段，服务器会根据请求体中的数据部分更新资源。
  - 未提供的字段保持不变。

**如果 `PUT` 请求没有提供某些字段的值，而你在数据库的更新 SQL 语句中明确写了这些字段的更新逻辑，那么这些字段会被更新为你指定的默认值或逻辑值**

~~~java
    @PatchMapping("/updateAvatar")
    public  Result<String> updateAvatar( @URL String avatarURL) {
        userService.updateAvatar(avatarURL);
        return Result.success(avatarURL);
    }
// 也可以 -->  @URL 也可以这样
    private String userPic;//用户头像地址 然后到时候加个@Validation就可以
~~~

![image-20250120101954883](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120101954883.png)

~~~java
    
// 不确定方法体绑定到哪就绑定到集合
@PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody  Map<String,String>params) //  参数校验怎么办
    {
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");
        if(!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd))
            return Result.error("请输入完整信息");
        userService.updatePwd(old_pwd,new_pwd,re_pwd);
        return Result.success("修改成功");
    }

}

~~~

sql基础：

~~~sql
constraint fk_category_user
    foreign key (create_user) references user (id)
constraint fk_category_user：定义一个名为 fk_category_user 的外键约束。

foreign key (create_user)：指定 create_user 字段为外键。

references user (id)：外键引用 user 表的 id 字段，表示 create_user 的值必须存在于 user 表的 id 字段中。
~~~

`@Validated` 加在类上时，表示该类中的所有方法都支持 **方法级别的校验**



~~~java
// 添加文章分类
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result addCategory(@RequestBody @Validated Category category) {
        Map<String,Object> mp = ThreadLocalUtil.get();
        Integer id = (Integer) mp.get("id");
        category.setId(id);
        categoryService.add(category);
 // 相比于一直传方法体参数会简便一点  直接传封装好的对象过去
        // categoryService.add(category.getCategoryName(),category.getCategoryAlias(),id);
        // categoryMapper.add(category.getCategoryName(),category.getCategoryAlias(),id)
        return Result.success();
    }
}
~~~

**同样的请求路径但是请求方法不一样的话就是不同接口**

## 分组校验

问题就是如果

```java
@NotNull
private Integer id;//主键ID
```

```java
public Result addCategory(@RequestBody @Validated Category category) 
// 新增的不需要传id因为是数据库自动增长的但是以下的更新要id 因为要查询
 // 所以这样就会矛盾
    
public Result updateCategory(@RequestBody @Validated Category category) 
```

![image-20250120172647384](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120172647384.png)

![image-20250120173029079](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120173029079.png)



~~~java
package com.senjay.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotNull(groups = Update.class) // 这个校验单独属于修改分组
    private Integer id;//主键ID

    @NotEmpty //没指定就是属于默认分组
    private String categoryName;//分类名称

    @NotEmpty
    private String categoryAlias;//分类别名

    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    public interface Add extends Default {
    }
    public interface Update extends Default{
    }
    // 新增和更新（修改)分组
}

~~~

==**注意点：**==

**但是这样违背了设计模式不重复原则 所以是不是还有别的方法呢**

**注意在进行mapper接口编写时注意是对那张表进行操作**

**反正在进行修改 只要是修改不是add 都要注意 就是 update啊 delete啊这样的**

`DELETE` 语句的作用是删除满足条件的记录。如果指定的 `id` 在数据表中不存在，`DELETE` 语句不会报错，而是会 **静默执行**，即不会删除任何数据，也不会抛出异常。



~~~java

// 注意参数列表 不止一个是要注意 不然就在service实现层将绑定的对象setCreateUser设置这样就不用传多个参数了

@Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
    "VALUES(#{article.title}, #{article.content}, #{article.coverImg}, #{article.state}, #{article.categoryId}, #{createUser},now(),now())")
    public void add(Article article,Integer createUser);

~~~

### 自定义校验（使用到了==自定义注解==）

![image-20250120185604563](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120185604563.png)



![image-20250120191836890](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120191836890.png)

~~~java
package com.senjay.annotation;

import com.senjay.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// 注解文件中不写逻辑 而是在其中定义的类中实现
@Documented // 注解文档
// 元注解
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
// 元注解
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class}) // 指定提供校验规则的类
public @interface State {
    String message() default "状态必须是草稿或是已发}"; // 校验失败信息

    // 指定分组
    Class<?>[] groups() default {};
    //负载 获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}

~~~



~~~java
package com.senjay.validation;

import com.senjay.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// <给哪个注解提供校验规则，校验的数据类型>
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      // 提供校验规则
        // s就是将来要检验的数据
        // constraintValidatorContext 是封装校验信息的对象
        if(s == null)
            return false;
        if(s.equals("草稿") || s.equals("已发布"))
            return true;
            return false;

    }
}

~~~



~~~java
    @NotEmpty
    @State
    private String state;//发布状态 已发布|草稿

~~~







## 外键的注意事项：

在 `article` 表中插入数据时，`category_id` 字段的值在 `category` 表中不存在，因此违反了**外键约束**

- 外键约束用于维护表与表之间的数据完整性。
- 它确保子表中的外键值必须引用父表中的主键或唯一键。
- 通过外键约束，可以防止无效数据的插入、更新和删除



## 条件分页问题（涉及动态SQL）

### 对于queryString非必须的参数问题

![image-20250120211440041](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120211440041.png)

### **分页查询的作用**

- **提高性能**：避免一次性加载大量数据，减少数据库和网络的负载。
- **提升用户体验**：用户可以通过分页浏览数据，而不是一次性加载所有数据。
- **节省资源**：减少内存占用和带宽消耗。

**动态SQL：前端传过来的数据不是写死的是有条件的查询**



导入pagehelper插件

~~~xml
<dependency>
          <groupId>com.github.pagehelper</groupId>
          <artifactId>pagehelper-spring-boot-starter</artifactId>
          <version>1.4.6</version>
      </dependency>
~~~

MyBatis 的注解（如 `@Select`、`@Insert`、`@Update`、`@Delete`）主要用于定义简单的 SQL 语句。它们不支持直接编写动态 SQL（如 `if`、`choose`、`foreach` 等）。

所以要使用到==xml mapper 映射文件==了 

这个映射的注意要点 :**1.在resource目录下创建和mapper包结构一样的mapper目录**

**2.将对应的mapper映射文件放到mapper目录下**



~~~java
// controller   
@GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);

        return Result.success(pb);

    }
~~~



~~~java
 // service实现层

@Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1.创建pageBean对象
        PageBean<Article> pb = new PageBean<>();
        // 2.开启分页查询
        PageHelper.startPage(pageNum,pageSize); // 传入参数：当前页码，每页显示条数
        // 3.调用mapper
        Map<String,Object> mp = ThreadLocalUtil.get();
        Integer createUser = (Integer) mp.get("id");
        // 用户只能看到自己创建的文章
        List<Article> la = articleMapper.list(createUser,categoryId,state);// 不需要传入当前页码和每页显示条数，pageHelper已经自动传入会拼接到sql语句中 重要！！
        Page<Article> p = (Page<Article>) la; // 强转使用page的方法

        // 利用page 填充pb
        pb.setItems(p.getResult()); // 返回一个list集合
        pb.setTotal(p.getTotal());
        return pb;
    }
~~~

![image-20250120233932676](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250120233932676.png)

**就和之前mybatis一样**

**xml映射文件**  最重要的是==动态SQL==的拼接规则

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
       PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
       "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.senjay.mapper.ArticleMapper">
<select id="list" resultType="com.senjay.pojo.Article">
    select * from article
    <where>
       <if test="categoryId != null">
          category_id = #{categoryId}
       </if>
       <if test="state!=null">
          and state = #{state}
       </if>
         <if test="1 == 1">
          and    create_user = #{createUser}
        </if>
    </where>
</select>
</mapper>
```

**if 包裹最后一个也是可以的**



**PageBean**

~~~java
package com.senjay.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private Long total;//总条数
    private List<T> items;//当前页数据集合
}
~~~





---





### 1. **PageHelper 是什么？**

**PageHelper** 是一个基于 MyBatis 的分页插件，它可以简化分页查询的实现。通过 PageHelper，你无需手动编写分页 SQL（如 `LIMIT` 和 `OFFSET`），只需在查询前调用 `PageHelper.startPage` 方法，PageHelper 会自动修改 SQL 语句并返回分页结果。

------

### 2. **`PageHelper.startPage(pageNum, pageSize)` 的作用**

`PageHelper.startPage(pageNum, pageSize)` 用于设置分页参数，告诉 PageHelper 当前查询的页码和每页大小。

- **`pageNum`**：当前页码（从 1 开始）。
- **`pageSize`**：**每页显示的记录数**。

调用该方法后，PageHelper 会拦截接下来的 SQL 查询，并自动添加分页逻辑。

```java
PageHelper.startPage(1, 10); // 查询第 1 页，每页 10 条记录
List<Article> articles = articleMapper.selectAll();
```

在这个例子中，`selectAll` 方法原本会返回所有数据，但由于调用了 `PageHelper.startPage`，PageHelper 会将其修改为分页查询，只返回第 1 页的 10 条记录。



# 文件上传 

## 文件本地上传

![image-20250121093551564](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121093551564.png)

**字符串随机拼接后缀**这样就不会出现同名上传不了的情况了（**多个用户有可能上传的文件的名字都一样**）



## 对象存储服务 OSS



![image-20250121093842339](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121093842339.png)

![image-20250121094116617](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121094116617.png)

![image-20250121102206466](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121102206466.png)



### 将==示例代码==改造为工具类（定义静态方法直接通过类去调用不用创建实例）使用 

~~~java
package com.senjay.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.InputStream;

public class AliOssUtil {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
    // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
    // 填写Bucket名称，例如examplebucket。
    private static final String BUCKET_NAME = "bigeventforwsj";
    // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
  		<slot1>
        <slot2>

    // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
    private static final String region = "cn-beijing";
    public static String uploadFile(String objectName, InputStream in) throws Exception {
        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url = "";
        try {
            // 填写字符串。
            String content = "hello world!";

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, in);

            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            //urL组成：https://bucket名称.区域节点/objectName
            url = "https://" + BUCKET_NAME + "." + ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1) + "/" + objectName;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            return url;
        }
    }
}

~~~

<slot1>   private static final String ACCESS_KEY_ID = "";

<slot2>  private static final String ACCESS_KEY_SECRET = ""; 

**此为敏感信息**



~~~java
package com.senjay.controller;

import com.senjay.pojo.Result;
import com.senjay.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("D:\\Desktop\\files\\" + file.getOriginalFilename()));
       String url =  AliOssUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}

~~~

![image-20250121112038496](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121112038496.png)



## Redis

**问题：**修改密码时然后重新登录会获取一个新的token 但是此时的旧token并没有销毁（如果还没有过期的话） 所以使用旧令牌还是可以正常通过拦截器 ！！

![image-20250121120933738](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121120933738.png)





![ ](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121121129143.png)

**为什么 Redis 的配置方式不同？**
**数据存储方式：**

MySQL 是磁盘存储的关系型数据库，数据以表的形式存储。

Redis 是内存存储的键值数据库，数据以键值对的形式存储。

**协议和连接方式：**

MySQL 使用 JDBC 连接，通过 SQL 语句操作数据。

Redis 使用 Redis 协议，通过 TCP/IP 连接，使用特定的命令（如 SET、GET）操作数据。

**用途：**

MySQL 用于持久化存储，适合存储重要且需要长期保存的数据。

Redis 用于缓存或临时存储，适合存储需要快速访问的数据（如会话信息、热点数据）。

**配置方式：**

MySQL 需要配置 JDBC 驱动、连接 URL、用户名和密码。

Redis 只需要配置主机和端口，因为它通常不需要用户名和密码（除非启用了认证）。

![image-20250121155750283](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121155750283.png)

![image-20250121155753337](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121155753337.png)



![image-20250121155802182](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121155802182.png)



**注意点：**

对于要不要 对一些不是自己创建的东西进行增删查改操作进行判断 ：如果展示的时候判断了（这样就只会展示用户自己创建的东西） 后面就无需再判断 因为用户此时只能对自己所能看到**的东西进行操作**

**只有在大量传参的时候要考虑是否进行参数检验**



# Springboot项目部署

![image-20250121165208345](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121165208345.png)



![image-20250121165216041](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121165216041.png)

**有些依赖/插件可能会和jdk版本出现冲突**

项目打包成jar包后使用命令行java -jar jar包位置 运行这个java程序 

**注意 ： 服务器的jdk版本和打包时使用的jdk版本要兼容** 

target  java -jar WSJ-1.0-SNAPSHOT.jar
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.UnsupportedClassVersionError: org/springframework/boot/loader/JarLauncher has been compiled by a **more recent version of the Java Runtime (class file version 61.0),** this version of the **Java Runtime** ==only== r**ecognizes class file versions up to 52.0** 

---



**如何使用docker打包呢！！！！ 运行环境的问题太麻烦了！！**



---

## Springboot 属性配置

![image-20250121172739873](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121172739873.png)

运维无法修改通过yml文件修改配置信息 因为已经打包成jar包了



![image-20250121172857995](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121172857995.png)



![image-20250121173007146](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121173007146.png)

**优先级越来越高**



![image-20250121173146298](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121173146298.png)

## Springboot多环境开发

###### 环境：开发、测试、生产环境

![image-20250121183142845](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121183142845.png)

1. 使用 application-{profile}.yml 文件
Spring Boot 支持通过 application-{profile}.yml 文件来管理不同环境的配置。例如：

application-dev.yml：开发环境

application-test.yml：测试环境

application-prod.yml：生产环境

2. 激活配置文件
通过 spring.profiles.active 属性**激活特定环境**的配置文件。可以在 **application.yml 中设置默认环境**，或通过命令行、环境变量等方式指定。

3. 公共配置抽取
**将各环境共用的配置放在 application.yml 中，环境特定的配置放在 application-{profile}.yml 中。**

## Axios(异步请求)

 **就是对原生的ajax进行了封装 简化了语法的书写**

![image-20250121202720215](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121202720215.png)

**result**代表服务器响应的所有的数据，包含了**响应头，响应体**，**result.data**代表的是接口响应的**核心数据**

![image-20250121203315937](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250121203315937.png)

###  **`axios.get` 方法**

- **用途**：用于发送 GET 请求，通常用于获取数据。
- **参数**：
  - **URL**：请求的地址。
  - **config**（可选）：配置对象，可以包含 `params`、`headers` 等。



### **`axios.post` 方法** 

**注意这些请求参数的默认值**

- **用途**：用于发送 POST 请求，通常用于提交数据。
- **参数**：
  - **URL**：请求的地址。
  - **data**（可选）：==请求体数据==。 **`POST` 方法的第二个参数（即请求体数据 `data`）的默认格式是 JSON 格式**
  - **config**（可选）：配置对象，可以包含 ``params`、`headers`` 等。

POST 请求的第二个参数通常是请求体（Request Body），但请求体的格式可能有所不同，具体取决于 **`Content-Type` 和请求的具体内容**。根据请求的类型，Spring 会选择不同的方式来解析请求体。在文件上传和普通 JSON 请求之间有一个很重要的区别。

至于content-type 如果没有设置的话默认就是根据数据类型

params 就是==查询参数== 也就是**queryString**

**示例：**

~~~js
axios.post('/api/submit', {
  username: 'alice',
  password: '123456'
}, {
  params: {
    type: 'user'
  }
});
~~~



![image-20250122000056161](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122000056161.png)





![image-20250122000035155](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122000035155.png)





![image-20250122000026582](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122000026582.png)

---



### 异步的核心特点

1. **非阻塞**：
   - 请求发起后，代码继续执行，不会等待请求完成。
2. **回调机制**：
   - 通过回调函数（如 `.then()` 或 `await`）处理请求结果。
3. **事件驱动**：
   - 请求完成后，系统触发事件通知代码处理响应。
4. **提高效率**：
   - 异步机制允许同时处理多个任务，避免资源浪费。

### 需要同步等待的场景

初始化数据：在页面加载时，需要先获取某些数据，然后才能渲染页面或执行后续逻辑。

表单提交：在提交表单之前，需要先验证某些数据是否合法，而这些数据需要通过异步请求获取。

依赖请求结果：后续逻辑依赖于某个异步请求的结果，必须等待请求完成才能继续执行。

顺序请求：多个请求需要按顺序执行，后一个请求依赖于前一个请求的结果。

---

- `articleGetAllService` 是一个返回 `Promise` 的函数，因为它使用了 `axios.get`，而 `axios.get` 是异步的。

- `articleGetAllService` 返回一个 `Promise` 对象，而不是实际的数据。
- `articleList.value` 被赋值为一个 `Promise` 对象，而不是实际的文章数据。
- Vue 的响应式系统会检测到 `articleList.value` 的变化（从 `[]` 变为 `Promise` 对象），但由于 `Promise` 对象本身并不是有效的数据，页面不会显示任何有意义的内容。

---



 **后台响应数据后会发生什么？**

即使后台在某个时间点返回了数据，**由于你没有使用 `await` 或 `.then()` 来处理异步操作**，V**ue 不会自动知道数据已经返回**。因此：

- 数据会被返回，但不会被赋值给 `articleList.value`。
- `articleList.value` 仍然是一个 `Promise` 对象。
- 页面不会重新渲染，因为 Vue 没有检测到有效的数据变化。



### async 和await

- **同步**：**Synchronous**
- **异步**：**Asynchronous**
- **`async`**：用于声明一个异步函数。异步函数总是返回一个 `Promise` 对象。
- **`await`**：用于等待一个 `Promise` 解决（resolve）或拒绝（reject）。`await` 只能在 `async` 函数中使用。

**注意await只能使用在异步的函数中**

![image-20250122163033052](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122163033052.png)

**await就是同步等待 加在要同步的操作前面**

![image-20250122163328012](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122163328012.png)

**这里是因为在Article.vue中articleGetAllService还是一个异步方法所以要await 但是await要在异步函数内所以要包裹一层的异步嵌套**

<span style="color:#FF0000;">在 `articleGetAllService` 函数中，`axios.get` 是一个异步操作，返回一个 `Promise`。如果你不加 `await`，`articleGetAllService` 会立即返回一个 `Promise`，而不是实际的数据。</span>



**异步方法嵌套想要同步的逻辑代码 这个同步的代码没有执行完是不会跳出这个异步的函数的**

使用baseURL 简化重复性劳动

![image-20250122164231464](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122164231464.png)

**封装request.js**   (在util目录下)

![image-20250122165417350](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122165417350.png)

## 拦截器(统一操作)

### **设置请求拦截器**

请求拦截器会在请求发送前执行，通常用于添加请求头或修改请求数据。

```js
import axios from 'axios';

// 创建 axios 实例
const instance = axios.create({
    baseURL: 'https://api.example.com', // 设置基础 URL
    timeout: 5000, // 设置超时时间
});

// 添加请求拦截器
instance.interceptors.request.use(
    (config) => {
        // 在发送请求之前做些什么
        console.log('请求拦截器 - 请求配置:', config);

        // 添加请求头（例如身份验证 Token）
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config; // 必须返回 config
    },
    (error) => {
        // 对请求错误做些什么
        console.error('请求拦截器 - 请求错误:', error);
        return Promise.reject(error);
    }
);
```

这里的config 是什么呢 ？

**每个通过该 Axios 实例发送的请求都会经过请求拦截器**，并带上你在拦截器中设置的参数。

#### 为什么？

- 请求拦截器是全局的，它会拦截所有通过该 Axios 实例发送的请求。
- 在拦截器中，你可以对 `config` 对象进行修改，这些修改会应用到所有请求中。

```js
if (tokenStore.token) {
    config.headers.Authorization = tokenStore.token;
}
```

这段代码会检查 `tokenStore` 中是否存在 `token`，如果存在，就会将 `token` 添加到请求头的 `Authorization` 字段中。因此，**每个请求都会自动带上这个 `Authorization` 头**（前提是 `token` 存在）



### ==顺序问题==

后端除了登录注册页面都配置了拦截器 只要没有token就是401 

前端根据请求拦截器携带token 然后响应拦截器判断status 如果是401就重定向到登录页面

~~~js
const login = async () => {
  // 规范化处理密码
  let result = await userLoginService(registerData.value)
  ElMessage.success("登录成功")
  tokenStore.setToken(result.data)
  // 要先将token存进去啊 不然调用userInfoGetService就会401 因为拦截器会去config中拿请求头
  let userInfo = await userInfoGetService() 
  userInfoStore.setUserInfo(userInfo.data)
  router.push('/')
}
~~~



------

### 3. **设置响应拦截器**

响应拦截器是在后端处理完请求并生成响应后，但在将响应返回给前端之前执行的。因此，它属于**响应后、返回前**的阶段。

```js
// 添加响应拦截器
instance.interceptors.response.use(
    (response) => {
        // 对响应数据做些什么
        console.log('响应拦截器 - 响应数据:', response);

        // 只返回响应数据中的 data 部分
        return response.data;
    },
    (error) => {
        // 对响应错误做些什么
        console.error('响应拦截器 - 响应错误:', error);

        // 统一处理 HTTP 错误状态码
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    console.error('未授权，请重新登录');
                    break;
                case 404:
                    console.error('请求的资源不存在');
                    break;
                case 500:
                    console.error('服务器内部错误');
                    break;
                default:
                    console.error('未知错误');
            }
        }

        return Promise.reject(error);
    }
);
```

------

### 4. **使用 `axios` 实例发送请求**

设置好拦截器后，你可以使用 `instance` 发送请求，拦截器会自动生效。

```js
// 发送 GET 请求
instance.get('/data')
    .then((data) => {
        console.log('请求成功:', data);
    })
    .catch((error) => {
        console.error('请求失败:', error);
    });

// 发送 POST 请求
instance.post('/data', { name: 'John', age: 30 })
    .then((data) => {
        console.log('请求成功:', data);
    })
    .catch((error) => {
        console.error('请求失败:', error);
    });
```

### **`api.js` 文件**

`api.js` 文件通常用于定义具体的 API 请求函数，例如：

- 封装每个具体的 API 请求。
- 调用 `request.js` 中配置好的 `axios` 实例。
- 组织和管理所有的 API 接口。

这边这个**API**就是和**后端交互**的接口 就是**发送请求request 的方法** 利用**request.js** 封装好的==拦截器==（全局处理） 和==axios实例==

---



- ### **`request.js` 文件**

  `request.js` 文件通常用于封装和配置网络请求的基础逻辑，例如：

  - 创建 `axios` 实例。
  - 设置全局的请求拦截器和响应拦截器。
  - 配置默认的请求头、超时时间等。
  - 统一处理错误。



<span style="color:#FF0000;">**这里就不用同步了在article.js (属于api.js)**</span>

![image-20250122170729370](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122170729370.png)

article 还是要处理异步await 


### 区别

![image-20250122172744783](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122172744783.png)

`articleGetAllService` 函数的设计目的是**立即处理请求的结果**，并将处理后的数据返回给调用方

![image-20250122172822129](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122172822129.png)

**所以在调用方处理请求的结果要在调用方await就行 也就是 Article.vue文件中**





## 前后端交互基础

### 跨域问题CrossOrigin

**跨域是对于浏览器的**

![image-20250122221658012](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122221658012.png)

![image-20250122221933345](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250122221933345.png)

**最好还是通过==nginx==也就是在后端层实现反向代理 这个前端的了解就好**



 **在user.js ==api接口==中**

~~~js
// 导入request工具
import request from '@/utils/request.js'

// 提供调用注册 后端接口 的函数 暴露在外
// 在api 一般都是以service结尾
// 注意箭头函数的写法（函数表达式）
export const userRegisterService = (registerData)=>{
  const params = new URLSearchParams()
  for(let key in registerData) {
    params.append(key,registerData[key])

  }
  return request.post('/user/register', params) // 要有post返回值

}  
~~~



**在login.vue 文件中点击注册触发函数 函数去使用暴露在外的api接口**

**注意要处理异步 同步等待**

~~~js
import {userRegisterService} from '@/api/user.js'
const register = async ()=>{
    let result = await userRegisterService(registerData.value) // registerData 是一个响应式对象 要获取具体值需要调用.value
    if(result.code === 0)
        alert(result.msg?result.msg:"注册成功")
    else
        alert(result.msg?result.msg:"注册失败")

}
~~~



**request.js工具**

~~~js
//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';

import { ElMessage } from 'element-plus'
//定义一个变量,记录公共的前缀  ,  baseURL
//const baseURL = 'http://localhost:8080';
const baseURL = '/api';
const instance = axios.create({ baseURL })

import {useTokenStore} from '@/stores/token.js'

// -----------------------------------------------
//添加请求拦截器
instance.interceptors.request.use(
    (config)=>{
        //请求前的回调
        //添加token
        const tokenStore = useTokenStore();
        //判断有没有token
        if(tokenStore.token){
            config.headers.Authorization = tokenStore.token
        }
        return config;
    },
    (err)=>{
        //请求错误的回调
        Promise.reject(err)
    }
)

// ----------------------------------------------------

 import {useRouter} from 'vue-router'
const router = useRouter(); 

 import router from '@/router'
//添加响应拦截器
instance.interceptors.response.use(
    result => {
        //判断业务状态码
        if(result.data.code === 0){
            return result.data;
        }

        //操作失败
        //alert(result.data.msg?result.data.msg:'服务异常')
        ElMessage.error(result.data.msg?result.data.msg:'服务异常')
        //异步操作的状态转换为失败
        return Promise.reject(result.data)
        
    },
    err => {
        //判断响应状态码,如果为401,则证明未登录,提示请登录,并跳转到登录页面
        if(err.response.status===401){
            ElMessage.error('请先登录')
            router.push('/login')
        }else{
            ElMessage.error('服务异常')
        }
       
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;
~~~



~~~html
   <el-link type="info" :underline="false" @click="isRegister = true;clearUserData()" >
                        注册 →
   </el-link>
vue一个事件执行多个操作 此时这个函数要加（）了
~~~

~~~js
const registerData = ref({
    username:"",
    password:"",
    rePassword:""
})
// 登录注册公用一个数据模型的问题 如果两个依次使用了 从注册到登录就会响应式的显示上一步的数据模型
// const loginData = ref({
//     username:"",
//     password:""
// })

注意vue中响应式数据要用ref包裹住
~~~



## 响应拦截器

- **响应拦截器是在后端处理完请求并生成响应后，但在将响应返回给前端之前执行的。因此，它属于**响应后、返回前**的阶段。**

**总之就是处理响应数据返回给前端**

~~~js
// import {useRouter} from 'vue-router'
// const router = useRouter()
// useRouter 只能在 Vue 组件的上下文中使用，不能在普通的 JavaScript 文件或模块中使用 而router 在vue和js中都可以使用
import router from '@/router';
// 添加响应拦截器
instance.interceptors.response.use(
    result => {
        //判断业务状态码
        if(result.data.code === 0){
            console.log(result)
            return result.data;
        }

        //操作失败
        // alert(result.data.msg?result.data.msg:'服务异常')
        ElMessage.error(result.data.message?result.data.message:'服务异常')

        //异步操作的状态转换为失败
        return Promise.reject(result.data)
        
    },
    err => {
        //判断响应状态码,如果为401,则证明未登录,提示请登录,并跳转到登录页面
        if(err.response.status===401){
            ElMessage.error('请先登录')
            router.push('/login')
        }else{
            ElMessage.error('服务异常')
        }
       
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

~~~

**`await` 的作用**：

- `await` 会暂停 `async` 函数的执行，直到后面的 `Promise` 完成。
- 如果 `Promise` 被 `resolve`，`await` 会返回 `resolve` 的值。
- 如果 `Promise` 被 `reject`，**`await` 会抛出错误，后续代码不会执行，然后进入catch （可以不写，因为在响应拦截器里写了） 。** **！！！！！！ 最重要**

- **进入 `err` 的场景**：**HTTP 错误状态码、网络错误、请求被取消、其他异常。**  ==后端可以返回状态码和前端协同==

**虽然会自动抛出错误但是不够灵活 所以要用到finally的情况还是手动处理异常**

~~~js
const addCategory = async () => {
  try {
    // 发送请求
    let result = await addCategoryService(categoryModel.value);

    // 如果请求成功，显示成功消息
    ElMessage.success(result.message ? result.message : '添加成功');
  } catch (error) {
      // 这里是捕获await 后面函数的异常
    // 如果请求失败，错误已经被响应拦截器处理，这里不需要额外处理
    // 如果你需要在这里处理特定的错误逻辑，可以添加代码
    console.error('添加分类失败:', error);
  } finally {
    // 无论请求成功还是失败，都会执行以下代码
    dialogVisible.value = false; // 关闭对话框
    articleCategoryList(); // 刷新列表
    categoryModel.value = { // 清空表单数据
      categoryName: '',
      categoryAlias: ''
    };
  }
};
~~~



![image-20250124214817959](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250124214817959.png)



传递对象 定义对象 let （不要无脑用const ） 包装对象->键值对  这样那边后端的接口也会以同样结构的对象去接收值（根据==键==）

~~~js
const articleList = async ()=>{
    let params ={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        // 对于可有可无的值
        categoryId:categoryId.value?categoryId:null,
        state:state.value?state.value:null  
        // 为什么不能传空字符串而要传null 呢 看后端接口具体实现
    }
    let result = await articleListService(params)
    articles.value = result.data.items 
    total.value = result.data.total

}
~~~

![image-20250125191740270](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250125191740270.png)

**get请求 默认参数传递方式: 参数会以 `query string` 的形式附加在 URL 后面！！！**

![image-20250125191924205](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250125191924205.png)

#### **适合直接查询的场景**

- 只需要查询单个表的字段。
- 数据量较小，查询频率较低。
- 对性能要求不高，代码简单优先。

#### **适合联查的场景**

- 需要同时查询多个表的字段。
- 数据量较大，查询频率较高。
- 对性能要求较高，希望减少数据库交互次数。
- 
- 需要利用数据库的优化能力（如索引、查询计划）。

### 通过一张表的id去查另一张表对应id的name（==await问题==）

以及涉及了数据模型的拓展 这里是没有catagoryName的 

**拓展主要是为了配合类库组件罢了**

~~~js
const articles = ref([
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "coverImg": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "categoryId": 2,
        "createTime": "2023-09-03 11:55:30",
        "updateTime": "2023-09-03 11:55:30"
    }
])
~~~



~~~js
const articleGetCategory = async (id)=>{

    let result = await articleGetCategoryNameService(id)
    return result.data.categoryName

}

const articleList = async ()=>{
    let params ={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        // 对于可有可无的值
        categoryId:categoryId.value?categoryId:null,
        state:state.value?state.value:null  
        // 为什么不能传空字符串而要传null 呢
    }
    let result = await articleListService(params)
    articles.value = result.data.items
    total.value = result.data.total
    for(let i = 0;i<articles.value.length;i++)
    {
        let article = articles.value[i] //提取
        article.categoryName = await articleGetCategory(article.categoryId) //数据模型扩展
        // 这里一定要加 await why？ 要等待拿到数据啊因为articleGetCategory函数本身就是一个异步函数
        // 而且这样加载会很丑 用户都看到一个个的信息跳出来
    }   


}
~~~

但是！！！**将请求放在循环里确实不是最佳实践**，尤其是在需要处理大量数据时，会导致以下问题：

1. **性能问题**:
   - 每次循环都会发起一次网络请求，导致请求次数过多，增加网络开销和服务器压力。
   - 如果数据量较大，可能会导致页面加载缓慢，甚至超时。
2. **代码可读性差**:
   - 代码逻辑分散，难以维护和调试。
3. **资源浪费**:
   - 每次请求都会占用客户端和服务端的资源，导致资源浪费。



### pinia核心（要知道这个技术有什么用）

~~~js
const currentUser = ref('')
// const getCurrentUser = ()=>{
//     currentUser.value = userInfoStore.userInfo.nickname  
// }
// getCurrentUser()
// TODO：直接在{{}} 中写pinia store里的数据 如果写函数 无法在不同页面修改数据的时候互相响应变化 pinia本质就是实现不同组件响应式数据共享的
// resolve：如上
~~~



~~~java
export const userRegisterService = (registerData)=>{
  const params = new URLSearchParams()
  for(let key in registerData) {
    params.append(key,registerData[key])

  }
  return request.post('/user/register', params) // post返回值

}  

export const userAvatarUpdateService = (avatarUrl)=>{
  // return request.patch('/user/updateAvatar?avatarUrl='+url)
  const params = new URLSearchParams()
  params.append('avatarURL',avatarUrl)
  return request.patch('/user/updateAvatar',params)
}

// url 这种参数 最好不要 ？xx=xx 这样
~~~

![image-20250126220156951](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250126220156951.png)
