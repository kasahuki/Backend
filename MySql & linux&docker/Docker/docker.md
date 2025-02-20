# Docker

**debian复制粘贴共享问题：**

sudo apt-get install open-vm-tools
sudo apt-get install open-vm-tools-desktop

## docker 安装 与 desbian 换源

desbian apt安装的源 ：==/etc/apt/sources.list==

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

1. 显示运行中的容器名



```bash
docker ps
```

1. 显示所有容器（包括已停止的）



```bash
docker ps -a
```

1. 只显示容器名



```bash
docker ps -a --format "{{.Names}}"
```

1. 详细信息并显示名字



```bash
docker ps -a --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
```

1. 如果想按特定镜像过滤



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

# Cookie & Session & Token







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

# vpn 与系统代理

在使用 VPN 时，系统代理的开启状态会影响网络流量的路径和访问某些网站的方式，这主要涉及到网络流量的路由和代理设置：

1. **不开启系统代理**：

    当系统代理关闭时，通常网络流量直接经过你的本地网络设置，通过本地的路由器和==互联网服务提供商（ISP）==提供的网络路径来访问互联网。
    
    VPN 连接会建立一个加密的==隧道==，将你的流量通过 VPN 服务器进行路由，隐藏你的真实 IP 地址，同时提供更安全的连接。
    
2. **开启系统代理**：

    当系统代理开启时，你的操作系统会通过==代理服务器==来发送和接收网络请求。这可能是公司或组织内部的代理服务器，或者是一些公共代理服务（如翻墙软件常用的代理服务器）。
    
    如果你在使用 VPN 的同时开启系统代理，那么流量可能会先经过系统代理服务器，然后再通过 VPN 隧道发送到 VPN 服务器，最终访问互联网。这种情况下，系统代理服务器和 VPN 服务器之间的路由可能会影响一些网站的访问能力，特别是那些对访问来源有特定要求或限制的网站。
    
    ~~~ceylon
    系统代理的影响： 系统代理的开启状态会影响你的网络流量走向，可能会经过另外的代理服务器或者公司的内部网络。某些网站可能根据代理服务器的 IP 地址或网络识别出你的访问来源，这可能导致一些网站在系统代理开启时可以访问，而在关闭时无法访问。 网站的访问策略和识别： 有些网站使用复杂的访问控制策略，可能会根据多个因素来决定是否允许访问，包括 IP 地址、用户代理（User-Agent）、Cookies 等。开启或关闭 VPN 和系统代理可能改变这些因素，从而影响你的访问结果。
    ~~~
    
## 代理的on/off

没有开启代理，而单纯使用 VPN 连接，访问国内没有被墙的网站会有以下情况：

**这和全局有点像**

1. **流量路由**：

   所有的网络流量都会通过 VPN 提供的加密隧道发送到 VPN 服务器。这意味着国内没有被墙的网站的访问流量也会经过 VPN 连接。

2. **访问效果**：

   VPN 连接可能会引入一定的延迟，因为流量需要经过 VPN 服务器路由。对于国内网站，这种延迟可能会导致访问速度稍微变慢一些，特别是如果 VPN 服务器位置远离你的实际位置。

3. **隐私和安全**：

   使用 VPN 可以提供加密和隐私保护，确保你的网络流量在公共网络中不易被截获或窃取。即使是访问国内网站，VPN 也可以保护你的数据传输安全，尤其是在使用不安全的公共 Wi-Fi 网络时尤为重要。

**流量路由**：

如果代理设置的优先级高于 VPN 或者单独使用代理，你的网络流量会通过代理服务器发送而不经过 VPN 隧道。

这意味着国内没有被墙的网站的访问流量不会通过 VPN 提供的加密通道，而是直接通过代理服务器发送和接收数据。

**访问速度和稳定性**：

使用代理服务器可能会影响访问速度和稳定性，具体取决于代理服务器的性能和网络质量。有时候使用代理可能会提高访问速度，尤其是对于国内网站，因为可以避免 VPN 所带来的额外延迟。

**隐私和安全**：

如果代理服务器未经过适当配置或管理，可能会存在隐私和安全风险，因为数据不会经过 VPN 提供的加密保护。这可能暴露你的真实 IP 地址和数据内容，尤其是对于敏感信息的传输。

# Vps 和 云服务器

VPS（Virtual Private Server）和云服务器（Cloud Server）都属于虚拟化技术，但它们有一些关键的区别：

### 1. **虚拟化方式和资源分配**
   - **VPS**：
     - VPS 是在一台物理服务器上创建多个虚拟服务器，每个 VPS 都有独立的操作系统和资源（如 CPU、内存、存储等）。
     - VPS 通常使用 **基于虚拟化技术**（如 KVM、OpenVZ 等）来实现资源分配。
     - 资源分配是固定的，即购买了多少资源就能使用多少。

   - **云服务器**：
     - 云服务器是基于云计算架构的，通常由多个物理服务器和存储设备组成，利用 **虚拟化技术和分布式计算**来动态分配资源。
     - 云服务器的资源（如 CPU、内存、存储）通常是按需分配的，可以随时扩展和缩减，具有较强的弹性。
     - 云服务器通常还提供多区域和多可用区的支持，使得服务具有高可用性和容错能力。

### 2. **弹性和扩展性**
   - **VPS**：
     - 扩展性较差，资源分配较为固定，增加或减少资源（如 CPU 或内存）往往需要手动操作，甚至需要迁移到另一台物理服务器。
     - VPS 的扩展通常比较复杂，无法像云服务器那样灵活地按需增加资源。

   - **云服务器**：
     - 云服务器提供高度的弹性和按需扩展功能，可以随时根据负载需求增加或减少资源，不需要迁移，能迅速响应变化的需求。
     - 云服务器还支持负载均衡、自动扩展等功能，适应大规模、高并发的应用场景。

### 3. **管理方式**
   - **VPS**：
     - 通常由用户自己管理和维护，用户需要自行配置操作系统、安装软件、处理安全问题等。
     - 比较适合需要定制化配置和有一定技术能力的用户。

   - **云服务器**：
     - 提供更多的自动化管理功能，比如自动备份、自动扩展、监控和警报等。云服务提供商通常会为用户提供更多的管理工具和服务。
     - 适合需要高可用性、可靠性和自动化管理的用户。

### 4. **可靠性和高可用性**
   - **VPS**：
     - VPS 受限于物理服务器，一旦物理服务器出现故障，可能会影响多个 VPS。
     - 高可用性和容错能力较弱，通常需要手动搭建高可用架构。

   - **云服务器**：
     - 云平台通常在多个数据中心之间分布，支持高可用性和自动容错。即使某个节点出现问题，云服务器可以自动迁移到其他节点，确保服务不间断。
     - 提供多区域、跨区域的部署选项，保障服务的可靠性和灾难恢复能力。

### 5. **价格**
   - **VPS**：
     - 通常价格较为固定和便宜，适合低预算的项目或个人使用。
     - 定价相对简单，按月或按年付费。

   - **云服务器**：
     - 通常价格较高，尤其是资源使用弹性较大的情况。但也支持按需计费，可以按小时或按流量收费，灵活性更高。
     - 可以根据实际使用量收费，适合流量波动大或需要动态扩展的应用场景。

### 总结：
- **VPS** 更适合小型网站、开发测试、预算有限的用户，资源相对固定，适合对性能要求较为简单的场景。
- **云服务器** 更适合需要高可用、弹性扩展、大流量应用的用户，具有更强的扩展性和高可靠性，支持按需计费，适合企业级应用。

希望这能帮助你理解两者的区别！如果有更多问题，欢迎继续问！

# 建站流程

将网站上线互联网通常需要使用服务器来托管和访问网站内容。下面分别介绍使用云服务器和不使用云服务器的方法：

### 使用云服务器的方法：

[具体流程](https://www.bilibili.com/video/BV1Gx421Q7sB/?spm_id_from=333.337.search-card.all.click&vd_source=9570fc9c9829e70449f020506364bf36)



1. **选择云服务提供商**：
   - 注册并选择一个信誉良好、服务稳定的云服务提供商，如AWS、Azure、阿里云、腾讯云等。

2. **购买服务器实例**：
   - 在云服务提供商的控制台上选择合适的服务器实例（例如虚拟机），根据你的需求选择适当的配置（CPU、内存、存储空间等）。

3. **配置服务器环境**：
   - 安装操作系统（如Linux、Windows），配置网络、安全组（防火墙规则）、存储等设置。

4. **部署网站**：
   - 将==网站文件上传到服务器实例==，配置==Web服务器==（如Nginx、Apache）、数据库（如MySQL、PostgreSQL）等服务。

5. **域名绑定**：
   - 在域名注册商处购买域名，并将域名解析指向你的云服务器IP地址或者使用云服务商提供的DNS服务。

6. **配置SSL证书**（可选但推荐）：
   - 为网站配置SSL证书，确保网站传输数据的安全性和可信度。

7. **监控和管理**：
   - 配置监控和警报，确保服务器和网站的稳定性和安全性，可以使用云服务商提供的监控工具或者第三方工具。

8. **备份和灾难恢复**：
   - 设置定期备份策略，保护网站数据，以便在需要时进行灾难恢复。

### 不使用云服务器的方法：

如果不使用云服务器，你可以考虑以下几种方法：

1. **使用共享托管服务**：
   - 注册并选择一个共享托管服务提供商，如Bluehost、HostGator、GoDaddy等。
   - 根据服务商提供的控制面板和工具，上传网站文件，配置域名和基本设置。

2. **自建服务器**：
   - 如果有技术能力和资源，可以购买自己的物理服务器（或租用托管服务），将服务器放置在数据中心或者自己的办公室。
   - 需要考虑网络连接、安全性、电力和空调等基础设施问题。

3. **使用其他云服务**：
   - 不直接使用云服务器，但可以使用其他云服务提供商的服务，如对象存储（如AWS S3、阿里云 OSS）来托管网站的静态文件，或者使用CDN加速服务来提供内容分发。

4. **使用服务器软件**：
   - 在本地计算机或者自己的物理服务器上安装服务器软件（如Nginx、Apache），通过公网IP或者动态DNS服务让其他人可以访问你的网站。

### 总结：

- **使用云服务器**通常更灵活、稳定，并且具备弹性伸缩的能力，适合大部分需要上线互联网的个人网站和应用。
- **不使用云服务器**需要考虑到网络带宽、服务器硬件的购买和维护成本、安全性等因素，适合技术能力较强且有特殊需求的用户。

根据你的具体需求、预算和技术能力选择适合的方法来上线你的个人网站。如有更多问题或需要进一步指导，请随时提问！



## vscode docker

vscode快捷键：

**打开工程：code + 路径**
**打开指定文件：ctrl + p**
**打开/关闭终端：ctrl + ` (1左边的按键)**
**跳转到行：ctrl + g**
**按单词移动光标：ctrl + 左右**
**选中单词：ctrl + d （重复按可以多选）**
**选中行：ctrl + l （重复按会同时选择下一行）**
**移动行：alt + 上下**
**格式化代码：ctrl + shift + i**
**跳转到定义：f12**
**查看当前文件符号：ctrl + shift + o**
**剪切/复制当前行：ctrl + x / ctrl + c （什么都不选的时候）**
**切换tab：alt+数字**
**顺序切换tab：ctrl + pageup / pagedown**
**关闭文件：ctrl+w**
**关闭所有文件：ctrl + k w （ctrl不松手）**



在vscode 命令面板中输入==>open folder== 即可在==远程服务==打开其他文件夹 ！！！！ 

==使用 > 指令==

教程 ：https://zhuanlan.zhihu.com/p/468768617

结合**远程SSH**

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

   ```
   bash
   
   
   复制代码
   sudo chown newuser my_directory
   ```

2. **更改所属组**： 使用 `chown` 命令来更改目录的所属组：

   ```
   bash
   
   
   复制代码
   sudo chown :newgroup my_directory
   ```

3. **更改所有者和所属组**： 使用 `chown` 命令同时更改目录的所有者和所属组：

   ```
   bash
   
   
   复制代码
   sudo chown newuser:newgroup my_directory
   ```

### Socket （套接字） ：



套接字（Socket）和端口（Port）是两个不同概念，但它们在网络通信中密切相关：

### 套接字（Socket）网络编程

1. **定义**：
   * 套接字是一种用于**进程间通信**的抽象概念，允许**不同进程**在**同一台计算机或通过网络（client and server ）进行数据交换**。
   * 在操作系统中，套接字被实现为一种特殊的文件类型（套接字文件 文件内没有实际数据），用于在进程间传输数据。
2. **特点**：
   * 套接字通常通过套接字 API（如 POSIX 的 socket API 或 Windows 的 Winsock API）来创建和使用。
   * 可以创建不同类型的套接字，包括面向连接的套接字（如 TCP 套接字）和无连接的套接字（如 UDP 套接字）。
3. **用途**：
   * 用于在**同一台计算机内部的进程间通信（UNIX 域套接字**）。
   * 用于在网络上**不同计算机间**的进程通信（网络套接字）

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

## 







# 虚拟机网络模式

