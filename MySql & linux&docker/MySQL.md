<center><font size=30 color=red>SQL</font></center>

# mysql启动

注意cmd 和powershell 中命令可能不一样



net stop/start MySQL80  ==(sql服务名)==

查询sql状态： sc query MySQL80（服务名）

或者win + R 打开 services.msc 服务程序查看各种服务的状态

*![image-20241001221437368](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001221437368.png)*

**Schema在数据库中起到核心作用，它是数据库的结构和设计的蓝图，定义了数据库中表的构成、数据字段、数据类型、索引、主键、外键、视图、存储过程以及触发器等元素。**

# *基础操作*

***启动服务：**systemctl start mysql**d***

***注释**：![image-20241001221511004](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001221511004.png)*

*区分大小写 ，关键字建议用大写！*





# 1.*SQL分类*

## *DDL*

### *数据库操作：*



*![image-20241001221719924](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001221719924.png)*

***create database if not exists test default charset utf8mb4;***

*utf8 是 MySQL 中最早支持的 Unicode 字符集，它使用 1 到 3 个字节来编码每个字符，最大能表示的 Unicode 码点是 U+FFFF，也就是 Unicode 的基本多文种平面（BMP）。这意味着 utf8 不能存储一些超出 BMP 的字符，例如 Emoji 表情、部分罕用汉字、新增的 Unicode 字符等。这些字符需要 4 个字节来编码，所以 utf8 会在遇到这些字符时报错或者出现乱码。*

### *表操作：*

*![image-20241001223222249](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001223222249.png)*

#### *表创建*



*![image-20241001223800347](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001223800347.png)*





#### *数据类型*

*<img src="https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001224019211.png" style="zoom:200%;" />*

***精度：数字的位数   标度：小数点后数字的位数***

*<img src="https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001224033255.png" alt="image-20241001224033255" style="zoom:200%;" />*

*varchar 和 char  变长：动态变化（最大长度，按照实际的长度存储)   定长 ： 定长度（少于一定长度，就用空字符补齐）*

> age tinyint [unsigned]  



![image-20241001230357657](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001230357657.png)

**注意点： char（1） 表示1个==字符==并不是一个==字节==。** 存汉字时要注意！

在mysql中，一个中文汉字所占的字节数与编码格式有关：如果是**gbk编码，**则一个中文汉字占2个字节；如果是**utf8编码**，则一个中文汉字占3个字节，而英文字母占1字节。**utf8m64** 一个汉字就表示 4个字节





#### 修改操作

**==删除关键字== ： drop**



##### 增删查改 字段   && 修改表名 删除表/删除表内内容 

![image-20241001230411882](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001230411882.png)



![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001230448745.png)



![image-20241001230430806](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001230430806.png)





![image-20241001230441163](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001230441163.png)

![image-20241001230713243](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241001230713243.png)





## *DML*

在DDL定义了字段的基础上添加该字段的成员（使用DML）

![image-20241002135633666](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002135633666.png)

---

==**添加和批量添加**==

### 添加数据



![image-20241002135640218](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002135640218.png)

---

### 修改数据



**修改表中字段对应的数据值**

![image-20241002135646888](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002135646888.png)

![image-20241002144500356](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002144500356.png)

**如没有where条件的话就修改了所有的字段对应的成员值**

![image-20241002144653672](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002144653672.png)

---

### 删除数据



![image-20241002135656168](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002135656168.png)

---

where条件就是去定位满足某种条件的!

## *DQL*

![image-20241002145352461](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002145352461.png)

### 基本查询

![image-20241002145402221](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002145402221.png)

**查询到的同时更改为别名**

### 条件查询





![image-20241002145411257](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002145411257.png)

**like : 模糊匹配** 

---



![image-20241002151157811](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002151157811.png)

<font size=6>**==条件列表==说明后面跟的条件可以有很多种**</font>

(列表--> 多种!!!)

### 聚合函数



![image-20241002151318180](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002151318180.png)

![img](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/%7B19BF7377-6B00-4435-A50D-E669C746A971%7D)



**聚合函数一般和分组查询结合使用！**

### 分组查询



![image-20241002152427414](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002152427414.png)

  <font color=red size=6>**聚合和非聚合列查询时一定要进行分组,因为聚合函数就是对某一个组进行操作**</font>



![image-20241002151413923](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002151413923.png)



![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002153205883.png)

**中间那个是光标!**



where 首先过滤 之后在此基础上进行聚合函数的操作(分组) 分组后 ,使用having 进行

<img src="https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/%7BADC53FD5-B2E4-4831-BC77-E1C3F62AEA1A%7D" style="zoom:150%;" />

<font color=blue>**也就是having后的关键字 ,分组后就只查询出了聚合函数和分组字段!**</font>

**having 就是类似 | grep 后滤**

![fkjsdf](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002155002350.png)

### 排序查询

![image-20241006220647003](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006220647003.png)

**asc：ascend** 

**desc ： descend**

### 分页查询 (*)

![image-20241006221438674](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006221438674.png)

![image-20241006231524837](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006231524837.png)



###  <font color=red>查询sum  :   具体执行顺序&编写顺序</font>

### ![image-20241002164927610](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002164927610.png)





---



## *DCL*

![image-20241002160231009](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002160231009.png)

![image-20241002160239026](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002160239026.png)



只有 同时定位 用户名和主机地址才可以完整定义一个用户

主机地址指的是,这个用户只能在这个主机地址上访问当前mysql服务器,所以就不能**远程访问!**



#### 权限和用户管理

##### 用户管理



![image-20241002160622080](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002160622080.png)

![image-20241002160628257](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002160628257.png)

##### 权限修改

 ![image-20241006222743764](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006222743764.png)

**数据库.表名 不用加引号 表示用户将获得这个数据库中的表某种权限**

**on 那张表 to（赋予）/from（撤销）**

---



## 2.函数

### 字符串函数

![image-20241002163515266](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002163515266.png)

### 日期函数



![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002163617674.png)

与[数据类型](#数据类型)中的时间类型做区别 

### 数值函数

![image-20241002163652488](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241002163652488.png)

### 流程函数

![image-20241006231605096](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006231605096.png)

![image-20241006231956434](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006231956434.png)

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232418677.png)

# 3.约束(constraint)

![image-20241006232550253](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232550253.png)



![image-20241006233016321](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006233016321.png)

- **`PRIMARY KEY`**：强制列中的数据唯一，并且不能包含 `NULL` 值。一个表只能有一个主键。
- **`UNIQUE`**：强制列中的数据唯一，但允许包含 `NULL` 值（每个 `NULL` 被视为不同的值）。一个表可以有多个唯一约束。



---

例子：

![image-20241006232603205](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232603205.png)

![image-20241006233115025](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006233115025.png)

---

## 重点：外键约束





![image-20241006232611318](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232611318.png)





![image-20241006232618920](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232618920.png)

**主表列名一般就是主键** 

![image-20241006232626926](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232626926.png)

**对于外键：**

**update 以及delete 约束**

（最后一个暂时不用管）

![image-20241006232637855](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241006232637855.png)

![image-20241007101848231](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007101848231.png)

# 4.多表查询

## 多表关系

### 一对多

![image-20241007101606854](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007101606854.png)

### 多对多

![image-20241007101742791](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007101742791.png)

### 一对一

![image-20241007102701259](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007102701259.png)



## 多表查询概述

**核心：搞清表结构和查询条件**

![image-20241007103344572](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007103344572.png)

![image-20241007103354179](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007103354179.png)

![image-20241007103400117](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007103400117.png)



## 多表查询分类

### 1.连接查询

==核心：集合部分==

![image-20241007103520413](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007103520413.png)



![image-20241007162718114](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007162718114.png)

![image-20241007162737851](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007162737851.png)

==对于以上的这个交集就是dept_id==

#### 内连接

##### 隐式内连接

![image-20241007103952020](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007103952020.png)

##### 显示内连接

![s](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007161955054.png)



**inner 可省略**

---

**这样就可以查询到内连接查询不到的null值了**

**（如果一个是null，交集肯定是没有的）**

所以内连接查不到

#### 外连接

![image-20241007162325426](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007162325426.png)

![image-20241007175858201](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007175858201.png)



![image-20241007175837594](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007175837594.png)

**左右表由书写顺序决定！**

#### 自连接

<font size=9 color=red>自连接分为内外连接</font>

**也就是说集合关系还是一样的，区分内外什么时候用（一般就看null值要不要拆查询）**

![image-20241007180433325](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007180433325.png)



**核心：将一张相同的表看作是两张一样的表设置主外键 来进行连接**

**自连接必须起别名 不然会混淆**

![image-20241007181014237](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007181014237.png)

**在排列组合中通过where确定对应关系**

![image-20241007181339391](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007181339391.png)



## 2.联合查询

![image-20241007182604689](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241007182604689.png)

## 3.子查询

![image-20250417215239480](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215239480.png)

#### 标量子查询



#### 列子查询

![image-20250417215246109](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215246109.png)

**多行数据==不能使用==等于运算符**

#### 行子查询

![image-20250417215309011](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215309011.png)

![image-20250417215542098](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215542098.png)



![image-20250417215550271](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215550271.png)

#### 表子查询



![image-20250417215635244](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215635244.png)



![image-20250417215644858](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215644858.png)

设置别名



![image-20250417215700125](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215700125.png)



![image-20250417215751559](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215751559.png)

# 5.事务

![781c95484310110a81382c0b1abbf80](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/781c95484310110a81382c0b1abbf80.jpg)

![d2e4d3b8473113bc3b79111b7973994](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/d2e4d3b8473113bc3b79111b7973994.jpg)

![image-20250417215815643](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215815643.png)

**脏读问题：**

![image-20250417215848314](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215848314.png)

在 SQL 中，**事务的隔离级别**决定了**一个事务在提交前更新的数据，其他事务读取时看到的是更新前的版本还是更新后的版本**。、



**不可重复读问题：**



![image-20250417215838646](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215838646.png)



**幻读问题：**



![image-20250417215931381](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417215931381.png)

这里是假设**已经解决不可重复读问题**的基础上

解决了指的是当前事务读不到别的事务commit后的数据库，相当于自己copy了一份数据库作为自己的局部变量

也就是保证了两次的读到的数据是一样的



## 事务的隔离级别 -- 解决事务并发问题

![image-20250417220126788](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250417220126788.png)

不可重复”(Non-Repeatable)的
含义
“不可重复”强调的是在同一个事务中，对同一数据的多次读取无法得到一致的结果。换句话说，事务无法“重复”地读取到相同的值。这是从事务内部的视角出发的，反映了事务在读取数据时的不一致性。

---



**Read Uncommitted(读未提交)**

**名字解释:**允许一个事务读取到其他事务尚未提交的数据(即“未提交”的数据)

解决并发问题:这种隔离级别几乎不解决并发问题，因为它允许事务读取到其他事务的“脏数据”(即未提交的数据)。例如，事务A正在修改数据事务B读取了事务A修改后的数据，但事务A最终回滚，事务B读取到的数据就不存在了。
并发问题:无法防止脏读、不可重复速和幻读

---



**Read Committed(读已提交)**

**名字解释:**一个事务只能读取到其他事务已经提交的数据。

**解决并发问题:**通过限制事务只能读取已提交的数据，解决了“脏读”问题。事务A修改数据但未提交时，事务B无法读取到事务A修改后的数据，只有当事务A提交后，事务B才能读取到新的数据。
并发问题:仍然无法防止不可重复读和幻读。例如，事务A先读取了数据。事务B修改并提交了数据，事务A再次读取时，数据可能已经改变

---



**Repeatable Read(可重复读)**

**名字解释:**在一个事务内，对于同一行数据的多次读取结果必须是一致的，即使其他事务修改了数据。

**解决并发问题:**通过锁定事务第一次读取的行，防止其他事务修改这些行，从而解决了不可重复读问题。例如

，事务A第一次读取了某行数据后，其他事务无法修改这行数据，直到事务A提交或回滚。
并发问题:仍然无法防止幻读。幻读是指事务A读取了某个范围的数据，事务B在这个范围内插入了新的数据，事务A再次读取时会发现多出了一些数据。

---



**Serializable(可串行化)**

**名字解释:**将多个并发事务串行化执行，即让事务按照某种顺序依次执行，如同它们是串行的。

**解决并发问题:**通过严格的锁定机制，确保事务之间完全隔离。事务A和事务B的执行顺序被明确安排，事务A执行时，事务B必须等待，反之亦然。这种方式可以防止脏读、不可重复读和幻读。
并发问题:虽然解决了所有并发问题，但代价是性能和并发性。因为事务需要等待其他事务完成，导致系统吞吐量降低。

