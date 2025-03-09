 

<center><span style="font-size:3.8em; letter-spacing:2pt; color:#00FF00;">Redis</span></center>

![image-20250126233848639](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250126233848639.png)

SQL : 关系型数据库

**一对多（One-to-Many）**：一个“父表”（如 `Department`）中的一条记录可以关联到“子表”（如 `Employee`）中的多条记录。通常通过外键关联。

**多对一（Many-to-One）**：多个“子表”（如 `Employee`）中的记录可以关联到同一条“父表”（如 `Department`）的记录。`Employee` 表中的外键 `department_id` 指向 `Department` 表。

# Sql和NoSql 区别

### Sql适用场景

1. **事务性系统**：
   - 需要强一致性和事务支持的场景，如银行系统、支付系统。
   - 例如：转账操作必须保证原子性，避免数据不一致。
2. **复杂查询和分析**：
   - 需要频繁进行复杂查询的场景，如报表系统、数据分析。
   - 例如：通过 JOIN 查询用户订单和商品信息。
3. **数据结构稳定**：
   - 数据结构相对固定，不需要频繁变更。
   - 例如：企业管理系统（ERP）、客户关系管理（CRM）。
4. **中小规模数据**：
   - 数据量适中，单机或小型集群即可满足需求。
   - 例如：传统企业的业务系统。

---



### NoSql适用场景

1. **大规模数据存储**：
   - **数据量巨大，需要分布式存储和水平扩展。**
   - 例如：社交网络（如 Facebook、Twitter）的用户数据。
2. **高并发读写**：
   - 需要支持高并发访问的场景，如电商平台的购物车、秒杀系统。
   - 例如：Redis 用于缓存热点数据，MongoDB 用于存储用户会话。
3. **半结构化或非结构化数据**：
   - 数据结构不固定，需要灵活的模式。
   - 例如：日志数据、传感器数据、JSON 文档。
4. **实时数据处理**：
   - 需要快速写入和查询的场景，如物联网（IoT）、实时推荐系统。
   - 例如：Cassandra 用于存储时间序列数据。
5. **图数据**：
   - 需要处理复杂关系的场景，如社交网络、推荐系统。
   - 例如：Neo4j 用于分析用户关系网络。





![image-20250127230557567](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250127230557567.png)

## ACID 与BASE



**ACID是数据库事务的四个关键特性，确保事务的可靠性和一致性。具体如下：**

1. **原子性（Atomicity）**
   事务是一个不可分割的操作单元，要么全部成功，要么全部失败。如果部分操作失败，整个事务**回滚**，数据库状态保持不变。
2. **一致性（Consistency）**
   事务执行前后，数据库必须保持一致性状态，即**符合所有预定义的规则和约束**。
3. **隔离性（Isolation）**
   多个事务**并发执行**时，**彼此互不干扰，**每个事务感觉像是在**独立运行**。数据库通过锁机制或MVCC实现隔离性。
4. **持久性（Durability）**
   事务一旦提交，其结果**永久保存**在数据库中，即使系统故障也不会丢失。



---



**BASE 是一种与 ACID 相对的数据库设计理念，强调系统的高可用性和可扩展性，常用于分布式系统和大规模数据处理。BASE 是以下三个特性的缩写：**

<span style="font-size:2em;">**1.Basically Available（基本可用）**</span>

- 系统在出现故障时，仍然能够提供基本的功能和服务，即使部分数据不可用或响应时间变长。
- 例如，分布式系统中某些节点宕机，其他节点仍能继续提供服务。

<span style="font-size:2em;">**2.Soft state（软状态）**</span>

- 系统的状态可能随时间变化，即使没有外部输入，也可能因为内部机制（如数据同步、缓存过期等）而改变。

- 这意味着系统不保证数据的强一致性，允许存在中间状态。

  **举个例子：分布式缓存系统**

  **场景描述**

  假设有一个分布式缓存系统，用于存储热门商品的信息。多个服务器节点共同维护缓存数据，但缓存数据可能会因为内部机制（如缓存过期、数据同步延迟等）而发生变化。

  **具体过程**

  1. **初始状态**：
     - 商品 A 的价格在缓存中被存储为 100 元。
     - 这个值被复制到多个缓存节点（节点 1、节点 2、节点 3）。
  2. **外部更新**：
     - 商品 A 的价格在数据库中更新为 120 元。
     - 缓存系统开始同步更新。
  3. **内部机制导致状态变化**：
     - **节点 1**：立即更新为 120 元。
     - **节点 2**：由于网络延迟，暂时仍显示 100 元。
     - **节点 3**：缓存过期，暂时没有数据，需要从数据库重新加载。
  4. **用户访问**：
     - 用户 1 访问节点 1，看到价格是 120 元。
     - 用户 2 访问节点 2，看到价格是 100 元。
     - 用户 3 访问节点 3，由于缓存失效，系统从数据库加载最新价格 120 元。
  5. **最终一致性**：
     - 经过一段时间，节点 2 同步更新为 120 元。
     - 所有节点最终显示一致的价格（120 元）。

  ------

  ### 关键点

  - **软状态**：在同步过程中，节点 2 和节点 3 的状态是临时的、不一致的，这是允许的。
  - **内部机制**：网络延迟、缓存过期等内部机制导致状态变化，即使没有外部输入。
  - **最终一致性**：系统不保证实时一致性，但最终会达到一致状态。

  <span style="font-size:2em;">**3.Eventually consistent（最终一致性）**</span>

- 系统不保证数据的实时一致性，但经过一段时间后，数据会达到一致状态。
- 例如，分布式数据库中，数据更新后可能需要一段时间才能同步到所有节点。

---



<span style="font-weight:bold; font-size:1.4em;">Redis 的特征：</span><span style="font-size:1.3em;"></span>

![image-20250127231932830](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250127231932830.png)

**数据操作（核心部分）**

- Redis 的核心数据操作（如读写键值对、执行命令）是**单线程**的。
- 这意味着 Redis 使用一个主线程来处理所有客户端请求，并按顺序执行命令。

**为什么设计为单线程？**

1. **避免竞争条件**：单线程避免了多线程并发操作数据时的锁竞争问题，简化了设计。
2. **高性能**：单线程避免了线程切换的开销，适合内存操作和简单的数据模型。
3. **原子性**：单线程保证了每个操作的原子性，无需额外的锁机制。

从 Redis 6.0 开始，Redis 引入了多线程支持，但主要用于**网络 I/O** 和**后台任务**

---

串行执行指的是多个任务按照**严格的顺序**依次执行，一个任务完成后，才能开始下一个任务。没有任务会同时执行。

原子性是指一个操作或一组操作要么**全部执行成功**，要么**全部不执行**，不会出现部分执行的情况。原子操作是不可分割的，就像物理学中的原子一样。

**特点**

- **不可分割**：操作要么全部完成，要么全部回滚。
- **一致性**：确保系统状态在操作前后保持一致。
- **事务支持**：在数据库中，原子性是事务（Transaction）的四大特性（ACID）之一



---

# Linux 虚拟机中安装redis教程







修改redis后台启动不要阻塞会话窗口 修改这种配置文件一般需要==备份==

**`sudo lsof -i` 是一个用于列出当前系统上所有打开的网络连接的命令。`lsof` 代表 "List Open Files"，是一个用于显示当前系统上所有打开文件的信息的工具。`-i` 参数用于显示所有与网络相关的文件，包括 TCP/UDP 连接、监听端口等。**

**sudo lsof -i :6379**



 **很重要的思想**

![image-20250128103610899](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128103610899.png)

![image-20250128110655059](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128110655059.png)

**后台启动：redis-server redis.conf (在这个文件目录下)**

**停止： kill -9 进程号（PID）**

服务端启动时：Redis 服务端并不进行密码验证。它只是在接收到客户端的连接请求时，要求客户端提供密码（如果已设置）。
密码设置：Redis 在服务端配置 requirepass 时，只是告诉服务器在连接过程中需要验证密码。服务端负责检查密码的正确性，但本身不需要进行认证。

![image-20250128110626924](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128110626924.png)

开机自启

我们也可以通过配置来实现开机自启。

首先，新建一个系统服务文件：

```sh
 vim /etc/systemd/system/redis.service
```

内容如下：

```conf
 [Unit]
 Description=redis-server
 After=network.target
 
 [Service]
 Type=forking
 ExecStart=/usr/local/bin/redis-server /home/guanhui/redis/redis-6.2.6/redis.conf
 PrivateTmp=true
 
 [Install]
 WantedBy=multi-user.target
```

**这样 就能用systemctl**

**然后重载系统服务：**

```sh
 systemctl daemon-reload
```

**现在，我们可以用下面这组命令来操作redis了：**

```sh
 # 启动
 systemctl start redis
 # 停止
 systemctl stop redis
 # 重启
 systemctl restart redis
 # 查看状态
 systemctl status redis
```

**执行下面的命令，可以让redis开机自启：**

```sh
 systemctl enable redis
```



可以**编写系统服务文件**(是不是window也可以这样呢 举一反三) 实现开机自启动 或是其他配置



# 分布式 与高并发

分布式存储 和 水平垂直扩展都是什么意思 集群 主从 分片集群都是什么意思 第二个视频







## 模式匹配与正则表达式

| 特性               | **模式匹配**                          | **正则表达式**                               |
| ------------------ | ------------------------------------- | -------------------------------------------- |
| **语法复杂度**     | 简单，使用 `*`、`?` 和 `[abc]` 等符号 | 较复杂，支持字符类、量词、分组等高级功能     |
| **灵活性**         | 较低，适用于简单的匹配需求            | 很高，适用于复杂的匹配需求                   |
| **性能**           | 高效，通常比正则表达式更快            | 复杂的正则表达式可能性能较差                 |
| **常见应用场景**   | 文件匹配、简单的字符串查找            | 字符串处理、日志分析、文本替换、URL 路由     |
| **支持的匹配规则** | `*`、`?`、`[abc]` 等简单规则          | 支持字符类、量词、分组、断言、回溯等复杂规则 |



# Redis 命令与数据结构



![image-20250128131827371](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128131827371.png)

**TTL 显示-1就表示无限期**

##  String

 ![image-20250128132125841](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128132125841.png)

![image-20250128132733684](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128132733684.png)

![image-20250128161341421](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128161341421.png)





## Hash

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128161607242.png)



![image-20250128161652897](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128161652897.png)

## List

![image-20250128163614492](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128163614492.png)

![image-20250128163844099](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128163844099.png)

**star 和end 也都是闭合区间**

**角标从0开始**

## Set

![image-20250128164509108](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128164509108.png)

![image-20250128165126055](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128165126055.png)

## SortedSet

![image-20250128165338645](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128165338645.png)

![image-20250128165513793](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128165513793.png)

range 就是按照排序的序号 

rangebyscore 就是按分数



**成员也就是数据结构中的元素**   member

**默认升序 ，角标从0开始** ZRANK  降序：ZREVRANK

**倒序就加个REV而已**



**min** 和 **max** 都是闭合的：在执行范围查询时，所指定的最小值和最大值都会包括在查询结果中。



# Redis java客户端

![image-20250128171333232](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128171333232.png)

![image-20250129160904015](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250129160904015.png)

序列化（Serialization）和反序列化（Deserialization）是编程中常用的概念，用于将数据结构或对象转换为可以存储或传输的格式，以及将存储或传输的格式转换回原始的数据结构或对象。

### 序列化（Serialization）

序列化是指将内存中的对象或数据结构转换为**字节流（binary format）或其他格式（如 JSON、XML 等）**，**以便于存储或在网络上传输**。序列化后的数据可以保存到文件系统中，也可以通过网络发送到远程系统。在序列化过程中，对象的状态（如变量值、对象属性等）被编码成一个持久化的格式，使其可以在不同的平台或语言之间进行传递和使用。

#### 序列化的目的：

- **持久化存储**：将对象保存到文件或数据库中，以便于后续读取和恢复。
- **网络传输**：通过网络发送数据，例如在客户端和服务器之间传递对象。

### 反序列化（Deserialization）

反序列化是序列化的逆过程，即将序列化后的数据恢复为原始的对象或数据结构。在反序列化过程中，从序列化的数据中读取字节流或其他格式，重构原始的对象或数据结构，以便程序可以继续操作和使用它们。

#### 反序列化的作用：

- **从持久化存储中读取数据**：从文件或数据库中读取序列化的数据，并将其转换为可用的对象。
- **处理接收到的网络数据**：接收网络传输的数据，解析并转换为原始对象供程序使用。





**引入依赖后进行设置配置文件：**springboot.md中也有涉及

![image-20250128172048356](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128172048356.png)

![image-20250128172213800](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250128172213800.png)





![image-20250129162937162](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250129162937162.png)



![image-20250129162933380](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250129162933380.png)

**熟练java的集合api用法**





![image-20250129164044199](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250129164044199.png)

# 项目实战

![image-20250129173554238](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250129173554238.png)

**前端部署在NGINX 后端部署在TomCat上**

### nginx

**安装：docker， 包管理器**

代理：vpn作为代理服务器 作为客户端代理我们的客户端去访问服务端

**正向代理就是代理客户端 而反向代理就是代理服务端**



反向代理：

**google对外暴露的只有一个域名！** 但是背后有多台服务器

![image-20250203143842779](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250203143842779.png)

从而隐藏了真实服务器的IP地址和端口等信息 这里的代理服务器代理的是服务端，这个过程对于客户端是透明的



**application.yaml**

~~~yaml
server:
  port: 8081
spring:
  application:
    name: hmdp
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/wsj_comment?useSSL=false&serverTimezone=UTC
    username: root
    password: 123456
  redis:
    host: 192.168.254.128
    port: 6379
    password: 0621
    lettuce:  # 这个和jedis 一样都是redis的java客户端 springdataredis 整合了这两个工具
      pool:
        max-active: 10
        max-idle: 10
        min-idle: 1
        time-between-eviction-runs: 10s
  jackson:
    default-property-inclusion: non_null # JSON处理时忽略非空字段
mybatis-plus:
  type-aliases-package: com.hmdp.entity # 别名扫描包  这个就是到时候在mapper xml文件中不用写全类名了而已
logging:
  level:
    com.hmdp: debug
~~~

## ==（版本问题）配置环境时出现的问题==



 **Redis 6.0+ 默认要求更强的密码**

（**涉及密码尽量不要太简单！！！** 特别是这种很重要的）

涉及远程连接的 一般 都需要 开放网络接口 bind 0.0.0.0 

还需要开放防火墙端口暴露外界 

**linux sql 远程连接**的时候 也和这个类似     **user@host**          **root@192.168.254.128**

检查应用程序是否在本机上监听该端口：

- **使用 `ss` 命令**（推荐，适用于现代 Linux 发行版）：

  ```bash
  ss -tuln | grep <port_number>
  ```

`sudo firewall-cmd --list-ports` 命令的输出 `6379/tcp` 表示防火墙当前开放了 **TCP 协议的 6379 端口**。这个端口是 Redis 默认的端口。





如果你看到的是类似这样的输出，意味着防火墙允许从外部连接到 **6379** 端口。

**如果你希望开放其他端口，可以使用类似以下命令来添加端口：**

```bash
sudo firewall-cmd --add-port=3306/tcp --permanent # 添加端口暴露
 
sudo firewall-cmd --reload # 加载

sudo systemctl status firewalld # 查看防火墙服务状态
```

创建用户并授权：

```sql
CREATE USER 'senjay'@'%' IDENTIFIED BY '0621';
GRANT ALL PRIVILEGES ON database_name.* TO 'username'@'192.168.254.1';
FLUSH PRIVILEGES;
```

注意点 ： 在mysql服务器上创建**主机用户**（user@host（要使用sql的host）） **要使用vmnet8 ipv4地址** 然后在mysql客户端工具连接 

### **MySQL 用户身份的本质**

在 MySQL 中，用户身份由以下两部分组成：

```sql
'用户名'@'主机名'
```

✅ **`'senjay'@'%'`** 与 ✅ **`'senjay'@'192.168.254.1'`** 是**不同的用户**。

> ➔ 即使两者用户名相同，由于**主机名（Host）不同**，MySQL 仍将其视为**不同用户**，拥有各自独立的权限。

![image-20250309163019750](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250309163019750.png)

如果要后台运行不想要阻塞前台可以试试daemon 守护进程



插件之间版本不兼容 springboot3 和mybatis-plus3/jdk17 

一些spring的模块之间都会不兼容 **-->parent标签**  

springboot3  和2有些工具类或是接口/注解在的包不一样 --> 重新导入就好了

在 **MySQL 5.x 及更早版本**，驱动类是：

```
com.mysql.jdbc.Driver
```

从 **MySQL 6.0 及以上（特别是 MySQL 8+）**，驱动类改为：

```
com.mysql.cj.jdbc.Driver
```

==Deprecated== 已被废弃 有些版本可能废弃但是还是会支持

**Spring Boot 2.5+** 及更高版本中，如果参数是 **简单类型（如 `String`、`int`、`double` 等）**，并且方法参数的名称与前端传递的参数名称一致，Spring Boot **可以自动解析 Query String 或表单数据**，即 `@RequestParam` **可以省略**：



---



**DTO（Data Transfer Object，数据传输对象）** 是一种用于在不同系统、层或模块之间传输数据的对象。它的主要作用是封装数据，以减少方法调用时的参数数量，并提高系统的可维护性和可读性。

## **DTO 的特点**

1. **无业务逻辑**：DTO 只用于数据传输，不包含任何业务逻辑。
2. **可序列化**：通常可用于序列化和反序列化，以便在网络、数据库或文件存储中传输数据。
3. **简化数据结构**：避免直接暴露数据库实体（Entity），有助于数据的安全性和解耦。
4. **减少远程调用开销**：在分布式系统中，可以通过 DTO 一次性传输多个字段，减少远程调用的次数。

## **DTO 的应用场景**

1. **前后端交互**：后端向前端返回 DTO，而不是直接暴露数据库实体。
2. **微服务通信**：不同微服务之间的数据传输使用 DTO，以保持 API 的稳定性。
3. **数据库查询结果封装**：数据库查询的结果可能与数据库实体不完全一致，可以使用 DTO 进行封装。



**POJO（Plain Old Java Object）** 直译为“普通的 Java 对象”，指的是一个没有特殊限制的 Java 类。它通常符合以下特点：

- **只有属性和方法**，不依赖于任何特定的框架（如 Spring、JPA）。
- **没有业务逻辑**，通常只包含 **字段（变量）**、**getter/setter 方法**、**构造方法** 和 **toString()** 方法。
- **可用于任何 Java 项目**，没有特殊要求。

**Entity（实体类） 是 POJO 的一种，但它专门用于数据库交互**



## 使用session 记录登录信息 而不是token

~~~java
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public Result sendCode(String phone, HttpSession session) {
        if(RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误");
        }else {
            String code = RandomUtil.randomNumbers(6); // 使用类库 / 工具包
            session.setAttribute("code", code); // 保存验证码到session
            log.debug("发送短信验证码成功，验证码：{}", code); // 模拟 要是真的就得调用第三方API
            return Result.ok("发送成功");
        }
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
          if(RegexUtils.isPhoneInvalid(loginForm.getPhone())) {
              return Result.fail("手机号格式错误"); // 为什么这里还需要验证？ 如果修改了phone 那么传过来的loginform中的phone就是修改后的phone 就会有问题
          } // 但这里也有问题 思考think:就是如果输入的手机号和发短信的手机号不一样 但是符合规则到时候根据手机号拆查询账号就会有问题
              String cacheCode = (String) session.getAttribute("code");  // session 是会过期的 过期了就获取不到验证码
              String code = loginForm.getCode(); // 获取用户输入的验证码
              String phone = loginForm.getPhone();
               if(cacheCode == null || !cacheCode.equals(code)) {
                   return Result.fail("验证码错误!");
               }
               User user = query().eq("phone",phone).one(); // 使用mp的 crud api
               if(user == null) {
                   user = createUserWithPhone(phone);
               }

               session.setAttribute("user", BeanUtil.copyProperties(user, UserDTO.class)); // 还要将userDTO存入session

               return Result.ok("登录成功");
    }
    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        // import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX; 此为静态导入 而且这样解耦合统一规范性良好
        save(user);
        return user;
    }
}

~~~

**拦截器**

~~~java
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserDTO user  = (UserDTO) session.getAttribute("user"); // session 获取到达是userDTO实例
        if(user==null) {
            response.setStatus(401);
            return false;
        }

        // 存入localThread中
        UserHolder.saveUser(user);

        return true;


    }

    // 避免内存泄露
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}

~~~



---



# Mybatis Plus 简要入门

**学习阶段多使用sql语句 不要去特意学mp**

`@Slf4j` 是 **Lombok** 库提供的一个注解，它用于 **自动生成日志记录器**





### **1️⃣ `ShopMapper` 继承自 `BaseMapper<Shop>`**

```java
public interface ShopMapper extends BaseMapper<Shop> {
}
```

- `ShopMapper` 继承自 `BaseMapper<Shop>`，这是 **MyBatis Plus** 提供的 **基础 Mapper 接口**。

- `BaseMapper` 提供了**通用的 CRUD 操作方法**

  ，因此，ShopMapper

   自动获得了对 Shop实体类的常用数据库操作方法，比如：

  - `selectById(Long id)`：根据主键查询。
  - `insert(Shop entity)`：插入数据。
  - `deleteById(Long id)`：根据主键删除。
  - `updateById(Shop entity)`：根据主键更新。
  - `selectList(Wrapper<Shop> queryWrapper)`：根据条件查询。
  - `selectPage(Page<Shop> page, Wrapper<Shop> queryWrapper)`：分页查询。

因此，`ShopMapper` 本身就包含了这些基础的数据库操作方法。

### **2️⃣ `IShopService` 接口**

```java
public interface IShopService extends IService<Shop> {
    public Shop queryById(Long id) throws Exception;
    public Result update(Shop shop);
}
```

- `IShopService` 继承自 **`IService<Shop>`**，这是 **MyBatis Plus** 提供的 **服务层接口**。
- `IService` 包含了 **通用的服务层方法**，例如：`save()`, `remove()`, `list()` 等方法。
- 通过继承 `IService<Shop>`，`IShopService` 可以使用 MyBatis Plus 提供的基本服务方法，同时可以 **定义一些自定义的业务逻辑方法**，例如 `queryById` 和 `update`。

### **3️⃣ 区别与联系**

| 维度         | `ShopMapper`                                        | `IShopService`                                               |
| ------------ | --------------------------------------------------- | ------------------------------------------------------------ |
| **职责**     | 负责与数据库的交互，执行基础的 CRUD 操作。          | 负责业务逻辑，包含更复杂的操作和服务层的逻辑。               |
| **继承**     | 继承自 `BaseMapper<Shop>`，自动获得 CRUD 操作。     | 继承自 `IService<Shop>`，提供更多通用服务方法，且可以自定义方法。 |
| **方法实现** | `BaseMapper` 中的 CRUD 方法自动实现，无需手动编写。 | 需要自定义实现业务逻辑方法，例如 `queryById` 和 `update`。   |
| **使用场景** | 用于数据访问层（DAO），直接操作数据库。             | 用于业务逻辑层（Service），处理实际的业务需求，调用 `Mapper` 层进行数据操作。 |

**如何确定查询的表**: ---》 ==根据实体类==

`ShopMapper` 的泛型是 `Shop`，而 `Shop` 实体类通过 `@TableName("shop")` 注解指定了 `shop` 表。

---

## 拦截器和ThreadLocal



![image-20250129222109653](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250129222109653.png)



![image-20250201105808363](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201105808363.png)

**将用户信息存入ThreadLocal 这样controller 能拿到**



1. preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

   - 意义：在请求被处理之前调用。
   - 执行时机：**在 Controller 方法执行之前。**
   - 返回值：boolean
     - 如果返回 true，**继续执行后续的拦截器和处理器。**
     - 如果返回 false，**则中断执行，不会执行后续的拦截器和处理器。**
   - 用途：
     - 进行请求的预处理（如登录检查、权限验证）。
     - 设置一些通用的数据。
     - 可以通过 response 直接向客户端返回响应。

2. postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)

   - 意义：在请求被处理后，视图渲染之前调用。
   - 执行时机：在 Controller 方法执行之后，视图渲染之前。
   - 返回值：void
   - 用途：
     - 可以对请求域中的模型和视图做出进一步修改。
     - 可以在此处记录一些信息，如处理时间等。
   - 注意：只有在 preHandle() 返回 true 时才会被调用。

   

3. afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)

   - 意义：在整个请求结束之后调用。
   - 执行时机：视图渲染之后。
   - 返回值：void
   - 用途：
     - 进行资源清理。
     - 记录日志信息。
     - 可以根据 ex 是否为 null 判断是否发生了异常，进行相应处理。
   - 注意：只有在 preHandle() 返回 true 时才会被调用。

   ---

   

~~~java
package com.senjay.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    //提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

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

用户发起请求 → `preHandle` 被调用（在请求处理前）

Controller 方法执行 → `postHandle` 被调用（请求处理之后、视图渲染之前）

视图渲染并生成响应内容

`afterCompletion` 被调用（视图渲染后、响应发送给客户端之前）

如果使用的是 **前后端分离**（通常是指前端使用现代 JavaScript 框架如 React、Vue.js、Angular 等，后端提供 API），那么通常情况下不需要使用 `postHandle`。原因如下：

### 前后端分离的情况：

1. 视图不再由服务器渲染

   ：

   - 在前后端分离架构中，后端通常只负责提供 API 接口（如 RESTful 或 GraphQL），而前端负责通过 JavaScript 渲染页面。即后端返回的是 JSON 数据而不是 HTML 视图。
   - 由于后端不再负责渲染视图（例如在传统的 MVC 中，后端生成 HTML 页面并返回），所以就不需要在视图渲染之前修改模型数据（即 `postHandle` 的主要用途）。

2. 前端负责渲染

   ：

   - 前端通过调用后端提供的 API 获取数据，然后由前端框架（React、Vue 等）处理数据并渲染页面。因此，后端只需要关心数据的返回，而不需要关心如何渲染这些数据到视图中。

3. 响应是纯数据

   ：

   - 后端响应的通常是数据（如 JSON、XML 等），而不是视图。前端会根据这个数据进行视图的更新。这样，`postHandle` 的作用就变得不那么重要了，因为它原本是为了在视图渲染之前修改模型数据（比如在传统的服务器渲染中修改模型数据后渲染页面）。

---

![f25ee2851a9073c007bc16660683b4e](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/f25ee2851a9073c007bc16660683b4e.jpg)

| **对比项**                              | **@Resource**                                                | **@Autowired**                                               |
| --------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **来源**                                | 来自 `javax.annotation.Resource`（JDK 提供，需 `jakarta.annotation-api` 依赖） | 来自 `org.springframework.beans.factory.annotation.Autowired`（Spring 提供） |
| **注入方式**                            | 通过 **名称**（默认）或 **类型** 进行依赖注入                | 通过 **类型** 进行依赖注入                                   |
| **默认行为**                            | 默认按 **名称** 匹配 (`name` 属性)，找不到再按 **类型** 匹配 | 默认按 **类型** 匹配                                         |
| **可选项**                              | `@Resource(name="beanName")` 可指定 bean 名称                | `@Autowired(required=false)` 可选依赖                        |
| **是否支持 `@Primary` 和 `@Qualifier`** | 不支持 `@Primary`，可结合 `name` 使用                        | 支持 `@Primary`，可与 `@Qualifier` 结合使用                  |
| **支持构造方法注入**                    | **不支持** 构造方法注入                                      | **支持** 构造方法注入                                        |
| **支持 `Optional<T>`**                  | 不支持                                                       | 支持                                                         |
| **使用场景**                            | 用于 Java 规范的兼容性，适用于 **与 Java EE 兼容的项目**     | 适用于 **Spring 框架中的依赖注入**                           |

**如果多个类实现了同一个==接口==** 然后对这个接口进行依赖注入的话 ==Autowired== 就会默认先按照类型进行加载



~~~java
package com.hmdp.utils;

import com.hmdp.dto.UserDTO;

public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user){
        tl.set(user);
    }

    public static UserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
// 这个·和入门项目工具类的区别就是这个是专门存储用户数据 而且这个用户数据还又经过了优化不是单纯的User类 那个是啥都可以存
// 为什么不是单纯的User类因为User类包括一些敏感信息

~~~

要想让拦截器生效必须配置拦截器的配置 webConfig

~~~java

// 一定要有Configuration 这个注解
package com.hmdp.config;

import com.hmdp.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) // 引入拦截器
                .excludePathPatterns(
                  "/user/login",
                  "/user/code",
                  "/blog/hot",
                  "/shop-type/**",
                  "/shop/**",
                  "/upload/**",
                  "/voucher/**"

                );
    }
}

~~~

### 强转规则

### 强制类型转换的前提条件：

1. **继承关系**：

   - 父类与子类之间可以进行强制类型转换。
   - 子类对象可以强制转换为父类对象（向上转型），父类对象可以强制转换为子类对象（向下转型）。
   - 向下转型（父类转换为子类）时需要确保该对象实际上是子类的实例，否则会抛出 `ClassCastException`。

2. **接口实现**：

   - 如果两个类实现了相同的接口，也可以进行强制类型转换。
   - 
   - 这时强制转换的前提是被转换的对象实际上是目标类型的实例。

   

   

   **子类赋值给父类（向上转型）**
   **特性：**

   **隐式转换（不需要强制类型转换）。**  ==子类-->父类==
   **安全，不会发生 ClassCastException。**
   **父类引用只能访问父类中定义的方法，不能访问子类的特有方法。**

也就是说 需要父类的 实际可以传一个子类 (这个也和需要一个接口 可以传实现它的所有类 一样)



**父类对象强制转换为子类（向下转型）**

**特性：**

- **需要强制类型转换**，必须显式使用 `(子类名)`。
- **可能抛出 `ClassCastException`**，如果（强转的对象）原对象不是子类的实例，转换会失败。
- 转换后，可以访问子类特有的方法。

==父类-->子类==

~~~java
  Parent p = new Child();  // 向上转型（隐式）
        
  Child c = (Child) p;  // 向下转型（显式）
~~~



---

**注意：**

有时候地址栏输入接口名访问不到的问题原因：

确保你访问的 URL 是 **GET 请求**，如果后端是通过 `@PostMapping` 或其他请求方式处理的请求，使用 **GET 请求访问会返回 404**。

例如，假设后端定义了 POST 请求：

```
java复制编辑@PostMapping("/login")
public String login(@RequestParam String username, @RequestParam String password) {
    // 登录逻辑
    return "login-success";
}
```

如果你使用浏览器地址栏直接输入 `http://yourdomain/login`，浏览器会默认发起 **GET 请求**，而此时你可能会收到 404，因为 `/login` 的映射是 `POST` 请求。





## session 带来的问题 和使用Token 结合redis

![image-20250131235125362](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250131235125362.png)

![image-20250201000457250](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201000457250.png)



~~~java
package com.hmdp.utils;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 36000L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_SHOP_TTL = 30L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
    public static final String BLOG_LIKED_KEY = "blog:liked:";
    public static final String FEED_KEY = "feed:";
    public static final String SHOP_GEO_KEY = "shop:geo:";
    public static final String USER_SIGN_KEY = "sign:";
}
// 专门设置一个工具类 放置一些常量 用的比较多的就需要使用这个 到时候修改很方便 统一整体性
~~~

**通过静态导入**





---

### Spring容器管理



只有被 Spring 容器管理的类才能进行依赖注入。如果类没有被标记为 Spring 组件（例如没有使用 `@Component`、`@Service`、`@Repository` 或 `@Controller` 注解），它将不会被 Spring 扫描并管理，因此无法注入依赖。

#### 构造函数注入

~~~java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;  // 自动注入 StringRedisTemplate

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate)) 
                .excludePathPatterns(
                        "/user/login",
                        "/user/code",
                        "/blog/hot",
                        "/shop-type/**",
                        "/shop/**",
                        "/upload/**",
                        "/voucher/**"
                );
    }
}
~~~

~~~java
public class LoginInterceptor implements HandlerInterceptor {
    // @Resource
   // private StringRedisTemplate stringRedisTemplate;
    // 不是spring容器管理用不了依赖注入

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    } // 构造函数方法 接收参数
~~~



#### 依赖注入

```java
package com.hmdp.config;

import com.hmdp.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)  // 由Spring容器管理
                .excludePathPatterns(
                        "/user/login",
                        "/user/code",
                        "/blog/hot",
                        "/shop-type/**",
                        "/shop/**",
                        "/upload/**",
                        "/voucher/**"
                );
    }
}
```

~~~java
@Component // 标记让他被spring容器管理
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
~~~

**`@Autowired` 依赖注入**：
使用 `@Autowired` 使得类之间的依赖关系更加松耦合。类不需要自己管理它们的依赖，而是将这些依赖交给 Spring 容器处理。这使得代码更容易维护、测试和扩展。

**`new` 实例化**：
使用 `new` 会导致类之间的强耦合。每个类都必须自己创建其所依赖的其他类实例，这增加了代码的耦合性，降低了可测试性。

### 1. **字段注入（使用 `@Autowired` 注解在字段上）**

字段注入是最直接的方式，Spring 会自动为类的字段注入依赖项，通常你不需要写 setter 方法或构造函数。

#### 示例代码：

```java
java复制编辑import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private DiscountService discountService;

    public void placeOrder(Order order) {
        // 处理订单业务逻辑
    }
}
```

#### 优点：

- 简单、易用，代码非常简洁。
- 适合依赖较少且不复杂的情况。

#### 缺点：

- **强耦合**：字段注入会使类直接依赖于 Spring 容器管理的实例，导致类的测试变得更难，因为 Spring 控制了这些字段的实例化，无法灵活地替换依赖项。
- **不易发现依赖**：通过字段注入，类的依赖关系不像构造函数注入那样显而易见，尤其是在一个大型应用中，查看类的构造函数并列出所有的依赖关系是更清晰的方式。

### 2. **构造函数注入（使用 `@Autowired` 注解在构造函数上）**

构造函数注入是最推荐的方式，Spring 会通过构造函数来注入依赖项。构造函数注入显式地列出了类的所有依赖关系，更容易理解和维护。

#### 示例代码：

```java
java复制编辑import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;
    private final DiscountService discountService;

    @Autowired  // 可选的，如果构造函数只有一个参数，Spring 会自动注入
    public OrderService(OrderRepository orderRepository,
                        PaymentService paymentService,
                        InventoryService inventoryService,
                        NotificationService notificationService,
                        DiscountService discountService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.discountService = discountService;
    }

    public void placeOrder(Order order) {
        // 处理订单业务逻辑
    }
}
```

#### 优点：

- **松耦合**：通过构造函数传入依赖，`OrderService` 不需要关心这些依赖是如何创建的，完全依赖 Spring 容器。
- **易于测试**：构造函数注入使得测试更容易，因为可以直接传入 mock 对象，避免了 Spring 容器的参与。
- **必需的依赖明确**：所有必需的依赖都明确列在构造函数中，避免遗漏依赖。

#### 缺点：

- **依赖多时可能不够简洁**：如果一个类依赖的服务过多，构造函数参数可能会很长。

**注：**==参数列表中的类也必须被spring容器管理 这样才会自动注入依赖==



## 拦截器优化业务思路



![image-20250201231727216](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201231727216.png)

![image-20250201231733474](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201231733474.png)

![image-20250201231740167](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201231740167.png)

**场景：**

​	**优化前：只有需要登录的http请求才会经过拦截器才会更新redis TTL**

**要求：让所有http请求都会更新redis TTL**

**业务实现：那就让所有请求都经过拦截器但是我都会放行 并且在这个拦截器就更新redis TTL --> 在拦截器外再套一个拦截器相当于滤网 ，层层过滤**



# 缓存

**通过前任迭代优化的思路 举一反三 模仿创新 融会贯通**

数据库的读写  ==  磁盘的读写

​	![image-20250201233117688](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201233117688.png)



缓存经典问题：数据不一致问题 ， 源数据改变缓冲区还没变化这时候用户再去请求响应的是缓冲区的就会有问题

![image-20250201233511099](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201233511099.png)



~~~java
  @GetMapping("/{id}")
    public Result queryShopById(@PathVariable("id") Long id) {
        return Result.ok(shopService.getById(id));
    }

// "/{id}"：这是 URL 路径的一部分，{id} 是一个路径变量（Path Variable），表示在 URL 中传递的动态值。例如，如果前端请求的 URL 是 /shop/123，那么 id 的值就是 123。



// Mybatis-plus
public interface IShopService extends IService<Shop> {

}
// IService<Shop> 是一个泛型接口，<Shop> 表示这个接口的操作是针对 Shop 实体类的。
// Shop 是一个实体类（通常与数据库中的表对应），IService<Shop> 提供了对 Shop 实体的通用操作方法（如增删改查）。


~~~

## 添加redis缓存

![image-20250201234136683](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201234136683.png)





![image-20250201235811382](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250201235811382.png)



==**注意：存到redis 中需要序列化 取出来需要反序列化**==

### **需要序列化的场景**

| 场景                                   | 需要序列化的原因                       |
| -------------------------------------- | -------------------------------------- |
| **存储对象到 Redis（但不使用 Hash）**  | 需要把对象转成 JSON 或者二进制格式存储 |
| **分布式 Session（存入 Redis）**       | 需要序列化 Session 对象                |
| **消息队列（MQ，如 Kafka、RabbitMQ）** | 需要对象传输时保持完整性               |
| **网络通信（RPC、Socket 传输）**       | Java 对象在网络上传输时需要序列化      |
| **存储对象到磁盘（如写入文件）**       | 需要保存 Java 对象状态并恢复           |

```java
Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
        new CopyOptions().create()
                .setIgnoreNullValue(true)
                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())
);
stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
```

### **📌 关键点**

- `BeanUtil.beanToMap`：把 `UserDTO` 转换成 **`Map<String, Object>`**。
- `.setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())`：**确保所有字段都是 String**。
- `stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);`：将 `Map` 直接存入 Redis Hash 结构。





**一切java对象均可转化成json字符串**

==简单总结==

在 Spring Boot 中，使用 `@RequestMapping` 和相关注解（如 `@PostMapping`、`@GetMapping`）来定义控制器类的方法映射路径。路径可以是**绝对路径**或**相对路径**：

1. **绝对路径**：以 `/` 开头，表示从根路径开始。例如 `@PostMapping("/code")`。
2. **相对路径**：不以 `/` 开头，表示相对于类级别的路径。例如 `@PostMapping("code")`。

在类级别使用 `@RequestMapping("/user")` 定义根路径时，这两种路径最终都会映射到 `http://<host>:<port>/user/code`。

**总之就是大部分情况都是一样的**

==最佳实践==

1. **一致性**：
   - **建议**：在方法级别尽量使用相对路径，保持代码的一致性和可读性。
   - **示例**：`@PostMapping("code")` 而不是 `@PostMapping("/code")`。
2. **清晰性**：
   - **使用有意义的路径**：选择具有描述性的路径名称，以便于理解和维护。例如，使用 `@PostMapping("sendCode")` 代替模糊的 `@PostMapping("code")`。 

## 缓存更新策略

![image-20250207105724403](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207105724403.png)



![image-20250207110159353](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207110159353.png)

两者都会出出现线程安全的问题 但是第二种方法概率更低（所以要用一个超时剔除缓存） 基于 redis的读写数据比数据库快非常多 



![image-20250207122059891](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207122059891.png)

~~~java
    @Override
    @Transactional // 保证事务原子性 （出现异常时回滚！）
    public Result update(Shop shop) {
        Long id = shop.getId();
        if(id == null)
        {
            return Result.fail("店铺id不能为空");
        }
        // 先更新数据库
        updateById(shop); // 注意mp方法的方法参数

        // 删除缓存
        stringRedisTemplate.delete("cache:shop:" + id);
        return Result.ok("更新成功！");
    }
~~~







## 缓存穿透 （面试）





![image-20250207130831949](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207130831949.png)







~~~java
@GetMapping("/{id}")
    public Result queryShopById(@PathVariable("id") Long id) {

        try {
            return Result.ok(shopService.queryById(id)); // 利用了mp
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }



// ---------------------------------
@Override
    public Shop queryById(Long id) throws Exception {
        String key = "cache:shop:" + id; // 不能直接id去查 要有业务：类型：键
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        Shop shop = null;
        if(StrUtil.isNotBlank(shopJson))
        {
            // 返回反序列化的shop
             shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop; // 尽量使用return 少用else
        }
        // 现在我要让缓存的空值不能用数据库去查而要用缓存去查
        if(shopJson != null) {
            // 所以最好还是在service层里返回一些最终的相应数据 复杂的逻辑都在service层里好
            throw new Exception("店铺不存在"); // 不然这样抛异常有点不优雅 不过也是用异常的一种处理方式 学习！
        }

         shop = getById(id);
        if(shop == null) {
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);// 避免缓存击穿
            throw new Exception("店铺不存在");
        }

         // 序列化存储
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
        return shop;
    }
~~~





## 缓存雪崩（springcloud redis高级）

![image-20250207133717214](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207133717214.png)

**后面三个都是高级篇和springcloud 微服务的知识**

## 缓存击穿

![image-20250207134938011](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207134938011.png)





![image-20250207135736714](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207135736714.png)

**逻辑上过期并不是真的过期**



![image-20250207135851965](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207135851965.png)

**一致性和高性能**

[==TODO==]: 先了解这两个方法， 用到在具体学做法

MuteX :互斥锁

LogicalExpire：逻辑过期

passthrough：穿透





## ==缓存工具封装==---泛型和函数参数/lambda应用

| **问题**     | **缓存穿透（Cache Penetration）**                   | **缓存击穿（Cache Breakdown）**                           |
| ------------ | --------------------------------------------------- | --------------------------------------------------------- |
| **本质**     | 查询**不存在的数据**，导致直接请求数据库            | 查询**热点数据**，但缓存失效，导致大量请求打到数据库      |
| **表现**     | Redis 和数据库都没有数据，每次查询都打到数据库      | 该数据曾经在缓存中，但由于失效，导致数据库承受高并发查询  |
| **危害**     | 可能导致数据库 **被恶意攻击或高并发查询**，系统崩溃 | 突然的大流量请求冲击数据库，造成 **短时间数据库压力过大** |
| **示例**     | 用户查询 ID = 999999999 的商品，数据库也无此数据    | 秒杀活动开始，大量用户查询商品库存，缓存刚好过期          |
| **应对方案** | **布隆过滤器拦截无效请求**，**缓存空值**            | **加互斥锁**，**设置热点数据永不过期**，**定时刷新缓存**  |

---



![image-20250207141516302](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207141516302.png)







![image-20250207141353110](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207141353110.png)

---

<span style="font-size:2.1em; color:#CC0000; font-weight:bold;">缓存穿透</span>



![image-20250207143732754](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207143732754.png)



![image-20250207143234658](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207143234658.png)

![image-20250207143237903](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207143237903.png)





![image-20250207142639935](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207142639935.png)



![image-20250207143248821](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207143248821.png)

**可简写**

~~~java 
this::getById 
~~~





​	

---





<span style="color:#CC0000; font-size:1.9em;">**缓存击穿**</span>

![image-20250207150203949](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207150203949.png)



### **==使用工具：Apache JMeter==**

**特点**：

- 支持 HTTP、HTTPS、JDBC、FTP 等协议。
- 可以通过图形界面和命令行模式进行操作。
- 支持创建复杂的请求序列，模拟用户行为。
- 支持分布式测试，能够模拟数千个并发用户。

**使用场景**：可以进行压力测试、负载测试和性能测试



## ==！！！测试==：

先利用==测试类（这样不用重启服务 ）== 存储redis 过期之后 使用多线程高并发模拟http请求 看看是不是只查询了一次数据库  （反正查询越少数据库越好）



### 逻辑过期

~~~java
 public <R, ID> R queryWithLogicalExpire(
            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        // 1.从redis查询商铺缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (StrUtil.isBlank(json)) {
            // 3.存在，直接返回
            return null;
        }
        // 4.命中，需要先把json反序列化为对象
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 5.判断是否过期
        if(expireTime.isAfter(LocalDateTime.now())) {
            // 5.1.未过期，直接返回店铺信息
            return r;
        }
        // 5.2.已过期，需要缓存重建
        // 6.缓存重建
        // 6.1.获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        boolean isLock = tryLock(lockKey);
        // 6.2.判断是否获取锁成功
        if (isLock){
            // 6.3.成功，开启独立线程，实现缓存重建
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    // 查询数据库
                    R newR = dbFallback.apply(id);
                    // 重建缓存
                    this.setWithLogicalExpire(key, newR, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    // 释放锁
                    unlock(lockKey);
                }
            });
        }
        // 6.4.返回过期的商铺信息
        return r;
    }
~~~



### 互斥锁

~~~java
 public <R, ID> R queryWithMutex(
        String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        // 1.从redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            // 3.存在，直接返回
            return JSONUtil.toBean(shopJson, type);
        }
        // 判断命中的是否是空值
        if (shopJson != null) {
            // 返回一个错误信息
            return null;
        }

        // 4.实现缓存重建
        // 4.1.获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        R r = null;
        try {
            boolean isLock = tryLock(lockKey);
            // 4.2.判断是否获取成功
            if (!isLock) {
                // 4.3.获取锁失败，休眠并重试
                Thread.sleep(50);
                return queryWithMutex(keyPrefix, id, type, dbFallback, time, unit);
            }
            // 4.4.获取锁成功，根据id查询数据库
            r = dbFallback.apply(id);
            // 5.不存在，返回错误
            if (r == null) {
                // 将空值写入redis
                stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
                // 返回错误信息
                return null;
            }
            // 6.存在，写入redis
            this.set(key, r, time, unit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            // 7.释放锁
            unlock(lockKey);
        }
        // 8.返回
        return r;
    }

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }
~~~





---



## Mybatis-Plus 回顾



这些mp的函数在serviceImpl实现类/或是controller类中调用

这样就知道要操作的表了

![image-20250207152224641](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250207152224641.png)



~~~java
public interface IShopService extends IService<Shop> {

    public Shop queryById(Long id) throws Exception;

    public Result update(Shop shop);
}

~~~



，`IShopService` 继承自 **MyBatis Plus** 提供的 **`IService<Shop>`** 接口，**所以在实现 `IShopService` 接口时，自动实现了 `IService<Shop>`** 的所有通用方法。这意味着，**你不需要为 `IService<Shop>` 中的基本增删改查方法提供实现**，这些方法会自动继承过来并可以直接使用。

---









# 秒杀与分布式锁

## 	全局唯一ID

1. **Redis 自增 ID**
2. **雪花算法（Snowflake）**
3. **UUID**
4. **数据库段（号段模式）**
5. **Twitter 雪花算法（Snowflake）**



<span style="color:#FF0000; font-size:1.8em;">**数据库自增问题**</span>

**性能瓶颈**：

- 自增 ID 依赖数据库，意味着每次插入数据时都要访问数据库，这会影响写入性能。
- 当大量请求同时访问数据库时，数据库可能会成为性能瓶颈。

**单点故障**：

- 如果数据库宕机，ID 生成就无法进行，影响整个业务。

**主从复制延迟**：

- 在 MySQL 主从架构中，自增 ID 可能会导致主从数据不一致。例如，在主库生成 ID 并写入后，还未同步到从库，而从库被读取时可能导致数据缺失或冲突。

**分布式系统难以保证唯一性**：

- 如果是多个数据库实例（分库分表），数据库自增 ID 可能会导致 ID 冲突或不连续，难以协调。



**知识点：JUC（并发编程）, 线程池， 时间戳， key的取名策略和拼接策略**

（缓存击穿涉及的互斥锁就是JUC）



### 1. **高性能**

Redis 是一个内存数据库，因此读写速度非常快，能够支持高并发的场景。在需要生成全局唯一 ID 的情况下，Redis 提供的自增计数器（`INCR`）可以在极短的时间内生成新的 ID，性能远超传统的数据库自增字段。

### 2. **分布式系统中的一致性**

在分布式系统中，不同的服务和节点可能会使用不同的数据库，依赖单一数据库的自增 ID 不容易保证全局唯一性。而 Redis 提供的自增 ID 是全局唯一的，它由 Redis 自身来管理，确保即使在多个节点或服务上进行操作，ID 也不会重复。



![image-20250208141934214](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250208141934214.png)



![image-20250208180002573](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250208180002573.png)

~~~java
package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {
    /**
     * 开始时间戳
     */
    private static final long BEGIN_TIMESTAMP = 1640995200L;
    /**
     * 序列号的位数
     */
    private static final int COUNT_BITS = 32;

    private StringRedisTemplate stringRedisTemplate;

    public RedisIdWorker(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public long nextId(String keyPrefix) {
        // 1.生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;

        // 2.生成序列号
        // 2.1.获取当前日期，精确到天
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        // 2.2.redis自增长
        long count = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + date);
//        第一次调用时，icr:order:2025-02-08 的值会从 0 增加到 1。
//        第二次调用时，值会增加到 2，以此类推。
//        这个计数器可以用来生成订单 ID 或其他基于计数的唯一标识符。

        // 3.拼接并返回
        return timestamp << COUNT_BITS | count;// 位运算
    }
}

~~~





利用线程池模拟大量全局唯一id的生成

~~~java
private ExecutorService es = Executors.newFixedThreadPool(500);

    @Test
    void testIdWorker() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);
        // CountDownLatch 内部有一个计数器，初始值由创建时传入的参数指定。每当某个线程完成任务后，就会调用 latch.countDown()，计数器的值减 1。主线程通过调用 latch.await() 来等待，直到计数器的值减为 0。

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        } 
        latch.await(); // 因为线程是异步的 所以主线程在此等待 这样计算的时间才是所有线程执行完的时间
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }

// private ExecutorService es = Executors.newFixedThreadPool(500);
// 这行代码创建了一个 固定大小为 500 的线程池。意思是线程池最多会同时有 500 个线程在工作，超过的任务会在队列中排队等待。
    
   // CountDownLatch latch = new CountDownLatch(300);
  //  这行代码创建了一个 计数器，它用于让主线程等待直到 300 个任务都完成。每当一个任务执行完，latch.countDown() 会被调用，latch.await() 会让主线程等待直到计数器变为 0。
~~~

---



### 秒杀券与普通卷 

数据库优化

~~~sql
-- auto-generated definition
create table tb_voucher
(
    id           bigint unsigned auto_increment comment '主键'
        primary key,
    shop_id      bigint unsigned                            null comment '商铺id',
    title        varchar(255)                               not null comment '代金券标题',
    sub_title    varchar(255)                               null comment '副标题',
    rules        varchar(1024)                              null comment '使用规则',
    pay_value    bigint unsigned                            not null comment '支付金额，单位是分。例如200代表2元',
    actual_value bigint                                     not null comment '抵扣金额，单位是分。例如200代表2元',
    type         tinyint unsigned default '0'               not null comment '0,普通券；1,秒杀券',
    status       tinyint unsigned default '1'               not null comment '1,上架; 2,下架; 3,过期',
    create_time  timestamp        default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  timestamp        default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    engine = InnoDB
    collate = utf8mb4_general_ci
    row_format = COMPACT;

# ——————————————————————————————————————————————————————————————————————————————————————————————————


enerated definition
create table tb_seckill_voucher
(
    voucher_id  bigint unsigned                     not null comment '关联的优惠券的id'
        primary key,
    stock       int                                 not null comment '库存',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    begin_time  timestamp default CURRENT_TIMESTAMP not null comment '生效时间',
    end_time    timestamp default CURRENT_TIMESTAMP not null comment '失效时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '秒杀优惠券表，与优惠券是一对一关系' engine = InnoDB
                                                collate = utf8mb4_general_ci
                                                row_format = COMPACT;


~~~

~~~java
// 实体类

@TableField(exist = false)
    private Integer stock;

    /**
     * 生效时间
     */
    @TableField(exist = false)
    private LocalDateTime beginTime;

    /**
     * 失效时间
     */
    @TableField(exist = false)
    private LocalDateTime endTime;

---
    
    public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements IVoucherService 
        
        
    @Override
    @Transactional
    public void addSeckillVoucher(Voucher voucher) {
        // 保存优惠券
        save(voucher); // voucher 实体类中有exist = false 数据库没有的字段实体类不会映射上去
        // 这个调用的是 VoucherServiceImpl 的 save() 方法 也就是接口实现类的方法
        // 保存秒杀信息
        SeckillVoucher seckillVoucher = new SeckillVoucher();
        seckillVoucher.setVoucherId(voucher.getId());
        seckillVoucher.setStock(voucher.getStock());
        seckillVoucher.setBeginTime(voucher.getBeginTime());
        seckillVoucher.setEndTime(voucher.getEndTime());
        seckillVoucherService.save(seckillVoucher);
    }
}
// save() 方法是通过 继承 和 接口实现 提供的通用保存功能。
// 你看到的没有 . 前缀是因为 VoucherServiceImpl 继承了 ServiceImpl，ServiceImpl 中已经提供了 save() 方法。
// 而有 .save() 的是调用了其他 服务接口（如 ISeckillVoucherService）的方法，这也是 MyBatis-Plus 和 Spring 服务层的标准用法，确保各个服务的职责分离。
~~~

当**需要在实体类中有一些临时计算或者业务相关的字段**，但这些字段不需要与数据库中的表字段进行映射时，可以使用 `@TableField(exist = false)` 来标记这些字段。这样 MyBatis-Plus 就**不会将它们映射到数据库。**

---

**所以说核心是表  对于==表要有实体类对应== 也要有==相应的service层（service接口实现类）==操作自己对应的表**



1. **`VoucherMapper` 中的 `Voucher`：**

- **`VoucherMapper`** 是 **数据访问层（DAO）**，它负责与数据库直接交互，进行 CRUD 操作。
- `VoucherMapper` 继承自 `BaseMapper<Voucher>`，`Voucher` 在这里是 **数据库表对应的实体类**，映射了数据库中的一张表。MyBatis-Plus 会通过 `Voucher` 类来自动映射数据库中的字段到类中的属性。

```java
public interface VoucherMapper extends BaseMapper<Voucher> {
    // 数据访问层（DAO）定义数据库相关操作
}
```

这里，`Voucher` 是 **数据库表结构的映射**，用于数据库的操作。

2. **`VoucherServiceImpl` 中的 `Voucher`：**

- **`VoucherServiceImpl`** 是 **业务逻辑层**，它处理业务相关的操作，并调用 **`VoucherMapper`** 来执行数据库操作。
- `VoucherServiceImpl` 通过 `ServiceImpl<VoucherMapper, Voucher>` 来继承和实现业务逻辑。`VoucherServiceImpl` 可能会进行一些数据的处理或转化，但最终还是会通过 `VoucherMapper` 来执行 CRUD 操作。

```java
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements IVoucherService {
    // 业务逻辑层，使用 Voucher 作为数据模型
}
```

### 需求流程图分析

![image-20250208222022018](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250208222022018.png)



## 超卖问题 与 乐观锁

![image-20250209000835683](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209000835683.png)



![image-20250209001147295](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209001147295.png)

优化：**用库存代替版本号 **

![image-20250209001320339](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209001320339.png)

这样虽然解决的超卖但是会出现大批失败的订单导致没卖完

~~~java
// 优化
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {
    @Resource
    private RedisIdWorker redisIdWorker;
    @Resource
    private ISeckillVoucherService seckillVoucherService;
    @Override
    public Result seckillVoucher(Long voucherId) {
        // 通过查询id查询秒杀券信息
        SeckillVoucher seckillVoucher = seckillVoucherService.getById(voucherId);
        // 如果是在秒杀时间内且有库存
        if (!(seckillVoucher.getBeginTime().isBefore(LocalDateTime.now()) &&
                seckillVoucher.getEndTime().isAfter(LocalDateTime.now()))) {
            return Result.fail("不在规定时间内");
        }
        if (seckillVoucher.getStock() < 1) {
            return Result.fail("库存不足");
        }
        boolean success = seckillVoucherService.update()
                .setSql("stock = stock - 1")
                .eq("voucher_id", voucherId).gt("stock", 0)
                .update();
        if(!success) {
            return Result.fail("库存不足");
        }
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(redisIdWorker.nextId("order"));
        Long userId = UserHolder.getUser().getId();
        voucherOrder.setUserId(userId);
        voucherOrder.setVoucherId(voucherId);
        // 订单写入数据库
        save(voucherOrder);

        return Result.ok(voucherOrder.getId());
    }
}
~~~





**超卖问题key：消息的不同步 数据库查询不同步** 基于不同步的错误信息进行操作



但是如果没有库存>0这个限制条件的业务场景要如何做呢？



**一人一单问题：**

![image-20250209004940228](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209004940228.png)

还是会有一人买多单的情况 主要还是在key也就是**数据库查询的问题** 上

造成==并发安全问题==

**ctrl+alt +M 封装方法快捷键**



**锁对象复习**

- **锁加在实例对象上**：
  如果你在实例方法中使用 `synchronized`，那么锁就是加在该实例的对象 (`this`) 上。

  ```java
  public synchronized void exampleMethod() {
      // 只有一个线程能够进入这个方法
  }
  ```

  这里，`synchronized` 锁住的是当前类的实例对象（即 `this`），意味着同一个对象的多个线程不能同时访问这个方法。

- **锁加在某个具体对象上**：
  你也可以在方法或代码块中显式地指定一个对象来加锁。这样可以控制并发访问某些特定资源。

  ```java
  private final Object lock = new Object();
  
  public void exampleMethod() {
      synchronized (lock) {
          // 只有一个线程能够进入这个代码块
      }
  }
  ```

  在这个例子中，`lock` 对象是同步的对象，只有一个线程可以进入 `synchronized (lock)` 代码块。当 `lock` 对象被锁住时，其他线程如果要访问同一个 `lock` 对象的同步代码块，就必须等待。

- **锁加在类本身上（类锁）**：
  如果你在 `static` 方法中使用 `synchronized`，锁定的是类的 `Class` 对象。

  ```java
  public static synchronized void exampleStaticMethod() {
      // 只有一个线程可以进入这个静态方法
  }
  ```

  这里，`synchronized` 锁住的是 `Class` 对象，它是类级别的锁。也就是说，所有对这个类的静态方法的访问都会受到同一把锁的控制。

### 锁加在哪个对象上，意味着什么？

- **加锁在实例对象 (`this`) 上**：
  这意味着同一个类的不同实例之间可以并发执行同步方法，不会互相干扰。只有同一个对象的多个线程在访问同步方法时会发生阻塞，防止并发访问同一对象的共享数据。
- **加锁在某个具体对象上**：
  如果锁定的是某个特定对象（如 `lock`），那么在不同的对象之间，如果没有显式的共享锁，这些对象上的线程仍然可以并发执行。例如，如果锁定的是不同的对象，每个对象的线程是独立的，不会发生阻塞。
- **加锁在类本身上（`Class`）**：
  这意味着所有线程对这个类的静态方法的访问是同步的，不论是哪个对象调用该类的静态方法，都会在类级别的锁上发生阻塞。





**Long和toString()底层都是new了一个新的对象 所有synchornize() 括号中写什么也是有讲究的**

使用**intern 字符串常量池**也就是复用

![image-20250209011415215](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209011415215.png)



注意还有锁范围的问题：为什么？ 注意@Transactional 事务方法管理 

锁释放后才会提交事务要是在这中间又有线程进来呢（此时数据库还未提交事务）

**所以要加在函数调用上才行**

![image-20250209011647280](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209011647280.png)







### Spring代理问题



但是还有问题 ==spring事务失效问题== 和动态代理有关

封装的方法加了事务， 而调用这个封装方法的函数没有加上事务管理注解

**解决方法:**

![	](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250209012335204.png)

![image-20250209012318499](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209012318499.png)

添加依赖， 暴露代理对象

**使用代理调用方法而不是this**

![image-20250209012229421](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209012229421.png)



**但是这个也只在单体结构模式上有用集群上这个方法就用不了了**



## 集群下的线程并发安全问题

| **特性**       | **集群架构**                         | **单体架构**                           |
| -------------- | ------------------------------------ | -------------------------------------- |
| **定义**       | 由多个节点组成的系统，分布式部署     | 所有功能集中在一个应用实例中           |
| **部署方式**   | 分布式部署在多个物理或虚拟节点上     | 部署在一个应用实例中，通常只有一个进程 |
| **可扩展性**   | 水平扩展，通过增加节点提高处理能力   | 垂直扩展（增加硬件）或部署多个实例     |
| **容错性**     | 高可用，单个节点失效时其他节点接管   | 故障通常会导致整个应用崩溃             |
| **管理复杂度** | 较复杂，需要管理多个节点、网络和协调 | 简单，管理单一应用实例                 |
| **负载均衡**   | 支持负载均衡，将请求分发到不同节点   | 通常不具备负载均衡功能，需要多实例部署 |
| **维护成本**   | 较高，需要维护集群的硬件、网络和软件 | 较低，只有单一应用实例                 |
| **适用场景**   | 高可用性、高扩展性、大规模分布式应用 | 小型应用、初期开发或较小规模的系统     |
| **容错能力**   | 较强，节点故障不影响整体服务         | 较弱，系统故障可能导致全部停机         |
| **开发难度**   | 较高，需要处理分布式系统中的复杂问题 | 较低，开发简单，模块较为紧耦合         |

模拟集群：

![image-20250209012930329](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209012930329.png)

![image-20250209111719405](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209111719405.png)

**使用分布式锁**



# ==分布式锁==

### 1. **分布式系统（Distributed System）**

分布式系统指的是由多台计算机组成的系统，这些计算机共同协作完成一个任务，但它们通常分布在不同的物理位置上，互相通过网络连接。分布式系统中的各个节点（计算机）在外部表现为一个统一的系统，但它们在内部相互独立、自治，并通过网络共享资源和信息。

**特点：**

- **透明性：** 用户和应用程序通常无法感知到系统内部的复杂性和多台机器的存在。
- **容错性：** 分布式系统往往具有容错性，即使某个节点发生故障，系统仍然可以继续运行（通常通过冗余和数据备份）。
- **扩展性：** 分布式系统可以通过增加新的节点来扩展系统的计算能力和存储容量。
- **异构性：** 节点之间可能使用不同的硬件、操作系统和网络环境。

**应用：**

- 分布式数据库（如 Google Spanner、Cassandra）。
- 分布式文件系统（如 HDFS）。
- 微服务架构。

**举个例子：** 比如一个大型的电商平台，后端的服务可能分布在多个服务器上，每个服务器可能负责不同的任务（如订单处理、用户管理、商品展示等），这些服务通过网络连接和协调工作，形成一个整体的系统。

------

### 2. **集群模式（Cluster Mode）**

集群模式通常指的是将多台服务器（节点）组合成一个工作集群，这些服务器在同一个任务或服务上协作工作，提供高可用性、负载均衡和扩展性。在集群模式中，多个节点在外部看来是一个统一的系统，但内部有一定的协调和调度机制。

**特点：**

- **统一管理：** 集群中的多个节点可以通过集中的管理工具进行管理和监控，通常由一个集群管理系统来进行资源调度和负载均衡。
- **高可用性：** 集群模式通常通过冗余节点来确保服务的高可用性。如果其中一台机器出现故障，其他机器可以接管其任务，保证系统持续运行。
- **负载均衡：** 集群中所有节点之间可以通过负载均衡策略来分担用户的请求和计算任务，从而提高系统的响应速度和吞吐量。

**应用：**

- 数据库集群（如 MySQL Cluster、Redis Cluster）。
- Web 服务器集群（如 Nginx 或 Apache 负载均衡）。
- Hadoop 集群用于大规模数据存储和计算。

**举个例子：** 例如，假设你有一个网站，使用了多个 Web 服务器来处理请求，这些 Web 服务器通过负载均衡器来分配请求，并且有一定的容错机制：如果某台服务器出现故障，其他服务器可以接管它的工作，这就是一个 Web 服务器的集群。

主从模式是一种常见的分布式架构模式，通常用于数据库、缓存系统等场景，在这种模式下，一台主服务器（Master）负责处理写请求和数据修改，多个从服务器（Slave）则负责处理读取请求。主服务器和从服务器之间通过同步机制保持数据一致性。





---



#### **主从模式：**

- **主服务器（Master）：** 负责所有写操作，包括插入、更新和删除。它是数据的唯一来源。
- **从服务器（Slave）：** 只负责读操作，它会从主服务器获取数据的副本并提供读取服务。一般来说，从服务器会定期从主服务器同步数据。
- **数据同步：** 主从模式中的**从服务器会定期从主服务器获取数据更新**。这种同步方式可以是**同步**的（主服务器等从服务器同步完成后才继续处理请求），也可以是**异步**的（主服务器不会等待从服务器的同步完成）。

​	![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209122612340.png)

![image-20250209124815997](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209124815997.png)

原子性问题：如果在设置过期前就宕机怎么办所以要原子化

![image-20250209125144503](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209125144503.png)

**阻塞式（Blocking）：** 当一个线程或进程请求某个资源（如锁、网络等）时，如果资源当前不可用，调用方将会被“阻塞”，即会等待直到资源变得可用再继续执行。比如，若当前线程请求的锁已被占用，它会一直等待直到锁被释放。

**非阻塞式（Non-blocking）：** 与阻塞式不同，非阻塞式请求不会让调用者等待，如果资源当前不可用，调用者会立即返回，可能会返回错误或指定的状态。调用方可以继续执行其他操作。

---



### ThreadLocal 和Thread

1. **`Thread.currentThread().getId()`**

这个方法返回的是当前正在执行的线程的 ID，它是 **全局共享** 的，即所有线程都可以访问到这个 ID。当你调用 `Thread.currentThread().getId()` 时，它会返回当前线程的唯一标识符，这个标识符在程序运行期间是唯一且持续不变的。

特点：

- 线程 ID 是 **全局唯一** 的，且由系统分配。
- 不能直接存储线程局部变量。
- 每个线程可以访问自己的 ID。
- 在多线程环境下，每个线程都有自己的线程 ID，其他线程无法访问到其他线程的 ID。

示例：

```java
long threadId = Thread.currentThread().getId();
System.out.println("Current Thread ID: " + threadId);
```

这个方法的用途一般是用来获取当前线程的唯一标识符，用于调试或日志记录等。

2. **`ThreadLocal`**

`ThreadLocal` 是 Java 提供的一种用于为每个线程提供 **局部变量** 的机制。使用 `ThreadLocal`，每个线程都会有自己的独立副本，其他线程无法访问或修改它。

特点：

- `ThreadLocal` 变量是 **每个线程独立的**，即每个线程访问 `ThreadLocal` 变量时，都会得到该线程专用的副本。
- 适用于需要存储线程私有数据的场景，避免了线程间的数据竞争。
- `ThreadLocal` 内部会维护每个线程的值，它为每个线程提供了一个 **隔离空间**。
- 一般用于存储一些与线程生命周期相关的私有数据（例如，数据库连接、用户会话信息等）。

示例：

```java
ThreadLocal<Long> threadLocalId = ThreadLocal.withInitial(() -> Thread.currentThread().getId());

long threadIdFromThreadLocal = threadLocalId.get();
System.out.println("Thread ID from ThreadLocal: " + threadIdFromThreadLocal);
```

在这个例子中，`ThreadLocal` 为每个线程提供了一个独立的 `long` 值，线程可以访问自己的副本，但其他线程无法访问。



之所以用userId拼接 是因为只给**同一个用户多次购买**做限制！！！！ **不同用户不做限制**

时间正确设定后，然后锁过期后同一用户再次购买就会被createVoucherOrder方法判断数据库**已存在购买信息** 

**抛出异常释放锁免得占用redis内存**

时间是根据业务的 以防出现此情况：对于同一个用户多个线程并发，锁过期但是前面一个线程还没有创建完订单就又获取到了锁然后还在创建订单

![image-20250209141210079](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250209141210079.png)

### 误删问题

![image-20250209160300463](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209160300463.png)

问题的核心就在于业务完成后解锁把其他线程的锁给解除了

![image-20250209160440513](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209160440513.png)

#### 使用UUID的原因

thread.getId()	---> **线程id是自增的。集群模式下，由不同JVM自增的线程id很有可能是重复的**







### 原子性问题

![image-20250209163536069](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209163536069.png)

由于在判断和释放锁中间还有JVM垃圾回收机制 可能会阻塞这种情况就要原子化 要么一起成功要么一起失败 

**那么如何原子化多命令呢??**

![image-20250209170053146](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209170053146.png)

**lua 语言角标从1不是0开始**

![image-20250209171125343](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209171125343.png)



![image-20250209172033289](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209172033289.png)

这些原子性代码并发流程是什么样呢 思考！

![image-20250209172822578](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209172822578.png)



![image-20250209174509687](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209174509687.png)





![image-20250209174436505](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209174436505.png)

`static {}` 是 Java 中的 **静态初始化块**，它的主要作用是在类加载时初始化类的静态成员变量或执行静态操作。静态初始化块属于类的一部分，而不是某个实例的一部分，它会在类的加载过程中执行。

这种代码的执行通常是在 **类的初始化阶段**，并且是在 **构造函数之前**，因此，在创建对象之前，静态块中的代码就会执行。



---



**硬编码**（Hard Coding）是指在程序中直接写入固定的、无法改变的值（例如常量、字符串、路径、配置等）。这些值通常被硬性地写死在代码中，而不是通过外部配置文件、数据库或用户输入等动态方式来获取。这种做法让程序在运行时无法灵活地根据不同环境或需求调整这些值。

### **硬编码的例子：**

```java
public class Example {
    public static void main(String[] args) {
        // 硬编码的数据库连接信息
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String dbUser = "admin";
        String dbPassword = "password123";

        // 执行数据库连接
        // ...
    }
}
// 在这个例子中，数据库的 URL、用户名和密码都被直接写在代码中，这就是硬编码。若以后数据库的连接信息发生变化（比如数据库地址变了、用户名密码变了），就需要修改代码并重新部署应用。
```

​				如果在配置文件中写了配置项，**不需要重新部署应用**的原因主要是因为现代的应用程序通常设计为支持**==动态读取配置文件==**，并且配置文件通常是**==外部化==的**。这意味着，配置文件中的信息可以在应用程序启动后或者运行时动态加载，而不必每次修改配置时都重新编译或重新部署应用。

## Redission

**问题：redis中如果业务完成前锁过期其他线程进入查询到不同步的数据怎么办 以下就是解决方案**

上面的就可以满足大部分分布式锁的情况了以下的情况就是概率会比较低 

这个就先了解以下就行

### 功能介绍

**阻塞式就是可重试的**

![image-20250209180347100](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209180347100.png)

### 快速入门



### 可重入锁



### 锁重试&WatchDog机制



### multiLock原理





---

# 秒杀优化

**思考：为什么要用redis实现秒杀 要会输出输入的知识 要会用知道如何使用！！！！**

==幂等性==是指：如果某个操作多次执行，结果应该与执行一次的结果相同，不会产生副作用。也就是说，操作的结果是稳定的，重复执行不会导致不必要的变化。

![image-20250209182521230](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209182521230.png)

**中间件**是连接软件组件和应用的计算机软件，用于实现多个软件之间的互操作性[4](https://baike.baidu.com/item/中間件/452240)。在微服务架构中，中间件可以用于**服务发现、API网关、负载均衡、身份验证和授权**

**消息队列**是一种异步通信机制，用于在不同的服务之间传递消息。 在微服务架构中，消息队列可以用于实现服务之间的解耦、异步处理和最终一致性。 消息队列适用于处理如订单处理、日志收集等任务。

![image-20250209183704429](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250209183704429.png)

**单体项目和微服务架构是相对概念**

**单体项目**是指将所有的应用功能、业务逻辑、数据访问、用户界面等都包含在一个单一的代码库中，并作为一个整体部署和运行的应用程序。

**微服务架构**是将单体应用拆分为多个小而独立的服务，每个服务完成特定的功能并能独立部署、扩展和升级。这些服务通常是围绕业务功能来划分的，每个微服务通常会有自己的数据库和业务逻辑。

~~~ceylon
分布式系统包括：
**分布式计算**：将计算任务分配到多个节点并行处理（例如 **Hadoop**、**Spark**）。

**分布式存储**：数据分布在不同的节点上，保障高可用和扩展性（例如 **HDFS**、**Ceph**）。

**分布式数据库**：数据存储和处理分布在多个节点上，提供一致性和容错（例如 **Cassandra**、**TiDB**）。

**分布式缓存**：将缓存数据分布在多个节点上，提高读取性能（例如 **Redis Cluster**、**Memcached**）。

**分布式消息队列**：服务间异步通信，解耦合，提高可扩展性（例如 **Kafka**、**RabbitMQ**）。

**服务发现与负载均衡**：确保服务之间能动态发现并且请求均匀分配（例如 **Eureka**、**Nginx**）。

**分布式协调服务**：提供分布式锁、选举、同步等功能（例如 **Zookeeper**、**Consul**）。
~~~



**分布式** 主要关注的是 **系统组件或服务如何分布在不同的节点上**，而 **微服务架构** 是一种 **具体的应用架构设计**，旨在将应用拆分为一组小的、独立的服务，每个服务执行单一的业务功能，并能够独立部署、扩展和升级。



<span style="color:#FF0000; font-size:2.1em;">**异步优化串行化**</span>

![image-20250210111437830](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250210111437830.png)

使用redis换缓存时要先选定合适的数据结构

![image-20250210112152234](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250210112152234.png)

~~~lua
-- 1.参数列表
-- 1.1.优惠券id
local voucherId = ARGV[1]
-- 1.2.用户id
local userId = ARGV[2]
-- 1.3.订单id
local orderId = ARGV[3]

-- 2.数据key
-- 2.1.库存key
local stockKey = 'seckill:stock:' .. voucherId
-- 2.2.订单key
local orderKey = 'seckill:order:' .. voucherId

-- 3.脚本业务
-- 3.1.判断库存是否充足 get stockKey
if(tonumber(redis.call('get', stockKey)) <= 0) then
    -- 3.2.库存不足，返回1
    return 1
end
-- 3.2.判断用户是否下单 SISMEMBER orderKey userId
if(redis.call('sismember', orderKey, userId) == 1) then
    -- 3.3.存在，说明是重复下单，返回2
    return 2
end
-- 3.4.扣库存 incrby stockKey -1
redis.call('incrby', stockKey, -1)
-- 3.5.下单（保存用户）sadd orderKey userId
redis.call('sadd', orderKey, userId)
-- 3.6.发送消息到队列中， XADD stream.orders * k1 v1 k2 v2 ...
redis.call('xadd', 'stream.orders', '*', 'userId', userId, 'voucherId', voucherId, 'id', orderId)
return 0
~~~



~~~java
   @Override
    public Result seckillVoucher(Long voucherId) {
        Long userId = UserHolder.getUser().getId();
        long orderId = redisIdWorker.nextId("order");
        // 1.执行lua脚本
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(), userId.toString(), String.valueOf(orderId)
        );
        int r = result.intValue();
        // 2.判断结果是否为0
        if (r != 0) {
            // 2.1.不为0 ，代表没有购买资格
            return Result.fail(r == 1 ? "库存不足" : "不能重复下单");
        }
        // 3.返回订单id 还要保存在阻塞队列中
        return Result.ok(orderId);
    }
~~~

**阻塞队列**（Blocking Queue）是一种特殊的队列，它支持在队列为空时等待获取元素，或者在队列满时等待插入元素的操作。常见于多线程或并发编程中，特别是在生产者-消费者模式下

**队列为空时的阻塞**：如果消费者尝试从队列中获取元素，而队列为空，则消费者线程会被阻塞，直到队列中有元素可用。

**队列满时的阻塞**：如果生产者尝试向队列中插入元素，而队列已满，则生产者线程会被阻塞，直到队列中有空间可以插入新元素。



**子线程** 是指在一个已有的线程（通常是主线程）中创建的新线程。子线程可以并行执行与主线程或者其他子线程不同的任务。线程在计算机程序中是执行任务的最小单位，而子线程就是在现有线程的基础上进一步派生出来的执行单元。



`ThreadLocal` 的值是线程隔离的，意味着它的值在不同的线程之间不可见。父线程设置的 `ThreadLocal` 值不会自动传递到子线程。如果你在父线程中设置了某个 `ThreadLocal` 的值，子线程并不能直接访问到该值。

# Redis消息队列



## 消息队列的概念

![image-20250210123806966](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250210123806966.png)

**阻塞队列**：一个重要的特性就是在队列为空时，消费者会阻塞等待，而在队列已满时，生产者会阻塞等待。这是阻塞队列的核心特点。

一般用于**单机多线程**环境中，解决线程间通信问题。它是线程同步工具的一种，适用于线程之间的数据传递。

**消息队列**：消息队列通常也有某种形式的等待机制（例如：等待队列中的消息、等待消费者消费消息），但消息队列的阻塞特性通常是**可配置的**，并不一定严格要求阻塞，而是可以选择异步、超时等处理方式。主要用于**分布式系统中**，用于跨应用、跨服务间(==微服务中使用==)的消息传递，解决系统解耦和异步处理的问题



 **单机多线程**指的是只有一台物理计算机，也就是说所有的线程都在同一台计算机上运行。与分布式计算（多个计算机上的线程协作）相对。

与单机多线程不同，**分布式计算**不仅仅依赖于一台计算机的多核处理器，而是利用多个计算机的资源来处理任务。分布式计算通常是 **横向扩展**（增加更多计算机）而不是 **纵向扩展**（增加单台计算机的处理能力）。



# 多功能业务开发

 ### 达人探店

图片上传功能：

~~~js
 <input type="file" @change="fileSelected" name="file" ref="fileInput" style="display: none">

     this.$refs.fileInput.click();
// 本质上就是模拟表单上传
fileSelected() {
    let file = this.$refs.fileInput.files[0];
    let formData = new FormData();
    formData.append("file", file); // 表单数据
    const config = {
      headers: {"Content-Type": "multipart/form-data;boundary=" + new Date().getTime()}
      //在表单中，文件上传和其他普通表单字段（比如文本框、选择框等）会同时提交。为了保证这些字段能够被正确解析和区分，boundary 被用来分隔每一部分的数据。
      //即使你上传的只有一个文件，multipart/form-data 格式依然要求在请求体中使用 boundary 来分隔不同的部分。因为 boundary 在此格式中的作用不仅仅是用于分隔多个文件，它还用于区分文件和其它表单数据部分（如表单字段）。
    };
    axios
      .post("/upload/blog", formData, config)
      .then(({data}) => this.fileList.push('/imgs' + data)) // 返回文件名称
      .catch(this.$message.error);
  },
~~~

~~~java
@RequestMapping("upload")
public class UploadController {

    @PostMapping("blog")
    public Result uploadImage(@RequestParam("file") MultipartFile image) {
        try {
            // 获取原始文件名称
            String originalFilename = image.getOriginalFilename();
            // 生成新文件名
            String fileName = createNewFileName(originalFilename);
            // 保存文件
            image.transferTo(new File(SystemConstants.IMAGE_UPLOAD_DIR, fileName));
            // 返回结果
            log.debug("文件上传成功，{}", fileName);
            return Result.ok(fileName);
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }
    // @RequestParam 通过字段名来从请求体中提取数据。例如，在你的例子中，表单中 <input type="file" name="file"> 将文件数据作为 file 字段上传，后端通过 @RequestParam("file") 获取这个字段的内容。
    // 表单数据的接收
~~~

Content-Type：请求体类型

`Content-Type` 是 HTTP 请求头的一部分，告诉服务器请求体的格式，**以便服务器能够正确解析收到的数据** （不然浏览器不知道你是不是文件啊）。`multipart/form-data` 是一种常见的编码格式，专门用于处理带有文件上传的表单。

当你上传文件时，浏览器会使用 `multipart/form-data` 格式把文件数据和其他普通表单字段一起打包。这个格式将数据分为多个部分，每个部分都会被一个唯一的 `boundary`（边界）分隔开。`boundary` 用于区分每个表单字段或文件部分。

**在入门项目中没有指定请求类型但是传了一个普通对象就是默认是application/json的格式**

### `application/x-www-form-urlencoded` 和 `multipart/form-data` 区别

1. **`application/x-www-form-urlencoded`**：

   - 这是最常见的表单提交类型，通常用于提交简单的表单数据，比如文本字段。

   - 当你使用 `application/x-www-form-urlencoded` 时，表单数据会编码在请求体中，并且键值对是通过 `&` 连接的。

   - 比如，当你提交用户名和密码时，表单数据会变成：

     ```
     username=John&password=12345
     ```

   - 这种格式会被 `@RequestParam` 处理，因为它是标准的表单数据。

2. **`multipart/form-data`**：

   - 这种类型通常用于上传文件，因为文件需要以二进制形式提交，而其他的表单字段（如文本框）仍然是键值对的形式。
   - 它的请求体会包含多个部分，每个部分都有一个内容类型（比如文本字段的 `application/x-www-form-urlencoded`，文件字段的 `application/octet-stream`）以及一个 `boundary` 来分隔每部分。
   - 即使你只上传一个文件，`multipart/form-data` 格式依然需要使用 `boundary` 来分隔文件和表单数据。
   - 文件和其他数据会一起提交，后端可以使用 `@RequestParam` 处理。

### `@RequestParam` 和 `@RequestBody` 的区别

- **`@RequestParam`**： 用于从请求的 **查询字符串** 或 **表单数据** 中获取数据。适用于 `application/x-www-form-urlencoded` 和 `multipart/form-data` 格式。
  - 对于 `application/x-www-form-urlencoded` 和 `multipart/form-data`，数据都会被发送在请求体（body）中。对于文件上传，后端使用 `@RequestParam` 来接收文件（`MultipartFile`）。
- **`@RequestBody`**： 用于处理 **整个请求体**，通常用来接收 **JSON** 或 **XML** 格式的数据。
  - 如果请求的 `Content-Type` 是 `application/json` 或类似的格式，Spring 会自动将请求体转换为 Java 对象。`@RequestBody` 并不适用于 `application/x-www-form-urlencoded` 或 `multipart/form-data`，因为这些格式的数据会以不同的方式编码在请求体中。

**这里上传图片回顾一下OSS对象存储**

- `data.forEach(b => ...)`：
  这部分是对 `data` 数组中的每一个元素 `b` 进行遍历。`forEach` 是数组的方法，它会依次处理数组中的每一项，并执行给定的回调函数。

- `b.images.split(",")`： `split(",")` 是字符串的方法，它会根据逗号（`,`）把字符串 `b.images` 分割成一个数组。例如，假设 `b.images` 的值是 `"image1.jpg,image2.jpg,image3.jpg"`，`split(",")` 会返回一个数组 `["image1.jpg", "image2.jpg", "image3.jpg"]`。

- `b.img = b.images.split(",")[0]`： 在这部分，`split(",")[0]` 是取分割后的数组的第一个元素。即，如果 `b.images` 是 `"image1.jpg,image2.jpg,image3.jpg"`，那么 `b.images.split(",")[0]` 就是 `"image1.jpg"`。

  然后，这个值会被赋给 `b.img`。也就是说，`b` 对象现在会有一个新的属性 `img`，它的值是 `b.images` 中逗号分隔的第一个图片路径。

  

  

  ！！！注意：

  

~~~ceylon
如果对象一开始没有 img 属性，代码会在运行时创建这个属性并赋值。
~~~



假设 `data` 数组的内容是：

```js
let data = [
  { id: 1, images: "image1.jpg,image2.jpg,image3.jpg" },
  { id: 2, images: "photo1.png,photo2.png" },
  { id: 3, images: "pic1.gif" }
];
```

执行这段代码后：

```js
data.forEach(b => b.img = b.images.split(",")[0]);
```

`data` 数组会变成：

```js
[
  { id: 1, images: "image1.jpg,image2.jpg,image3.jpg", img: "image1.jpg" },
  { id: 2, images: "photo1.png,photo2.png", img: "photo1.png" },
  { id: 3, images: "pic1.gif", img: "pic1.gif" }
]
```

~~~js
export const userRegisterService = (registerData)=>{
  const params = new URLSearchParams()
  for(let key in registerData) {
    params.append(key,registerData[key])

  }
  return request.post('/user/register', params) // post返回值

}  
// 发送这个请求时，URL 不会携带参数，因为你是通过请求体（body）发送数据，而不是通过查询参数（URL）。发送这个请求时，URL 不会携带参数，因为你是通过请求体（body）发送数据，而不是通过查询参数（URL）。
// 请求头会自动设置为 Content-Type: application/x-www-form-urlencoded，告诉服务器数据是以表单格式编码的。
// 如果表单数据包含特殊字符（如 &、=、# 等）或需要避免 URL 编码时，POST 请求会更好。因为 URL 中的这些特殊字符可能会导致数据解析错误。
~~~

### 点赞

~~~java
@TableField(exist = false)
    private Boolean isLike;
~~~



当你查询数据库并将结果映射到实体类时，**实体类中没有被映射到数据库中的属性**（即通过`@Transient`注解标记的属性）将不会自动从数据库中获取值。它们的值通常是默认值



stream流的使用

Blooean 返回值

数据库操作可能不成功 返回的时boolean 表示操作是否成功

为blog实体类设置非数据库字段属性表示每个用户查询blog时都是默认值

利用redis set数据结构判断有没有点过赞 

~~~java
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Resource
    private IUserService userService;
    private void queryBlogUser(Blog blog) {
        Long userId = blog.getUserId();
        User user = userService.getById(userId);
        blog.setName(user.getNickName());
        blog.setIcon(user.getIcon());
    }
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryHotBlog(Integer current) { // index首页列表
        // 根据用户查询
        Page<Blog> page = query()
                .orderByDesc("liked")
                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        // 获取当前页数据
        List<Blog> records = page.getRecords();
        // 查询用户
        records.forEach(blog -> {
            this.queryBlogUser(blog);
            this.isBlogLiked(blog);
        });
        return Result.ok(records);
    }

    @Override
    public Result queryBlogById(Long id) { // 点进详情页
        // 1.查询blog
        Blog blog = getById(id);
        if (blog == null) {
            return Result.fail("笔记不存在！");
        }
        // 2.查询blog有关的用户
        queryBlogUser(blog);
        // 3.查询blog是否被点赞
        isBlogLiked(blog);
        return Result.ok(blog);
    }

    @Override
    public Result likeBlog(Long id) {
        String userId = UserHolder.getUser().getId()+"";
        String key = BLOG_LIKED_KEY + id;
        Double isLike = stringRedisTemplate.opsForZSet().score(key, userId); // 返回的是包装类
        // Boolean isLike = stringRedisTemplate.opsForSet().isMember(key, userId); 普通set数据结构
        if(isLike != null) {

            // 数据库更新
            boolean isSuccess = update().setSql("liked = liked - 1").eq("id", id).update();
            if(!isSuccess)
                return Result.fail("取消点赞失败");

            stringRedisTemplate.opsForZSet().remove(key, userId); // 要数据库更新成功才能修改redis
            return Result.ok("取消点赞");
        }

        // 数据库更新
        boolean isSuccess = update().setSql("liked = liked + 1").eq("id", id).update();
        if(!isSuccess)
            return Result.fail("点赞失败");

        stringRedisTemplate.opsForZSet().add(key, userId, System.currentTimeMillis());
        return Result.ok("点赞成功");
    }
    public void isBlogLiked(Blog blog) {
        UserDTO userDTO = UserHolder.getUser(); // 由于blog/hot 不拦截 如果没有登录这里执行会空指针异常
        if(userDTO == null) {
            return ;
        }
        String userId = userDTO.getId()+"";
        String key = BLOG_LIKED_KEY + blog.getId();
        Double isLike = stringRedisTemplate.opsForZSet().score(key, userId);
        if(isLike == null)
            blog.setIsLike(false);
        else
            blog.setIsLike(true);


    }

    // ！！！ 重点 stream流 学习
    @Override
    public Result queryBlogLikes(Long id) {
        String key = BLOG_LIKED_KEY + id;
        Set<String> top5 = stringRedisTemplate.opsForZSet().range(key, 0, 4); // 0-4 都是闭合的
        if(top5 == null || top5.isEmpty())
            return Result.ok(Collections.emptyList()); // 返回一个空的list
        List<Long>ids = top5.stream().map(Long::valueOf).collect(Collectors.toList());
        // string流获取点赞前五名的id 然后根据id查user
//        List<UserDTO> userDTOS = userService.listByIds(ids) // 使用mp 批量查询 但使用listByIds底层的sql语句时IN
//                .stream()
//                .map(user -> BeanUtil.copyProperties(user, UserDTO.class))
//                .collect(Collectors.toList());
        
        
        // where id in (5,2,3) order by field(id,5,1) 要这样才行
        
        String idStr = StrUtil.join(",", ids); // 拼接字符串
        List<UserDTO> userDTOS = userService.query().in("id", ids).last("ORDER by FIELD(id," + idStr + ")") // 拼接sql字符串
                .list().stream()
                .map(user -> BeanUtil.copyProperties(user, UserDTO.class))
                .collect(Collectors.toList());
        return Result.ok(userDTOS);

    }
}

~~~

### 排行榜功能

在 SQL 语句中，`IN` 只是用来筛选数据，数据库默认不会按照 `IN` 里的顺序返回数据。例如：

```sql

SELECT * FROM user WHERE id IN (3, 1, 2);
```

可能返回的顺序是：

```sql
id | name | score
-----------------
1  | A    | 100
2  | B    | 95
3  | C    | 90
```

即使 `IN (3,1,2)`，结果可能是按主键索引排序的，而**不会按照 `(3,1,2)` 的顺序返回**。



### 好友关注

页面加载时需要发送一下请求

~~~js
 created() {
      let id = util.getUrlParam("id");
      this.queryBlogById(id);
    },
        
  queryBlogById(id) { // created 生命周期调用函数  点击页面时要发送这些请求
        axios.get("/blog/" + id)
          .then(({data}) => {
            data.images = data.images.split(",")
            this.blog = data;
            this.$nextTick(this.init);
            this.queryShopById(data.shopId) 
            this.queryLikeList(id);
            this.queryLoginUser();
          })
          .catch(this.$message.error)
      },
          
  queryLoginUser(){ // 查看当前登录的用户
        // 查询用户信息
        axios.get("/user/me")
          .then(({ data }) => {
            // 保存用户
            this.user = data;
            if(this.user.id !== this.blog.userId){ // 如果不是本人查看就去判断是否关注 那前端就不用显示关注/取消关注了
              this.isFollowed();
            }
          })
          .catch(console.log)
      },
          
         <div class="logout-btn" @click="follow" v-show="!user || user.id !== blog.userId "> 
          <!-- 根据值 进行是否显示判断逻辑 -->
          {{followed ? '取消关注' : '关注'}}
        </div>
~~~



~~~java
    @Override
    public Result isFollow(Long followUserId) {
        Integer count = query().eq("user_id", UserHolder.getUser().getId())
                .eq("follow_user_id", followUserId).count();
        return Result.ok(count > 0);
    }
    @Override
    public Result follow(Long followUserId, Boolean isFollow) {
        Long userId = UserHolder.getUser().getId();
        if(isFollow) {
            Follow follow = new Follow();
            follow.setUserId(userId);
            follow.setFollowUserId(followUserId);
            save(follow);

        }else {
            followMapper.deleteFollow(userId,followUserId);
        }

        return Result.ok("操作成功");
    }
~~~

~~~markdown
设计的核心问题：

**设计数据库表如何设计**

**如何使用redis**
~~~

### 共同关注

~~~vue
  <el-tabs v-model="activeName" @tab-click="handleClick"> 
      <el-tab-pane label="笔记" name="1">
        <div v-for="b in blogs" :key="b.id" class="blog-item">
          <div class="blog-img"><img :src="b.images.split(',')[0]" alt=""></div>
          <div class="blog-info">
            <div class="blog-title" v-html="b.title"></div>
            <div class="blog-liked"><img src="/imgs/thumbup.png" alt=""> {{b.liked}}</div>
            <div class="blog-comments"><i class="el-icon-chat-dot-round"></i> {{b.comments}}</div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="共同关注" name="2">
        <div>你们都关注了：</div>
        <div class="follow-info" v-for="u in commonFollows" :key="u.id">
          <div class="follow-info-icon" @click="toOtherInfo(u.id)">
            <img :src="u.icon || '/imgs/icons/default-icon.png'" alt="">
          </div>
          <div class="follow-info-name">
            <div class="name">{{u.nickName}}</div>
          </div>
          <div class="follow-info-btn" @click="toOtherInfo(u.id)">
              去主页看看
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>


~~~

~~~js

handleClick(t) {
    if (t.name === '2') {
      this.queryCommonFollow();
    }
  } // 利用element组件实现类似路由功能

 queryCommonFollow() {
    axios.get("/follow/common/" + this.user.id) // 传递路径参数 当前点击详情页的用户
      .then(({data}) => this.commonFollows = data) // 返回共同关注的列表 
     // todo问题闪念：如果自己点击自己的共同关注呢？
      .catch(err => {
        this.$message.error(err);
      })
  },
~~~

![image-20250211235712512](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20250211235712512.png)

**所以需要改造一下关注接口** 使用redis数据结构存贮关注列表

但直接使用java的set求交集的功能也行：分别查数据库 当前用户 和 查询用户 的关注列表中的关注用户然后求交集 

~~~java
@Override
public Result followCommons(Long id) {
    Long userId = UserHolder.getUser().getId();
    Set<String> intersect = stringRedisTemplate.opsForSet().intersect("follows:" + userId, "follows:" + id);
    if(intersect == null ||  intersect.isEmpty()) // 看看空值是否会对接下来的逻辑有影响
        return Result.ok(Collections.emptyList());
    // 利用stream 解析id集合
    List<Long> ids = intersect.stream().map(Long::valueOf).collect(Collectors.toList());
    List<UserDTO> users = userService.listByIds(ids).stream()
            .map(user -> BeanUtil.copyProperties(user, UserDTO.class))
            .collect(Collectors.toList());
    return Result.ok(users);


}
~~~









---



### Feed流

**relate to 消息队列**

### 附近商铺

​	![image-20250212004610283](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212004610283.png)

**滚动查询的基本概念**

滚动查询的核心思想是将查询结果分成多个小的 "窗口" 或 "批次"，每次检索一部分数据。用户可以逐步请求接下来的数据，而不需要从头到尾重新查询整个数据集。

这种方法非常适合处理 **大规模数据集**，特别是在实时查询时，能够高效地处理和返回数据，减少服务器和网络的负担。

~~~java
 @GetMapping("/of/type")
    public Result queryShopByType(
            @RequestParam("typeId") Integer typeId,
            @RequestParam(value = "current", defaultValue = "1") Integer current
    ) {
        // 根据类型分页查询
        Page<Shop> page = shopService.query()
                .eq("type_id", typeId)
                .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
        // 返回数据
        return Result.ok(page.getRecords());
    }
~~~

![image-20250212011630432](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212011630432.png)



“**加载**”在计算机科学和信息技术中通常指的是将数据或程序从存储介质（如磁盘、网络等）传输到内存中，并准备好供程序或用户使用的过程。它可以用于不同的上下文中，具体含义可能会有所不同，但通常都有一个**共同的特点：将数据从一个地方获取到一个可以快速处理或使用的地方。**

---




- **加载程序**：指的是操作系统将程序从硬盘或其他存储设备加载到计算机的内存中，供 CPU 执行。例如，当你双击一个应用程序图标时，操作系统会把该程序的文件从硬盘加载到内存中，然后开始执行。
- **动态加载**：有些程序可能会在运行时根据需要动态加载某些资源或库（例如 DLL 或共享对象文件）。这可以提高效率，减少不必要的内存占用。

2. **数据加载**

- **加载数据**：指的是从存储设备（如数据库、磁盘、文件等）中将数据读取到内存中，以便程序能够处理。例如，数据库查询会从磁盘中加载数据到内存中，供后续操作使用。
- **懒加载**：一种优化策略，数据或资源在实际需要时才会加载，而不是在程序启动时就全部加载。这样可以减少程序启动时间，降低内存消耗。

3. **网页加载**

- **加载网页**：当你访问一个网页时，浏览器会从服务器加载网页的 HTML、CSS、JavaScript、图片等资源，显示在浏览器中。这个过程包括了数据的传输和渲染。
- **异步加载**：网页中的资源可能会通过异步加载（比如 AJAX）逐步加载，而不是一次性加载所有资源。这样可以提高网页的响应速度，避免页面卡顿。

4. **游戏加载**

- **加载游戏**：游戏启动时，所有的游戏资源（如关卡、图形、音效等）会被加载到内存中，游戏才能开始运行。在一些大型开放世界游戏中，游戏也可能会根据需要加载不同的区域或资源。
- **加载画面**：在游戏中，特别是在大型游戏中，游戏场景、关卡或者地图的切换通常需要时间，这时游戏会展示一个“加载中”画面来提示玩家正在加载资源。

5. **缓存加载**

- **加载缓存**：在计算机系统中，缓存用于存储频繁访问的数据。加载缓存是指将存储在缓存中的数据读取到内存中，以便更快速地访问，而不需要每次都从较慢的存储（如硬盘、网络）中重新读取。

6. **网络加载**

- **加载内容**：在客户端-服务器模型中，客户端从服务器加载数据或页面时，这个过程就被称为“加载”。例如，浏览器加载一个网页，应用程序加载云端数据等。

7. **系统启动加载**

- **加载操作系统**：当计算机启动时，操作系统会从硬盘中加载必要的系统文件、驱动程序等到内存中，来让系统正常启动并可供使用。这一过程通常涉及到加载启动管理器、内核和用户空间等。

8. **加载时间与性能**

- **加载时间**：指的是从发起加载请求到完成加载的时间。对于用户体验来说，较长的加载时间会带来不好的体验，尤其是在网页和应用程序中。开发者通常会采取各种优化措施，如延迟加载、异步加载等，来减少加载时间。
- **性能优化**：为了提高加载效率，开发者常常优化加载过程，例如使用压缩文件、优化数据传输、采用缓存机制、减少不必要的加载等。

### **加载数据库的数据到redis**

stream流的使用以及redis API的使用

**从一个一个添加迭代优化**？！



![image-20250212012509446](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212012509446.png)



### 实现附近商户功能

# ==TODO==:



### 用户签到

![image-20250212110729623](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212110729623.png)

![image-20250212111036972](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212111036972.png)

~~~java
    @Override
    public Result sign() {
        // 时间api的使用 类似分页插件我们都是通过业务功能去学习技术 然后知道这个技术的用法不要死记语法要用的时候ai即可
        String userId = UserHolder.getUser().getId().toString();
        String keySuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix ;
        int day = LocalDateTime.now().getDayOfMonth(); // 获取当前是本月第几天
        stringRedisTemplate.opsForValue().setBit(key,day-1, true);
        return Result.ok("签到成功");
    }
~~~

**统计连续签到:**

~~~java
   @Override
    public Result signCount() {
        String userId = UserHolder.getUser().getId().toString();
        String keySuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix ;
        int day = LocalDateTime.now().getDayOfMonth(); // 获取这个月的第几天
        List<Long> results = stringRedisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(day)).valueAt(0) // 子命令
        );
        if(results == null || results.isEmpty()) {
            return Result.ok(); // 没有任何的签到记录
        }
        Long num = results.get(0); // 获取的是十进制
        System.out.println(num);
        if(num==0)
            return Result.ok("没有任何签到记录");
        int count = 0;
        int MaxCount = -1;
        while(num !=0L) {
            if((num&1)==0) {
                MaxCount = Math.max(count,MaxCount);
            }
            else
                count++;
            num >>>=1;
        }
        MaxCount = Math.max(count,MaxCount);// 循环结束后再判断一次 因为只有再出现没签到的才会结算
        System.out.println(MaxCount);
        return Result.ok(MaxCount);

    }
~~~





### UV统计

![image-20250212155041969](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212155041969.png)

![image-20250212154954302](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212154954302.png)

![image-20250212155904977](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250212155904977.png)

~~~java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Service
public class UVService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "0 0 0 * * ?")  // 每天午夜 0 点清理和重新统计
    public void clearAndResetUV() {
        // 1. 删除前一天的 UV 统计数据
        LocalDate yesterday = LocalDate.now().minusDays(1);
        stringRedisTemplate.delete("uv:" + yesterday);

        // 2. 统计新的 UV 数据（假设通过访问记录来统计）
        // 你可以通过访问接口等记录新的用户访问数据
        recordVisit(101L);  // 记录用户访问
        recordVisit(102L);
    }

    public void recordVisit(Long userId) {
        String key = "uv:" + LocalDate.now();
        stringRedisTemplate.opsForHyperLogLog().add(key, userId.toString());
    }
}
// @Scheduled 是 Spring 框架提供的一个注解，用于 定时执行任务。通过配置 cron 表达式，您可以非常灵活地指定任务的执行时间。cron 是一个 Unix/Linux 系统 下的 定时任务调度器，用于定期执行某些任务（如备份、日志清理、自动更新等）。
// 在 Linux 和类 Unix 系统中，cron 服务允许用户定义定时任务（例如，每天、每小时等）以特定的时间间隔执行。

~~~

