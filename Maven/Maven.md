# Maven 是什么

**[Manven下载](https://maven.apache.org/download.cgi)**

## ==${Maven 的核心功能}：==

**依赖管理：**

Maven 允许开发人员在项目中自动化管理库依赖。通过定义项目所需的依赖库，Maven 可以自动从-中央仓库-或-私有仓库-下载和管理这些库以及它们的版本和依赖关系。
项目结构标准化：Maven 提供了一个标准化的项目结构（例如，源代码、测试代码、资源文件的目录结构），因此新项目可以快速遵循一个统一的约定。

**生命周期管理：**

Maven 定义了一套标准的构建生命周期，包括**编译、测试、打包、验证、发布**等不同阶段，开发人员可以通过执行这些阶段来自动完成项目的构建工作。

**插件机制：**

Maven 的强大之处在于其插件机制。Maven 本身是轻量级的，很多具体功能（如编译、打包、部署等）是通过插件实现的。开发者可以根据需求使用或编写插件来扩展功能。

**POM 文件**

：Maven 使用一个名为 pom.xml 的文件（Project Object Model，项目对象模型）来定义项目的元数据、依赖项、插件配置、项目构建步骤等。POM 文件是 Maven 项目的核心配置文件。
仓库管理：Maven 使用本地仓库和远程仓库（例如 Maven 的中央仓库）来存储项目的依赖和构建结果。项目构建时，Maven 会自动检查这些仓库，确保项目所需的依赖库能够被正确获取。

----



### 1.2 为什么要使用Maven
- **依赖管理**：Java项目中会有很多的依赖库文件，这些库文件可能有很多的依赖关系，如果我们手动去下载这些依赖的话，不但非常的麻烦，而且不同的依赖版本之间可能会有冲突，这个时候就可以使用Maven来帮助我们管理这些依赖，我们需要做的就是在POM文件中告诉Maven我们需要哪些依赖，然后Maven就可以自动的将这个jar包，以及它所依赖的其他所有jar包全部都下载并导入到项目中，非常的方便。
- **构建管理**：在Java项目中，我们需要把Java的源文件编译成字节码文件，然后再把字节码文件打包成一个可执行的jar包或者war包，如果没有一个自动化的构建工具的话，这个过程就会非常的繁琐，而且容易出错，Maven提供了一个标准的项目结构和构建流程，只需要按照这个标准来组织项目，就可以非常轻松方便的构建Java项目。
- **项目管理**：Maven提供了一个标准的项目结构和构建流程，只需要按照这个标准来组织项目，就可以非常轻松方便的构建Java项目。

### 1.3 Maven的核心概念及工作原理
Maven的核心概念是项目对象模型**（Project Object Model，POM）**，它是一个XML文件，也是 Maven 项目的核心文件，定义了项目的配置、依赖、插件以及构建的过程。Maven读取pom.xml文件之后，会根据这个文件中定义的规则去下载依赖包，然后编译工程中的源代码，最后将工程打包成一个可执行的jar包或者war包，这个过程中会有很多的插件来帮助我们完成这些工作，比如说编译插件、打包插件、测试插件等等，这些插件都是Maven提供的，我们只需要在pom.xml文件中配置一下就可以了，Maven会自动的去执行这些插件，完成构建的过程。

### 1.4 Maven仓库
Maven中有一个仓库的概念，仓库简单来说就是指存放jar包的地方，按照作用范围的不同可以分为**本地仓库、远程仓库和中央仓库。**
- 本地仓库就是我们自己电脑上的一个目录，一般默认是在用户**家目录**(`$HOME`)下的`.m2`这个目录里面，这个位置可以在Maven的配置文件中修改
- **家目录就是 user目录**
  
  ```xml
  <settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
  <!--
   | The path to the local repository maven will use to store artifacts.
   | Default: ${user.home}/.m2/repository
  -->
  <localRepository>/path/to/local/repo</localRepository>
      /------------填写家目录下m2下的仓库文件夹------------------------/
  
  ```
- 远程仓库也叫做**私服仓库**，一般是公司内部搭建的一个仓库，用来给公司内部的项目提供统一的依赖管理，这样就可以避免jar包的重复下载，而且也可以把一些公司内部发布的私有的jar包放到这个仓库里面，供其他项目来使用，一般由公司内部专门的运维人员来维护，最常用的搭建私服仓库的工具是 *[Nexus](https://help.sonatype.com/en/download.html)*，远程仓库并不是必须的，如果没有配置的话，Maven会直接去中央仓库中下载依赖。
- 中央仓库是Maven官方提供的一个仓库，里面包含了大量的开源项目，地址是 https://repo.maven.apache.org/maven2



## 2. 安装配置

### 2.1 安装配置JDK
Maven要求JDK版本至少在1.7以上，所以首先需要安装JDK，可以到[Oracle官网](https://www.oracle.com/java/technologies/downloads/)下载JDK，然后配置系统环境变量`JAVA_HOME`和`PATH`。

* Linux/Mac系统
```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_291.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```
* Windows系统

Windows系统下找到系统环境变量，添加`JAVA_HOME`和`PATH`，如下图所示：

找到系统属性中的环境变量：
`JAVA_HOME`和`PATH`，`JAVA_HOME`的值为`JDK`的安装路径，`PATH`中添加`%JAVA_HOME%\bin`，如下图所示：
如下图所示：

![](https://neucms.com/img/20240816203622.png)

设置环境变量：

![](https://neucms.com/img/20240816204034.png)

### 2.2 下载安装Maven
[官网下载地址](https://maven.apache.org/download.cgi)

配置系统环境变量`MAVEN_HOME`和`PATH`

### 2.3 系统环境变量配置

#### 2.3.1 Linux/Mac系统
根据使用的`Shell`不同，配置文件也不同，
可以使用`echo $SHELL`来查看当前使用的`Shell`，一般是`bash`或者`zsh`。
在`~/.bashrc`或者`~/.zshrc`文件中添加如下内容：

```bash
export MAVEN_HOME=/Users/yiny/soft/apache-maven-3.9.8
export PATH=$MAVEN_HOME/bin:$PATH
```
配置完成之后需要重启一下终端，或者使用`source ~/.bashrc`或者`source ~/.zshrc`来使配置生效。

#### 2.3.2 Windows 系统

Windows系统下找到系统环境变量，添加`MAVEN_HOME`和`PATH`，如下图所示：

![](https://neucms.com/img/20240816200803.png)

`MAVEN_HOME`的值为Maven的安装路径，`PATH`中添加`%MAVEN_HOME%\bin`，如下图所示：

![](https://neucms.com/img/20240816204550.png)

### 2.4 配置镜像仓库

由于中央仓库是部署在国外的服务器上，所以下载速度可能会比较慢，我们可以配置一个国内的镜像仓库来加速下载速度，比如阿里云的镜像仓库，配置方法如下：

```xml
<!-- settings.xml -->
<mirrors>
  <mirror>
    <id>aliyunmaven</id>
    <mirrorOf>*</mirrorOf>
    <name>阿里云公共仓库</name>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
</mirrors>
```

###  ==${2.5 配置jdk}==

**如果已有就无需在**![image-20241104215318161](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241104215318161.png)

**<profile></profile>下配置jdk1.8**

**查看jdk 方法有很多 （检索技巧）**



## 3. Maven工程的目录结构

Maven的工程目录结构是有一定的规范的，这样可以方便Maven来自动的构建项目，下面是一个标准的Maven工程目录结构：
```bash
project                     # 项目根目录
|-- src                     # 源代码目录
|   |-- main                # 主目录
|   |   |-- java            # Java源代码目录
|   |   |-- resources       # 资源文件目录
|   |   |-- webapp          # Web应用目录
|   |-- test                # 测试目录
|       |-- java            # 测试源代码目录
|       |-- resources       # 测试资源文件目录
|-- target                  # 项目构建目录
|-- pom.xml                 # 项目配置文件
```
例如：

![](https://neucms.com/img/20240805231706.png)


## 4. POM文件

POM文件是Maven项目的核心文件，它是一个XML文件，定义了项目的配置、依赖、插件以及构建的过程。

以下是一个简单的POM文件示例：

```xml
<project xmlns = "http://maven.apache.org/POM/4.0.0"
    xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation = "http://maven.apache.org/POM/4.0.0
    http://maven.apache.org/xsd/maven-4.0.0.xsd">

  <!-- 模型版本 -->
  <modelVersion>4.0.0</modelVersion>
  <!-- 公司或者组织的唯一标志，并且配置时生成的路径也是由此生成，
        如：com.companyname.project-group，
        maven会将该项目打成的jar包放本地路径：
        /com/companyname/project-group -->
  <groupId>com.companyname.project-group</groupId>

  <!-- 项目的唯一ID，一个groupId下面可能多个项目，就是靠artifactId来区分的 -->
  <artifactId>project</artifactId>

  <!-- 版本号 -->
  <version>1.0</version>

  <!-- 属性变量 -->
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <!-- 依赖 -->
  <dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.3.9</version>
    </dependency>
  </dependencies>

  <!-- 依赖管理 -->
  <dependencyManagement>
    <dependencies>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-core</artifactId>
          <version>5.3.9</version>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <!-- 仓库管理 -->
  <repositories>
    <repository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
  </repositories>

  <!-- 构建 -->
  <build>
    <!-- 插件管理 -->
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```

## ==${有无Maven 的区别}==

### 1. 项目初始化

#### 使用 Maven:
- **创建项目**：使用 Maven 的 archetype 生成器创建标准化的项目结构。
  ```bash
  mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```
- **项目结构**：
  ```
  my-app/
  ├── pom.xml
  ├── src/
  │   ├── main/
  │   │   └── java/
  │   │       └── com/example/App.java
  │   └── test/
  │       └── java/
  │           └── com/example/AppTest.java
  ```

### 2. 添加依赖

#### 使用 Maven:
- **定义依赖**：在 `pom.xml` 文件中添加所需的依赖。
  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-core</artifactId>
          <version>5.3.10</version>
      </dependency>
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.13.2</version>
          <scope>test</scope>
      </dependency>
  </dependencies>
  ```

### 3. 编译和构建

#### 使用 Maven:
- **编译**：使用 Maven 编译项目源代码和测试代码。
  ```bash
  mvn compile
  mvn test
  ```
- **打包**：使用 Maven 打包项目，生成 JAR 文件。
  ```bash
  mvn package
  ```

#### 不使用 Maven:
- **编译**：手动编译源代码。
  ```bash
  javac -d bin src/main/java/com/example/App.java
  ```
- **运行测试**：手动运行测试代码。
  ```bash
  javac -d bin -cp .:junit-4.13.2.jar src/test/java/com/example/AppTest.java
  java -cp .:junit-4.13.2.jar org.junit.runner.JUnitCore com.example.AppTest
  ```
- **打包**：手动使用 `jar` 工具打包项目。
  ```bash
  jar cvf my-app.jar -C bin .
  ```

### 4. 部署和运行

#### 使用 Maven:
- **部署**：将生成的 JAR 文件上传到 Nexus 仓库或直接部署到服务器。
  ```bash
  mvn deploy
  ```
- **运行**：使用 JAR 文件运行应用程序。
  ```bash
  java -jar target/my-app-1.0-SNAPSHOT.jar
  ```

#### 不使用 Maven:
- **部署**：将手动生成的 JAR 文件上传到服务器。
- **运行**：手动运行 JAR 文件。
  ```bash
  java -jar my-app.jar
  ```

### 打包和不打包的区别

| 项目阶段   | 使用 Maven                        | 不使用 Maven                 | 区别与影响                              |
| ---------- | --------------------------------- | ---------------------------- | --------------------------------------- |
| 项目初始化 | 自动生成标准化结构                | 手动创建目录和文件结构       | 使用 Maven 更加规范和方便，减少人为错误 |
| 依赖管理   | 自动管理和下载依赖，版本控制      | 手动下载和管理依赖，容易出错 | 使用 Maven 提高效率和可靠性             |
| 编译和打包 | 自动化编译和打包，生成 JAR 文件   | 手动编译和打包，过程繁琐     | 使用 Maven 简化流程，提高效率           |
| 部署和运行 | 自动化部署和运行，可与 CI/CD 集成 | 手动上传和运行，容易出现错误 | 使用 Maven 提高部署效率和一致性         |

### JAR 包的内容和用途
- **内容**：包括编译后的类文件、资源文件（如配置文件、图片）、依赖库（如果使用 Maven 的插件）以及元数据文件（`MANIFEST.MF`）。
- **用途**：简化分发和部署、提高运行效率、版本管理和控制、确保依赖一致性和完整性。

通过以上流程可以看出，使用 Maven 打包 JAR 文件极大地优化了项目的开发和部署流程，提高了效率和可靠性。而不使用 Maven 则需要手动处理多个步骤，容易出现错误且耗时费力。

希望这些解释能帮你更好地理解 Maven 的使用、JAR 包的内容以及为什么要打包。如果有更多问题或需要进一步讨论，随时告诉我！ 😊







## 5. 构建生命周期

Maven提供了三种主要的生命周期：`Clean`、`Default`和`Site`
### 5.1 `Clean`：用于项目清理（`mvn clean`）

执行`clean`生命周期，会删除`target`目录下的所有文件，包括编译后的字节码文件、打包后的jar包、生成的站点等等。

```bash
mvn clean
```

### 5.2 `Default` ：用于项目部署
```
validate` => `compile` => `test` => `package` => `verify` => `install` => `deploy
```

- `mvn clean`：清理项目
- `mvn compile`：编译项目
- `mvn test`：运行测试
- `mvn package`：编译代码并打包成可分发格式，比如jar/war
- `mvn install`：将包安装到本地仓库中，供其他模块使用
- `mvn deploy`：将包部署到远程仓库中，供其他项目和开发人员使用
- `mvn site`：生成项目站点



| 阶段           | 处理     | 描述                                                     |
| -------------- | -------- | -------------------------------------------------------- |
| `mvn validate` | 验证项目 | 验证项目是否正确且所有必须信息是可用的                   |
| `mvn compile`  | 执行编译 | 源代码编译在此阶段完成                                   |
| `mvn test`     | 测试     | 使用适当的单元测试框架（例如JUnit）运行测试。            |
| `mvn package`  | 打包     | 将编译后的代码打包成可分发的格式，例如 JAR 或 WAR        |
| `mvn verify`   | 检查     | 对集成测试的结果进行检查，以保证质量达标                 |
| `mvn install`  | 安装     | 安装打包的项目到本地仓库，以供其他项目使用               |
| `mvn deploy`   | 部署     | 拷贝最终的工程包到远程仓库中，以共享给其他开发人员和工程 |

### 5.3 Site：用于生成项目站点

用于生成项目站点，包括项目的文档、报告、API文档等等。

```bash
# 生成站点文档
mvn site
# 部署站点文档
mvn site:deploy
```

### 5.4 插件命令
Maven插件扩展了Maven的功能，可以用来完成一些特定的任务，
1. `mvn archetype:generate`：创建一个新的Maven项目，并生成项目骨架
2. `mvn dependency:tree`：查看项目依赖树
3. `mvn dependency:analyze`：分析项目依赖
4. `mvn dependency:resolve`：解析项目依赖
5. `mvn dependency:copy-dependencies`：复制项目依赖
6. `mvn versions:display-dependency-updates`：显示项目依赖的更新
## 6. 依赖管理
### 6.1 依赖的范围
- `compile`：默认范围，编译、测试、运行时都有效
- `provided`：编译、测试有效，运行时无效，比如servlet-api
- `runtime`：测试、运行时有效，编译时无效
- `test`：测试时有效，编译、运行时无效
- `system`：类似`provided`，但是需要指定jar包的路径
- `import`：导入依赖的范围



### 6.2 依赖的传递性
Maven会自动的解决依赖的传递性，比如说A依赖B，B依赖C，那么Maven会自动的将C也导入到A中，这样就不需要我们手动的去导入C了。
只有当依赖的范围是`compile`或者`runtime`的时候，依赖才会被传递，如果依赖的范围是`provided`或者`test`的时候，依赖是不会被传递的。


### 6.3 依赖的排除
有时候我们引入的依赖包中可能会包含一些我们不需要的依赖，
这个时候我们可以使用`<exclusions>`标签来排除这些依赖。

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>6.1.11</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
也可以通过`<optional>`标签来指定依赖是否可选，如果依赖是可选的，那么在引入这个依赖的时候，可以不用引入这个依赖的依赖。

### 6.4 依赖的版本冲突
当通过依赖传递导入的两个依赖包版本不一致时，Maven会根据一定的规则来解决这个冲突，
一个是最短路径优先，另一个是先声明优先。


## 7. Nexus私服仓库

Nexus是最常用的Maven私服仓库，可以用来存放公司内部的jar包，以及一些第三方的jar包，这样就可以避免重复下载，提高构建速度。

### 7.1 下载安装Nexus
下载地址：[https://help.sonatype.com/en/download.html](https://help.sonatype.com/en/download.html)

### 7.2 安装配置Nexus

#### 7.2.1 直接解压安装
下载完成之后解压到本地，然后进入到解压后的目录，执行`bin/nexus run`命令，启动Nexus服务。

Linux或者Mac环境可以直接执行`bin/nexus run`命令来启动，
Windows环境可以执行`bin/nexus.bat run`命令来启动，
启动之后可以通过浏览器访问`http://localhost:8081`来访问Nexus的管理界面，
第一次登录会提示输入用户名和密码，
用户名默认是admin，
密码可以在`nexus-data/admin.password`文件中查看。

#### 7.2.1 使用Docker安装

* Mac (Apple Silicon)
```bash
docker pull klo2k/nexus3
docker run -d -p 8081:8081 --name nexus klo2k/nexus3
```

* Windows 和其他系统
```bash
docker run -d -p 8081:8081 --name nexus sonatype/nexus3
```

### 7.3 登录Nexus
第一次登录需要到配置文件中修改默认密码
```bash
docker exec -it nexus cat /nexus-data/admin.password
```

登录后修改即可

### 7.4 创建和管理仓库

登录之后，可以在左侧的`Repositories`菜单中创建仓库，
一般会创建四个仓库：
- `releases`：用来存放正式版本的jar包
- `snapshots`：用来存放快照版本的jar包
- `proxy`：代理中央仓库，用来缓存中央仓库的jar包
- `public`：用来发布jar包，组合了以上三种仓库


### 7.5 配置连接私服仓库

#### 7.5.1 修改Maven的settings.xml文件
修改`settings.xml`，配置私服仓库地址，
使得Maven可以从私服仓库中下载jar包。
```xml
<mirrors>
  <mirror>
    <id>maven-nexus</id>
    <mirrorOf>*</mirrorOf>
    <name>Nexus私服</name>
    <url>http://localhost:8081/repository/maven-public/</url>
  </mirror>
</mirrors>
```
如果Nexus中不允许匿名访问，需要在`settings.xml`中配置用户名和密码
```xml
<servers>
  <server>
    <id>maven-nexus</id>
    <username>admin</username>
    <password>admin</password>
  </server>
```
#### 7.5.2 修改项目的pom.xml配置
修改项目中的`pom.xml`文件，配置私服仓库地址，
使项目可以从私服仓库中下载jar包，或者上传jar包到私服仓库中。

```xml
<!-- 发布管理 -->
<distributionManagement>
  <!-- 正式版本 -->
  <repository>
    <id>maven-nexus</id>
    <name>Project Releases Repositories</name>
    <url>http://localhost:8081/repository/maven-releases/</url>
  </repository>
  <!-- 快照版本 -->
  <snapshotRepository>
    <id>maven-nexus</id>
    <name>Project Snapshots Repositories</name>
    <url>http://localhost:8081/repository/maven-snapshots/</url>
  </snapshotRepository>
</distributionManagement>
```
注意：这里的id要和settings.xml中的id一致

### 7.6 上传jar包到私服仓库
执行一个`mvn deploy`命令，就可以将jar包上传到私服仓库中，
上传之后在Nexus的管理界面中就可以看到对应的jar包。