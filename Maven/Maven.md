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

---

# pom 和setting xml文件区别

###  **`pom.xml`**

`pom.xml` 是 Maven 项目的核心配置文件，主要用于**定义项目的基本信息、依赖项和构建配置。它的作用范围是单个项目，通常位于项目的根目录中。**

#### 主要功能：

- **项目的基本信息**：如 `groupId`、`artifactId`、`version` 等。
- **依赖管理**：使用 `<dependencies>` 元素来声明项目所需要的外部库或其他项目的依赖。
- **构建设置**：包括编译插件、打包插件等。
- **插件配置**：可以为构建过程配置插件，如编译插件、测试插件等。
- **模块管理**：在多模块项目中，`pom.xml` 可以管理多个子模块。
- **构建生命周期**：定义项目在不同生命周期阶段的行为（编译、测试、打包等）。

#### 作用范围：

- **针对每个项目**：`pom.xml` 是项目特定的，作用范围仅限于包含该文件的项目及其子模块。

#### 示例 `pom.xml`：



```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.34</version>
        </dependency>
    </dependencies>

    <build>
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

###  **`settings.xml`**

`settings.xml` 是 Maven 的全局或用户级配置文件，主要用于配置 Maven 的运行时行为、镜像、代理、认证、仓库等信息。它的作用范围是 Maven 的全局配置或用户级别的配置，而不是项目级别。

#### 主要功能：

- **本地仓库配置**：通过 `<localRepository>` 元素配置 Maven 下载和安装依赖的本地仓库路径。
- **远程仓库、镜像配置**：通过 `<mirrors>` 元素配置远程镜像仓库，通常用于配置 Maven 中央仓库的镜像。
- **代理配置**：通过 `<proxies>` 元素配置 Maven 使用的网络代理。
- **服务器认证**：通过 `<servers>` 元素配置访问私有仓库时的认证信息。
- **激活配置文件**：通过 `<profiles>` 元素配置 Maven 的全局构建配置文件，可以根据不同的环境使用不同的配置文件。

#### 作用范围：

- **全局或用户级别**：`settings.xml` 可以位于两个位置：

  1. **全局级别**：Maven 安装目录下的 `Maven_Home/conf/settings.xml`（对所有用户生效）。
  2. **用户级别**：用户主目录下的 `~/.m2/settings.xml`（仅对当前用户生效）。

  当两个 `settings.xml` 文件同时存在时，用户级别的 `settings.xml` 会覆盖全局级别的设置。

  ---

  

### 1.4 Maven仓库

![image-20241123164025958](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123164025958.png)

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

由于中央仓库是部署在国外的服务器上，所以下载速度可能会比较**慢**，我们可以配置一个国**内的镜像仓库来加速下载速度**，比如阿里云的镜像仓库，配置方法如下：

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

## ==${Maven & 仓库 & 项目 }== 

**本地仓库 ** 本地仓库是一个用于存储 Maven 依赖和构建项目所需的库和插件的目录。它允许 Maven 在不依赖于远程仓库的情况下进行构建和运行，从而加快构建过程并提高离线工作的能力

**私服仓库**  

**中央仓库**

- **Apache Maven** 是一个强大的构建工具，帮助管理 Java 项目的依赖和构建过程。
- **本地仓库** 是 Maven 存储下载的依赖和插件的地方，提供了快速访问和离线支持。
- 在 **IntelliJ IDEA** 中创建的 **Maven 项目**与本地仓库紧密相关，通过 `pom.xml` 文件管理依赖，**构建过程依赖于本地仓库中的文件**。

![image-20241104231402015](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241104231402015.png)

==此项目基于idea中的配置文件==

**ArcheType（原型） 是项目模板**

## 3. Maven工程的目录结构

Maven的工程目录结构是有一定的规范的，这样可以方便Maven来自动的构建项目，下面是一个标准的Maven工程目录结构：
```bash
project                     # 项目根目录
|-- src                     # 源代码目录
|   |-- main                # 主目录
|   |   |-- java            # Java源代码目录
|   |   |-- resources       # 资源文件目录 多媒体media文件之类
|   |   |-- webapp          # Web应用目录 例如 HTML、JSP、CSS、JavaScript 文件，以及 WEB-INF 目录等。
|   |-- test                # 测试目录
|       |-- java            # 测试源代码目录
|       |-- resources       # 测试资源文件目录
|-- target                  # 项目构建目录 
|-- pom.xml                 # 项目配置文件
```
对于target ： 

- **功能**: Maven 在构建项目时生成的输出目录。所有的编译结果、打包文件和临时文件都会放在这个目录下。

- 内容

  :

  - **编译后的类文件**: 在 `target/classes` 中存放编译后的 Java 类文件。
  - **测试结果**: 在 `target/test-classes` 中存放编译后的测试类文件。
  - **打包文件**: 如果项目的打包类型是 `jar` 或 `war`，最终生成的文件将放在 `target` 目录中，例如 `project-1.0-SNAPSHOT.jar` 或 `project.war`。



例如：

![](https://neucms.com/img/20240805231706.png)


## 4. POM文件（最核心）

**POM文件是Maven项目的核心文件，它是一个XML文件，定义了项目的配置、依赖、插件以及构建的过程。**

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

### JAR 包

通过一个大型项目的实际生产开发工作流来详细说明打包 **JAR 文件**的用处和使用。

### ==${实例项目：企业级电商平台}==

#### 1. 项目初始化
团队在项目的初期阶段使用 Maven 初始化项目，创建标准的项目结构和基础的依赖。
```bash
mvn archetype:generate -DgroupId=com.company -DartifactId=ecommerce-platform -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

#### 2. 项目结构
项目结构由 Maven 标准化管理：
```plaintext
ecommerce-platform/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/company/ecommerce/
│   │   │       ├── App.java
│   │   │       └── controllers/
│   │   ├── resources/
│   │       ├── application.properties
│   └── test/
│       └── java/
```

#### 3. 依赖管理
团队在 `pom.xml` 文件中添加所需的依赖，比如 Spring Boot、Hibernate、MySQL 等。
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.5.4</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.5.7.Final</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
    </dependency>
</dependencies>
```

还有导入依赖更为简单的方式吗？？

![image-20241122230150294](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122230150294.png)

#### 4. 开发与版本控制

开发者在各自的分支中进行功能开发，使用 Git 进行版本控制和协作。

#### 5. 编译和测试
开发者在本地环境中使用 Maven 命令编译和测试代码：
```bash
mvn compile
mvn test
```

#### 6. 打包应用
项目开发完成后，使用 `mvn package` 命令生成可执行的 JAR 文件：
```bash
mvn package
```
这将生成一个可执行的 JAR 文件，比如 `ecommerce-platform-1.0-SNAPSHOT.jar`，位于 `target` 目录中。

#### 7. 部署应用
在实际生产环境中，运维团队将生成的 JAR 文件**部署到服务器**上。使用以下命令启动应用：
```bash
java -jar target/ecommerce-platform-1.0-SNAPSHOT.jar
```

#### 8. 日常维护
对于每个版本的更新，开发团队都会重复上述过程，确保每次更新都是通过 Maven 构建、测试和打包的，从而保证版本一致性和依赖的正确性。





### JAR 文件的用处总结
1. **简化分发**：将整个应用打包成一个文件，方便分发和部署。
2. **确保一致性**：统一打包所有类文件和资源，确保版本一致。
3. **自动化流程**：结合 Maven，简化编译、测试、打包和部署流程，提高工作效率。
4. **依赖管理**：通过 `pom.xml` 自动管理和下载依赖，避免手动管理带来的错误。
5. **安全性**：通过签名机制，确保 JAR 文件的完整性和来源可信。

通过这个流程，我们可以看到 JAR 文件在实际生产环境中的重要性和实用性。希望这能帮你更好地理解 JAR 文件的用途和使用。如果有其他问题或需要更多示例，请告诉我！ 😊





## 5. 构建生命周期

**Maven提供了三种主要的生命周期：`Clean`、`Default`和`Site`**

### 5.1 `Clean`：用于项目清理（`mvn clean`）

执行`clean`生命周期，会删除`target`目录下的所有文件，包括编译后的字节码文件、打包后的jar包、生成的站点等等。

```bash
mvn clean
```

### 5.2 `Default` ：用于项目部署
```
validate` => `compile` => `test` => `package` => `verify` => `install` => `deploy  --》 执行流
```

- 
- | 生命周期类型     | 阶段        | 描述                                                       |
  | :--------------- | :---------- | :--------------------------------------------------------- |
  | **默认生命周期** | validate    | 验证项目是否正确，并且所有必要的信息是否可用。             |
  |                  | compile     | 编译项目的源代码。                                         |
  |                  | test        | 使用适当的单元测试框架运行测试。                           |
  |                  | package     | 将编译后的代码打包成可分发格式，如 JAR、WAR 等。           |
  |                  | verify      | 运行任何检查，以验证包的质量是否符合标准。                 |
  |                  | install     | 将包安装到本地 Maven 仓库，以供其他项目使用。              |
  |                  | deploy      | 将最后的包复制到远程仓库，以便其他开发人员和项目可以使用。 |
  | **清理生命周期** | pre-clean   | 在清理之前执行的操作。                                     |
  |                  | clean       | 清理项目，删除 `target` 目录。                             |
  |                  | post-clean  | 在清理之后执行的操作。                                     |
  | **站点生命周期** | pre-site    | 在生成站点之前执行的操作。                                 |
  |                  | site        | 生成项目的文档和站点。                                     |
  |                  | post-site   | 在生成站点之后执行的操作。                                 |
  |                  | site-deploy | 将生成的站点部署到服务器。                                 |

==注意：==

```bash
mvn package
```

这实际上会执行 `validate`、`compile`、`test` 和 `package` 阶段。这意味着 Maven 会按照顺序完成这些阶段中的所有任务





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

---



| 依赖范围     | 说明                                                         | 应用场景                           |
| ------------ | ------------------------------------------------------------ | ---------------------------------- |
| **compile**  | 默认范围，适用于编译、测试和运行期都需要的依赖               | 核心业务逻辑或框架依赖             |
| **provided** | 编译期需要，但运行期由容器或环境提供，不会打包进 JAR 文件中  | Web 容器提供的类库，如 Servlet API |
| **runtime**  | 运行期需要，但编译期不需要                                   | 数据库驱动程序                     |
| **test**     | 仅在测试编译和运行时需要的依赖，不会包含在发布包中           | 测试框架，如 JUnit 或 Mockito      |
| **system**   | 需要显式指定 JAR 文件的路径，不常用                          | 系统特定的 JAR，未在远程仓库中托管 |
| **import**   | 用于引入依赖管理的 BOM（Bill of Materials），只适用于 `<dependencyManagement>` 中 | 管理多模块项目中的依赖版本         |

这样看起来是不是清晰多了？有什么其他问题想聊聊吗？ 😊

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