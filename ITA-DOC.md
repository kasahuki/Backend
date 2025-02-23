<span style="font-size:1.9em; font-weight:bold; color:#CC0000;">Indoor True Artifact DOC</span>

# 项目recall

根据阿里巴巴《Java 开发手册》的分层规范，以下是各层方法命名的典型约定（注：手册会随版本更新调整，以下内容基于常见规范整理）：

------

### **1. Controller 层**

- **职责**：处理 HTTP 请求，参数校验，路由分发。

- **命名规范**：

  - **动词 + 名词**：直接体现 HTTP 操作语义，如 `add`、`remove`、`update`、`get`、`list`。
  - **避免冗余**：禁止出现 `getXxxInfo` 这类冗余命名，直接用 `getXxx`。
  - **RESTful 风格**：与 HTTP Method 对应（如 `@PostMapping → createXxx`）。

- **示例**：

  

  ```java
  @PostMapping("/users")
  public Result<UserDTO> createUser(@RequestBody UserCreateRequest request) { ... }
  
  @GetMapping("/users/{id}")
  public Result<UserDTO> getUser(@PathVariable Long id) { ... }
  
  @GetMapping("/users")
  public Result<List<UserDTO>> listUsers(UserQueryCondition condition) { ... }
  ```

------

### **2. Service 层（接口层）**

- **职责**：定义业务逻辑接口，不涉及具体实现。

- **命名规范**：

  - **业务语义优先**：方法名需直接体现业务场景，如 `submitOrder`、`auditProject`。
  - **避免技术术语**：禁止出现 `Manager`、`Processor` 等无业务含义的后缀。
  - **动作 + 领域对象**：如 `createOrder`、`cancelOrder`。

- **示例**：

  

  ```java
  public interface OrderService {
      OrderDTO createOrder(OrderCreateCommand command);
      void cancelOrder(Long orderId);
      PageResult<OrderDTO> queryOrders(OrderQuery query);
  }
  ```

------

### **3. ServiceImpl 层（实现层）**

- **职责**：实现 Service 接口，处理具体业务逻辑。

- **命名规范**：

  - **与接口严格一致**：实现类方法名必须与接口完全一致。
  - **事务控制**：涉及数据库操作的方法需添加 `@Transactional`。
  - **参数转换**：需将 DTO 转换为领域对象（DO）后再操作。

- **示例**：

  

  ```java
  @Service
  public class OrderServiceImpl implements OrderService {
      @Override
      @Transactional
      public OrderDTO createOrder(OrderCreateCommand command) {
          OrderDO orderDO = convertToDO(command);
          orderMapper.insert(orderDO);
          return convertToDTO(orderDO);
      }
  }
  ```

------

### **4. Mapper 层（DAO 层）**

- **职责**：直接与数据库交互，执行 SQL 操作。

- **命名规范**：

  - **CRUD 语义**：方法名需明确体现操作类型（`insert`、`deleteById`、`updateXxx`、`selectXxx`）。
  - **查询方法前缀**：单条用 `select`，列表用 `list`，分页用 `page`，统计用 `count`。
  - **条件命名**：按查询条件明确命名（如 `selectByUserIdAndStatus`）。

- **示例**：

  

  ```java
  public interface UserMapper {
      int insert(UserDO userDO);
      int deleteById(Long id);
      int updateStatusById(@Param("id") Long id, @Param("status") String status);
      UserDO selectById(Long id);
      List<UserDO> listByStatus(String status);
      Page<UserDO> pageByCondition(UserQueryCondition condition);
  }
  ```

------

### **关键注意事项**

1. **分层隔离**：
   - Controller 禁止直接调用 Mapper，必须通过 Service 层。
   - Service 方法需体现业务聚合性，避免“透传”式调用（如直接转发 Controller 参数到 Mapper）。
2. **参数与返回对象**：
   - Controller 使用 DTO（数据传输对象）与前端交互。
   - Service 层内部使用 DO（领域对象）或 Command/Query 对象。
3. **命名一致性**：
   - 相同操作在不同层需保持语义一致（如 `create` → `insert` → `save` 应统一）。
4. **避免过度设计**：
   - 简单 CRUD 场景无需刻意设计复杂领域方法（如直接使用 `UserService.getById` 而非 `UserService.fetchUserEntityById`）。

------

### **完整调用链示例**

```
// Controller
@PostMapping("/orders")
public Result<OrderDTO> createOrder(@RequestBody OrderCreateRequest request) {
    return orderService.createOrder(request.toCommand());
}

// Service
public OrderDTO createOrder(OrderCreateCommand command) {
    OrderDO orderDO = convertToDO(command);
    orderMapper.insert(orderDO);
    return convertToDTO(orderDO);
}

// Mapper
public interface OrderMapper {
    int insert(OrderDO orderDO);
}
```

遵循这些规范可显著提升代码可读性和维护性，建议结合团队实际场景调整细节。

![image-20250221091557907](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221091557907.png)

![image-20250221091628122](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221091628122.png)

、![image-20250221091636861](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221091636861.png)

![image-20250221091644207](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221091644207.png)

**登录逻辑**

用户登录时 利用jwt生成token 然后拼接redis关键字 生成redis key 值就存当前用户的信息/或者可以使用claim也就是jwt提供的存储空间存当前用户的信息

当用户发起请求时，拦截器生效， 获取header的token 然后解析出claim 如果获取不到claim 就表示token失效 但是这样修改密码后会有bug 所以要配合redis 再加一层判断 如果根据token在redis中**获取不到相关数据**就表示token失效

然后再将当前用户信息存储到threadLocal中方便其他请求使用



**所以浏览器都是通过用户的唯一token来区分每次的http请求**



<span style="font-size:1.1em; color:#FF0000; font-weight:bold;">但就是有一个问题：</span>**如果一个登录的用户修改了自己的token为其他用户的token会怎么样**

签名验证：使用强加密算法（如 HMAC、RSA）签名令牌，防止内容篡改。

HttpOnly Cookie：阻止客户端 JavaScript 直接读取令牌，降低 XSS 攻击窃取风险。

令牌绑定上下文：将令牌与用户设备、IP 或地理位置关联，增加冒用难度。

短有效期 & 刷新机制：限制令牌生命周期，并强制定期刷新，减少泄露后的影响时间。

日志监控：记录异常登录行为（如多地同时活跃），触发警报或强制登出。





![image-20250221091802220](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221091802220.png)



~~~java
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }
~~~



~~~java
 public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }
~~~



**入门项目的注册/登录逻辑 （比较完善）**

！**前后端的校验问题TODO**

~~~java
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
    public Result<String> login(String username, String password) {
        User user = userService.findUserByUsername(username);
        if(user == null)
            return Result.error("用户不存在");
        else
        {
            if(Md5Util.checkPassword(password, user.getPassword()))
            {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id",user.getId());
                claims.put("username",user.getUsername());
                String token  = JwtUtil.genToken(claims);
                // 将这个token存入到redis中
                ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
                operations.set(token,token,12, TimeUnit.HOURS); // 在redis中的token存在周期 为12个小时

                return Result.success(token); // 将token作为响应体返回
            }
                // 此时对象里存的是加密过的密码
                return Result.error("密码错误或者用户名错误，请重新输入");
        }
    }
~~~



~~~java
 @Override
    public void register(String username, String password) {
        // 要加密密码！！
        String md5String = Md5Util.getMD5String(password); // 将密码进行md5加密后存储到数据库中
        userMapper.add(username, md5String);

    }
~~~

JWT 最常见的用途是身份认证。当用户登录到一个系统时，系统会生成一个 JWT 并将其返回给用户。用户可以将这个令牌保存在 **浏览器的 localStorage 或 cookie** 中，或者作为 HTTP 请求头的一部分在后续请求中发送。

就是生成token（令牌）

## 配置类的书写

~~~yaml
sky:
  jwt:
    # 设置jwt签名加密时使用的秘钥
    admin-secret-key: itcast
    # 设置jwt过期时间
    admin-ttl: 7200000
    # 设置前端传递过来的令牌名称
    admin-token-name: token

~~~



~~~java
package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.jwt")  // 两个作用 指名这个类是配置类 然后获取指定文件中的字段前缀
@Data
public class JwtProperties {  // 一下的属性就是对应配置文件中的属性

    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * 用户端微信用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}

~~~

Spring Boot 在解析 `application.yml` 或 `application.properties` 时，支持以下几种命名风格，并且能够自动进行转换：

| **YAML / Properties 格式** | **Java 变量格式** |
| -------------------------- | ----------------- |
| `admin-secret-key`         | `adminSecretKey`  |
| `admin.secret.key`         | `adminSecretKey`  |
| `ADMIN_SECRET_KEY`         | `adminSecretKey`  |



---



### **为什么要用 `builder()` 方式创建对象？**

在 Java 中，我们通常用 `new` 关键字创建对象：

```ceylon
EmployeeLoginVO vo = new EmployeeLoginVO();
vo.setId(employee.getId());
vo.setUserName(employee.getUsername());
vo.setName(employee.getName());
vo.setToken(token);
```

但如果类有很多字段，每次都 `setXXX()` 会让代码很冗长。
`Builder 模式` 可以 **链式调用**，让代码更清晰。

### **具体工作原理**

**EmployeeLoginVO.builder()**

- **`builder()`** 方法返回 `EmployeeLoginVO.Builder` 类的一个 **构造器对象**。

```ceylon
.id(employee.getId())
.userName(employee.getUsername())
.name(employee.getName())
.token(token)
```

- 这些方法 **链式调用**，设置各个字段的值。

```ceylon
.build();
```

- **`build()`** 方法 **返回** 一个 **真正的 `EmployeeLoginVO` 对象**。



## Redis的使用？

**JWT的使用**

```java
Map<String, Object> claims = new HashMap<>();
claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
String token = JwtUtil.createJWT(
        jwtProperties.getAdminSecretKey(),
        jwtProperties.getAdminTtl(),
        claims);
```

- 创建 `claims`（载荷）
  - `claims` 是 JWT 令牌中的 **用户信息部分（Payload）**，用于存储 **员工 ID**，后续可以用它来验证身份。
- 调用 `JwtUtil.createJWT(...)` 生成 JWT
  - `jwtProperties.getAdminSecretKey()` 获取 **JWT 的密钥**（用于签名和验证令牌）。
  - `jwtProperties.getAdminTtl()` 获取 **JWT 令牌的过期时间**。
  - `claims` 存储用户 ID 等信息，生成 **加密的 JWT 令牌**。

---







**自定义异常类**

~~~java
package com.sky.exception;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}

~~~



~~~java
package com.sky.exception;

/**
 * 账号不存在异常
 */
public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}

~~~

**全局异常handler**

~~~java
package com.sky.handler;

import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j  // debug 工具
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage()); // 打印日志 定位错误
        return Result.error(ex.getMessage()); // 返回给用户端错误信息 这个ex就是接收异常的msg信息
    }

}

~~~

## Contants 常量类解耦  -- 公用!

~~~java
package com.sky.constant;

/**
 * 信息提示常量类
 */
public class MessageConstant {

    public static final String PASSWORD_ERROR = "密码错误";
    public static final String ACCOUNT_NOT_FOUND = "账号不存在";
    public static final String ACCOUNT_LOCKED = "账号被锁定";
    public static final String UNKNOWN_ERROR = "未知错误";
    public static final String USER_NOT_LOGIN = "用户未登录";
    public static final String CATEGORY_BE_RELATED_BY_SETMEAL = "当前分类关联了套餐,不能删除";
    public static final String CATEGORY_BE_RELATED_BY_DISH = "当前分类关联了菜品,不能删除";
    public static final String SHOPPING_CART_IS_NULL = "购物车数据为空，不能下单";
    public static final String ADDRESS_BOOK_IS_NULL = "用户地址为空，不能下单";
    public static final String LOGIN_FAILED = "登录失败";
    public static final String UPLOAD_FAILED = "文件上传失败";
    public static final String SETMEAL_ENABLE_FAILED = "套餐内包含未启售菜品，无法启售";
    public static final String PASSWORD_EDIT_FAILED = "密码修改失败";
    public static final String DISH_ON_SALE = "起售中的菜品不能删除";
    public static final String SETMEAL_ON_SALE = "起售中的套餐不能删除";
    public static final String DISH_BE_RELATED_BY_SETMEAL = "当前菜品关联了套餐,不能删除";
    public static final String ORDER_STATUS_ERROR = "订单状态错误";
    public static final String ORDER_NOT_FOUND = "订单不存在";

}
~~~





## nginx 的使用

### 负载均衡和反向代理

![image-20250221095227198](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250221095227198.png)

![image-20250221095237699](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221095237699.png)



![image-20250221095246289](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221095246289.png)



![image-20250221095254876](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221095254876.png)



![image-20250221095306141](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221095306141.png)



![image-20250221095404243](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221095404243.png)

---

# 设计与导入接口文档

![image-20250221180616232](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221180616232.png)

## 使用swagger

![image-20250221182222304](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221182222304.png)

![image-20250221182416188](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221182416188.png)

**@Bean  // 第三方类的bean对象，交给spring容器管理 注意要返回**

![image-20250221182425814](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221182425814.png)4

### **静态资源 vs 动态请求：主要区别**

| 特性             | 静态资源                                | 动态请求                                         |
| ---------------- | --------------------------------------- | ------------------------------------------------ |
| **内容是否变化** | 内容固定，不会变化                      | 内容根据请求动态生成                             |
| **服务器负担**   | 轻量，直接返回文件                      | 需要处理逻辑，如查询数据库、生成响应等           |
| **缓存**         | 可缓存（浏览器、CDN）                   | 不适合缓存，或缓存周期短                         |
| **典型例子**     | 图片、CSS、JS、HTML 文件                | 用户登录、商品详情、查询结果等动态内容           |
| **请求方式**     | 请求对应的文件                          | 请求后由服务器生成内容并返回                     |
| **技术**         | 直接通过 Web 服务器（如 Nginx）返回文件 | 需要 Web 框架处理，生成响应（如 Spring、Django） |

**然后直接访问服务端端口/doc.html 即可查看接口文档和调试接口** 

~~~markdown
通过Swagger就可以生成接口文档，那么我们就不需要Yapi了？

 1、Yapi是设计阶段使用的工具，管理和维护接口 

2、Swagger在开发阶段使用的框架，帮助后端开发人员做后端的接口测试
~~~

**swagger  常用的注解**

![image-20250221183714812](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221183714812.png)

~~~java
@Api(tags="员工相关接口")
@ApiOperation(value="员工登录方法")
@ApiModel(descripration="")
@ApiModelProperty("") 

~~~



# 接口开发

![image-20250221230654039](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221230654039.png)

前端将用户数据以某种格式传过来 （前端在传用户数据之前也要做好==数据校验==）

后端用自定义的java对象或者是各种数据类型的变量接收前端传过来的数据， 此时要注意有些数据有一定的要求所以也需要数据校验如果不符合规则是不会赋值给java对象或者变量的！

**(数据校验的工作也要在数据库设计中就要配置，以防存入不合法的信息)**

然后利用这些变量做各种逻辑 操作数据库 

接口检验， 测试有无bug

**先完成，再完善  完善代码，优化代码**

---

![image-20250221185917148](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221185917148.png)

## 代码开发

![image-20250221190316425](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221190316425.png)

！！！！注意这个DTO就是简化了数据库实体类的属性 轻量化、贴合前端 但是后端没有对应这个DTO的表啊 所以我们需要将这个DTO的数据属性拷贝到==数据库实体类==中

### 硬编码

**硬编码**（Hardcoding）是指将程序中的数据或配置直接写死在代码中，而不是通过外部配置或动态读取的方式来获取这些数据。硬编码会使代码缺乏灵活性和可维护性，通常会增加修改和扩展的难度。

### **总结：如何防止硬编码**

1. **外部化配置**：将所有变动的配置（如数据库、API URL、第三方密钥等）放到外部配置文件中。
2. **使用环境变量**：在生产环境中，使用环境变量存储敏感数据。
3. **使用数据库存储配置**：对于变化频繁或复杂的配置，可以存储在数据库中，程序动态读取。
4. **利用配置管理工具**：使用配置管理工具（如 Spring Cloud Config）集中管理和动态更新配置。
5. **参数化设计**：避免在方法中写死具体的常量，而是通过方法参数传递这些值。



---

数据库映射实体类

```yaml
mybatis:
  #mapper配置文件
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.sky.entity
  configuration:
    #开启驼峰命名
    map-underscore-to-camel-case: tru
```

**核心就是数据外部化**



## 全局异常处理类

### 自定义sql异常的处理类

**前提要知道会发生什么异常 然后这些异常之间的继承关系**

巧妙利用sql异常信息 和类库方法 ！！！

~~~Java
package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    // 处理SQL异常
    public Result excepitionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        if(message.contains("Duplicate entry")) {
            // 处理唯一性异常
            String[] split = message.split(" ");
            String msg = split[2] + MessageConstant.ALREADY_EXIST;
            return Result.error(msg);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR); // 暂时输出“未知信息”
        }
    }


}

~~~

**contain方法判断异常信息包含什么**

**对象属性拷贝类库**

BeanUtils.copyProperties();



当一个用户发起多个 HTTP 请求时，每个请求都可能在不同的线程中处理，因为 **Tomcat** 会将每个 HTTP 请求分配给线程池中的一个独立线程来处理。因此，虽然是同一个用户发起了多个请求，但每个请求的处理是在不同的线程中进行的。

**1. 多个 HTTP 请求与线程的关系**

- **每个 HTTP 请求对应一个线程**，Tomcat 会根据当前的线程池设置（例如 `maxThreads` 配置项）为每个请求分配一个新的线程。
- **线程隔离性**：在多线程环境中，每个线程会处理一个 HTTP 请求。即使这些请求是同一个用户发起的，它们也会在不同的线程中处理，因此每个请求的上下文（例如当前线程的用户信息）需要隔离。

**2. 用户身份的维持**

即使用户发起了多个 HTTP 请求，这些请求仍然可能在不同的线程中被处理。那么，如何确保同一个用户在多个请求中能够维持一致的身份信息呢？这就涉及到如何在多个请求之间保持用户上下文信息。

**解决方案**：通过 **会话管理** 和 **线程隔离存储（ThreadLocal）** 等方式来保持用户的上下文信息。

**3. `ThreadLocal` 与用户发起多个 HTTP 请求的关系**

`ThreadLocal` 主要用于 **线程内部的数据隔离**，它确保每个线程拥有自己的独立副本。因此，在一个 HTTP 请求的处理过程中，`ThreadLocal` 可以用来存储当前请求的上下文信息，例如当前用户的身份信息、权限等。

但是，由于每个请求是由不同的线程处理的，`ThreadLocal` 并不适合跨请求共享数据。它只保证每个请求（线程）中的数据隔离，不能用于不同请求之间的数据共享。

###  **`Thread` 和 `ThreadLocal` 的区别**

| 特性                 | `Thread`                                       | `ThreadLocal`                                                |
| -------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| **定义**             | 表示一个独立的执行线程。每个线程执行一个任务。 | 为每个线程提供独立的变量副本。每个线程都可以存取自己的副本。 |
| **作用**             | 通过线程实现并发执行任务。                     | 用于在多线程中存储线程本地数据，确保每个线程的数据独立。     |
| **使用方式**         | 通过创建 `Thread` 对象来执行并发任务。         | 通过 `ThreadLocal` 类存储和访问线程特定的数据。              |
| **共享数据**         | 不共享数据，每个线程有自己的执行栈和栈变量。   | 每个线程有自己独立的副本，无法与其他线程共享数据。           |
| **线程安全**         | 多线程间可能需要通过同步来保证线程安全。       | `ThreadLocal` 本身保证线程内部的数据隔离，不会发生冲突。     |
| **典型应用场景**     | 用于并发任务的执行。                           | 用于线程本地存储，例如数据库连接、用户会话信息等。           |
| **线程间的数据交互** | 线程间共享堆内存中的对象，可能需要同步。       | 线程间无法交互数据，`ThreadLocal` 保证每个线程独立存储自己的数据。 |

客户端每次发起一个请求都是一个单独的线程

![image-20250221225130493](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250221225130493.png)

### ThreadLocal 工具类

~~~Java
package com.sky.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}

~~~

### 拦截器



~~~java
package com.sky.interceptor;
/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源 （如静态资源）
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：", empId);
            BaseContext.setCurrentId(empId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空当前线程中的数据
        BaseContext.removeCurrentId();
    }
}

~~~



~~~java
  @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        // 对象属性拷贝类库
        BeanUtils.copyProperties(employeeDTO, employee);
        // 其他没有的就set方法即可
        employee.setStatus(StatusConstant.ENABLE); // 如果写个1 就是硬编码了 后期不好维护
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes())); // 新增都设置一下默认的密码（存入的时候也就是新增（注册）的时候就要加密了）
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // TODO 后期要获取当前登录用户的id 使用ThreadLocal
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }
~~~

# 分页查询功能



**分页查询对象 用于接收前端传过来的数据** 



~~~Java
@Data
public class EmployeePageQueryDTO implements Serializable {

    //员工姓名
    private String name;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}

~~~

**构造返回数据**

~~~java
public class PageResult implements Serializable {

    private long total; //总记录数

    private List records; //当前页数据集合

}
~~~



### **为什么要用 `Serializable`？ 涉及到redis**

1. **对象持久化**：

   - Java 提供了 **序列化机制**，可以把对象转换为字节流并保存到磁盘文件中，在需要的时候可以恢复（反序列化）。

   - 例如，保存用户信息到数据库或文件中：

     ```java
     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.dat"));
     oos.writeObject(employee);
     oos.close();
     ```

2. **网络传输**：

   - 在 **分布式系统** 或 **RPC（远程过程调用）** 场景中，数据通常需要通过 **网络传输**，而 Java 只支持传输 **字节流**，所以需要 **序列化** 对象后再传输。
   - 例如，Spring Boot 结合 Redis 缓存对象时，必须序列化对象，否则会报错。

3. **Java 内存管理（深拷贝）**：

   - Java 中的 `clone()` 方法可以用 `Serializable` 来进行 **深拷贝**（即完整复制对象，而不仅仅是引用）。

---

## ==查询参数注意问题==

### **`@RequestParam(required = false)`**

`@RequestParam` 用来将 **请求参数**（query parameters）映射到方法的参数上。默认情况下，`@RequestParam` 的 `required` 属性是 `true`，这意味着这个请求参数是必需的，如果请求中没有这个参数，Spring 会抛出异常。

当 `required = false` 时，如果请求中没有这个参数，Spring 不会抛出异常，而是将参数的值设置为 `null` 或其默认值（例如 `int` 类型的默认值为 `0`）。

---



当请求参数是一个 **Java 对象**（例如 `EmployeePageQueryDTO`），Spring 会使用 **数据绑定机制（Data Binding）** 来填充对象字段：

- Spring 会尝试将 **请求的查询参数（Query Parameters）** 按照 **对象的属性名** 进行匹配，并填充它们的值。
- **未提供的参数不会引发异常**，而是保持字段的默认值（如果是对象类型则为 `null`，如果是基本数据类型则为默认值，如 `0`）。
- **不会抛出 `MissingServletRequestParameterException`**，因为 Spring 认为整个 `EmployeePageQueryDTO` 是一个 **可选对象**，而不是单独的必需参数。





Spring MVC 处理 **对象参数** 和 **基本数据类型参数（`@RequestParam`）** 的方式确实不同，主要体现在 **请求参数解析方式**、**默认值处理** 和 **缺失参数的行为** 这几个方面。

**1. 绑定方式的不同**

**（1）基本数据类型 / `@RequestParam`**

- `@RequestParam` 只能绑定 **单个参数**，通常用于基本数据类型（`int`、`boolean`）或包装类型（`Integer`、`Boolean`）。

- Spring **强制要求这些参数必须传递**，除非显式使用 `required = false` 或提供默认值 `defaultValue`。

- 未传递参数时：

  - **基本数据类型（如 `int`, `boolean`）** → 使用默认值（`0`, `false`）。
  - **对象类型（如 `String`, `Integer`）** → 如果 `required=true`，会报错；如果 `required=false`，会赋值 `null`。

  

  

**（2）对象参数（DTO）**

- Spring **自动解析 URL 查询参数**，并填充到 DTO（数据传输对象）字段中。

- 未传递参数时

  ：

  - **基本数据类型字段（`int`, `boolean`）** → 使用默认值（`0`, `false`）。
  - **包装类型字段（`Integer`, `String`）** → 赋值为 `null`，不会报错。
  - **整个 DTO 为空时** → Spring **仍然会创建一个对象实例**，只是所有字段都是默认值。





在 **SQL 查询** 中，`LIMIT` 主要用于 **限制返回的结果数量**，通常用于 **分页查询** 或 **只获取部分数据**。

通常会结合 `OFFSET` 使用

但是这样会比较麻烦 

**回顾一下入门项目的动态sql**（使用mapper映射文件）以及==模糊查询==

### 使用pagehelper 简化分页查询代码

~~~xml
 <properties>
        <mybatis.spring>2.2.0</mybatis.spring>
        <lombok>1.18.20</lombok>
        <fastjson>1.2.76</fastjson>
        <commons.lang>2.6</commons.lang>
        <druid>1.2.1</druid>
        <pagehelper>1.3.0</pagehelper>
        <aliyun.sdk.oss>3.10.2</aliyun.sdk.oss>
        <knife4j>3.0.2</knife4j>
        <aspectj>1.9.4</aspectj>
        <jjwt>0.9.1</jjwt>
        <jaxb-api>2.3.1</jaxb-api>
        <poi>3.16</poi>
    </properties>




	<dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>${pagehelper}</version>
    </dependency>

~~~



~~~java
    
@Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());// 开始分页查询这个方法是 MyBatis 分页插件 PageHelper 提供的一个 分页查询 方法。它的作用是 在执行 SQL 查询前，自动添加 LIMIT 和 OFFSET，从而实现分页查询。
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return pageResult;
    }


// mapper 接口
 Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO); // 使用mapper映射文件配置动态sql语句
~~~



~~~xml
<!--mapper 映射文件  动态sql-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.sky.mapper.EmployeeMapper">
	<select id="pageQuery" resultType="com.sky.entity.Employee">
	select * from employee
	<where>
		<if test="name != null and name != ''">
			and name like concat('%',#{name},'%')
		</if>
	</where>
	order by create_time desc
	</select>
</mapper>



concat 就是动态拼接sql字符串语句
然后%% 就是模糊查询
~~~



`PageHelper.startPage()` **不会直接执行 SQL**，而是 **拦截后续的 MyBatis 查询**，在 **最近执行的 SQL 语句** 上 **自动添加 `LIMIT` 和 `OFFSET`**。

PageHelper **会自动转换 `pageQuery` 的查询结果为 `Page<T>` 对象**，这个 `Page<T>` 继承自 `ArrayList<T>`，但 **它额外包含了分页信息**：

```java
page.getTotal();  // 获取总记录数
page.getPages();  // 获取总页数
page.getPageNum(); // 获取当前页码
page.getPageSize(); // 获取每页大小
page.getResult();  // 获取当前页的数据（等价于 `List<T>`）
```

这样，你 **不需要手动再执行 `COUNT(\*)` 计算总记录数**，PageHelper 会自动处理



### 日期格式化问题(消息转换器)

一般考虑有无 工具类 注解 使用不行就自己造轮子 

如果有想想如果处理的东西多了是不是每条都要使用这个注解或者工具类 能不能全局处理

![image-20250222011725910](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250222011725910.png)



![image-20250222012658006](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250222012658006.png)

将自己的消息转换器加入到消息转换器集合中 在这个集合中本身就自带很多消息转换器 而且是按顺序执行的 所以要让自己配置的消息转换器优先执行就要设置索引优先使用

~~~java
package com.sky.json;
// JacksonObjectMapper.java  

/**
 * 对象映射器:基于jackson将Java对象转为json，或者将json转为Java对象
 * 将JSON解析为Java对象的过程称为 [从JSON反序列化Java对象]
 * 从Java对象生成JSON的过程称为 [序列化Java对象到JSON]
 */
public class JacksonObjectMapper extends ObjectMapper {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    //public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public JacksonObjectMapper() {
        super();
        //收到未知属性时不报异常
        this.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        //反序列化时，属性不存在的兼容处理
        this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule simpleModule = new SimpleModule()
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

        //注册功能模块 例如，可以添加自定义序列化器和反序列化器
        this.registerModule(simpleModule);
    }
}

~~~

**修改员工状态**

~~~java
@Update("update employee set status = #{status} where id = #{id}")
void updateStatus(Integer status, Long id); // 也可以传递一个对象进来然后使用他的属性去更新 这样就是传参更简洁一点

@PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        employeeService.startOrStop(status,id);
        return Result.success("操作成功");
    } 

@Override
    public void startOrStop(Integer status, Long id) {
        // 根据id 查询员工状态
        Employee employee = employeeMapper.getById(id);
        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 一般查询都要判断数据库查询是否成功这个在mp中要注意 然后对一个变量操作时要注意非空指涉 万一空指针了呢

            employeeMapper.updateStatus(status, id);


    }
~~~

但是这样修改也仅仅只是修改status 难道修改任何一个属性都要重复的这样书写sql吗 这时候就可以使用动态sql了

~~~java
<!--	编写动态sql 动态编辑修改-->
	<update id="update" parameterType="Employee">
		update employee
		<set>
			<if test="name!=null">
				name = #{name},
			</if>
		    <if test="username!=null">
				username = #{username},
			</if>
		    <if test="password!=null">
				password = #{password},
			</if>
			<if test="sex!=null">
				sex = #{sex},
			</if>
			<if test="phone!=null">
				phone = #{phone},
			</if>
			<if test="idNumber!=null">
				id_number = #{idNumber},
			</if>
			<if test="status!=null">
				status = #{status},
			</if>

			<if test="updateUser!=null">
				update_user = #{updateUser},
			</if>
			<if test="updateTime!=null">
				update_time = #{updateTime},
			</if>
		</set>
		where id = #{id}
	</update>
~~~



**修改员工信息**

~~~java
// 回显
  @Override
    public EmployeeDTO getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
// 修改编辑
 @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);// 拷贝属性到employee中
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.update(employee);
    }
~~~



### 修改、删除之关联问题

注意如果是直接导入java文件类有可能**不会编译** 要用maven 的complie 生命周期

Maven 会：

1. **编译 `src/main/java/` 目录下的 Java 代码**。
2. **生成 `.class` 字节码文件**，存放在 `target/classes/` 目录中。
3. **不会编译 `src/test/java/`（测试代码）**，也不会运行测试。





# 公共字段自动填充(spring AOP)

### 技术点：枚举 切面类 反射 AOP



![image-20250222134903701](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250222134903701.png)

![image-20250222135253306](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250222135253306.png)

**`execution(\* com.sangeng.service.\*.\*(..))` 详解**

| 关键字                  | 含义                                                         |
| ----------------------- | ------------------------------------------------------------ |
| `execution`             | 指定使用 **方法执行** 作为切点                               |
| `*`                     | **返回类型**，`*` 表示 **任意返回类型**（void, int, String 等都匹配） |
| `com.sangeng.service.*` | **匹配 `com.sangeng.service` 包下的所有类**（`*` 代表所有类） |
| `.*(..)`                | **匹配所有方法**（`*` 代表方法名，`(..)` 代表任意参数）      |





---



**在mapper中书写增强方法的注解**

~~~java
  @Insert("insert into employee (name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user, status)" + "values" +
    "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);


  @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);
~~~



**自定义注解**

~~~java
// 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 持久层
public @interface AutoFill {
    // 指定数据库操作类型
    OperationType value();

}
~~~

### ==切面类==(注意这里和spring的联系区别)

~~~java
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    // 指定切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)") // 用Pointcut注解中的属性来指定对哪些方法进行增强 也就是指定增强哪些方法  
    public void autoFillPointCut(){}


    // 通知 就是对代码进行增强的部分
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){ // 就是那些实际业务中被增强的方法
        // 获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 方法签名对象（签名对象的子接口）
        AutoFill autoFillAnnotation = signature.getMethod().getAnnotation(AutoFill.class);// 获取方法上的注解对象
        OperationType value = autoFillAnnotation.value();// 获取数据库操作类型

        // 获取实体对象（也就是被拦截（加强）方法的参数） 然后准备 数据 对这些实体对象 赋值  通过反射操作
        Object[] args = joinPoint.getArgs(); // 这里是获取所有参数
        if(args == null || args.length == 0) return ;

        Object entity = args[0]; // 这里做好约定 第一个参数就是实体对象（when someone coding）  使用object来接收 因为 实体对象可能存在很多类型（有多个业务多个实体类的）
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        // 根据不同的操作类型不同的处理逻辑
        if(value == OperationType.INSERT) {
            // 通过反射赋值
            // 这种经常用的常量就不要硬编码了容易出错而且耦合
            try {
                // 获取方法 方法名+参数类型
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 开始调用方法
                setCreateTime.invoke(entity,now); // 这里的两个参数分别是 被增强的方法的实体对象 和 方法的参数
                setUpdateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateUser.invoke(entity,currentId);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if(value == OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
~~~

## 新增菜品

### 数据库设计

**主表（Primary Table）是主要的数据来源**，它的每一条数据是“独立”的，可以单独存在，不依赖于从表的数据。

**从表（Foreign Table）依赖于主表**，每一条数据都必须与主表的某个记录相关联，否则它就没有意义。

#### **数据示例**

| **departments（主表）** | name   |
| ----------------------- | ------ |
| 1                       | IT部   |
| 2                       | 财务部 |

| **employees（从表）** | name | department_id |
| --------------------- | ---- | ------------- |
| 1                     | 张三 | 1             |
| 2                     | 李四 | 1             |

> **解释：**
>
> - `departments` 是主表，`employees` 是从表。

**逻辑外键**（Logical Foreign Key）是指在数据库中，用于表示两个表之间关系的字段，它并不直接依赖于物理数据库中的外键约束，而是通过应用程序的逻辑或设计规则来确保数据的完整性和关联性。

简单来说，逻辑外键是一种约定，它没有在数据库中通过正式的外键约束来实现，但是通过表中的数据和应用程序的代码来维护和检查关系。例如，一个订单表中的“customer_id”字段可能是一个逻辑外键，它关联着客户表中的“id”字段，但数据库中并没有实际的外键约束（即没有通过数据库的外键约束来强制保证这两个字段的一致性）。

![image-20250222220744558](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250222220744558.png)

### 接口实现



#### 查询分类





#### 文件上传

`@RestController` 是 **Spring Boot** 中的一个注解，**用于创建 RESTful Web 服务**。它是 `@Controller` 和 `@ResponseBody` 的组合，作用是**返回 JSON 或 XML 数据**，而不是视图页面。



| **Content-Type**                    | **适用的 Spring 注解** | **说明**               |
| ----------------------------------- | ---------------------- | ---------------------- |
| `application/json`                  | `@RequestBody`         | 解析 JSON 格式的请求体 |
| `application/x-www-form-urlencoded` | `@RequestParam`        | 解析普通表单提交       |
| `multipart/form-data`               | `@RequestParam`        | 解析文件上传           |

`MultipartFile` 是 Spring 框架提供的一个**专门用于处理文件上传**的接口。它可以解析 `multipart/form-data` 类型的 HTTP 请求，帮助我们获取上传的文件。



---



~~~java
@Component
@ConfigurationProperties(prefix = "sky.alioss")
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}

~~~

这段代码是 **Spring Boot 的配置类**，用于  **从 `application.properties` 或 `application.yml`** ==**读取**== **阿里云 OSS（Object Storage Service）** 的配置信息，并封装成 Java 对象。

---

**在配置文件中**

里面的数据源啊或者其他配置可能不同环境不一样 所以这些不确定的就在主配置文件中引用其他环境的配置文件

```yml
spring:
  profiles:
    active: dev  // 在这里先指定引用的文件
    
    
    
    
  alioss:
   endPoint:${sky.alioss.endPoint}
   access-key-id:${sky.alioss.access-key-id}
   access-key-secret:${sky.alioss.access-key-secret}
   bucket-name:${sky.alioss.bucket-name}  
   
   // 就用这种方式动态读取active值对应不同环境配置文件中的属性 
```

application-yml

application-dev-yml 

---



在 Spring 中，`@Bean` 主要用于**==手动==创建并交给 Spring 容器管理对象**。

`@Bean` 是 Spring 框架中的一个**方法级别的注解**，用于**创建和管理 Spring 容器中的 Bean**。

在 Spring 配置类（`@Configuration` 标注的类）中，我们可以用 `@Bean` 方法返回一个对象，让 Spring 容器管理它。

## **📌 为什么不能直接用 `@Component` / `@Service`，而要用 `@Bean`？**

在 Spring 里，我们通常用 `@Component`、`@Service`、`@Repository` 来标注类，让 Spring 自动扫描并管理它们。但是，在某些情况下，这些注解**无法满足需求**，所以需要用 `@Bean` 方式手动创建对象。

------

## **📌 1. `@Component` 无法管理第三方库的 Bean**

**✅ 问题：**
`@Component` 只能用在**自己写的类**上，而**第三方类**（比如 `DataSource`、`RestTemplate`）不能直接加 `@Component`，因为我们没法改它的源码。

**✅ 解决方案：**
可以用 `@Bean` 手动创建并交给 Spring 容器管理：

```java
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

这样 `RestTemplate` **就能被 `@Autowired` 注入**：

```java
@Autowired
private RestTemplate restTemplate;
```

------

## **📌 2. 需要自定义 Bean 的创建逻辑**

有时候，Bean 需要**特殊的初始化**，比如：

- **需要从配置文件读取参数**（`DataSource`）。
- **需要构造方法传参**（`RedisTemplate`）。
- **需要手动配置属性**。

**✅ 例子：数据库连接池**

```java
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(properties.getUrl());
        ds.setUsername(properties.getUsername());
        ds.setPassword(properties.getPassword());
        return ds;
    }
}
```

- `DataSource` **不能直接 `@Component`，因为它需要参数（URL、用户名、密码）**。
- 用 `@Bean` **手动创建**，可以从配置文件读取参数。



---

```java
@Component
@ConfigurationProperties(prefix = "sky.alioss")
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}

// 配置类用于创建AliOssUtil对象
@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        return new AliOssUtil(aliOssProperties.getEndpoint(),aliOssProperties.getAccessKeyId(),aliOssProperties.getAccessKeySecret(),aliOssProperties.getBucketName());
    }
    // 交给spring容器管理的同时初始化

}
```

~~~java
@RestController
@RequestMapping("/admin/common")  // 注意这里是RequestMapping
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil; // 获取bean对象

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();// 获取原始文件名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString()+ extension; // 使用UUID以防不能存同名照片
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);

        } catch (IOException e) {
            log.error("文件上传失败:{}",e); // slf4j lombook的日志管理工具 
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);

    }

}
~~~



**AliOssUtil**

~~~java

@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
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
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName); // 这里就是文件修改过后呢name

        log.info("文件上传到:{}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}

~~~





**TODO ：// 注意对比三个项目的文件上传功能 以及阿里云对象存储的使用**







#### 提交新增菜品

在数据库中，通常会有 **主键自增（AUTO_INCREMENT）** 或 **序列（SEQUENCE）** 的情况，当我们执行 `INSERT` 语句后，数据库会自动生成一个主键 ID，而 `useGeneratedKeys` 可以让 MyBatis 获取这个主键并回填到 Java 对象的属性中。

在 Java 中，不管是使用 **JDBC** 还是 **MyBatis**，数据库操作默认是 **同步的**：



**DishMapper**

~~~java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sky.mapper.DishMapper">
	<!--    命名空间 肯定要和接口绑定映射-->
	<!--在映射文件里书写sql语句-->


	<insert id="insert" userGeneratedKeys="true" keyProperty="id">
		insert into dish (name,category_id,price,image,description,status,create_time,update_time,create_user,update_user)
		values (#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})
	</insert>
<!--	表示当insert语句执行完后会将这个数据表中的主键赋值到keyProperity中也就是id-->
</mapper>
~~~

**DishFlavorMapper**

~~~java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sky.mapper.DishFlavorMapper">
	<!--    命名空间 肯定要和接口绑定映射-->
	<!--在映射文件里书写sql语句-->


<!--	传入的是集合的话 使用动态sql-->
	<insert id="insertBatch">
		insert into dish_flavor (dish_id,name,value) values
		<foreach collection="flavors" item="flavor" separator=",">
			(#{flavor.dishId},#{flavor.name},#{flavor.value})
		</foreach>
	</insert>
</mapper>
~~~



~~~java
    @Override
    @Transactional // 涉及到多个数据库操作就要保证事务的一致性  还要在启动类中开启注解方式的事务管理

    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        // 向菜品表插入一条数据
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);
        Long dishId = dish.getId(); // 获取insert语句生成的主键值
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId); // 一个菜有多个口味
            });
            // 向口味表插入多条数据
            dishFlavorMapper.insertBatch(flavors);// 批量插入
        }

    }
~~~





### 分页查询何多表联查（使用DTO 和 VO）

~~~java
  @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询：{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    } 


@Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

<!--	对于复杂sql先在queryconsole中调试再使用java代码嵌入-->
	<select id="pageQuery" resultType="com.sky.vo.DishVO">  // 查询的结果使用VO 类接收
		select d.*,c.name as category_name from dish d left join category c on d.category_id = c.id
		<where>
			<if test="name != null and name != ''">
				and d.name like concat('%',#{name},'%')
			</if>
			<if test="categoryId != null">
				and d.category_id = #{categoryId}
			</if>
			<if test="status != null">
				and d.status = #{status}
			</if>
		</where>
		order by d.create_time desc
	</select>
~~~

### 删除菜品和批量删除（删除/修改调整带来的影响）

**涉及数据库的多种逻辑操作**

![image-20250223151529790](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250223151529790.png)

操作一张表就需要一张关于这个表的mapper

<span style="font-size:1.9em; color:#FF0000;"><!--    涉及参数是批量的sql语句如何书写呢--></span>



~~~java

 @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam("ids") List<Long> ids){ // 这里一定要加RequestParam 不然无法解析前端传过来的 1,2,3 这样的数据
        log.info("菜品批量删除：{}",ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }


———————————————————————————————————————————————————————————————————————————————————————————————————————
    
 @Override
 @Transactional // 操作多个数据表 要使用事务管理一致性
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id -> {
           Dish dish = dishMapper.getById(id);
           if(dish.getStatus() == StatusConstant.ENABLE){
               throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
           }
        });
        List<Long> setmealIds = setmealDishMapper.getSetmealIDSByDishId(ids); // 个数不确定所以要使用动态sql
        if(setmealIds != null && setmealIds.size() > 0){  // 被套餐关联不可删除
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        ids.forEach(id-> {
            // 删除菜品
            dishMapper.deleteById(id);
            // 删除菜品相关联的口味数据 所以要操作口味表 通过口味mapper
            dishFlavorMapper.deleteByDishId(id);
        });

    }
~~~



**setmealDishMapper.xml**

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace= "com.sky.mapper.SetmealDishMapper">
	<!--    命名空间 肯定要和接口绑定映射-->
	<!--在映射文件里书写sql语句-->

<!--	涉及参数是批量的sql语句如何书写呢-->
<!--	sql 原型语句 为 select setmeal_id from setmeal_dish where dish_id in (1,2,5,3,4,7) -->
	<select id="getSetmealIDSByDishId" resultType="java.lang.Long">
		select setmeal_id from setmeal_dish where dish_id in
		<foreach collection="dishIds" item="dishId" separator="," open="(" close=")">
<!-- 开始遍历前加上个左括号 遍历结束后加上个右括号然后每个item使用separator作为分隔符-->
			#{dishId}
		</foreach>
	</select>
</mapper>
~~~

**for循环每次查询一个这样太慢了显然可以进行优化**

 就和一次查询多个一样

也就是对删除优化为==批量删除==



~~~java
    dishMapper.deleteByIds(ids); // 批量删除
    dishFlavorMapper.deleteByDishId(ids);
~~~



~~~xml
	<delete id="deleteByIds">
		delete from dish where id in
		<foreach collection="ids" item="id" separator="," open="(" close=")">
			#{id}
		</foreach>
	</delete>

	<delete id="deleteByDishId">
		delete from dish_flavor where dish_id in
		<foreach collection="ids" item="id" separator="," open="(" close=")">
			#{id}
		</foreach>
	</delete>
~~~

### 修改菜品

- **DTO（数据传输对象）**：用于**在不同层之间传递数据**（如 Controller → Service → DAO）。==偏向数据库实体类的简化封装==
- **VO（视图对象）**：用于**封装前端所需的返回数据**（主要是 Controller 层返回给前端的数据）。==偏向前端需要的数据==

#### 数据回显 也就是根据id查询  

~~~java
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable("id") Long id){
        log.info("根据id查询菜品：{}",id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        // 通过mapper返回dish基本数据和flavor数据然后赋值到DishVO中
        Dish dish = dishMapper.getById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        List<DishFlavor> flavors =dishFlavorMapper.getByDishId(id);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

~~~



### 修改完成提价数据

~~~java
@PutMapping
@ApiOperation("修改菜品")
public Result update(@RequestBody DishDTO dishDTO){
    log.info("修改菜品：{}",dishDTO);
    dishService.update(dishDTO);
    return Result.success();
}


@Override
    @Transactional
    public void update(DishDTO dishDTO) {
        // 更新dish表
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);
        // 更新对应的口味表
        // 但是这个修改包含删除增加什么的 比较复杂 所以我们修改可以 先删除原来的 然后直接插入就行 思路学习
        List<Long> ids = new ArrayList<>();
        ids.add(dishDTO.getId());
        dishFlavorMapper.deleteByDishId(ids);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId()); // 一个菜有多个口味
            });
            // 向口味表插入多条数据
            dishFlavorMapper.insertBatch(flavors);// 批量插入
        }

    }

<update id="update">
		update dish
		<set>
			<if test="name != null">
				name = #{name},
			</if>
			<if test="categoryId != null">
				category_id = #{categoryId},
			</if>
			<if test="price != null">
				price = #{price},
			</if>
			<if test="image != null">
				image = #{image},
			</if>
			<if test="description != null">
				description = #{description},
			</if>
			<if test="status != null">
				status = #{status},
			</if>
			<if test="updateTime != null">
				update_time = #{updateTime},
			</if>
			<if test="updateUser != null">
				update_user = #{updateUser},
			</if>
<!--			所以这是使用Dish 数据库对象不使用 DishDTO 传到数据库更新的原因 DTO 中缺少update时间和用户-->
		</set>
		where id = #{id}
	</update>
~~~





# HTTP client

![image-20250223151902405](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250223151902405.png)



在这里aliyun.oss 传递过来了这个依赖项



![image-20250223152000255](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250223152000255.png)



![image-20250223152549984](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250223152549984.png)



![image-20250223154238433](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250223154238433.png)

~~~java
/**
 * Http工具类
 */
public class HttpClientUtil {

    static final  int TIMEOUT_MSEC = 5 * 1000;

    /**
     * 发送GET方式请求
     * @param url
     * @param paramMap
     * @return
     */
    public static String doGet(String url,Map<String,String> paramMap){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = "";
        CloseableHttpResponse response = null;

        try{
            URIBuilder builder = new URIBuilder(url);
            if(paramMap != null){
                for (String key : paramMap.keySet()) {
                    builder.addParameter(key,paramMap.get(key));
                }
            }
            URI uri = builder.build();

            //创建GET请求
            HttpGet httpGet = new HttpGet(uri);

            //发送请求
            response = httpClient.execute(httpGet);

            //判断响应状态
            if(response.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 发送POST方式请求
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> paramMap) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建参数列表
            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * 发送POST方式请求
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String doPost4Json(String url, Map<String, String> paramMap) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            if (paramMap != null) {
                //构造json格式数据
                JSONObject jsonObject = new JSONObject();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    jsonObject.put(param.getKey(),param.getValue());
                }
                StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
                //设置请求编码
                entity.setContentEncoding("utf-8");
                //设置数据类型
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }
    private static RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MSEC)
                .setConnectionRequestTimeout(TIMEOUT_MSEC)
                .setSocketTimeout(TIMEOUT_MSEC).build();
    }

}

~~~

