![image-20250325200948720](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325200948720.png)

-mysql

​	-conf

​	-init

用于镜像提供初始化数据

**端口映射:**在运行 MySQL 容器时使用了-p参数将容器内部的 MySQL端口(通常是 3306)映射到虚拟机的外部端口。例如，docker run-p 3306:3306会将虚拟机的 3306端口与容器的 3306 端口连接起来从而允许外部设备通过虚拟机的IP 地址和端口访问 MySQL。

![image-20250325200950430](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325200950430.png)



# Maven POM文件re

父子工程继承的问题：

**`dependencyManagement`的作用**

- **功能**：`dependencyManagement`用于**声明依赖及其版本**，但不会直接引入依赖到子模块。它仅提供版本管理，子模块需要**显式声明依赖**（但可省略版本号）。
- **优点**：
  - **统一版本管理**：所有子模块可以共享父工程中定义的依赖版本，避免版本冲突。
  - **按需引入**：子模块仅声明需要的依赖，减少冗余。



**父工程中的`dependencies`**：

- 子模块**自动继承**这些依赖，无需显式声明。
- 适用于所有子模块都需要的依赖（如公共工具包）



---





type ： pom文件类型

import ： 导入这个pom文件 使用这个pom文件定义的依赖

**注意properties 标签和dependencyManagement标签**



![image-20250325202145655](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325202145655.png)







![image-20250325201158228](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325201158228.png)

![image-20250325201147436](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325201147436.png)

不要去改代码这里的active属性 不然就属于侵入式了

![image-20250325201247997](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325201247997.png)

如果代码量庞大，那每次修改编译的话 需要非常长的时间

然后如果有非常多用户对一个接口在同一时间段内访问的话，会导致这个接口并发过高然后Tomcat的资源就占用完了

最后导致用户访问其他接口的速度都变得慢了





![image-20250325201345927](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325201345927.png)

每个功能和模块都部署到了不同的服务上去 **都有自己的服务器和数据库去进行处理**

![image-20250325201913389](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250325201913389.png)

**springcloud最新版一般支持Springboot3这个依赖jdK17所以要明白版本之间的管理是否兼容**



---

注意：

**基本数据类型**

- **包括**：`int`, `float`, `double`, `boolean`, `char`, `byte`, `short`, `long`。
- **行为**：传递的是值的 **副本**，函数内修改参数 **不会影响原变量**。

**对象引用类型**

- **包括**：数组、`String`、集合类（`List`, `Map`）、自定义对象等。
- **行为**：传递的是对象引用的 **副本**，但通过这个副本可以 **修改对象内容**，从而影响原对象

~~~Java

public class DataTest {

    public void test1(List<Integer> str){
        str.add(6);
        return;
    }
    @Test
    public void test(){
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        test1(num);
        System.out.println(num);
    }
}
~~~



**不可变对象**

- **包括**：`String`, `Integer`, `Double` 等包装类。
- **行为**：由于不可变性，任何修改都会生成新对象，原对象不受影响

~~~java
   public void test1(String str){
        str = "test";
    }
    @Test
    public void test(){
        String str = "senjay";
        test1(str);
        System.out.println(str); // out:senjay
    }
~~~



# 微服务拆分

![image-20250328170414844](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328170414844.png)

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328170428951.png)



微服务工程结构：

![image-20250328170522658](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328170522658.png)

一project 多 modules（maven聚合）

一文件夹多project（独立project）

---



IDE中设置环境变量是会自动找到系统中对应的不用自己在本机中配置 但是部署的时候需要指定他是固定路径的（IDE会去找对应的）

~~~xml
		<dependency>
			<groupId>com.heima</groupId>
			<artifactId>hm-common</artifactId>
			<version>1.0.0</version>
		</dependency>
<!--		引入自己的依赖项-->
如何创建自己的maven依赖工程呢
~~~



**拆分注意：最重要的就是配置文件的配置 以及项目文件结构布局 还有项目文件的配置和代码 逻辑 以及pom文件的配置**

**还有启动项的问题 （以什么环境启动 端口有没有被占用）**



~~~java
// 启动类中写
package com.hmall.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hmall.item.mapper")  // 扫描
@SpringBootApplication
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
~~~



**传统方法：手动加 `@Mapper`**

你可以在每个 `Mapper` 接口上手动加 `@Mapper`，例如：

```java
@Mapper
public interface ItemMapper {
    Item selectById(Long id);
}
```

但是如果有很多 `Mapper`，每个都要加 `@Mapper`，就很麻烦。



配置文件中配置日志文件保存配置 whereby 让不同的微服务日志保存在不同的目录下

~~~yaml
logging:
  level:
    com.hmall: debug
  pattern:
    dateformat: HH:mm:ss:SSS
  file:
    path: "logs/${spring.application.name}"
~~~



~~~yaml

spring:
  application:
    name: item-service # 服务名称 服务注册要用来做服务发现 每一个微服务都有自己的名字
  profiles:
    active: dev
  datasource: 
    url: jdbc:mysql://${hm.db.host}:3306/hmall/item?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: ${hm.db.pw}
~~~

每个**微服务**都对应一个数据库服务器（DBMS） 但在学习中我们对应每一个数据库（Database） 所以上面的url要对应着修改一下



这里的active 不要在这个配置文件中修改

**alt  + 8**  service 配置 中修改**启动** 的时候会以这个为准 

~~~yaml
# Swagger 配置


knife4j:
  enable: true
  openapi:
    title: 黑马商城商品管理接口文档
    description: "黑马商城商品管理接口文档"
    email: zhanghuyi@itcast.cn
    concat: 虎哥
    url: https://www.itcast.cn
    version: v1.0.0
    group:
      default:
        group-name: default
        api-rule: package
        api-rule-resources:
          - com.hmall.item.controller
~~~



# 远程调用（RPC）

![image-20250328181641415](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328181641415.png)



也就是用一个微服务调用另一个微服务 whereby 每一服务单一职责 不要内容耦合

这个调用就是利用java代码发起HTTP请求



---



注入到spring的bean当中去

![image-20250328181859626](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328181859626.png)



会将查到的json字符串反序列化为java对象

但是这种带泛型的就不行了 因为字节码中没有泛型 

所以这种情况就无法传字节码了

![image-20250328191921169](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328191921169.png)



将集合转化为逗号隔开的字符串

请求有可能失败

查询到的参数有可能指针涉空



![image-20250328192345835](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328192345835.png)









---



lombok @RequiredArgsConstructor

作用就是只 only 仅仅 给加了final 的成员变量依赖注入

cause 相比于 @Autowired 使用构造函数依赖注入更好 为了方便不用写构造函数就用上面的注解即可

---

# Nacos



![image-20250328192440973](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328192440973.png)



配置nacos



~~~conf
PREFER_HOST_MODE=hostname
MODE=standalone
SPRING_DATASOURCE_PLATFORM=mysql
MYSQL_SERVICE_HOST=192.168.254.128
MYSQL_SERVICE_DB_NAME=nacos
MYSQL_SERVICE_PORT=3307
MYSQL_SERVICE_USER=root
MYSQL_SERVICE_PASSWORD=123456
MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai
~~~

将custom.env 放到目录 然后在当前目录：

~~~bash
docker run -d \
--name nacos \
--env-file ./nacos/custom.env \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
--restart=always \
nacos/nacos-server:v2.1.0-slim
~~~

## 服务注册



![image-20250328205614951](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328205614951.png)



~~~yaml
spring:
  application:
    name: cart-service # 服务名称 服务注册要用来做服务发现
  cloud:
    nacos:
      server-addr: 192.168.254.128:8848
#      server-addr: ${hm.nacos.host}:${hm.nacos.port} 从不同环境读取不同配置
~~~

![image-20250328213214093](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328213214093.png)



ide 中模拟多服务实例 实际上这个都是请求到同一个服务器

**一般是多个服务器或者多个容器中**

**“实例"**是同一个微服务的运行拷贝，每个实例独立运行但功能相同。这些实例通常会分布在不同的主机上，或者在同一主机的多个容器中。
**目的:**
**a.高可用性:如果某个实例宕机，其他实例仍然可以继续服务，避免系统中断。**
**b.负载分担:通过负载均衡将流量分配给多个实例，减少单个实例的压力。**
**c.横向扩展:在高峰期快速增加实例数量以应对流量增长。**



**注意：**

容器内部端口:每个 Docker 容器内部的端口(例如 80)可以相同，因为容器之间是隔离的。主机绑定端口:当容器的端口暴露到主机时，必须确保主机端口唯一，否则会导致冲突。

---



## 服务发现和负载均衡



<img src="https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328215349914.png" alt="image-20250328215349914" style="zoom: 200%;" />



![image-20250328215524282](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328215524282.png)





## 实体类分析（数据库属性分析）



~~~Java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_order")
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
   // 指定 id 为主键，并使用 MyBatis-Plus 的 ASSIGN_ID 策略（类似于 Snowflake 算法自动生成 ID）。


    /**
     * 业务订单号
     */
    private Long bizOrderNo;
    // business Order number

    /**
     * 支付单号
     */
    private Long payOrderNo;

    /**
     * 支付用户id
     */
    private Long bizUserId;
   

    /**
     * 支付渠道编码
     */
    private String payChannelCode;

    /**
     * 支付金额，单位分
     */
    private Integer amount;

    /**
     * 支付类型，1：h5,2:小程序，3：公众号，4：扫码，5：余额支付
     */
    private Integer payType;

    /**
     * 支付状态，0：待提交，1:待支付，2：支付超时或取消，3：支付成功
     */
    private Integer status;

    /**
     * 拓展字段，用于传递不同渠道单独处理的字段
     */
    private String expandJson;

    /**
     * 第三方返回业务码
     */
    private String resultCode;

    /**
     * 第三方返回提示信息
     */
    private String resultMsg;

    /**
     * 支付成功时间
     */
    private LocalDateTime paySuccessTime;

    /**
     * 支付超时时间
     */
    private LocalDateTime payOverTime;

    /**
     * 支付二维码链接
     */
    private String qrCodeUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 逻辑删除
     */
    private Boolean isDelete;


}

~~~

**`@TableName("pay_order")`**

- 指定该实体类对应的数据库表名 `pay_order`。

**`@TableId(value = "id", type = IdType.ASSIGN_ID)`**



- 指定 `id` 为主键，并使用 **MyBatis-Plus** 的 **ASSIGN_ID** 策略（类似于 Snowflake 算法自动生成 ID）。

- `bizOrderNo` 是商城或业务系统的订单编号，**对应于用户下单的订单。 **
- `payOrderNo` 是支付系统内部的支付编号，用于**处理支付相关操作。**



业务分析：



![90be0d44536d8501bfd059ada00d88e](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/90be0d44536d8501bfd059ada00d88e.jpg)

~~~java
@Service
@RequiredArgsConstructor
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements IPayOrderService {
    private final UserClient userClient;
    private final TradeClient tradeClient;

    @Override
    public String applyPayOrder(PayApplyDTO applyDTO) {
        // 1.幂等性校验
        PayOrder payOrder = checkIdempotent(applyDTO);
        // 2.返回结果
        return payOrder.getId().toString();
    }

    @Override
    @Transactional
    public void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO) {
        // 1.查询支付单
        PayOrder po = getById(payOrderFormDTO.getId());
        // 2.判断状态
        if(!PayStatus.WAIT_BUYER_PAY.equalsValue(po.getStatus())){
            // 订单不是未支付，状态异常
            throw new BizIllegalException("交易已支付或关闭！");
        }
        // 3.尝试扣减余额


        userClient.deductMoney(payOrderFormDTO.getPw(), po.getAmount());


        // 4.修改支付单状态
        boolean success = markPayOrderSuccess(payOrderFormDTO.getId(), LocalDateTime.now());
        if (!success) {
            throw new BizIllegalException("交易已支付或关闭！");
            // Spring 默认会对 未捕获的 RuntimeException 或 Error 进行回滚
        }

        tradeClient.markOrderPaySuccess(po.getBizOrderNo());



    }

    public boolean markPayOrderSuccess(Long id, LocalDateTime successTime) {
        return lambdaUpdate()
                .set(PayOrder::getStatus, PayStatus.TRADE_SUCCESS.getValue())
                .set(PayOrder::getPaySuccessTime, successTime)
                .eq(PayOrder::getId, id)
                // 支付状态的乐观锁判断
                .in(PayOrder::getStatus, PayStatus.NOT_COMMIT.getValue(), PayStatus.WAIT_BUYER_PAY.getValue())
                .update();
    }


    private PayOrder checkIdempotent(PayApplyDTO applyDTO) {
        // 1.首先查询支付单
        PayOrder oldOrder = queryByBizOrderNo(applyDTO.getBizOrderNo());
        // 2.判断是否存在
        if (oldOrder == null) {
            // 不存在支付单，说明是第一次，写入新的支付单并返回
            PayOrder payOrder = buildPayOrder(applyDTO);
            payOrder.setPayOrderNo(IdWorker.getId());
            save(payOrder);
            return payOrder;
        }
        // 3.旧单已经存在，判断是否支付成功
        if (PayStatus.TRADE_SUCCESS.equalsValue(oldOrder.getStatus())) {
            // 已经支付成功，抛出异常
            throw new BizIllegalException("订单已经支付！");
        }
        // 4.旧单已经存在，判断是否已经关闭
        if (PayStatus.TRADE_CLOSED.equalsValue(oldOrder.getStatus())) {
            // 已经关闭，抛出异常
            throw new BizIllegalException("订单已关闭");
        }
        // 5.旧单已经存在，判断支付渠道是否一致
        if (!StringUtils.equals(oldOrder.getPayChannelCode(), applyDTO.getPayChannelCode())) {
            // 支付渠道不一致，需要重置数据，然后重新申请支付单
            PayOrder payOrder = buildPayOrder(applyDTO);
            payOrder.setId(oldOrder.getId());
            payOrder.setQrCodeUrl("");
            updateById(payOrder);
            payOrder.setPayOrderNo(oldOrder.getPayOrderNo());
            return payOrder;
        }
        // 6.旧单已经存在，且可能是未支付或未提交，且支付渠道一致，直接返回旧数据
        return oldOrder;
    }

    private PayOrder buildPayOrder(PayApplyDTO payApplyDTO) {
        // 1.数据转换
        PayOrder payOrder = BeanUtils.toBean(payApplyDTO, PayOrder.class);
        // 2.初始化数据
        payOrder.setPayOverTime(LocalDateTime.now().plusMinutes(120L));
        payOrder.setStatus(PayStatus.WAIT_BUYER_PAY.getValue());
        payOrder.setBizUserId(UserContext.getUser());
        return payOrder;
    }
    public PayOrder queryByBizOrderNo(Long bizOrderNo) {
        return lambdaQuery()
                .eq(PayOrder::getBizOrderNo, bizOrderNo)
                .one();
    }
}

~~~

主键一般是自增的

这里的支付订单id是随机生成算法生成的 所以不适合作为主键

# OpenFeign

简化http客户端操作

**具体就是简化了==远程调用==和==负载均衡==**

![image-20250328215532860](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328215532860.png)

![image-20250328215631820](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328215631820.png)







当你在 `pom.xml` 中引入一个依赖，例如：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

它的 **`pom.xml`** 可能会包含：

```xml
<dependencies>
    <dependency>
        <groupId>io.github.openfeign</groupId>
        <artifactId>feign-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    </dependency>
</dependencies>
```

那么你在项目里引入 `spring-cloud-starter-openfeign`，就 **自动继承** 了 `feign-core` 和 `spring-cloud-starter-loadbalancer` 这些依赖，这就是 **传递依赖**。

## **确保 `@EnableFeignClients` 已启用**

`@FeignClient` 需要 Feign 组件的支持，你需要在 Spring Boot 启动类（`@SpringBootApplication` 标注的类）上加上：

```java
@EnableFeignClients(basePackages = "com.hmall.api.client")
```

或者：

```java
@EnableFeignClients
```

这样 Spring 才能扫描 `UserClient` 并注册为 Bean。



---



## **依赖范围（Scope）影响继承**

在 `pom.xml` 里，依赖可能会有不同的 `scope`（作用范围）：

- **`compile`（默认）**：会被 **继承**，在编译、测试、运行时都可用。
- **`provided`**：不会被继承，只在编译和测试时可用，运行时需要环境提供。
- **`runtime`**：不会在编译时继承，但在运行时需要。
- **`test`**：不会被继承，只在测试时可用。

---



在 `@FeignClient` 中，`value` 指定的服务名用于 **从服务注册中心查找对应的微服务**，然后通过 Feign 进行远程调用。



![image-20250328215702449](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328215702449.png)

**实现远程调用直接 就通过spring管理的feignclient 接口 调用这个接口的方法** 



**就是再写一遍接口 然后远程调用者调用这个接口对应的方法！**



**用这个Collection 就可以不用转化 集合了** 

**所有集合都可以用！**

```Java
package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient("cart-service")
public interface CartClient {
    @DeleteMapping("/carts")
    void deleteCartItemByIds(@RequestParam("ids") Collection<Long> ids);
}
```

**对比差异**



![image-20250328221209504](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328221209504.png)





## 👻feighClient 问题

![1743593689466](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1743593689466.png)

![1743593705666](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1743593705666.png)



~~~java
// 位于trade微服务中 的 OrderServiceImpl

List<OrderDetailDTO> detailDTOS = orderFormDTO.getDetails();
// 需要修改OrderFormDTO（trade微服务） 中的details类导入路径
~~~



```java
itemClient.deductStock(detailDTOS); // 这个DTO 是api 那边的 路径是api那里的 不是trade微服务路径的
```



```java
//ItemClient的deductStock方法要OrderDetailDTO，hm-api总不能从trade-service导这个类型吧。
// 那hm-api模块里有这个DTO，其他微服务模块调用这个远程调用时，传入的DTO就必须与hm-api 是相同类型（路径也要相同）
```

就算是名字一样也不行 要保证路径一致 







---



## TODO ：stream 流 以及集合 

**HTTP 请求参数以逗号分割的处理**

在 Web 开发中，我们可能会遇到**前端以逗号分隔参数**的需求，比如：
- 传递多个 ID：`http://example.com/api/users?ids=1,2,3,4`
- 传递多个标签：`http://example.com/api/tags?names=java,python,javascript`

**📌 1. 前端如何构造请求？**

在前端，构造以**逗号分隔**的请求参数可以这样做：

**🟢 JavaScript 示例**

```javascript
const ids = [1, 2, 3, 4];
const url = `http://localhost:8080/api/users?ids=${ids.join(",")}`;

fetch(url)
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error("Error:", error));
```
🔹 `ids.join(",")` 会将数组转换为 `"1,2,3,4"`，形成 `?ids=1,2,3,4`

**📌 2. 后端如何接收请求？**

**🟢 Java Spring Boot 处理逗号分隔的参数**

在 Spring Boot 后端，可以使用 **`@RequestParam`** 接收参数，并转换为 `List<String>` 或 `List<Integer>`。

**（1）基本处理：手动拆分**

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<Integer>> getUsers(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());
        return ResponseEntity.ok(idList);
    }
}
```
📌 **测试 URL**
```
http://localhost:8080/api/users?ids=1,2,3,4
```
📌 **返回结果**
```json
[1, 2, 3, 4]
```
**（2）自动转换：使用 `List<>`**

Spring Boot 允许 `@RequestParam` 直接转换为 `List<>`：
```java
@GetMapping
public ResponseEntity<List<Integer>> getUsers(@RequestParam List<Integer> ids) {
    return ResponseEntity.ok(ids);
}
```
📌 **这样，Spring Boot 会自动把 `?ids=1,2,3,4` 转换为 `List<Integer>`！**



---



这个是最原始的 不涉及**远程调用** 或是 **openfeign**

~~~java
 @Override
    public List<CartVO> queryMyCarts() {
        // 1.查询我的购物车列表
        List<Cart> carts = lambdaQuery().eq(Cart::getUserId, UserContext.getUser()).list();
        if (CollUtils.isEmpty(carts)) {
            return CollUtils.emptyList();
        }

        // 2.转换VO
        List<CartVO> vos = BeanUtils.copyList(carts, CartVO.class);

        // 3.处理VO中的商品信息
        handleCartItems(vos);

        // 4.返回
        return vos;
    }

    private void handleCartItems(List<CartVO> vos) {
        // 1.获取商品id
        Set<Long> itemIds = vos.stream().map(CartVO::getItemId).collect(Collectors.toSet());
        // 2.查询商品
        List<ItemDTO> items = itemService.queryItemByIds(itemIds);
        if (CollUtils.isEmpty(items)) {
            return;
        }
        // 3.转为 id 到 item的map
        Map<Long, ItemDTO> itemMap = items.stream().collect(Collectors.toMap(ItemDTO::getId, Function.identity()));
        // 4.写入vo
        for (CartVO v : vos) {
            ItemDTO item = itemMap.get(v.getItemId());
            if (item == null) {
                continue;
            }
            v.setNewPrice(item.getPrice());
            v.setStatus(item.getStatus());
            v.setStock(item.getStock());
        }
    }
~~~





## openfeign 的最佳实践

以上的方法 如果要远程调用某个微服务那都要写这东西（而且都是写一样的openfeign 万一接口逻辑变了 就要全部修改） 很麻烦 解决方案有两种：

根据**微服务两种结构**来定：

一文件夹多project

![image-20250328221845847](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250328221845847.png)





一project多modules



![image-20250329090830319](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329090830319.png)



**依赖传递：**

在api 模块的pom文件中先导入openfeign的依赖 

然后这个模块的类以及所有东西（依赖）都可以被  导入api模块的模块使用

~~~xml
		<dependency>
			<groupId>com.heima</groupId>
			<artifactId>hm-api</artifactId>
			<version>1.0.0</version>
			<scope>compile</scope>
		</dependency>
~~~



**扫描包问题：**

 ![image-20250329104443827](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329104443827.png)

推荐第一种



## openfeign日志输出

![image-20250329120921995](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329120921995.png)



![image-20250329121305706](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329121305706.png)



feignclient 就是 某个微服务的RPC 方法

其他微服务要调用这个微服务的方法只需要 使用这个feignclient即可

调试的时候再配置即可！！！



**TODO ：枚举类的使用**

~~~java
package com.hmall.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.hmall.common.exception.BadRequestException;
import lombok.Getter;

@Getter
public enum UserStatus {
    FROZEN(0, "禁止使用"),
    NORMAL(1, "已激活"),
    ;
    @EnumValue
    int value;
    String desc;

    UserStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserStatus of(int value) {
        if (value == 0) {
            return FROZEN;
        }
        if (value == 1) {
            return NORMAL;
        }
        throw new BadRequestException("账户状态错误");
    }
}
~~~



---



# 网关



![image-20250329130047614](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329130047614.png)

路由（判断这个请求到底由哪个服务去进行处理 ）确定路径 转发就是通过这个路径发送

（微服务中可能有多个实例 所以使用负载均衡算法挑选一个合适的实例）

![image-20250329130330618](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329130330618.png)

## 网关路由

网关本质也是一个微服务



![image-20250329144857482](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329144857482.png)

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>hmall</artifactId>
        <groupId>com.heima</groupId>
        <version>1.0.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>hm-gateway</artifactId>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
    <dependencies>
        <!--common-->
        <dependency>
            <groupId>com.heima</groupId>
            <artifactId>hm-common</artifactId>
            <version>1.0.0</version>
        </dependency>
        <!--网关-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <!--nacos discovery-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--负载均衡-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
        </dependency>
    </dependencies>
    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
~~~

![image-20250329145249010](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329145249010.png)



**配置路由**

~~~yaml
server:
  port: 8080
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: 192.168.150.101:8848
    gateway:
      routes:
        - id: item # 路由规则id，自定义，唯一
          uri: lb://item-service # 路由的目标服务，lb代表负载均衡，会从注册中心拉取服务列表
          predicates: # 路由断言，判断当前请求是否符合当前规则，符合则路由到目标服务
            - Path=/items/**,/search/** # 这里是以请求路径作为判断规则
        - id: cart
          uri: lb://cart-service
          predicates:
            - Path=/carts/**
        - id: user
          uri: lb://user-service
          predicates:
            - Path=/users/**,/addresses/**
        - id: trade
          uri: lb://trade-service
          predicates:
            - Path=/orders/**
        - id: pay
          uri: lb://pay-service
          predicates:
            - Path=/pay-orders/**

~~~

## 路由属性

![image-20250329151932480](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329151932480.png)

![image-20250329151945860](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329151945860.png)

![image-20250329152109896](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329152109896.png)



最后一个的应用场景：前端 发请求 /api/items/list 但是nginx都可以处理这个 去掉这个前缀 所以就没有这个必要了、

required = false 不是必须的

接口对于请求的数据都可以获取的到！！！



![image-20250329152607161](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329152607161.png)



## 网关登录校验

![image-20250329153639498](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329153639498.png)





![image-20250329153939381](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329153939381.png)

![image-20250329154715595](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329154715595.png)



**自定义过滤器：**





![image-20250329155259367](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329155259367.png)

<span style="font-size:1.3em; background:#990000; color:#FFFFFF;">**自定义GlobalFilter：**</span>



![image-20250329160515426](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329160515426.png)





Ctrl + H 类/方法 查看**继承链（层次）**

![image-20250329161248479](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329161248479.png)

<span style="font-size:1.3em; background:#990000; color:#FFFFFF;">**自定义GatewayFilter过滤器：**</span>

(*不常用)

---



配置类是用于加载配置文件中的属性的 （如果这些属性有用到的地方就要去加载）

### TODO ：自定义异常



~~~java
// 登录校验
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final JwtTool jwtTool;


    // 这个是外部提供的工具类 如果要像上面那样DI 也可以但需要配置spring容器
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 有些路径不需要拦截
        if(isExclude(request.getPath().toString())){
            return chain.filter(exchange);
        }
        // 获取请求头
        List<String> authorization = request.getHeaders().get("Authorization");
        String token = null;
        if(  authorization != null  &&  !authorization.isEmpty()){ // 防止空指针 先判断非空指涉
            token = authorization.get(0);
        }
        Long userId = null;
        try {
            // 解析token
            userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException e){ // 这个就是自定义的异常
            ServerHttpResponse response = exchange.getResponse();
            // 设置响应码 这个HttpStatus不是自己写的
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 不会继续往下执行，直接返回
            return response.setComplete();
        }
        // 放行
        // 传递用户信息 见下述
            String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        return chain.filter(swe);

    }


    @Override
    public int getOrder() {
        return 0;
    }

    private boolean isExclude(String path){
        for(String excludePath:authProperties.getExcludePaths()){
            if(antPathMatcher.match(excludePath, path))
                return true;
        }
        return false;
    }
}

~~~





**网关相关配置：**

~~~yaml
server:
  port: 8080
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: 192.168.254.128:8848
    gateway:
      routes:
        - id: item # 路由规则id，自定义，唯一
          uri: lb://item-service # 路由的目标服务，lb代表负载均衡，会从注册中心拉取服务列表
          predicates: # 路由断言，判断当前请求是否符合当前规则，符合则路由到目标服务
            - Path=/items/**,/search/** # 这里是以请求路径作为判断规则
        - id: cart
          uri: lb://cart-service
          predicates:
            - Path=/carts/**
        - id: user
          uri: lb://user-service
          predicates:
            - Path=/users/**,/addresses/**
        - id: trade
          uri: lb://trade-service
          predicates:
            - Path=/orders/**
        - id: pay
          uri: lb://pay-service
          predicates:
            - Path=/pay-orders/**
hm:
  jwt:
    location: classpath:hmall.jks
    alias: hmall
    password: hmall123
    tokenTTL: 30m
  auth:
    excludePaths:
      - /search/**
      - /users/login
      - /items/**
      - /hi
~~~



### 网关传递用户

拦截器：简化操作（每个微服务都去写获取请求头保存用户信息很麻烦）

![image-20250329183557869](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329183557869.png)

![image-20250329185236398](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329185236398.png)

![image-20250329201426315](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329201426315.png)

区别是：这里不用做拦截 因为真正的拦截已经在网关过滤器做过了

~~~java
// 拦截器要想生效，必须要写配置类
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());

    }
}


------------------------------------------------------------------------
// 拦截器
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
         String userInfo = request.getHeader("user-info");
         if(StrUtil.isNotBlank(userInfo)) {
             UserContext.setUser(Long.valueOf(userInfo));
         }
         return true;


    }
    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();
    }
}
~~~



这个**配置类**也得被 扫描到！！！



### springboot 扫描



在 Spring Boot 中，**组件扫描（Component Scanning）** 是框架自动发现和注册应用程序中组件（如 Controller、Service、Repository 等）的核心机制。它的作用是简化配置，让开发者无需手动声明每个 Bean，而是通过规则自动注册到 Spring 容器中。

**自动装配的核心三阶段**

**(1) 扫描（Scanning）**

- **作用**：在类路径（Classpath）中查找 **候选组件**
- **目标**：识别带有 `@Component`、`@Configuration` 等注解的类
- **范围**：默认扫描 **主类所在包及其子包**

**(2) 注册（Registration）**

- **作用**：将扫描到的组件实例化为 **Bean**，并存入 Spring 容器
- **关键**：`@Bean` 方法或 `@Component` 类会被转换为 Bean 定义

**(3) 使用（Usage）**

- **作用**：通过依赖注入（`@Autowired`）或上下文查找获取 Bean
- **前提**：Bean 必须已在容器中注册



---



<span style="font-size:1.3em; color:#CC0000;">key：**每个微服务是独立的 Spring Boot 应用，拥有自己的 IoC 容器，彼此隔离**</span>

**默认行为：组件扫描范围**
**默认情况下，Spring Boot只会扫描主类所在包及其子包中的组件（包括配置类）。**

例如：

- 模块B的主类在 `com.example.moduleb`
- 模块A的配置类在 `com.example.modulea.config`

此时模块A的配置类**不会**被模块B自动扫描到，因为不在同一包路径下。

假设有两个模块：

- **模块A**：定义了配置类 `ConfigA`
- **模块B**：依赖模块A，并尝试使用 `ConfigA` 定义的 Bean

```yaml
项目结构：
moduleA/
└─ src/main/java
   └─ com.moduleA
      ├─ MainA.java （主类，扫描包：com.moduleA）
      └─ config
         └─ ConfigA.java（@Configuration + @Bean）

moduleB/
└─ src/main/java
   └─ com.moduleB
      ├─ MainB.java （主类，扫描包：com.moduleB）
      └─ service
         └─ ServiceB.java（尝试注入 ConfigA 的 Bean）
```

**1. 模块A的配置类确实被扫描到了吗？**

**(1) 模块A内部**

- `MainA` 扫描 `com.moduleA` 包 → `ConfigA` 被扫描 → 其 `@Bean` 被注册到容器 → **模块A内部可以正常使用这些 Bean**。

**(2) 模块B的视角**

- 模块B的主类 `MainB` 默认扫描 `com.moduleB` 包 → **不会扫描 `com.moduleA.config`** → `ConfigA` **未被注册到容器** → 模块B无法使用其 Bean。

**2. 关键误区：扫描的隔离性**

- **模块A的扫描**：仅影响模块A自身启动时的容器，**不会自动传递到依赖它的模块B**。
- **共享容器**：当模块B作为主模块启动时，它创建自己的容器，而模块A的配置类需要**显式注册到该容器**。





![image-20250403231030296](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250403231030296.png)

由于WebMvcConfigurer 底层是mvc 

而网关底层不是mvc 是响应式客户端

**所以现在需求就是要让一个配置类在指定的地方不生效** How



根据条件生效 conditonal  on xxx

~~~java
// 拦截器要想生效，必须要写配置类
@Configuration
@ConditionalOnClass(DispatcherServlet.class)
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());

    }
}
~~~

![image-20250404095801197](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404095801197.png)



---





### OpenFeign传递用户



问题：微服务通过openfeign 调用其他微服务的时候 用户的信息如何传递呢



![image-20250403230951877](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250403230951877.png)

![image-20250403230930685](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250403230930685.png)







~~~java
public class DefaultConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Long userId = UserContext.getUser();
                if (userId != null) {
                    // 数据类型转化基本方法
                    requestTemplate.header("userId", String.valueOf(userId));
                }

            }
        };
    }
}

~~~



![image-20250403233210957](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250403233210957.png)

网关 --> trade-service（使用openfeign 所以会进入拦截器 （要想让这个配置生效就要指名））-->其他微服务

用户点击xx按钮 触发网关接口 -> 触发**网关层的过滤器**（过滤器进行身份校验 然后再通过请求头封装用户信息 来进行传递到微服务中）->  进入**MVC 拦截器**（在这里微服务获取请求头封装用户信息（threadlocal） 不然每个微服务都得单独写一遍？）

对于微服务之间呢  ：

**OpenFeign 是客户端工具**：负责构造和发送 HTTP 请求 到微服务中 （本质就是客户端工具 就是发起http请求的）

- **服务端拦截器的触发**：仅取决于是否有 HTTP 请求到达服务端，与客户端实现方式无关。

~~~markdown
Spring MVC 拦截器的触发时机
Spring MVC 的拦截器（HandlerInterceptor）会在请求处理流程的特定阶段触发，具体分为以下三个核心方法：

1. preHandle 方法
触发时机：在请求进入 Controller 方法之前执行。

典型用途：权限校验、请求日志记录、参数预处理等。

返回值：

true：继续执行后续拦截器和 Controller 方法。

false：中断请求，直接返回响应。

2. postHandle 方法
触发时机：在 Controller 方法执行之后，视图渲染（View Rendering）之前执行。

典型用途：修改模型数据（ModelAndView）、记录请求处理时间等。

3. afterCompletion 方法
触发时机：在整个请求处理完成之后触发（视图渲染完毕，响应已返回客户端）。

典型用途：资源清理（如数据库连接释放）、全局异常日志记录等。
~~~



问题汇总：

​	**spring 自动装配的扫描问题**

- **`hmall-common` 的角色**：它是一个**公共库模块**，而非独立运行的微服务。它的代码和配置文件会被打包为 JAR 文件，供其他微服务依赖。
- 当其他微服务（如 `service-order`）依赖 `hmall-common` 时，构建工具（Maven/Gradle）会将 `hmall-common` 的 JAR 包添加到 `service-order` 的类路径中。此时，`hmall-common` 中的 `spring.factories` 和配置类对 `service-order` **完全可见**。

~~~markdown
核心原因：Spring Boot 的自动配置机制依赖 类路径扫描 和 模块化设计
你提到的 common 模块（如 hmall-common）中定义的 spring.factories 文件，本质上是一个 “配置清单”，它告诉 Spring Boot：“当应用启动时，自动加载这些配置类”。而其他微服务能生效的关键在于 模块依赖的传递性 和 类路径的共享性。以下是分步解释：

一、模块化设计与依赖传递
假设你的项目结构如下：

复制
hmall-common（公共模块）
  ├─ src/main/resources/META-INF/spring.factories  # 声明公共配置类
  └─ 包含 MyBatisConfig、JsonConfig、WebMvcConfig 等类

service-order（订单微服务）
  └─ 依赖 hmall-common
模块依赖

当 service-order 微服务在 pom.xml 或 build.gradle 中声明依赖 hmall-common 时，构建工具（Maven/Gradle）会将 hmall-common 的 JAR 包包含到 service-order 的类路径中。

传递性：这意味着 hmall-common 的代码和资源文件（包括 META-INF/spring.factories）对 service-order 是可见的。

类路径共享

service-order 启动时，JVM 会加载所有依赖模块的类路径内容，包括 hmall-common 的 META-INF/spring.factories 文件。

Spring Boot 会扫描所有类路径下的 spring.factories 文件，读取其中定义的配置类（如 MyBatisConfig），并自动加载它们。
~~~

**为什么需要显式声明？**

- **设计哲学**：Spring Boot 遵循“约定优于配置”原则，但为了避免类路径中所有 `@Configuration` 类被无差别加载（可能导致冲突），它要求开发者通过 `spring.factories` **显式声明需要自动加载的配置类**。
- **安全性**：显式声明确保只有明确指定的配置类会被加载，避免意外引入不需要的 Bean。



![image-20250404091911523](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404091911523.png)



**组合注解：**

![1743731865390](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1743731865390.png)



类似

~~~java
@EnableFeignClients(basePackages = "com.hmall.api.client")
// 帮你自动扫描并注册 @FeignClient 注解的接口。
//
//配置 basePackages：
//指定要扫描的 Feign 接口包路径。
@MapperScan("com.hmall.cart.mapper")
@SpringBootApplication
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
~~~











|                         |                                                              |
| :---------------------- | :----------------------------------------------------------- |
| `spring.factories` 现状 | 新版本推荐使用 `AutoConfiguration.imports`，但 `spring.factories` |



---



​	**feignClient 配置文件有什么用呢**

当某个 FeignClient 需要**复用一组固定配置**（如自定义编解码器、拦截器、错误处理器等），可以通过 `defaultConfiguration` 指定默认配置类，避免在每个方法或子类中重复配置。

（注意feignclient 有多个）





# 配置管理



![image-20250404153819099](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404153819099.png)

![image-20250404154216024](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404154216024.png)



**配置 ：**

​	外部服务配置

​	业务配置数据

如果硬编码 修改就需要重新编译打包

如果在外部配置问题 就需要重启服务



---



## 配置共享

我们可以把微服务共享的配置抽取到Nacos中统一管理，这样就不需要每个微服务都重复配置了。分为两步：

- 在Nacos中添加共享配置
- 微服务拉取配置

### 添加共享配置

以cart-service为例，我们看看有哪些配置是重复的，可以抽取的：

首先是jdbc相关配置：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YWY5YTJkMjc2MTZlMjYyODY3ZTAyYmZiMjg0NGRiZjdfU01DaUtEV1BJS0J0NTRQZGdOeUI4Y0VPZzhPSEhXcHVfVG9rZW46U0d6TGJjOU9ob21YckJ4THd5ZmNqT1I4blRiXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

然后是日志配置：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YzBkN2FmMDk0MTJkYTVlOTk0NDdlYzM1NWYxYTU2YmJfd2pNRnZKUFMzRWpYNVZvcWtiUDM0MWdJTlR6NVRwSmRfVG9rZW46SER6WGJNb09ob3V6Wk54YjBzZGN0YzJNbnFnXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

然后是swagger以及OpenFeign的配置：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTk0MjVmMTNkZjY2NWM5MGQ5YTY5YzQzMTcyNzEzOGFfb2RNYmZNdHR5VzczMnB0OTFCaGRudjJwWWd2MFJDckpfVG9rZW46UkhrT2J0bG1jb2dHdTF4WFRsVWNlR2lsbm9iXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

我们在nacos控制台分别添加这些配置。

首先是jdbc相关配置，在`配置管理`->`配置列表`中点击`+`新建一个配置：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=N2NhNTdlZTNiYTdhMzQ4YTliYTliZmQwMDZhYzNlNzJfOUhmMWZxQU4yRFJReUN5c0UzVFNpT2ZrN1JnQVdhMjRfVG9rZW46VG9hb2JnWm1Vb08yYU94OXQ3dmNIQ2ZibktmXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

在弹出的表单中填写信息：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MTgzODFhNzI1ZTIxOTU4NzMwYTk3MDM1MzgzMjY1MWRfVzA1bUVqTWRqSWVSSVMxUU9jM01iRGJHb3FvdTQ4TldfVG9rZW46RU52YmJNMkNmb1dhVFh4MkJkUGNEanlzbkVjXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

其中详细的配置如下：

```YAML
spring:
  datasource:
    url: jdbc:mysql://${hm.db.host:192.168.150.101}:${hm.db.port:3306}/${hm.db.database}?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${hm.db.un:root}
    password: ${hm.db.pw:123}
mybatis-plus:
  configuration:
    default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler
  global-config:
    db-config:
      update-strategy: not_null
      id-type: auto
```

注意这里的jdbc的相关参数并没有写死，例如：

- `数据库ip`：通过`${hm.db.host:192.168.150.101}`配置了默认值为`192.168.150.101`，同时允许通过`${hm.db.host}`来覆盖默认值
- `数据库端口`：通过`${hm.db.port:3306}`配置了默认值为`3306`，同时允许通过`${hm.db.port}`来覆盖默认值
- `数据库database`：可以通过`${hm.db.database}`来设定，无默认值

然后是统一的日志配置，命名为`shared-log.``yaml`，配置内容如下：

```YAML
logging:
  level:
    com.hmall: debug
  pattern:
    dateformat: HH:mm:ss:SSS
  file:
    path: "logs/${spring.application.name}"
```

然后是统一的swagger配置，命名为`shared-swagger.yaml`，配置内容如下：

```YAML
knife4j:
  enable: true
  openapi:
    title: ${hm.swagger.title:黑马商城接口文档}
    description: ${hm.swagger.description:黑马商城接口文档}
    email: ${hm.swagger.email:zhanghuyi@itcast.cn}
    concat: ${hm.swagger.concat:虎哥}
    url: https://www.itcast.cn
    version: v1.0.0
    group:
      default:
        group-name: default
        api-rule: package
        api-rule-resources:
          - ${hm.swagger.package}
```

注意，这里的swagger相关配置我们没有写死，例如：

- `title`：接口文档标题，我们用了`${hm.swagger.title}`来代替，将来可以有用户手动指定
- `email`：联系人邮箱，我们用了`${hm.swagger.email:``zhanghuyi@itcast.cn``}`，默认值是`zhanghuyi@itcast.cn`，同时允许用户利用`${hm.swagger.email}`来覆盖。

### 拉取共享配置

接下来，我们要在微服务拉取共享配置。将拉取到的共享配置与本地的`application.yaml`配置合并，完成项目上下文的初始化。

不过，需要注意的是，读取Nacos配置是SpringCloud上下文（`ApplicationContext`）初始化时处理的，发生在项目的引导阶段。然后才会初始化SpringBoot上下文，去读取`application.yaml`。

也就是说引导阶段，`application.yaml`文件尚未读取，根本不知道nacos 地址，该如何去加载nacos中的配置文件呢？

SpringCloud在初始化上下文的时候会先读取一个名为`bootstrap.yaml`(或者`bootstrap.properties`)的文件，如果我们将nacos地址配置到`bootstrap.yaml`中，那么在项目引导阶段就可以读取nacos中的配置了。

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGYwZTQwMjVmZmRkZmE3MDExNjUxNmYzZWY3OGMwNzFfcVBsRTZsN1ppUXJFZUZERXNZeDNadWJmbUF6Q1RTSXVfVG9rZW46TGlkdWJianlob0Y0c0t4RmR3S2NaWGNubldmXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

因此，微服务整合Nacos配置管理的步骤如下：

1）引入依赖：

在cart-service模块引入依赖：

```XML
  <!--nacos配置管理-->
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
  </dependency>
  <!--读取bootstrap文件-->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-bootstrap</artifactId>
  </dependency>
```

2）新建bootstrap.yaml

在cart-service中的resources目录新建一个bootstrap.yaml文件：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MzZjOWJmNTZmNTM2YzYwYzczMWIzMjc1YTQ4MzRkZGJfVEJMUlV4bW5EckUzNHlvYXdtQTloM0JocURodkFpb0tfVG9rZW46VldKQWJEUjJlb1F6SEJ4VGVwZGNPb3MzbktlXzE3NDM3NTI2ODU6MTc0Mzc1NjI4NV9WNA)

内容如下：

```YAML
spring:
  application:
    name: cart-service # 服务名称
  profiles:
    active: dev
  cloud:
    nacos:
      server-addr: 192.168.150.101 # nacos地址
      config:
        file-extension: yaml # 文件后缀名
        shared-configs: # 共享配置
          - dataId: shared-jdbc.yaml # 共享mybatis配置
          - dataId: shared-log.yaml # 共享日志配置
          - dataId: shared-swagger.yaml # 共享日志配置
```

3）修改application.yaml

由于一些配置挪到了bootstrap.yaml，因此application.yaml需要修改为：

```YAML
server:
  port: 8082
feign:
  okhttp:
    enabled: true # 开启OKHttp连接池支持
hm:
  swagger:
    title: 购物车服务接口文档
    package: com.hmall.cart.controller
  db:
    database: hm-cart
```

重启服务，发现所有配置都生效了。



## 配置热更新

有很多的业务相关参数，将来可能会根据实际情况临时调整。例如购物车业务，购物车数量有一个上限，默认是10，对应代码如下：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=NDgxMDA5MTNiNDgxMjdiZDFlNDg3NjU2NWVmMWFiMDFfNlB0VW5KTlBVTEYzWUhOeFR2bm9TZVpMcHJXWVpROXlfVG9rZW46R1FpOWJuNGF0b3M4bWZ4SHJPRGM1QXdIbkpmXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

现在这里购物车是写死的固定值，我们应该将其配置在配置文件中，方便后期修改。

但现在的问题是，即便写在配置文件中，修改了配置还是需要重新打包、重启服务才能生效。能不能不用重启，直接生效呢？

这就要用到Nacos的配置热更新能力了，分为两步：

- 在Nacos中添加配置
- 在微服务读取配置

### 添加配置到Nacos

首先，我们在nacos中添加一个配置文件，将购物车的上限数量添加到配置中：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=NDQxMjcxNzdiODk3MTY5NTYxMWFmYzhmMGQ4ZDFlMzJfTm14Sk55alpaU1hIWjVYejJqell2RlAzcU9FZ2RhTEJfVG9rZW46TnZicmJxSU1Yb1NPdDV4NGNxd2M0a21tbnJnXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

注意文件的dataId格式：

```Plain
[服务名]-[spring.active.profile].[后缀名]
```

文件名称由三部分组成：

- **`服务名`**：我们是购物车服务，所以是`cart-service`
- **`spring.active.profile`**：就是spring boot中的`spring.active.profile`，可以省略，则所有profile共享该配置
- **`后缀名`**：例如yaml

这里我们直接使用`cart-service.yaml`这个名称，则不管是dev还是local环境都可以共享该配置。

配置内容如下：

```YAML
hm:
  cart:
    maxAmount: 1 # 购物车商品数量上限
```

提交配置，在控制台能看到新添加的配置：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YWYyYTMxNjJmMWY1MTljZGZjNWNkZDc2MWE0NWI1MjlfNU51eUZuRW5MTXpmNzNGOWJsOWlMVE1reHBrQnFHRWtfVG9rZW46VzY3SGI3RDlkb1dpYkd4a3JRemNTdUhabnJmXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

### 配置热更新

接着，我们在微服务中读取配置，实现配置热更新。

在`cart-service`中新建一个属性读取类：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=NzE3OTJkOTNjNDc1M2NkNjYwMDMxYWY3MGY1MjUxNmNfUlhWaVFOeU5mb21rWVBaMGFNZnhUTmNZako3bVVlMlBfVG9rZW46UW5QT2JwNVFNbzNscHZ4QldVWWMzdFpPbnhkXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

代码如下：

```Java
package com.hmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxAmount;
}
```

接着，在业务中使用该属性加载类：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTA4YWVlOTA5YTkyMTZkZWFlYjQxZmZkOTgxNzkyNTZfNDEwako4R0R1a3hWS0pZQTY5ZGtNYkp2VHlpUGpuenRfVG9rZW46TG13ZmJyRWpub2ZZUDN4SVZZY2NXaWlvbjVmXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

测试，向购物车中添加多个商品：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2FkY2NkNWFkYTJjYTIyM2MxOTMwNTgyZGM4MWNhOTFfT3MzYTdSMldOUUx5eXFmd3h1NkR0U09DZVhZZ3VieHJfVG9rZW46TFNuMWJwNWNDb1lXa0d4UDVJaWNQcUZPbmNkXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

我们在nacos控制台，将购物车上限配置为5：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YTBiZjkxZjk3Yzg2YjAzNDA1YzUzYzEzNjljODdmOGFfQTF0bFNkSHZOZ083akNRWVlFY3RQZGZMMmNkYW9ZTHdfVG9rZW46WGJTQWJxWGJRb0lOQnd4MUF2VGNGcnhybkxlXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

无需重启，再次测试购物车功能：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=NmRkMzY4MjBiZjZlNjZiNGVlMzgxZDM4YzcxNTNlNGFfZmNtcFE0RFdRRmpYa2Q4Q0JYWUxMdkRwYTZZTVJTUWFfVG9rZW46UlZYOWJkUXRBb2lnZ1N4dzVpeWNwb0VXbjdkXzE3NDM3NTI3Mzk6MTc0Mzc1NjMzOV9WNA)

加入成功！

无需重启服务，配置热更新就生效了！



## 

## 动态路由

网关的路由配置全部是在项目启动时由`org.springframework.cloud.gateway.route.CompositeRouteDefinitionLocator`在项目启动的时候加载，并且一经加载就会缓存到内存中的路由表内（一个Map），不会改变。也不会监听路由变更，所以，我们无法利用上节课学习的配置热更新来实现路由更新。

因此，我们必须监听Nacos的配置变更，然后手动把最新的路由更新到路由表中。这里有两个难点：

- 如何监听Nacos配置变更？
- 如何把路由信息更新到路由表？

### 监听Nacos配置变更

在Nacos官网中给出了手动监听Nacos配置变更的SDK：

https://nacos.io/zh-cn/docs/sdk.html

如果希望 Nacos 推送配置变更，可以使用 Nacos 动态监听配置接口来实现。

```Java
public void addListener(String dataId, String group, Listener listener)
```

请求参数说明：

| **参数名** | **参数类型** | **描述**                                                     |
| :--------- | :----------- | :----------------------------------------------------------- |
| dataId     | string       | 配置 ID，保证全局唯一性，只允许英文字符和 4 种特殊字符（"."、":"、"-"、"_"）。不超过 256 字节。 |
| group      | string       | 配置分组，一般是默认的DEFAULT_GROUP。                        |
| listener   | Listener     | 监听器，配置变更进入监听器的回调函数。                       |

示例代码：

```Java
String serverAddr = "{serverAddr}";
String dataId = "{dataId}";
String group = "{group}";
// 1.创建ConfigService，连接Nacos
Properties properties = new Properties();
properties.put("serverAddr", serverAddr);
ConfigService configService = NacosFactory.createConfigService(properties);
// 2.读取配置
String content = configService.getConfig(dataId, group, 5000);
// 3.添加配置监听器
configService.addListener(dataId, group, new Listener() {
        @Override
        public void receiveConfigInfo(String configInfo) {
        // 配置变更的通知处理
                System.out.println("recieve1:" + configInfo);
        }
        @Override
        public Executor getExecutor() {
                return null;
        }
});
```

这里核心的步骤有2步：

- 创建ConfigService，目的是连接到Nacos
- 添加配置监听器，编写配置变更的通知处理逻辑

由于我们采用了`spring-cloud-starter-alibaba-nacos-config`自动装配，因此`ConfigService`已经在`com.alibaba.cloud.nacos.NacosConfigAutoConfiguration`中自动创建好了：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmU3NjRiOGJlY2UwMDRjYmYyNWIxN2U0MDg3MzYzMjNfZVEyamxPbDE1SVlzZkMzbXY1YW90UFBSUWNRb2NCd1VfVG9rZW46SU5FSWJGWDlXb3pNcnl4MExqdmN4bVYxbnFlXzE3NDM3NTI3OTI6MTc0Mzc1NjM5Ml9WNA)

NacosConfigManager中是负责管理Nacos的ConfigService的，具体代码如下：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=NzQzZWRlYzFiMjhlNWZkZjNiOWZiYzFlZDkyZGZhZGNfZHN0RzNZbW1xY2QyeEhUNHB2c21PMzBGeWEza1g4ZFhfVG9rZW46Sm1xYmJBUG9Ib054ZUF4ZUtpa2NqbjFNbkloXzE3NDM3NTI3OTI6MTc0Mzc1NjM5Ml9WNA)

因此，只要我们拿到`NacosConfigManager`就等于拿到了`ConfigService`，第一步就实现了。

第二步，编写监听器。虽然官方提供的SDK是ConfigService中的addListener，不过项目第一次启动时不仅仅需要添加监听器，也需要读取配置，因此建议使用的API是这个：

```Java
String getConfigAndSignListener(
    String dataId, // 配置文件id
    String group, // 配置组，走默认
    long timeoutMs, // 读取配置的超时时间
    Listener listener // 监听器
) throws NacosException;
```

既可以配置监听器，并且会根据dataId和group读取配置并返回。我们就可以在项目启动时先更新一次路由，后续随着配置变更通知到监听器，完成路由更新。

### 更新路由

更新路由要用到`org.springframework.cloud.gateway.route.RouteDefinitionWriter`这个接口：

```Java
package org.springframework.cloud.gateway.route;

import reactor.core.publisher.Mono;

/**
 * @author Spencer Gibb
 */
public interface RouteDefinitionWriter {
        /**
     * 更新路由到路由表，如果路由id重复，则会覆盖旧的路由
     */
        Mono<Void> save(Mono<RouteDefinition> route);
        /**
     * 根据路由id删除某个路由
     */
        Mono<Void> delete(Mono<String> routeId);

}
```

这里更新的路由，也就是RouteDefinition，之前我们见过，包含下列常见字段：

- id：路由id
- predicates：路由匹配规则
- filters：路由过滤器
- uri：路由目的地

将来我们保存到Nacos的配置也要符合这个对象结构，将来我们以JSON来保存，格式如下：

```JSON
{
  "id": "item",
  "predicates": [{
    "name": "Path",
    "args": {"_genkey_0":"/items/**", "_genkey_1":"/search/**"}
  }],
  "filters": [],
  "uri": "lb://item-service"
}
```

以上JSON配置就等同于：

```YAML
spring:
  cloud:
    gateway:
      routes:
        - id: item
          uri: lb://item-service
          predicates:
            - Path=/items/**,/search/**
```

OK，我们所需要用到的SDK已经齐全了。

### 实现动态路由

首先， 我们在网关gateway引入依赖：

```XML
<!--统一配置管理-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
<!--加载bootstrap-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

然后在网关`gateway`的`resources`目录创建`bootstrap.yaml`文件，内容如下：

```YAML
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: 192.168.150.101
      config:
        file-extension: yaml
        shared-configs:
          - dataId: shared-log.yaml # 共享日志配置
```

接着，修改`gateway`的`resources`目录下的`application.yml`，把之前的路由移除，最终内容如下：

```YAML
server:
  port: 8080 # 端口
hm:
  jwt:
    location: classpath:hmall.jks # 秘钥地址
    alias: hmall # 秘钥别名
    password: hmall123 # 秘钥文件密码
    tokenTTL: 30m # 登录有效期
  auth:
    excludePaths: # 无需登录校验的路径
      - /search/**
      - /users/login
      - /items/**
```

然后，在`gateway`中定义配置监听器：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YWQ4OTIwMDI0ZDZkNjE3MjcwZjI0M2Q1MzI1ODRiYzZfRWpBNWFpZXlCc2FMQndKNmxaYjFkT2pudElaS3B3Rm1fVG9rZW46TUNlRWI4c21xb3ZNcnF4Q292dGNkUGRobkFjXzE3NDM3NTI3OTI6MTc0Mzc1NjM5Ml9WNA)

其代码如下：

```Java
package com.hmall.gateway.route;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.hmall.common.utils.CollUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicRouteLoader {

    private final RouteDefinitionWriter writer;
    private final NacosConfigManager nacosConfigManager;

    // 路由配置文件的id和分组
    private final String dataId = "gateway-routes.json";
    private final String group = "DEFAULT_GROUP";
    // 保存更新过的路由id
    private final Set<String> routeIds = new HashSet<>();

    @PostConstruct
    public void initRouteConfigListener() throws NacosException {
        // 1.注册监听器并首次拉取配置
        String configInfo = nacosConfigManager.getConfigService()
                .getConfigAndSignListener(dataId, group, 5000, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        updateConfigInfo(configInfo);
                    }
                });
        // 2.首次启动时，更新一次配置
        updateConfigInfo(configInfo);
    }

    private void updateConfigInfo(String configInfo) {
        log.debug("监听到路由配置变更，{}", configInfo);
        // 1.反序列化
        List<RouteDefinition> routeDefinitions = JSONUtil.toList(configInfo, RouteDefinition.class);
        // 2.更新前先清空旧路由
        // 2.1.清除旧路由
        for (String routeId : routeIds) {
            writer.delete(Mono.just(routeId)).subscribe();
        }
        routeIds.clear();
        // 2.2.判断是否有新的路由要更新
        if (CollUtils.isEmpty(routeDefinitions)) {
            // 无新路由配置，直接结束
            return;
        }
        // 3.更新路由
        routeDefinitions.forEach(routeDefinition -> {
            // 3.1.更新路由
            writer.save(Mono.just(routeDefinition)).subscribe();
            // 3.2.记录路由id，方便将来删除
            routeIds.add(routeDefinition.getId());
        });
    }
}
```

重启网关，任意访问一个接口，比如 http://localhost:8080/search/list?pageNo=1&pageSize=1：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=OTIzNDc5NDJmYmZjNmY0Yzc1ZjljZTVhZDc3MDRkMDBfdXM3MTlRYmZ1aXZjVDFhM1daRW04U1pRTWo2TE04VWhfVG9rZW46QUk2TWI2bWtub0JOY0F4ckk0cWN1djlVbnhnXzE3NDM3NTI3OTI6MTc0Mzc1NjM5Ml9WNA)

发现是404，无法访问。

接下来，我们直接在Nacos控制台添加路由，路由文件名为`gateway-routes.json`，类型为`json`：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YTRjNWE5MWExMmYxY2I5MmI2ZWJkZjIyM2E3ODljNGNfOUUzNGZtZ29ienJGYmxUWldaVTFwZGxvMGQ0cDRzOW5fVG9rZW46R0ZGTWJyS0Yxb3UyTHB4c2dPeGNUYmZPbmJkXzE3NDM3NTI3OTI6MTc0Mzc1NjM5Ml9WNA)

配置内容如下：

```JSON
[
    {
        "id": "item",
        "predicates": [{
            "name": "Path",
            "args": {"_genkey_0":"/items/**", "_genkey_1":"/search/**"}
        }],
        "filters": [],
        "uri": "lb://item-service"
    },
    {
        "id": "cart",
        "predicates": [{
            "name": "Path",
            "args": {"_genkey_0":"/carts/**"}
        }],
        "filters": [],
        "uri": "lb://cart-service"
    },
    {
        "id": "user",
        "predicates": [{
            "name": "Path",
            "args": {"_genkey_0":"/users/**", "_genkey_1":"/addresses/**"}
        }],
        "filters": [],
        "uri": "lb://user-service"
    },
    {
        "id": "trade",
        "predicates": [{
            "name": "Path",
            "args": {"_genkey_0":"/orders/**"}
        }],
        "filters": [],
        "uri": "lb://trade-service"
    },
    {
        "id": "pay",
        "predicates": [{
            "name": "Path",
            "args": {"_genkey_0":"/pay-orders/**"}
        }],
        "filters": [],
        "uri": "lb://pay-service"
    }
]
```

无需重启网关，稍等几秒钟后，再次访问刚才的地址：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2QxMzlhNjNiMWY1NWNhMzgwY2YyYTRkNTkwNDdlZDNfYmxFVGlMV2ZFQ2pja3RIaXV5STFkNEZsZWpTUmZvaldfVG9rZW46U2s5NWJQYVVZbzFnRVp4WHVod2M5aXREbk9kXzE3NDM3NTI3OTI6MTc0Mzc1NjM5Ml9WNA)

**网关路由成功了！**





# 微服务保护和分布式事务  （link to Redis）

## 服务保护

### 使用Sentinel

![image-20250404101730973](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101730973.png)



Sentinel 的使用可以分为两个部分:

- **核心库**（Jar包）：不依赖任何框架/库，能够运行于 Java 8 及以上的版本的运行时环境，同时对 Dubbo / Spring Cloud 等框架也有较好的支持。在项目中引入依赖即可实现服务限流、隔离、熔断等功能。
- **控制台**（Dashboard）：Dashboard 主要负责管理推送规则、监控、管理机器信息等。

为了方便监控微服务，我们先把Sentinel的控制台搭建出来。

1）下载jar包

下载地址：

https://github.com/alibaba/Sentinel/releases

2）运行

将jar包放在任意非中文、不包含特殊字符的目录下，重命名为`sentinel-dashboard.jar`：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=YzQxNDk2NTU4NWJhYjgzMDBmNTM5N2MyYWVjMTFiN2ZfbDI1WjVvRU5QWkxQZ1J6ak56bUxoTlVWZnMxS2tsam5fVG9rZW46T1pzdmI4bGlZb1Z4S2Z4b3pNR2NIZDlIblJ4XzE3NDM3MzMxMzg6MTc0MzczNjczOF9WNA)

**然后运行如下命令启动控制台：**

cmd写法 一行

```Shell
java -Dserver.port=8090 
-Dcsp.sentinel.dashboard.server=localhost:8090 -Dproject.name=sentinel-dashboard 
-jar sentinel-dashboard-1.8.6.jar
```

pwsh**分行输入**

~~~sh
& java `
  "-Dserver.port=8090" `
  "-Dcsp.sentinel.dashboard.server=localhost:8090" `
  "-Dproject.name=sentinel-dashboard" `
  "-jar" "sentinel-dashboard-1.8.6.jar"

~~~



这个是独立于 我们的项目的

所以需要微服务进行整合



**我们在`cart-service`模块中整合sentinel，连接`sentinel-dashboard`控制台，步骤如下：**

 **1）引入sentinel依赖**

```XML
<!--sentinel-->
<dependency>
    <groupId>com.alibaba.cloud</groupId> 
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```

**2）配置控制台**

修改application.yaml文件，添加下面内容：

```YAML
spring:
  cloud: 
    sentinel:
      transport:
        dashboard: localhost:8090
```

3）访问`cart-service`的任意端点

重启`cart-service`，然后访问查询购物车接口，sentinel的客户端就会将服务访问的信息提交到`sentinel-dashboard`控制台。并展示出统计信息：

![img](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/asynccode)

点击簇点链路菜单，会看到下面的页面：

![image-20250404102238828](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404102238828.png)

所谓簇点链路，就是单机调用链路，是一次请求进入服务后经过的每一个被`Sentinel`监控的资源。默认情况下，`Sentinel`会监控`SpringMVC`的每一个`Endpoint`（接口）。

因此，我们看到`/carts`这个接口路径就是其中一个簇点，我们可以对其进行限流、熔断、隔离等保护措施。

不过，需要注意的是，我们的SpringMVC接口是按照Restful风格设计，因此购物车的查询、删除、修改等接口全部都是`/carts`路径：

![image-20250404102305766](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404102305766.png)

默认情况下Sentinel会把路径作为簇点资源的名称，**无法区分路径相同但请求方式不同的接口**，查询、删除、修改等都被识别为一个簇点资源，这显然是不合适的。

所以我们可以选择打开Sentinel的请求方式前缀，把**`请求方式 + 请求路径`作为簇点资源名**：

首先，在`cart-service`的`application.yml`中添加下面的配置：

```YAML
spring:
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8090
      http-method-specify: true # 开启请求方式前缀
```

然后，重启服务，通过页面访问购物车的相关接口，可以看到sentinel控制台的簇点链路发生了变化：

![image-20250404102323002](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404102323002.png)



本质就是要避免微服务故障 然后 避免传递这个影响

![image-20250404101411966](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101411966.png)

也称**级联失败**

![image-20250404101525092](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101525092.png)



### 请求限流

![image-20250404101623321](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101623321.png)

![image-20250404105046459](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404105046459.png)

qps阈值：每秒请求数 

请求限流：设置qps阈值 也就是限制每秒的并发













### 线程隔离

![image-20250404101656615](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101656615.png)

tomcat是有资源限制的

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404110245168.png)



![image-20250404110621934](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404110621934.png)

## 

| 名称       | 代表含义             | 控制的是…                      |
| ---------- | -------------------- | ------------------------------ |
| QPS        | 每秒放进来的请求次数 | **单位时间内的请求速率**       |
| 并发线程数 | 同时处理的请求数量   | **某一时刻的请求数量（并发）** |

假如一个线程 请求执行500ms 那么**每秒就可以处理两个请求**

5个并发线程 那每秒就可以处理10个请求

---



**tomcat也可以设置这些配置！！！**

需要注意的是，默认情况下SpringBoot项目的tomcat最大线程数是200，允许的最大连接是8492，单机测试很难打满。

所以我们需要配置一下cart-service模块的application.yml文件，修改tomcat连接：

```YAML
server:
  port: 8082
  tomcat:
    threads:
      max: 50 # 允许的最大线程数
    accept-count: 50 # 最大排队等待数量
    max-connections: 100 # 允许的最大连接
```





修改cart-service模块的application.yml文件，开启Feign的sentinel功能：

```YAML
feign:
  sentinel:
    enabled: true # 开启feign对sentinel的支持
```

这样就可以让feignClient 也变成一个簇点资源

!Sentinel控制台支持热更新功能。通过 Sentinel控制台，你可以动态地加载和更新规则，而无需重启应用。这种动态规则的加载和更新对于快速响应系统变化、应对突发流量和维护系统的稳定性非常重要





#### Fallbck

![image-20250404132414920](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404132414920.png)

![image-20250404132442683](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404132442683.png)



![image-20250404132501246](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404132501246.png)

返回这个client对象 并且要实现所有client方法的失败逻辑





**编写这个接口方法如果异常执行的逻辑**

这个方法就是feignclient 中的方法 也就是对应着远程调用的接口方法



![image-20250404132510380](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404132510380.png)



错误处理逻辑 ：不处理的话就是抛出限流异常 

处理的话可以返回给用户一些 友好的数据 如空值 或者 默认值（根据返回值 和 业务逻辑而定）





~~~java
@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        return new ItemClient() {
            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                log.error("扣减商品库存失败");
                throw new RuntimeException(cause);
                // 有些业务不知道如何处理的就直接抛出异常就行了 由调用者来处理即可
                
            }

            @Override
            public List<ItemDTO> getItemsByIds(Collection<Long> ids) {
                log.error("查询商品失败");
                return CollUtils.emptyList();

            }
        }

    }
}

~~~





~~~java
    @Bean
    public ItemClientFallbackFactory itemClientFallbackFactory() {
        return new ItemClientFallbackFactory();
    }

//————————————————————————————————————————————————————————————————————————————————————

@FeignClient(value = "item-service",fallbackFactory = ItemClientFallbackFactory.class)
public interface ItemClient {

    @GetMapping("/items")
    List<ItemDTO> getItemsByIds(@RequestParam("ids") Collection<Long> ids);

    @PutMapping("/items/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> items);


}
~~~





### 服务熔断

![image-20250404101704226](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101704226.png)



![image-20250404144023481](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144023481.png)



![image-20250404144033368](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144033368.png)



![image-20250404144041677](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144041677.png)



熔断了所有请求也是走fallback逻辑

![image-20250404101711369](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404101711369.png)



---



## 分布式事务

![image-20250404144135407](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144135407.png)

每个微服务都有自己的数据库

![image-20250404144212946](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144212946.png)



![image-20250404144241765](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144241765.png)



![image-20250404152802118](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404152802118.png)



**TC 统筹协调 操作事务的提交或者回滚**

这个tc服务本身也是一个微服务 tc 就是seata-server



## 部署TC服务

### 2.2.1.准备数据库表

Seata支持多种存储模式，但考虑到持久化的需要，我们一般选择基于数据库存储。执行课前资料提供的`《seata-tc.sql》`，导入数据库表：

![image-20250404144903398](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144903398.png)

### 2.2.2.准备配置文件

课前资料准备了一个seata目录，其中包含了seata运行时所需要的配置文件：

![image-20250404144917462](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144917462.png)

其中包含中文注释，大家可以自行阅读。

我们将整个seata文件夹拷贝到虚拟机的`/root`目录：

![image-20250404144932866](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404144932866.png)

### 2.2.3.Docker部署

需要注意，要确保nacos、mysql都在hm-net网络中。如果某个容器不再hm-net网络，可以参考下面的命令将某容器加入指定网络：

```Shell
docker network connect [网络名] [容器名]
```

在虚拟机的`/root`目录执行下面的命令：

```Shell
docker run --name seata \
-p 8099:8099 \
-p 7099:7099 \
-e SEATA_IP=192.168.150.101 \
-v ./seata:/seata-server/resources \
--privileged=true \
--network hm-net \
-d \
seataio/seata-server:1.5.2
```

**为什么需要挂载这样的文件夹**

Seata 服务器（Seata Server）默认需要**读取配置文件**，比如：

- `registry.conf`（注册中心配置）
- `file.conf`（事务管理配置）
- 其他自定义配置

如果不挂载目录，Seata 容器内的 `/seata-server/resources/` 目录默认是空的，**你可能需要手动进入容器去配置，非常麻烦**。

挂载本地 `./seata` 目录后：

- 你可以在本地直接修改 `./seata` 里的配置文件。
- 容器内的 Seata 服务器会自动读取这些配置。
- 这样即使容器被删除，你的配置仍然保留在本地。





## 2.3.微服务集成Seata

参与分布式事务的每一个微服务都需要集成Seata，我们以`trade-service`为例。

### 2.3.1.引入依赖

为了方便各个微服务集成seata，我们需要把seata配置共享到nacos，因此`trade-service`模块不仅仅要引入seata依赖，还要引入nacos依赖:

```XML
<!--统一配置管理-->
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
  </dependency>
  <!--读取bootstrap文件-->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-bootstrap</artifactId>
  </dependency>
  <!--seata-->
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
  </dependency>
```

### 2.3.2.改造配置

首先在nacos上添加一个共享的seata配置，命名为`shared-seata.yaml`：

![image-20250404145310474](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404145310474.png)

内容如下：

```YAML
seata:
  registry: # TC服务注册中心的配置，微服务根据这些信息去注册中心获取tc服务地址
    type: nacos # 注册中心类型 nacos
    nacos:
      server-addr: 192.168.150.101:8848 # nacos地址
      namespace: "" # namespace，默认为空
      group: DEFAULT_GROUP # 分组，默认是DEFAULT_GROUP
      application: seata-server # seata服务名称
      username: nacos
      password: nacos
  tx-service-group: hmall # 事务组名称
  service:
    vgroup-mapping: # 事务组与tc集群的映射关系
      hmall: "default"
```

然后，改造`trade-service`模块，添加`bootstrap.yaml`：

![image-20250404145319461](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404145319461.png)

内容如下:

```YAML
spring:
  application:
    name: trade-service # 服务名称
  profiles:
    active: dev
  cloud:
    nacos:
      server-addr: 192.168.150.101 # nacos地址
      config:
        file-extension: yaml # 文件后缀名
        shared-configs: # 共享配置
          - dataId: shared-jdbc.yaml # 共享mybatis配置
          - dataId: shared-log.yaml # 共享日志配置
          - dataId: shared-swagger.yaml # 共享日志配置
          - dataId: shared-seata.yaml # 共享seata配置
```

可以看到这里加载了共享的seata配置。

然后改造application.yaml文件，内容如下：

```YAML
server:
  port: 8085
feign:
  okhttp:
    enabled: true # 开启OKHttp连接池支持
  sentinel:
    enabled: true # 开启Feign对Sentinel的整合
hm:
  swagger:
    title: 交易服务接口文档
    package: com.hmall.trade.controller
  db:
    database: hm-trade
```

参考上述办法分别改造`hm-cart`和`hm-item`两个微服务模块。

### 2.3.3.添加数据库表

seata的客户端在解决分布式事务的时候需要记录一些中间数据，保存在数据库中。因此我们要先准备一个这样的表。

将课前资料的seata-at.sql分别文件导入hm-trade、hm-cart、hm-item三个数据库中：

![image-20250404145328452](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404145328452.png)

结果：

![image-20250404145336088](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250404145336088.png)

OK，至此为止，微服务整合的工作就完成了。可以参考上述方式对`hm-item`和`hm-cart`模块完成整合改造

## 2.4.XA模式

Seata支持四种不同的分布式事务解决方案：

- **XA**
- **TCC**
- **AT**
- **SAGA**

这里我们以`XA`模式和`AT`模式来给大家讲解其实现原理。

`XA` 规范 是` X/Open` 组织定义的分布式事务处理（DTP，Distributed Transaction Processing）标准，XA 规范 描述了全局的`TM`与局部的`RM`之间的接口，几乎所有主流的数据库都对 XA 规范 提供了支持。

### 2.4.1.两阶段提交

A是规范，目前主流数据库都实现了这种规范，实现的原理都是基于两阶段提交。

正常情况：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MmIzNDFiZDIwNzI2NDgzMzc3MTkyMTViZDEyOWE4OTdfSVJ4THFpNlk5SjBnQjVDbFJKSkIwcms4MjN3ZDVicEJfVG9rZW46RkZhSmJZOVByb1V1dmN4c0NLd2MweHpNbno4XzE3NDM3NTc1MTM6MTc0Mzc2MTExM19WNA)

异常情况：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDJlYzk2Y2IxNDdjNmMzZTQ4MDBkMDlmM2FjNjJkY2FfcFJPRjV5R1BOYjAwMVNydzVid2t3OTJFYTJybkFsVTFfVG9rZW46R0RYcGJKS3BWb3kzRGV4MGdOTGNlWUdDbmlmXzE3NDM3NTc1MTM6MTc0Mzc2MTExM19WNA)

一阶段：

- 事务协调者通知每个事务参与者执行本地事务
- 本地事务执行完成后报告事务执行状态给事务协调者，此时事务不提交，继续持有数据库锁

二阶段：

- 事务协调者基于一阶段的报告来判断下一步操作
- 如果一阶段都成功，则通知所有事务参与者，提交事务
- 如果一阶段任意一个参与者失败，则通知所有事务参与者回滚事务

### 2.4.2.Seata的XA模型

Seata对原始的XA模式做了简单的封装和改造，以适应自己的事务模型，基本架构如图：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MTY5OGZkZGVmNjY5MGE2ZjExNzg2MDdiZTc0ZWYyY2NfMFVTclZpTjNvUzFidFh4VEk3Z2pORUJPZlhteUJoWlNfVG9rZW46VmFCemI3cnMwb24yQ2N4V3JPVmNNNlhZbjNnXzE3NDM3NTc1MTM6MTc0Mzc2MTExM19WNA)

`RM`一阶段的工作：

1. 注册分支事务到`TC`
2. 执行分支业务sql但不提交
3. 报告执行状态到`TC`

`TC`二阶段的工作：

1.  `TC`检测各分支事务执行状态
   1. 如果都成功，通知所有RM提交事务
   2. 如果有失败，通知所有RM回滚事务 

`RM`二阶段的工作：

- 接收`TC`指令，提交或回滚事务

### 2.4.3.优缺点

`XA`模式的优点是什么？

- 事务的强一致性，满足ACID原则
- 常用数据库都支持，实现简单，并且没有代码侵入

`XA`模式的缺点是什么？

- 因为一阶段需要锁定数据库资源，等待二阶段结束才释放，性能较差
- 依赖关系型数据库实现事务

### 2.4.4.实现步骤

首先，我们要在配置文件中指定要采用的分布式事务模式。我们可以在Nacos中的共享shared-seata.yaml配置文件中设置：

```YAML
seata:
  data-source-proxy-mode: XA
```

其次，我们要利用`@GlobalTransactional`标记分布式事务的入口方法：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MWYzZDJhYTRiNzAwNTY5YmVkZGM2MWE0NTg5ZjQ1NmRfcDc4VUZrUDUySk9CTDAxRHVZNVBOSlFwc0wyZ1RMOEVfVG9rZW46VW1NMmJ6RWZQb2hadEh4bEM0a2NPa1M2blZoXzE3NDM3NTc1MTM6MTc0Mzc2MTExM19WNA)







---

## 2.5.AT模式

`AT`模式同样是分阶段提交的事务模型，不过缺弥补了`XA`模型中资源锁定周期过长的缺陷。

### 2.5.1.Seata的AT模型

基本流程图：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MjMyYzA1OGNlMWZiNDUwNTgzZmE1ZjlhMjk5NWQ4ODBfN1ViNFVkRldsb3BvdlZPSklTR2I0QnF1NFRuVm9zQ0NfVG9rZW46Q1hRcWJ1SUN2b0I2YVp4MUFSM2NkS0tPbk5iXzE3NDM3NTc1Mzg6MTc0Mzc2MTEzOF9WNA)

阶段一`RM`的工作：

- 注册分支事务
- 记录undo-log（数据快照）
- 执行业务sql并提交
- 报告事务状态

阶段二提交时`RM`的工作：

- 删除undo-log即可

阶段二回滚时`RM`的工作：

- 根据undo-log恢复数据到更新前

### 2.5.2.流程梳理

我们用一个真实的业务来梳理下AT模式的原理。

比如，现在有一个数据库表，记录用户余额：

| **id** | **money** |
| :----- | :-------- |
| 1      | 100       |

其中一个分支业务要执行的SQL为：

```SQL
 update tb_account set money = money - 10 where id = 1
```

AT模式下，当前分支事务执行流程如下：

**一阶段**：

1. `TM`发起并注册全局事务到`TC`
2. `TM`调用分支事务
3. 分支事务准备执行业务SQL
4. `RM`拦截业务SQL，根据where条件查询原始数据，形成快照。

```JSON
{
  "id": 1, "money": 100
}
```

1. `RM`执行业务SQL，提交本地事务，释放数据库锁。此时 money = 90
2. `RM`报告本地事务状态给`TC`

**二阶段**：

1. `TM`通知`TC`事务结束
2. `TC`检查分支事务状态
   1. 如果都成功，则立即删除快照
   2. 如果有分支事务失败，需要回滚。读取快照数据（{"id": 1, "money": 100}），将快照恢复到数据库。此时数据库再次恢复为100

流程图：

![img](https://b11et3un53m.feishu.cn/space/api/box/stream/download/asynccode/?code=MzhkZjNjZWQ2ZTA3NDQ5ZjBjOGNmODg1MThjNzM4NDNfU1BTeDhTdU1PMVBzVWphRVBCSXlOcWVmQXlkWWNnWHJfVG9rZW46SXZuaGJxMU13b2hSYnZ4d0pGVGNzWm9CbkZnXzE3NDM3NTc1Mzg6MTc0Mzc2MTEzOF9WNA)

### 2.5.3.AT与XA的区别

简述`AT`模式与`XA`模式最大的区别是什么？

- `XA`模式一阶段不提交事务，锁定资源；`AT`模式一阶段直接提交，不锁定资源。
- `XA`模式依赖数据库机制实现回滚；`AT`模式利用数据快照实现数据回滚。
- `XA`模式强一致；`AT`模式最终一致

可见，AT模式使用起来更加简单，无业务侵入，性能更好。因此企业90%的分布式事务都可以用AT模式来解决。



---





# MQ入门

MQ就是用于异步通信的

![image-20250329213216428](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329213216428.png)



![image-20250329213657928](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329213657928.png)



## 同步调用

<span style="font-size:1.3em; background:#990000; color:#FFFFFF;">**开闭原则：**</span>

**对扩展开放（Open for extension）**

- 允许对已有系统进行扩展，以满足新的需求，而不需要修改已有代码。
- 通过增加新的代码（如新的类或方法）来增强功能，而不是修改原有代码。

**对修改关闭（Closed for modification）**

- 一旦一个类、模块或函数经过测试并投入使用，就不应该频繁修改其内部实现，否则可能引入新的错误。
- 现有代码不应因新需求而被改动，而是通过 **新增代码** 来适应变化。

---

![image-20250329214504567](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329214504567.png)

## 异步调用

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329214805035.png)





![image-20250329223157238](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329223157238.png)









## MQ技术选型

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329230541290.png)

官网：https://www.rabbitmq.com/

安装：

- 15672：RabbitMQ提供的**管理控制台**的端口
- 5672：RabbitMQ的**消息发送处理接口**

~~~bash
docker run \
 -e RABBITMQ_DEFAULT_USER=itheima \
 -e RABBITMQ_DEFAULT_PASS=123321 \
 -v mq-plugins:/plugins \
 --name mq \
 --hostname mq \
 -p 15672:15672 \
 -p 5672:5672 \
 --network hm-net\
 -d \
 rabbitmq:3.8-management
~~~





![image-20250329234319297](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329234319297.png)

---

多个项目共享 这个RabbitMQ Server

防止冲突，隔离操作。





![image-20250329234428484](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250329234428484.png)







## RabbitMQ 的java客户端

![image-20250330124451858](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330124451858.png)

![image-20250330125259550](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330125259550.png)





![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330125306417.png)

![image-20250330125601171](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330125601171.png)

这边是直接向队列发送！！！



![image-20250330130530293](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330130530293.png)







---



**WorkQueue：**

![image-20250330132922472](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330132922472.png)





![image-20250330132904694](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330132904694.png)



![image-20250330132935856](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330132935856.png)

---

 ## Fanout交换机

- ‌**Fan-in（扇入）**‌：指一个模块或逻辑门允许的输入端数量，即**有多少个上级模块或信号可以调用它**。（被调入）
- ‌**Fan-out（扇出）**‌：指一个模块或逻辑门能够驱动的下级模块或信号数量，即其输出端可以连接的负载数量。 （反之）

多扇入少扇出！！！过高的扇出会增加管理复杂性，可通过增加中间层次优化。

![image-20250330140005200](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330140005200.png)![image-20250330140717058](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330140717058.png)



## Direct交换机

![image-20250330141222439](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330141222439.png)



也可有一样的BindingKey 这样所有队列都可以收到同个RoutingKey的消息

  交换机可以和队列绑定多个key

  ![image-20250330142852275](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330142852275.png)

交换机根据消息的RouteKey转发消息到相应队列中

消费者监听队列 接收消息



![image-20250330142910328](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330142910328.png)

## Topic交换机

TODO:**通配符与正则表达式**

![image-20250330143216056](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330143216056.png)



## 声明队列&交换机

### 基于Bean

(*用到在补充)

配置类中 声明

代码复杂不够优雅

### 基于注解

![image-20250330144605372](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330144605372.png)



## 消息转换器

![image-20250330150152912](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330150152912.png)

message 可以是Object 但是发送时需要发送的是字节 所以 这个方法名叫转换并发送

转换就是由消息转换器来转换的

![image-20250330152146281](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330152146281.png)



![ ](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330152155560.png)





## 业务改造



(*inbox)对黑马商城进行改造 <BASE-{前面微业务拆分业务的完成}>









---

**TODO：最终作业 在redis 点评项目中完成消息队列的应用**  可以和redis一起复习

---



# MQ高级

（*pending）





# ElasticSearch

高性能分布式搜索引擎

基于数据库的模糊搜索性能不好

  ![image-20250330154306726](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330154306726.png)



提供Restful接口就表明 只要发起HTTP请求就可以使用了

![image-20250330154638957](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330154638957.png)

## 安装elasticsearch

通过下面的Docker命令即可安装单机版本的elasticsearch：

```Bash
docker run -d \
  --name es \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  -e "discovery.type=single-node" \
  -v es-data:/usr/share/elasticsearch/data \
  -v es-plugins:/usr/share/elasticsearch/plugins \
  --privileged \
  --network hm-net \
  -p 9200:9200 \
  -p 9300:9300 \
  elasticsearch:7.12.1
```

注意，这里我们采用的是elasticsearch的7.12.1版本，由于8以上版本的JavaAPI变化很大，在企业中应用并不广泛，企业中应用较多的还是8以下的版本。

## 安装Kibana

通过下面的Docker命令，即可部署Kibana：

```Bash
docker run -d \
--name kibana \
-e ELASTICSEARCH_HOSTS=http://es:9200 \
--network=hm-net \
-p 5601:5601  \
kibana:7.12.1
```



## 倒排索引

![image-20250330180929239](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330180929239.png)

![image-20250330180932619](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330180932619.png)

## IK分词器

![image-20250330181756107](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250330181756107.png)

