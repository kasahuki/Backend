# Docker

**debian复制粘贴共享问题：**

sudo apt-get install open-vm-tools
sudo apt-get install open-vm-tools-desktop

## docker 安装 

desbian apt安装的源 ：==/etc/apt/sources.list==

![image-20250316125922717](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316125922717.png)



docer镜像源就去阿里云或者什么开源平台上找



### [Install using the `apt` repository](https://docs.docker.com/engine/install/debian/#install-using-the-repository)

Before you install Docker Engine for the first time on a new host machine, you need to set up the Docker `apt` repository. Afterward, you can install and update Docker from the repository.

1. Set up Docker's `apt` repository.

   

   ```bash
   # Add Docker's official GPG key:
   sudo apt-get update
   sudo apt-get install ca-certificates curl
   sudo install -m 0755 -d /etc/apt/keyrings
   sudo curl -fsSL https://download.docker.com/linux/debian/gpg -o /etc/apt/keyrings/docker.asc
   sudo chmod a+r /etc/apt/keyrings/docker.asc
   
   # Add the repository to Apt sources:
   echo \
     "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/debian \
     $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
     sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
   sudo apt-get update
   ```

   > **Note**
   >
   > 
   >
   > If you use a derivative distribution, such as Kali Linux, you may need to substitute the part of this command that's expected to print the version codename:
   >
   > 
   >
   > ```console
   > $(. /etc/os-release && echo "$VERSION_CODENAME")
   > ```
   >
   > Replace this part with the codename of the corresponding Debian release, such as `bookworm`.

2. Install the Docker packages.

   Latest Specific version

   ------

   To install the latest version, run:

   

   ```console
   $ sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
   ```

   ------

3. Verify that the installation is successful by running the `hello-world` image:

   

   ```console
   $ sudo docker run hello-world
   ```

   This command downloads a test image and runs it in a container. When the container runs, it prints a confirmation message and exits.

You have now successfully installed and started Docker Engine.

# 安装Docker相关组件：
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

 docker-ce：Docker引擎核心组件
 docker-ce-cli：Docker命令行工具
 containerd.io：容器运行时
 docker-buildx-plugin：Docker构建插件
 docker-compose-plugin：Docker Compose插件



本地开发和部署是软件开发中常见的两个概念，主要指开发和运行软件或应用程序的不同场景和阶段。以下是它们的详细解释：

------

### **本地开发**

本地开发是指开发者在自己的计算机（本地环境）上进行应用程序的开发、调试和测试。
它是软件开发的初始阶段，通常为了方便和高效，开发者会在本地环境中进行代码编写和功能验证。

#### 特点：

1. **运行环境在本地**：
   - 开发者在自己的电脑上安装所需的软件（如代码编辑器、数据库、运行时环境等）来运行和调试程序。
   - 例如，Web 开发者可能会在本地安装 Node.js、Python 或 PHP，搭配本地数据库（如 MySQL 或 SQLite）。
2. **快速迭代**：
   - 本地开发允许开发者快速修改代码并实时测试，提升开发效率。
3. **工具支持**：
   - 使用开发工具（如 VS Code、IntelliJ IDEA、PyCharm）和调试器来帮助开发。
   - 通常也会使用本地模拟服务器（如 Flask、Django、XAMPP、Webpack Dev Server）来运行应用。
4. **隔离性**：
   - 本地开发环境与实际生产环境隔离，开发者可以独立工作，而不影响线上系统。

#### 示例：

- Web 开发时，开发者在本地运行一个 Web 服务器（如 localhost:3000）来测试网站。
- 使用 Docker 在本地创建一个模拟的生产环境。

------

### **部署**

部署是指将开发完成的应用程序发布到服务器或云平台上，使其可以被实际用户访问和使用。
也就是说，部署是将代码从开发环境迁移到生产环境或测试环境的过程。

#### 部署的过程：

1. **准备部署环境**：
   - 配置服务器（如安装所需的操作系统、Web 服务器、数据库等）。
   - 部署环境可以是云服务（如 AWS、阿里云、腾讯云）或本地服务器。
2. **代码发布**：
   - 将应用程序的代码、静态资源（如 HTML、CSS、JavaScript）和其他依赖项传输到目标环境中。
   - 这可以通过手动操作（如 FTP 上传）或自动化工具（如 CI/CD）完成。
3. **配置运行环境**：
   - 配置环境变量、数据库连接、日志路径等。
   - 确保生产环境与本地开发环境的兼容性。
4. **启动服务**：
   - 启动应用程序，使其能够对外提供服务。

#### 常见的部署环境：

- **生产环境**：用户直接访问的真实运行环境。
- **测试环境**：用于测试的环境，通常与生产环境隔离。
- **开发环境**：用于开发团队协作测试的环境。

#### 示例：

- 部署一个 Web 应用程序到云服务器上（如 Nginx + Node.js）。
- 使用 CI/CD 工具（如 Jenkins、GitLab CI）将代码自动部署到生产环境。

------

### **本地开发与部署的区别**

| **对比点**       | **本地开发**               | **部署**                                      |
| :--------------- | :------------------------- | :-------------------------------------------- |
| **运行环境**     | 本地计算机（开发者的机器） | 远程服务器（生产或测试环境）                  |
| **目标**         | 编写、调试和测试代码       | 让用户能够访问和使用应用程序                  |
| **使用工具**     | IDE、调试器、本地服务器    | 部署工具（如 Docker、Kubernetes、CI/CD 工具） |
| **用户类型**     | 开发人员                   | 最终用户或测试人员                            |
| **是否对外开放** | 通常不对外开放             | 对外开放，允许外部访问                        |



# Docker在linux中使用

## 安装docker engine

[docker文档教程](https://docs.docker.com/)

## 安装docker 镜像

[docker hub](https://hub.docker.com/)

**==prerequisite : 外网环境 启用 tun模式 （v2rayN）==**

## 显示容器

### Docker容器端口映射

**docker port 容器名**

**在执行docker run --name my-container（容器名） -it ubuntu（镜像）时 ， 如果不指定--name 的话就会随机生成一个容器名字！**

1. 显示**运行中**的容器名



```bash
docker ps
```

1. 显示所有容器（包括已停止的）-all



```bash
docker ps -a
```

1. 只显示容器名

格式化输出

```bash
docker ps -a --format "{{.Names}}"
```

1. 详细信息并显示名字



```bash
docker ps -a --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
```

1. 如果想按特定镜像过滤

管道符 和 grep

 

```bash
docker ps -a | grep 镜像名
```

## 显示镜像

查看Docker镜像名字的方法：

1. 列出本地所有镜像



```bash
docker images
```

1. 只显示镜像名



```bash
docker images --format "{{.Repository}}"
```

1. 显示详细镜像信息



```bash
docker images --format "table {{.Repository}}:{{.Tag}}\t{{.ID}}\t{{.CreatedAt}}"
```

1. 查看正在运行的容器使用的镜像



```bash
docker ps --format "{{.Image}}"
```

1. 查看特定容器的镜像

inspect 检查

```bash
docker inspect 容器名/ID | grep Image
```

1. 搜索镜像



```bash
docker search 关键词
```

## 容器（镜像……）位置



在 `/var/lib/docker/` 目录下，Docker数据主要存储在以下子目录：

1. 容器数据：



```
/var/lib/docker/containers/
```

1. 镜像数据：



```
/var/lib/docker/images/
```

1. 数据卷：



```
/var/lib/docker/volumes/
```

1. 网络配置：



```
/var/lib/docker/network/
```

如果你想查看具体某个容器的数据，可以：



```bash
# 列出所有容器ID
docker ps -aq

# 在 /var/lib/docker/containers/ 下会有对应ID的文件夹
```

## 关闭容器

关闭Docker容器有几种方法：

1. 停止容器



```bash
docker stop wewe-rss
```

1. 强制停止



```bash
docker kill wewe-rss
```

1. 删除容器



```bash
# 先停止
docker stop wewe-rss

# 再删除
docker rm wewe-rss
```

1. 强制删除运行中的容器



```bash
docker rm -f wewe-rss
```

1. 查看容器状态



```bash
docker ps -a
```

推荐顺序：

1. 先 `docker stop`
2. 确认停止后再 `docker rm`

选择 `docker stop` 更温和地关闭容器，`docker kill` 是强制立即终止

### 停止方法

~~~tex


停止所有Docker容器的方法：

停止所有正在运行的容器

docker stop $(docker ps -q)
强制停止所有容器

docker kill $(docker ps -q)
停止并删除所有容器
~~~

# 停止所有容器
docker stop $(docker ps -a -q)

# 删除所有容器
docker rm $(docker ps -a -q)

# 容器和镜像的区别

==容器就是镜像的运行实例 （类比OOP）==

Docker start 和 Docker run 的区别：

Docker run:
1. 创建并启动新容器
2. 用于第一次运行镜像
3. 需要完整参数
```bash
docker run -d \
  --name 容器名 \
  -p 宿主机端口:容器端口 \
  -v 本地路径:容器路径 \
  -e 环境变量 \
  镜像名:tag
```

Docker start:
1. 启动已存在但停止的容器
2. 快速重启之前创建的容器
3. 只需要容器名
```bash
docker start 容器名
```

应用场景：
- run: 首次创建容器
- start: 重启已停止的容器

示例：
```bash
# 第一次运行
docker run -d --name myapp -p 8080:80 nginx

# 后续启动
docker start myapp
```

推荐流程：
1. 首次 docker run
2. 后续 docker start/stop

# why docker

node.js 是一个运行时环境，可以让我们在非浏览器的地方运行js代码

使用 Docker 主要有以下几个主要的优势和应用场景：

1. **环境一致性和隔离性**：
   - Docker 提供了轻量级的容器化技术，可以将应用程序及其依赖、运行环境（如操作系统、库、工具等）打包成一个独立的容器。这保证了应用在不同环境下具有一致的运行行为，避免了“在我机器上能运行”的问题。
   - 每个 Docker 容器都是相互隔离的，有自己的文件系统、网络空间和进程空间，可以避免不同应用或不同版本之间的冲突和影响。

2. **快速部署和扩展**：
   - Docker 容器可以在几秒钟内启动和停止，相比传统的虚拟机（VM）更加轻量级和高效。这使得应用的部署、升级和扩展变得更加快速和灵活。
   - Docker 提供了 Docker Compose 和 Kubernetes 等工具，能够方便地管理多个容器组成的应用，实现自动化的部署和扩展。

3. **资源利用率和成本优化**：
   - Docker 容器共享宿主机的操作系统内核，因此在资源利用率上比传统虚拟机更高。多个容器可以在同一台物理机上运行，节省硬件资源和成本。
   - Docker 可以在不同的云平台和本地基础设施上运行，提供了跨平台的部署和一致的开发、测试、生产环境。

4. **持续集成和持续交付（CI/CD）**：
   - Docker 容器被广泛用于构建 CI/CD 流水线，支持快速、可重复的构建、测试和部署过程。开发团队可以利用 Docker 的镜像和容器来实现自动化的部署和版本控制。

5. **技术生态系统和社区支持**：
   - Docker 是一个开放源代码项目，拥有活跃的社区支持和丰富的技术生态系统。您可以轻松地访问到成千上万个官方和第三方的 Docker 镜像，涵盖了几乎所有流行的应用和服务。



## docker容器特点 

docker容器的数据不会持久化（什么时候丢失呢 ？会缓存吗？） 

这就要提到逻辑卷了



# docker多容器compose

## why compose

**解耦 更好维护**……………………





---



## **1. amd64**

- **全称**：AMD64（有时也称作 x86_64 或 x64）
- **含义**：
  - `amd64` 是 64 位架构的名称，由 AMD 公司在 2003 年推出，用于扩展传统的 32 位 x86 架构。
  - 它是目前主流桌面、笔记本和服务器 CPU 使用的架构，适用于 Intel 和 AMD 的 64 位处理器。
- **特点**：
  - 能运行 64 位操作系统和软件，也可以兼容运行 32 位应用程序。
  - 支持更多的内存寻址，理论上最多支持 16 exabytes（1 exabyte = 1024 petabytes）。
  - 每次可以处理更大的数据块（64 位）。
- **适用的硬件**：
  - 常见的 Intel 和 AMD 64 位处理器，如 Intel Core 系列和 AMD Ryzen 系列。
- **应用环境**：
  - 主流桌面操作系统（如 64 位版本的 Windows、Linux、macOS）。
  - 服务器环境（如运行 64 位 Linux 的服务器）。

------

## **2. arm64**

![image-20241229202834452](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229202834452.png)

- **全称**：ARM64（也称作 AArch64）
- **含义**：
  - `arm64` 是 ARM 架构的 64 位版本，由 ARM Holdings 推出。
  - ARM 是一种精简指令集架构（RISC），以其高能效著称，广泛应用于移动设备、嵌入式系统、物联网设备等。
- **特点**：
  - 高效能耗比：与传统的 x86 架构相比，ARM 处理器消耗更少的电力，因此非常适合电池供电的设备（如手机和平板）。
  - 可扩展性：支持从低功耗嵌入式芯片到高性能服务器芯片（如 Apple M 系列芯片、AWS Graviton）。
  - 支持 64 位指令集，能运行 64 位操作系统和软件。
- **适用的硬件**：
  - 移动设备（如 Android 手机、iPhone、iPad）。
  - 嵌入式设备（如树莓派、路由器、智能家居设备）。
  - 部分现代桌面和服务器硬件（如 Apple MacBook 使用的 M1/M2 芯片，或者基于 ARM 的服务器处理器）。
- **应用环境**：
  - 移动操作系统（如 Android 和 iOS）。
  - 桌面和服务器操作系统（如 macOS、基于 ARM 的 Linux 发行版，例如 Ubuntu for ARM）。
  - 嵌入式设备和物联网系统。

------

## **3. x86**

- **全称**：x86（最初由 Intel 8086 CPU 命名）
- **含义**：
  - `x86` 是 32 位处理器架构的名称，最早由 Intel 在 1970 年代推出。
  - 它是现代计算机 CPU 的早期基础架构，后来被扩展为 64 位（即 amd64）。
- **特点**：
  - 32 位架构，最多支持 4GB 的内存寻址（2^32 字节）。
  - 已经逐渐被 64 位架构（amd64）取代，但仍然广泛存在于老旧设备和嵌入式系统中。
  - 能运行许多经典的 32 位操作系统和软件。
- **适用的硬件**：
  - 老旧的 32 位台式机、笔记本电脑和服务器。
  - 一些低功耗嵌入式设备。
- **应用环境**：
  - 32 位操作系统（如 32 位版本的 Windows、Linux）。
  - 老旧的软件和硬件环境。

------

## **总结对比**

| 特性         | **amd64 (x86_64)** | **arm64 (AArch64)**     | **x86**             |
| :----------- | :----------------- | :---------------------- | :------------------ |
| **位数**     | 64 位              | 64 位                   | 32 位               |
| **架构类型** | CISC（复杂指令集） | RISC（精简指令集）      | CISC（复杂指令集）  |
| **内存支持** | 16 exabytes        | 16 exabytes             | 4 GB                |
| **功耗**     | 较高               | 较低                    | 较高                |
| **应用领域** | 桌面、服务器       | 手机、嵌入式、服务器    | 老旧 PC、嵌入式设备 |
| **制造商**   | Intel、AMD         | ARM Holdings 及授权厂商 | Intel、AMD 等       |



## vscode docker



在vscode 命令面板中输入==>open folder== 即可在==远程服务==打开其他文件夹 ！！！！ 

==使用 > 指令==

教程 ：https://zhuanlan.zhihu.com/p/468768617

结合**远程SSH**



---



## 文件权限问题 （linux）

问题 ： 文件权限    srw-rw-rw- 1 root docker 0 12月28日 10:54 /var/run/docker.sock

对于上述输出：

目录的所有者：root

目录的所属组 docker

rw-(目录所有者) rw-（目录所属组） rw-（其他用户） 

**创建目录的用户**就是该目录的 **所有者**，而且默认情况下，**创建目录的用户的默认组**就是该目录的 **所属组**。



### 如何更改所有者和组？

虽然默认情况下，创建目录的用户会是所有者，创建时的默认组会是所属组，但可以使用命令更改这些信息：

1. **更改所有者**： 使用 `chown` 命令来更改目录的所有者：

   ```bash
   sudo chown newuser my_directory
   ```
   
2. **更改所属组**： 使用 `chown` 命令来更改目录的所属组：

   ```bash
   sudo chown :newgroup my_directory
   ```
   
3. **更改所有者和所属组**： 使用 `chown` 命令同时更改目录的所有者和所属组：

   ```bash
   sudo chown newuser:newgroup my_directory
   ```

## debian root用户登录禁止

配置文件  ：/etc/ssh/sshd_config

![image-20241228162231004](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241228162231004.png)

**创建新用户**

---

![image-20241228162243258](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241228162243258.png)





为什么系统提示没有这个指令？
如果你不使用 sudo 或没有 root 权限，会出现类似以下的错误信息：

command not found：表示当前用户没有权限执行该命令，或者**命令没有安装在用户的路径下**。实际上，它是因为普通用户无法执行管理员级别的操作。
permission denied：表示权限被拒绝，不能执行命令。

---

![image-20241228162250516](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241228162250516.png)

---

**查看用户**

* **`x`**：
  * **密码占位符**（password）：这里的 `x` 表示密码信息存储在 ******`/etc/shadow`** 文件中，而不是直接存储在 `/etc/passwd` 文件中。出于安全原因，现代 Linux 系统将密码信息移到 `/etc/shadow` 文件中，以便加密存储。
* **`1001`**：
  * **用户ID（UID）**（User ID）：每个用户在系统中都有一个唯一的数字标识符。这里的 `1001` 是该用户的 UID。
* **`1001`**：
  * **组ID（GID）**（Group ID）：每个用户也属于一个组。这个 `1001` 是该用户的默认组 ID。通常情况下，系统会为每个用户创建一个同名的用户组，UID 和 GID 相同（例如 `username` 用户的 GID 就是 `1001`）。







---



# Docker 的基本使用

## 基本命令

| **类别**           | **命令**                                                     | **描述**                                               |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------ |
| **容器管理**       | `docker ps`                                                  | 查看正在运行的容器                                     |
|                    | `docker ps -a`                                               | 查看所有容器，包括已停止的容器                         |
|                    | `docker start <container_name_or_id>`                        | 启动一个容器                                           |
|                    | `docker stop <container_name_or_id>`                         | 停止一个容器                                           |
|                    | `docker restart <container_name_or_id>`                      | 重启一个容器                                           |
|                    | `docker rm <container_name_or_id>`                           | 删除一个容器                                           |
|                    | `docker logs <container_name_or_id>`                         | 查看容器的日志                                         |
|                    | `docker exec -it <container_name_or_id> /bin/bash`           | 进入容器的终端                                         |
|                    | `docker stats <container_name_or_id>`                        | 查看容器的资源使用情况                                 |
| **镜像管理**       | `docker images`                                              | 查看本地镜像                                           |
|                    | `docker pull <image_name>:<tag>`                             | 拉取镜像                                               |
|                    | `docker rmi <image_name_or_id>`                              | 删除镜像                                               |
|                    | `docker build -t <image_name>:<tag> <path_to_dockerfile>`    | 使用 Dockerfile 构建镜像                               |
| **容器与镜像管理** | `docker run <options> <image_name>`                          | 运行一个容器，常用选项如 `-d`, `-p`, `-v`, `--name` 等 |
|                    | `docker history <image_name>`                                | 查看镜像的历史记录                                     |
|                    | `docker export <container_name_or_id> > container.tar`       | 导出容器文件系统到 `.tar` 文件                         |
|                    | `docker import <file.tar> <new_image_name>:<tag>`            | 从 `.tar` 文件导入镜像                                 |
| **网络管理**       | `docker network ls`                                          | 查看 Docker 网络                                       |
|                    | `docker network create <network_name>`                       | 创建 Docker 网络                                       |
|                    | `docker inspect <container_name_or_id>`                      | 查看容器的网络设置                                     |
|                    | `docker network connect <network_name> <container_name_or_id>` | 连接容器到网络                                         |
|                    | `docker network disconnect <network_name> <container_name_or_id>` | 断开容器与网络的连接                                   |
| **数据卷管理**     | `docker volume create <volume_name>`                         | 创建数据卷                                             |
|                    | `docker volume ls`                                           | 查看数据卷                                             |
|                    | `docker volume inspect <volume_name>`                        | 查看数据卷详细信息                                     |
|                    | `docker volume rm <volume_name>`                             | 删除数据卷                                             |
| **容器编排**       | `docker service ls`                                          | 查看正在运行的服务（在 Swarm 模式下）                  |
|                    | `docker service inspect <service_name>`                      | 查看服务的详细信息                                     |
|                    | `docker service ps <service_name>`                           | 查看服务的任务（容器实例）                             |
|                    | `docker service scale <service_name>=<replica_count>`        | 扩展服务副本数                                         |
| **系统管理**       | `docker --version`                                           | 查看 Docker 版本                                       |
|                    | `docker info`                                                | 查看 Docker 配置信息                                   |
|                    | `docker system prune`                                        | 清理未使用的镜像、容器、网络和数据卷                   |
|                    | `docker stats`                                               | 查看 Docker 容器资源使用情况                           |
| **Docker Compose** | `docker-compose up`                                          | 启动 Compose 服务                                      |
|                    | `docker-compose up -d`                                       | 后台启动 Compose 服务                                  |
|                    | `docker-compose down`                                        | 停止 Compose 服务                                      |
|                    | `docker-compose logs`                                        | 查看 Compose 服务的日志                                |
|                    | `docker-compose ps`                                          | 查看 Compose 服务的状态                                |

`docker inspect` 命令用于获取容器、镜像、网络或数据卷等 Docker 资源的详细信息。它会返回 JSON 格式的数据，包含资源的配置、状态、网络设置等信息。

### 基本语法：

```bash
docker inspect <resource_name_or_id>
```

### 资源类型：

- **容器**：可以查看容器的详细信息（如网络、挂载卷、环境变量等）。
- **镜像**：可以查看镜像的详细信息（如层次结构、标签等）。
- **网络**：可以查看 Docker 网络的详细信息（如连接的容器、IP 地址范围等）。
- **数据卷**：可以查看数据卷的详细信息（如挂载位置、使用的容器等）。

### 



此表格总结了 Docker 中常用的命令及其描述，帮助你快速查找和使用各种操作。

![image-20250316140521315](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316140521315.png)



这个容器是有着自己独立的隔离空间：独立的文件系统 内存空间 网络空间之类的

还有自己的IP地址 端口也有

e.g.

`5700:5700` 中，第一个 `5700` 是 **容器内的端口**，第二个 `5700` 是 **主机的端口**。意思是将容器的端口 `5700` 映射到主机的 `5700` 端口，这样外部网络可以通过访问主机的 `5700` 端口来访问容器中的服务。

这种就是端口映射



![image-20250316140910573](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316140910573.png)



**环境变量的作用**
Docker 使用环境变量来传递启动参数这样可以避免在运行时手动编辑配置文件或使用命令行参数。对于 MySQL，常用的环境变量包括:
·MYSQL ROOT PASSWORD:设置MySQL的 root用户密码。这是强制性的，如果未提供，容器将拒绝启动。这是为了安全性，确保默认的超级用户账户不会无密码暴露在外。
MYSOL DATABASE:指定在容器启动后自动创建的数据库名称。
·MYSOL USER和MYSOL PASSWORD:用于创建一个普通用户及其密码。其他环境变量:如字符集、排序规则等，也可以通过环境变量设置，
这些环境变量允许用户在启动容器时快速配置数据库，而无需进入容器后再手动操作。

![1742105379905](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1742105379905.png)

![1742105384078](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1742105384078.png)



---

docker exec -it 容器名 bash

进入到容器内部 交互 使用exec 通过 终端交互 bash

---

一般来说修改了程序的配置文件就需要重新加载 reload 



**~的含义**
是当前用户的 主目录(Home的快捷表示符。Directory)
举例来说，**如果用户名是 hui,主目录通常是/home/hui**



## 数据卷 （Volume）

**了解数据卷是什么 有什么用**

### 为什么需要 Docker 数据卷？

1. **持久化数据**： 容器本身是临时的，数据存储在容器内的文件系统中，如果容器被删除或重新创建，容器内的数据也会丢失。为了避免这种情况，我们可以使用数据卷将数据保存在容器外部，这样数据可以在多个容器之间共享并持久化。
2. **解耦应用和数据**： 数据卷使得数据独立于容器的生命周期，因此你可以轻松地更改或重建容器而不影响数据。通过数据卷，容器仅关注应用逻辑，而数据的存储与管理则交由数据卷来处理。
3. **共享和管理数据**： 数据卷可以在多个容器之间共享，允许容器之间共享文件、配置等数据，支持分布式应用的部署和协同工作。
4. **性能优化**： 数据卷通常比绑定挂载（bind mounts）提供更好的性能，尤其是在使用 Docker 进行高频读写操作时。因为数据卷由 Docker 管理，Docker 会为其优化性能。
5. **方便备份和恢复**： 数据卷的数据可以轻松地备份、恢复和迁移。你可以将数据卷导出到本地文件系统，或者将其复制到其他系统中。

![image-20250316144029221](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316144029221.png)



![image-20250316144038844](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316144038844.png)

方法一：

![image-20250316145132008](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316145132008.png)

数据卷在哪里呢 ： docker volume inspect 数据卷名     （查看在宿主机的挂载点）Mounpoint 

方法二：（推荐）**可以自己指定目录挂载点**

![image-20250316150216446](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150216446.png)

![image-20250316150417909](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150417909.png)

## 自定义镜像

![image-20250316150456477](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150456477.png)



**JVM（Java Virtual Machine）** 在不同操作系统上并不是使用相同的系统函数库。虽然 JVM 本身是跨平台的，并能够在不同操作系统上运行，但其底层与操作系统的交互部分（如文件 I/O、网络通信、内存管理等）会依赖于操作系统的系统函数库。

所以还得**部署一个系统**在docker 中

JVM 是 Java 虚拟机，是用来运行 Java 字节码的环境。它的核心职责是将编译后的.class字节码文件解释并运行。
具体来说，JVM 是一个抽象的执0行环境，与具体的平台无关。

JRE 是 Java 的运行环境，它包含了 JVM 和运行 Java 程序所需的库文件(如标准类库)。
如果只想运行 Java 程序，JRE 就足够了。

JDK是 Java 的开发工具包，包含了 JRE 和开发工具(如编译器javac 和调试工具 idb)如果需要开发 Java 程序，就需要安装 JDK。



![image-20250316150802799](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150802799.png)

![image-20250316150815275](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150815275.png)

## DockerFile



![image-20250316150823908](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150823908.png)

![image-20250316150846102](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150846102.png)

![image-20250316150900891](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316150900891.png)



在 Docker 中，`docker save`  导出tar包 ，`tar` 包通常用来导出容器文件系统或镜像文件。你可以将一个容器的文件系统或者镜像导出为 `tar` 文件，然后将该文件传输到其他机器上，再通过 `docker load` 来加载镜像。



## Docker 网络

docker容器都有一个自己的ip地址

![image-20250316151325929](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316151325929.png)





这个ip地址都是由docker 网桥分配的 

如果容器重启 的过程中也有容器启动了 此时这个容器的ip地址就会被新启动的这个容器占用

所以配置就会很麻烦



使用自定义网络： 核心通过**容器名**进行访问 （管你ip地址变来变去的）

![image-20250316151733990](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250316151733990.png)

在创建容器 的时候 通过 --network 自定义网络名 就不会加入到默认的docker网桥了













## Docker 部署 java 应用





## Docker 部署前端应用









## Docker Compose

![1742109575390](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1742109575390.png)

![1742109579308](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/1742109579308.png)

