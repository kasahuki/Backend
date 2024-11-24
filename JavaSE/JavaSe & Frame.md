<center><font size=10>JavaSE  & Frame introduction</font></center>

# Java语言特性

``Java 实现了“Write Once, Run Anywhere”（一次编写，随处运行）的理念。源代码编译成字节码后，字节码可以在任何安装了 Java 虚拟机（JVM）的设备上运行，而不需要修改代码`

**编写源代码（Source Code）：**
开发者使用 Java 编程语言编写源代码，文件扩展名为 .java。例如，MyProgram.java。
编译（Compilation）：
使用 javac 编译器将 .java 文件编译成字节码文件，生成 .class 文件。例如：

javac MyProgram.java
编译后的字节码文件可以在任何支持 JVM 的平台上运行。

**类加载（Class Loading）：**
JVM 中的类加载器（ClassLoader）将 .class 文件加载到内存中。Java 的类加载机制允许在运行时动态加载类。

**动态 -- 按需** 

**字节码验证（Bytecode Verification）：**
JVM 会对加载进来的字节码进行验证，确保字节码的安全性和正确性，防止恶意代码对内存或系统造成破坏。



**解释/即时编译（Interpretation/Just-In-Time Compilation, JIT）：**
JVM 将字节码解释为机器码，或者在 JIT 编译器的帮助下，直接将字节码编译为本地机器码，提高程序的执行效率。
JIT 编译器会在程序运行时，将一些热点代码（经常执行的代码）动态编译成本地机器码，以提高性能。



**执行（Execution）：**
JVM 解释或执行编译后的代码，最终在操作系统上运行，产生程序的输出和效果。

# 执行方式：



### 1. **C 语言的执行方式**

**C 语言**是一种**编译型语言**，其执行方式与 Java 和 Python 都不同：

- 静态编译

  ：

  - C 代码在执行之前，会通过编译器（如 `gcc`）编译成**机器码**，即特定平台的二进制可执行文件。这个过程是一次性的。
  - 编译后的机器码可以直接运行在操作系统上，不需要任何解释或虚拟机的支持。
  - 由于编译生成的文件是与操作系统和硬件密切相关的，C 是一种**高效**的语言，执行速度非常快。

**执行流程**：

- 编写 C 代码（`.c` 文件）。
- 使用编译器（如 `gcc`）将 `.c` 文件编译成目标系统的机器码（`.exe`，`.out` 等可执行文件）。
- 直接在操作系统上运行生成的可执行文件，整个程序已经是机器码，不需要解释器。

**总结**：C 语言经过编译后，生成的机器码直接在硬件上运行，执行速度非常快。这是一个典型的**一次编译，直接运行**的过程。

### 2. **Python 的执行方式**

**Python**是一种**解释型语言**，其执行方式与 C 和 Java 都不同：

- 逐行解释执行

  ：

  - Python 代码在运行时由**解释器**（如 CPython）逐行解释执行，不需要事先编译成机器码。
  - Python 是**动态类型语言**，因此在运行时才会确定类型和做相应的检查。
  - 这种逐行解释的方式使得开发和调试更加灵活，但是运行速度相对较慢，因为每次执行时都需要通过解释器逐行解析代码并执行。

**执行流程**：

- 编写 Python 代码（`.py` 文件）。
- 运行 Python 解释器（如 `python`），解释器逐行解释执行代码。
- Python 解释器会将 `.py` 文件编译为**字节码**（`.pyc` 文件）并存储在缓存中。
- **字节码**会被 Python 虚拟机解释执行，但仍然是逐行解释的方式，无即时编译优化。

**总结**：Python 是逐行解释执行的语言，每次运行代码时都通过解释器逐行执行，灵活性很高，但性能相对较低。Python 也有一些优化（如字节码缓存），但其核心仍然是解释执行。

### 3. **Java 的执行方式**

**Java**是一种结合了**编译型**和**解释型**的语言，使用的是**字节码解释 + JIT 编译**的混合模式：

- **编译成字节码**：
  - Java 代码首先通过 `javac` 编译器编译成与平台无关的**字节码**（`.class` 文件）。
  - 字节码不是直接在硬件上运行的，而是由 Java 虚拟机（JVM）解释执行。
- **解释执行 + JIT 编译**：
  - 当 Java 程序运行时，JVM 会先**解释执行**字节码，将字节码逐行解释为机器码并执行。
  - 为了提高性能，JVM 会通过**即时编译（JIT）\**的方式，将一些\**热点字节码**（频繁执行的代码）编译为**本地机器码**，下次再遇到这些代码时就直接用编译好的机器码执行，不再解释。
- **JIT 编译的优势**在于：程序运行时可以动态优化，将经常执行的代码编译为机器码，从而提升性能。

# 面向对象三大特性

##  封装

**封装**（Encapsulation）是面向对象编程（OOP）中的基本概念之一。封装的核心思想是将对象的**状态（字段/属性）\**和\**行为（方法）\**封装在一个类中，并通过提供\**访问控制**来限制外部对这些数据的直接访问和修改，从而实现数据的隐藏和保护。

在封装中，类的内部实现细节对外部是不可见的，外部只能通过暴露的**公共接口**（即公开的方法）与对象进行交互，而不能直接操作对象的内部数据。这样做的目的是为了保证对象的完整性和安全性，避免外部程序随意修改对象的内部状态。

### 封装的关键点

1. **属性私有化**：类的属性（成员变量）通常使用 `private` 关键字进行修饰，使其在类的外部不可直接访问。
2. **提供公共访问方法**：通过 `public` 关键字提供公共的**getter** 和 **setter** 方法，允许外部代码通过这些方法安全地访问和修改类的私有属性。
3. **隐藏实现细节**：类的内部实现细节对外部不可见，外部只需要关心接口和功能，而不需要了解具体是如何实现的。
4. **控制访问权限**：通过访问控制符（如 `private`、`protected`、`public`）控制类成员的可见性，确保类的内部数据不会被外部误修改或滥用。

### 封装的作用

1. **保护数据的完整性**：通过将属性私有化，防止外部随意修改类的内部状态，确保数据的正确性和一致性。例如，某个属性应该是正数，可以在 `setter` 方法中加入校验逻辑，避免不合法的数据被赋值。
2. **隐藏实现细节，降低复杂度**：封装的另一个重要作用是**隐藏实现细节**。外部用户不需要了解类的内部实现，只需要通过公开的方法来使用类的功能。这降低了系统的复杂度，提高了代码的可维护性。
3. **提高代码的灵活性和扩展性**：通过封装，类内部的实现可以随时更改，而不影响外部代码的调用。只要保持接口不变，外部代码就可以继续正常工作。这使得代码在未来的修改和扩展中更加灵活。
4. **提高安全性**：通过访问控制符，封装可以防止外部对象直接访问敏感数据，确保类的内部状态不被外部不正当的访问。

### 封装的应用场景

1. **类和对象的设计**：在设计类时，通常会将类的属性设置为 `private`，并提供 `public` 的 getter 和 setter 方法。这样可以在设置属性值时加入额外的逻辑（如校验、日志记录等），从而保证类的安全性和健壮性。

   

   ```java
   public class Person {
       private String name;
       private int age;
   
       public String getName() {
           return name;
       }
   
       public void setName(String name) {
           this.name = name;
       }
   
       public int getAge() {
           return age;
       }
   
       public void setAge(int age) {
           if (age > 0) {
               this.age = age;
           } else {
               System.out.println("Age must be positive");
           }
       }
   }
   ```

   在这个例子中，`name` 和 `age` 都是通过 `private` 修饰的，不能被外部直接访问。外部代码只能通过 `setAge()` 和 `setName()` 方法来修改这些属性，并且在 `setAge()` 方法中加入了年龄必须为正数的校验逻辑，保证了数据的合法性。

2. **避免数据不一致的情况**：在复杂的系统中，多个对象之间可能共享某些状态。通过封装，状态的更改可以集中管理，避免数据不一致。例如，在银行系统中，账户余额的修改应该通过专门的方法处理，这样可以保证每次修改余额时都能进行合法性检查和记录日志。

   

   ```java
   public class BankAccount {
       private double balance;
   
       public double getBalance() {
           return balance;
       }
   
       public void deposit(double amount) {
           if (amount > 0) {
               balance += amount;
           }
       }
   
       public void withdraw(double amount) {
           if (amount > 0 && amount <= balance) {
               balance -= amount;
           } else
   ```

## 继承 & 多态

![image-20241120155553520](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120155553520.png)

### 实际类型 & 引用类型 

### 实际类型（Actual Type）：

==运行时类型==

实际类型是指对象在**内存中真正的类型**。
它是在**运行时**确定的，是对象实例化时使用的具体类。
<font size=10 color=red>实际类型</font>决定了对象可以使用哪些<font size=10 color=red>方法</font>

### 引用类型（Reference Type）：

==编译时类型==



引用类型是**变量声明时使用**的类型。
它是在编译时确定的，可以是实际类型的本身，也可以是其父类或实现的接口。
<font size=10 color=red>引用类型</font>决定了通过该引用可以访问哪些<font size=10 color=red>成员变量</font>

parent A = new sons()

parent B = new daughters()

---



**引用类型 			实际类型**

引用类型的对象数据存储在栈内存中 ，实际类型的对象数据存储在堆内存中

也就是A 只可使用父类的字段然后在运行的时候调用方法指向堆内存中子类的方法

（**但是变量还是父类的**）

---

**so ： 子类重写的方法限不能小于父类的方法** （维护了==多态性==）

**多态本质：**

new 返回的是一个对象的引用

返回的是一个对sons对象的引用 

sons本身是自己的实际类型

引用类型 =new 实际类型 （返回对象的引用）

```java
// Animal是父类，Dog是子类
Animal animal = new Dog();  // 向上转型

// 引用类型是Animal，实际类型是Dog
animal.makeSound();  // 调用Dog类的makeSound方法，而不是Animal类的

// 向下转型，需要显式转换
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.wagTail();  // Dog特有的方法
```

**子类赋值给父类**      

**实现类赋值给接口**

~~~java
// 使用接口

interface AudioPlayer {
    void play(String fileName);
}

class MP3Player implements AudioPlayer {
    public void play(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

class WAVPlayer implements AudioPlayer {
    public void play(String fileName) {
        System.out.println("Playing WAV file: " + fileName);
    }
}

class FLACPlayer implements AudioPlayer {
    public void play(String fileName) {
        System.out.println("Playing FLAC file: " + fileName);
    }
}

public class MusicSystem {
    public static void main(String[] args) {
        AudioPlayer player1 = new MP3Player();
        AudioPlayer player2 = new WAVPlayer();
        AudioPlayer player3 = new FLACPlayer();

        // 播放不同格式的文件
        player1.play("song1.mp3");
        player2.play("song2.wav");
        player3.play("song3.flac");

        // 可以轻松添加新的音频格式,只需实现AudioPlayer接口
    }
}
~~~

## 赋值的本质 堆区和栈区存储

**对象赋值的本质：**

1. **引用传递：**
   - **在Java中，对象变量存储的是对象的引用（内存地址），而不是对象本身。**
   - **当进行对象赋值时，实际上是复制这个引用，而不是复制整个对象。**
2. **共享对象：**
   - **赋值后，两个引用指向同一个对象。**
   - **通过任何一个引用修改对象，都会影响到其他引用所看到的对象状态。**





![image-20241107082239273](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082239273.png)

![image-20241107082343542](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082343542.png)

![image-20241107082354980](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082354980.png)

**多态具体运用： 对象数组**

![image-20241107082144419](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082144419.png)

**<font color=red size=6>java导包：</font>Java中的导包是指为了在源码中使用某个包中的类或接口而引入相应的包。当我们使用Java中的类或接口时，需要用到这个类或接口的全限定名，如果没有引入相应的包，我们在代码中就需要使用完整的类名来访问这个类或接口。而导包可以帮助我们简化代码，提高代码的可读性和可维护性。**

**java 默认（默认级别）** 包级别访问机制  （不包括子类）

---

final关键字 ： **修饰类/方法 变量**

一旦被修饰 类 ： **无法被继承** 

方法：**不能被子类重写** 

变量：**不可以被改变**

---

# 构造方法：

**构造方法不能被继承**：子类不能继承父类的构造方法。每个类都有自己的构造方法，即使父类构造方法存在，子类也不能直接使用它。子类只能通过 `super()` 来调用父类的构造方法

每当创建对象时，构造方法都会被调用， 不能返回任何值， 如果在继承的状态下， 

核心：

**无论子类是否显式调用父类的构造方法，父类的构造方法总会在子类的构造方法中执行。这是因为 Java 需要确保在创建子类对象时，父类的部分能够被正确地初始化。**

- 如果子类的构造方法**没有显式调用父类**的构造方法，那么 Java 编译器会**隐式**插入一个对父类**无参构造方法**的调用（`super()`）。
- 如果父类没有无参的构造方法，而子类**没有显式调用父类**的有参构造方法，那么编译会失败。

---

**方法重载 ： 同名方法 ， （形式参数）参数类型 数量 顺序不同**

**和修饰符、返回类型无关 ，只和参数列表有关**

---



![image-20241107083820942](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107083820942.png)



# 访问权限和static 关键字

![image-20241107082551761](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082551761.png)

# 类 抽象类

![image-20241107082807147](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082807147.png)

![image-20241107082814982](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082814982.png)

![image-20241107082821386](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082821386.png)

# 接口



# Java 集合操作

**acwing**  



# java 基础

## Tips

**1.边看视频边写代码。** **先看视频了解一个大概** 模拟出流程后 然后自己手写 最后回过头看哪里有缺或是哪里不够好（总结方法论）

**2.在没有充分了解Java这个行业之前贸然开始 自信，破釜沉舟 **  

学习一个技能技术前 要懂得 it  **who （谁发明）  when（什么时候发明） whether （这个技术是否过时 是否可替代）what（这个技术是什么） why（为什么要发明这个技术） how（这个技术长什么样，如何学习，如何了解学习渠道）**

学东西光学知识点没p用 一定要有应用场景的实现！！！

 **3.学习不专注**  起码得保证45min不被打扰专注时间

 **4.不写测试代码 **  判空指涉

**5.拒绝学习代码规范，展示代码**   不要害怕别人说自己菜

**6.碰见bug不思考就发问**   foremost : 学会自己看，这样下次碰到了就会解决了，实在不行就上网解决，贵在坚持，不要轻易放弃。最后实在不行了在问别人！

**7.手懒不想动 **   **写程序不能光想和光临摹别人的代码 要自己实现 先有个全局观 然后再从细节出发**

 **8.没有极致的求知精神** 

**9.没有目的的去写代码，不刻意的练习**   要知道自己在干嘛

**10.急于求成想快速精通。** 让自己痛苦才是学习的最好buff

**多个类在一个文件中的规则：**

- 在Java中，一个源文件可以包含多个类
- 但只能有一个public类，且public类的名字必须与文件名相同
- 其他类（如Mypanel）是包访问级别的类，只能在同一个包内访问



核心：

java一切皆对象

**main方法是函数的入口方法 **  执行这个类 一定会先从主方法开始执行！！！ 

## 画图基本用法 & 封装思想

![image-20241108233919044](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241108233919044.png)



~~~java
package draw;
import javax.swing.*;
import java.awt.*;
// 导包和包路径 放置顺序注意！！！

public class DrawCircle extends JFrame{
    private Mypanel mp = null;// 定义一个画板
    public DrawCircle(){// 构造器
        mp = new Mypanel();
        this.add(mp);//把画板放到框架窗口中
        this.setSize(1000,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口后结束程序的执行
        // 设置框架的属性
    }
    public static void main(String[] args) { // 主方法
        new DrawCircle(); // 一new （创建）就会调用构造方法

    }
}
class Mypanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10,10,100,100); // 起始点从圆的外切正方形的左上角顶点开始！
        g.drawLine(10,10,100,100);
        g.setColor(Color.red); //设置画笔颜色
//        g.setFont(new Font("隶书",Font.BOLD,50)); // 设置画笔字体

//        g.drawString("lunaFreya",100,100);
//        Image img = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("image/Freya.png")); src:引入图片写法
//        g.drawImage(img, 10, 10, 500,500,this);



    }
}
~~~

**查文档能力 ：java 8 以及各种框架！！** Graphics 类用法

**类比能力 ：java画图体系类似css中position的定位坐标系**



**hero 继承了tank类 ---》封装思想**

~~~java
// Mypanel.java
package TankGame;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
// 决定画什么内容
public class Mypanel extends JPanel implements KeyListener{
    // 实现键盘事件监听接口 --> 重写其方法（规范性）
    Hero hero = null;// 定义坦克
    Vector<enemyTank> enemyTanks = new Vector<>();
    // 创建一个集合
    int enemyTankSize = (int)(10*(Math.random()));

    //变量 默认是包访问权限
    public Mypanel() {
        hero = new Hero(100,100);
        // 初始化坦克对象
        hero.setDirection(2);
        // 没有必要封装到hero里 , 直接调用方法改变/获取值
        for(int i=0;i<enemyTankSize;i++)
        {
            enemyTank e = new enemyTank(100*(i+1),0);
            e.setDirection((int)(Math.random()*4));// 随机方向
            enemyTanks.add(e);

        } // 初始化坦克集合2


    }
    // 将画坦克封装为一个方法
    // 传入 坐标  朝向  画笔  阵营
    public void drawTank(int x, int y, int direction, Graphics g, int bloc) {
        if( bloc == 0) {
            g.setColor(Color.green);
        } else {
            g.setColor(Color.red);
        }
        switch (direction) {
            case 0: // 向上
                g.fill3DRect(x, y, 10, 60, false); // 左
                g.fill3DRect(x + 30, y, 10, 60, false); // 右
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间
                g.fillOval(x + 10, y + 20, 20, 20); // 圆盖
                g.drawLine(x + 20, y + 30, x + 20, y); // 炮筒
                break;
            case 1: // 向右
                g.fill3DRect(x, y, 60, 10, false); // 上
                g.fill3DRect(x, y + 30, 60, 10, false); // 下
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 中间
                g.fillOval(x + 20, y + 10, 20, 20); // 圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20); // 炮筒
                break;
            case 2: // 向下
                g.fill3DRect(x, y, 10, 60, false); // 左
                g.fill3DRect(x + 30, y, 10, 60, false); // 右
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间
                g.fillOval(x + 10, y + 20, 20, 20); // 圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 炮筒
                break;
            case 3: // 向左
                g.fill3DRect(x, y, 60, 10, false); // 上
                g.fill3DRect(x, y + 30, 60, 10, false); // 下
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 中间
                g.fillOval(x + 20, y + 10, 20, 20); // 圆盖
                g.drawLine(x + 30, y + 20, x, y + 20); // 炮筒
                break;
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750); // 对于画板(面板)界面：填充矩形 颜色默认为黑色
        drawTank(hero.getX(),hero.getY(),hero.getDirection(), g,0);
        for(int i=0;i<enemyTankSize;i++)
        {
            enemyTank e = enemyTanks.get(i);
            // 集合方法
            drawTank(e.getX(),e.getY(),e.getDirection(),g,1);
        }
        // 把绘画部分封装成一个方法后调用即可
        // 注意·传递的参数
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if((char)e.getKeyCode() == 'W') {
            hero.setDirection(0);
            hero.moveUp();
            // 继承了tank 就可以直接使用tank的方法了
        }
        if((char)e.getKeyCode() == 'D') {
            hero.setDirection(1);
            hero.moveRight();

        }
         if((char)e.getKeyCode() == 'S') {
             hero.setDirection(2);
             hero.moveDown();
       }
        if((char)e.getKeyCode() == 'A') {
            hero.setDirection(3);
            hero.moveLeft();

        }

        this.repaint();
        // 重绘
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
~~~

~~~java
// round01.java
package TankGame;

import javax.swing.*;

public class Round01 extends JFrame {
    private Mypanel mp = null; // 从包中拿另一个类（public）
    public static void main(String[] args) {

        Round01 round01 = new Round01();

    }
    public Round01() {
        mp = new Mypanel(); // 实例化面板
        this.add(mp); // 窗口中填放画板
        this.setSize(1000, 750); // 设定窗口大小
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

~~~

## 继承 特性（Tank -->Hero）

**当一个类继承了另一个类时，子类会自动获得父类的所有字段（属性）和方法，尽管子类可以选择覆盖（override）父类的方法来提供自己的实现**





## java 事件处理机制

![image-20241109100416434](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109100416434.png)

![image-20241109100420764](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109100420764.png)

![image-20241109100533341](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109100533341.png)

# XML

![image-20241109180028172](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109180028172.png)

![image-20241109180031729](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109180031729.png)

# 网络编程

本质： 就是计算机通过网络进行数据传输

![image-20241109175613478](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109175613478.png)

BS/CS 架构  （browser server/ client server）

**网页游戏画质稀烂 why 资源不是在本地 而是要服务器传输** 

## 网络编程三要素

​	ip：（接收数据的通信设备地址）

端口号：一个端口号只能被一个软件绑定使用（接收数据的软件）

网络传输的规则（协议）

![image-20241109230101148](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109230101148.png)

![image-20241109230421868](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109230421868.png)

![image-20241109231833818](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109231833818.png)

![image-20241109232124413](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109232124413.png)

**128位分成八组**

## 局域网和公网

~~~tex
192.168开头的IP地址是私有IP地址范围中最常见的一类。具体来说：

1. 地址范围：
   192.168.0.0 到 192.168.255.255
2. 子网掩码：
   255.255.0.0 （也写作 /16 in CIDR notation）
3. 分类：
   这属于C类私有地址范围
4. 可用IP数量：
   65,536个（2^16）
5. 常见用途：
   - 家庭网络
   - 小型办公室网络
   - 无线路由器默认设置
   - 虚拟机网络
6. 特点：
   - 不可直接在互联网上路由
   - 可在不同的局域网中重复使用
   - 需要通过NAT（网络地址转换）才能访问互联网
7. 配置示例：
   - 路由器IP：192.168.1.1
   - DHCP范围：192.168.1.2 到 192.168.1.254
8. 安全性：
   提供了一定程度的网络隔离，因为这些地址不能直接从互联网访问
9. 标准：
   定义在RFC 1918中
10. 其他常见配置：
    - 192.168.0.x
    - 192.168.1.x
    - 192.168.2.x 等

这个地址范围非常流行，因为它提供了足够多的地址用于大多数小型到中型网络，同时又易于记忆和配置。然而，在大型企业网络中，可能会选择使用10.0.0.0/8范围，因为它提供了更多的地址空间。

// 重要提示：虽然192.168.x.x地址在不同的局域网中可以重复使用，但在同一个网络中，每个设备的IP地址必须是唯一的。
~~~



## 特殊IP地址

​	![image-20241109235358709](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109235358709.png)

**不同的局域网下ip地址不一样**

而127.0.0.1 一定是本机ip



## 公网  外网 广域网  内网 局域网



```HTML
公网就是 互联网
内外网只是一个相对概念 
广域网 局域网 
广域网是大型的局域网
```

 ![da3812272922ce6f6af0cf6d94df339](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/da3812272922ce6f6af0cf6d94df339.jpg)

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/da3812272922ce6f6af0cf6d94df339.jpg)



## InetAdress 类

~~~java
package com.Senjay;

import java.net.InetAddress;
import java.net.UnknownHostException; // 要抛出异常
public class InetAdressDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress addr =InetAddress.getByName("senjay");
   //   InetAddress addr =InetAddress.getByName("192.168.0.9"); 
        // 主机名/ip地址
        // 先获取 InetAdress 对象
        System.out.println(addr);
        ------------------------------------------------
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());
        // 解构数据

    }
}

~~~

## 端口号

![image-20241111182843348](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111182843348.png)

## 协议

**basic：**

![bdb1d23f5fc9cf0569dffcf9eec898e8](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/bdb1d23f5fc9cf0569dffcf9eec898e8.png)

![image-20241111182900646](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111182900646.png)

**根据优势劣势划分应用场景！**

![image-20241111182950599](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111182950599.png)

### UDP协议

**Socket 即插座**

==学会查看方法参数列表不要死记硬背==

data**gram** 数据包（**报**）

####  发送数据：

![image-20241111183201616](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111183201616.png)

![image-20241111183223003](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111183223003.png)

~~~java
package com.Senjay.socketdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketUDP_send {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();// no parameter ：send data by random port

        String str="hello world!";
        byte [] datas =new byte[1000];
        datas = str.getBytes();
        InetAddress address = InetAddress.getByName("192.168.101.116");
        int port = 10086;
        // 指定发送的地址和端口号
        // 打包数据和发送地址
        DatagramPacket dp = new DatagramPacket(datas, datas.length,address,port); // parameters link to cue so that show
        // the datas we send is byte Arrays
        ds.send(dp);
        ds.close();
        // clear the source

    }
}

~~~



#### 接收数据



![image-20241111183318539](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111183318539.png)

![image-20241111183322055](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111183322055.png)

![image-20241111183324666](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111183324666.png)

~~~java
package com.Senjay.socketdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketUDP_received {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);
        byte [] temp = new byte [1024];
        DatagramPacket dp = new DatagramPacket(temp, temp.length);
        ds.receive(dp);
        // 接收 将数据放入dp中

        // 解构数据
        byte [] data=dp.getData();
        int len = dp.getLength();
        InetAddress address = dp.getAddress();
        int port = dp.getPort();

        System.out.println("地址为"+address.getHostAddress()+"端口号为"+port+"发送了"+"<"+new String(data,0,len)+">"+"数据");


    }

}

~~~

```tex
new String(data, 0, len)

这是 String 类的一个构造方法，用于从字节数组创建字符串。它有三个参数：

data：字节数组，这里是通过 dp.getData() 获取的数据包中的字节数据
0：起始位置（offset），表示从字节数组的第一个位置开始读取
len：要转换成字符串的字节长度





- 如果不指定起始位置和长度，可以使用 `new String(data)` 转换整个数组

- 建议在网络通信中指定字符编码，如 `new String(data, 0, len, "UTF-8")`

  
```



---

### UDP 的三种通信方式 （代码实现）

![image-20241111191944907](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111191944907.png)

#### 单播

etc.....

#### 组(多)播

==**在send中指名发送的组名**==

![image-20241111192453774](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192453774.png)

==**在receive中加入指名的组中**==

在接收方创建`MultiSocket `对象！

##### 区别注意：

**`DatagramSocket`** 是用于**点对点**的UDP通信。它可以发送/接收独立的数据包到/从指定的接收者/发送者。

**`MulticastSocket`** 是用于多播UDP通信。它可以将数**据发送到一个多播组中的多个接收者**，允许多个主机同时**接收**到同一数据包。



![image-20241111192514964](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192514964.png)

`ms.join(InetAddress对象)`

#### 广播

**直接将发送的ip地址改为255.255.255.255!!**



### TCP协议

![image-20241111192237673](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192237673.png)

#### 发送数据

![image-20241111192243547](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192243547.png)

~~~java
package com.Senjay.socketdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTCP_send {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("192.168.101.116",10086);// 创建对象的同时来连接服务端
        OutputStream os = socket.getOutputStream();// 获取输出流通道
        String conversation = sc.nextLine();

        os.write(conversation.getBytes());  // 传输的数据一定要是字节文件
//        os.close(); 没有必要 因为关闭通道这个也跟着关了
        socket.close(); // 断开连接


    }
}

~~~





#### 接收数据



![image-20241111192249192](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192249192.png)

~~~java
package com.Senjay.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP_received {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086); // 指名接收的端口号
        Socket socket = ss.accept();// 监听客户端的连接 等待并接受客户端的连接请求。这个方法会阻塞，直到有客户端连接。
        InputStream is = socket.getInputStream();

        InputStreamReader isr = new InputStreamReader(is); // 将字节流转化为字符流
        BufferedReader br = new BufferedReader(isr);

        // 获取通道的输入流
        int b;
        while ((b = br.read()) != -1) {
            // 对于字节流
            // is.read() 方法从输入流中读取一个字节的数据。
            // 如果读取成功，它返回一个 0 到 255 之间的整数，表示读取的字节值（几个字节）。
            // 如果到达流的末尾，它返回 -1。
            
            // 对于字符流
            // 这是一个 while 循环，它会持续执行，直到从流中读取到 EOF（End of File）标记。
            // br.read() 方法：
            // 从 BufferedReader 中读取一个字符。
            // 返回值是该字符的 Unicode 编码点（一个整数），范围是 0 到 65535。
            // 如果到达流的末尾，返回 -1。
            
            System.out.print((char) b); // 一个字节一个字节地获取 如果是中文的要怎么办
        }
//        is.close();
        socket.close();
    }
}

~~~

`ss.accept 用于接收一个来自客户端的连接请求，连接了就会返回一个Socket `

#### 通信流

![image-20241111192316713](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192316713.png)

#### 三次握手&四次挥手

![image-20241111192252760](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192252760.png)

![image-20241111192256195](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192256195.png)



# 异常

## 异常的表现

**当语句有异常时直接进入catch语句块 不会执行异常语句下面的语句了**



## 异常类型

### 编译时异常（受检异常）

编译时异常是在编译时由==编译器强制检查==的异常。程序员必须==显式==地处理（两种方法）这些异常，否则程序将==无法编译==。

### 运行时异常（非受检异常）

 运行时异常是在程序运行过程中可能出现的异常，编译器不强制要求程序员进行处理。这些异常通常是由程序中的==逻辑错误==引起的

### 错误（error）

- 错误是指应用程序无法处理的严重问题。这些通常是由==运行时环境==导致的，程序员不应试图捕获这些错误。：错误通常表示 JVM 的内部错误或资源耗尽，例如==内存不足、栈溢出==等。

## 异常的处理方式：

对于异常的处理 ： 直接在本层函数中处理 ，或者抛出到调用本层函数的上层函数去处理这个异常，如果main函数还去抛出就抛到了jvm中 。

## 异常处理的目的：

因为异常不处理会导致：编译不通过或是程序运行时被迫中止，

异常处理的主要目的是提高程序的健壮性和可维护性，确保在程序出现异常情况时能够进行适当的响应，而不是简单地崩溃。具体来说，异常处理的目的包括以下几个方面：

1. **提高程序的可靠性**：
   - 异常处理使程序能够在遇到错误或意外情况时继续运行或安全地终止。通过捕获和处理异常，程序可以避免因异常崩溃，维持一定的服务质量。
2. **提供错误信息**：
   - 在异常发生时，能够输出有意义的错误信息，帮助开发人员和用户理解错误的原因。异常处理可以记录详细的错误日志，这对于后续的调试和问题分析非常有帮助。
3. **资源的正确释放**：
   - 在异常发生后，通过 `finally` 块或 `try-with-resources` 语句，确保资源（如文件、数据库连接等）能够被正确地关闭和释放，防止资源泄漏。
4. **程序的健壮性和用户体验**：
   - 通过处理异常，程序可以提供更好的用户体验。例如，当出现网络错误时，程序可以提示用户重试，而不是直接崩溃。
5. **隔离错误和恢复操作**：
   - 异常处理允许程序在局部范围内处理错误，而不需要在整个程序中检查和处理错误。这种错误隔离机制使得代码更加简洁和可维护。
6. **控制程序流程**：
   - 通过异常处理，程序可以在特定条件下改变正常的执行流程。虽然不建议过度使用异常来控制程序逻辑，但在某些情况下，如处理特定的错误条件时，异常是非常有用的。
7. **防止安全漏洞**：
   - 通过正确的异常处理，可以防止程序暴露内部实现细节，从而减少安全风险。例如，防止 SQL 注入或文件路径泄露等问题。

## 异常处理 

~~~java
public class ThrowingExceptions {
    public static void checkAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        System.out.println("年龄是: " + age);
    }

    public static void main(String[] args) {
        try {
            checkAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到异常: " + e.getMessage());
        }
    }
}
~~~

**注意：throws和throw关键字**

### 自定义异常

~~~java
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class CustomExceptionExample {
    public static void validate(int number) throws CustomException {
        if (number > 100) {
            throw new CustomException("数字不能大于100");
        }
        System.out.println("数字是: " + number);
    }

    public static void main(String[] args) {
        try {
            validate(150);
        } catch (CustomException e) {
            System.out.println("捕获到自定义异常: " + e.getMessage());
        }
    }
}
~~~





# 注解（结合反射才有用）

java中的字段 : 成员变量和成员方法



## overview：

@Override 告诉编译器这个方法是覆盖父类的方法。
@WebServlet("/test")表示某个类是一个 Servlet，Web 容器就会识别这个注解，在运行的时候调用它。
@Controller("/test") 表示某个类是一个控制器，告诉 Spring 框架该类是一个控制器。
注解和注释是完全不同的两个东西，看起来有点类似，其实完全不同，注解会影响程序的运
行。
注释是给开发人员看的，不会影响程序的编译和运行。注解并不是给开发人员看的，**是用于给程序看的**，会影响程序的编译和运行。

## 注解的作用范围

自定义开发一个 Web 容器，基本功能是加载 Servlet，需要管理它的生命周期，所以必须先识别程序中的哪些类是 Servlet。
程序启动的时候，扫描所有的类，找出添加了@WebServlet 注解的类，进行加载.

@Webservet 是在程序运行的时候起作用的，java 就把它的作用范围规定为 RUNTIME。

@Override 是给编译器看的，编译器工作的时候识别出包含了 @Override 注解的方法，就去检查它上层父类的相应方法，存在则通过，否则报错。
@Override 是编译时候起作用，Java 就把它的作用范围规定为 SOURCE。



## @Target 指定注解针对的地方

ElementType:
ElementType.TYPE							    针对类、接口

ElementType.FIELD 						  	针对成员变量

ElementType.METHOD 					    针对成员方法

ElementType.PARAMETER 			   	针对方法参数

ElementType,CONSTRUCTOR		  	针对构造器	

ElementType.PACKAGE					     针对包

ElementType.ANNOTATION TYPE	   针对注解


## @Retention 指定注解的保留域

RetentionPolicy:
**RetentionPolicy.SOURCE** 源代码级别，由编译器处理，处理之后就不再保留

**RetentionPolicy.CLASS** 注解信息保留到类对应的 class 文件中

**RetentionPolicy.RUNTIME** 由JM 读取，运行时使用



## 自定义注解

![bbc1a7eb9d907fd73c64971b1ba414b](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/bbc1a7eb9d907fd73c64971b1ba414b.jpg)



# 反射

# overview

`机器人，它能在运行时看见自己的零件(字段、方法)并----根据需要----进行调整或操作。反射就是这个机器人用来查看和操作自身的方法`

## 反射的应用场景
### 1.动态创建对象:
**概念:**在运行时根据需要创建新的对象，而不是提前写死。

**例子:**想象你在玩一个游戏，随着游戏进展，动态生成新的角色或道具。

### 2.依赖注入(Dependency Injection)
**概念:**在运行时把所需的对象注入到另一个对象中，让它们可以协同工作。
**例子:**假设你有一个工厂，需要不同的零件来组装产品。依赖注入就像是自动把这些零件分配给工厂。

---



**不使用反射直接拿到成员属性の局限性:**
这种方式要求在编译时就知道类的结构和方法，如果我们要在运行时动态决定访问哪些字段或方法，就无能为力了。

**使用反射的优势：**
可以在运行时决定访问哪些字段和方法，不需要在编译时知道类的结构。使得代码更加灵活，能够处理未知类或通过配置文件加载的类。

## 反射的具体操作



# 动态代理

link to [静态代理](#实现Runnable接口)









# I/O 流

![image-20241114221323944](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114221323944.png)

![image-20241114221524113](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114221524113.png)

**抽象类无法实例化**

**所以底层就实现了几个子类继承InputStream 以此来实现父类的方法**

![image-20241114221334670](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114221334670.png)

## 路径问题

~~~java
package com.Senjay.io_modules;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoDemo {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos2 = new FileOutputStream("src/com/Senjay/io_modules/test1");
        // 在当前工程（项目）目录下 
        // 相对路径
        FileOutputStream fos = new FileOutputStream("D:\\桌面\\Backend\\JavaSE\\Projectrs\\SocketInet\\src\\com\\Senjay\\io_modules\\test2");
        // 绝对路径
        try {
            fos.write(58);
            fos2.write(90);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fos.close();
            fos2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

~~~

**一个项目中有多个模块 每个模块都会有一个src注意**

**不同模块不好使用相对路径 除非是主模块（项目文件）** 下新建模块 则相对路径的根目录就是当前模块

![image-20241115000906797](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241115000906797.png)

**创建模块文件时注意模块路径**

## 文件输出流

~~~java
package iodemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class BytesIODemo {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("testdemo/src/iodemo/test1",false);// 续写开关 (默认为false)
//            fos.write(98);  (一个字节大小的数据) -- 一次写一个字节数据
        byte[] bytes = {97,98,99,100};// 每个元素都是一个字节的数据
//        try {
//            fos.write(bytes); // -- 一次写一个字节数组的所有数据
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            fos.write(bytes,1,3);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // 一次写一个字节数组的部分数据
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        byte[] datas = str.getBytes();// 将字符串转化为字节流
        // 为什么输入中文后不会乱码 中文不是以三字节存储的吗
        try {
            fos.write(datas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(datas));
//        System.out.println(Charset.defaultCharset().name());// java 系统默认编码格式
        // getbyte() 的编码标准


        try {
            fos.close();// 最后一定要释放资源
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

// 输入：是啊
// 输出结果
// [-26, -104, -81,   -27, -107, -118]
~~~

### 对于换行书写

![image-20241120164235989](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120164235989.png)



## 文件输入流

![image-20241120164531650](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120164531650.png)

**读入的时候指针也会移动的**

~~~java
   while((b=fis.read())!=-1)
        {
            fos.write(b);
            //  Byte by byte 读取/写入
        }
~~~

​	

~~~java


public class InputStream2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("testdemo/src/iodemo/test2");
        FileOutputStream fos = new FileOutputStream("testdemo/src/iodemo/test1");
        byte[] buf = new byte[10]; // 不能有空字节被写入进去！！！
        // 开一定大小的字节数组
        int len;
        try {
           while((len = fis.read(buf))!=-1) // 返回实际读入的字节数
           {
               fos.write(buf,0,len);
               // off -->当前指针位置
           }
            System.out.println(len); // 返回字节数 汉字*3 英文*1（byte
            System.out.println(Arrays.toString(buf));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

~~~

![image-20241120181828891](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120181828891.png)

~~~java
read方法的读取位置通常不会因为遇到换行符而停止，除非你指定了特定的读取长度或使用了readline()这样的方法。
如果你想要按行读取，可以使用readline()方法，它会在遇到换行符时停止读取
~~~





![image-20241120165732058](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120165732058.png)

### 读写问题

---



![image-20241120165736162](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120165736162.png)



**| 因为会尽可能填满字节数组 所以在写入字节数组的时候要注意写入实际读入的内容！！**

## 计算机编码

### ASCII



![image-20241120170908551](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120170908551.png)

### GBK



![image-20241120170912280](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120170912280.png)

### Unicode



![image-20241120170916483](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120170916483.png)

![image-20241120170922558](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120170922558.png)

![image-20241120170929462](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120170929462.png)

## 编码与解码



![image-20241120171032331](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120171032331.png)

## 字符流

![image-20241120171117252](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120171117252.png)

**字节流：对多媒体文件进行读写操作！！**



### 字符输入流

---







![image-20241120182651018](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120182651018.png)



![image-20241120182654325](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120182654325.png)



~~~java
package iodemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CharInput {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("testdemo/src/iodemo/test2");
//        int c;
//       while((c = fr.read())!=-1)
//       {
//           System.out.print((char)c);
//       }
        int ch;
        char [] buf =new char[50];
        while ((ch = fr.read(buf)) != -1) { // 读入数据由char数组存储 返回字符数 （字节流就是字节数）
            System.out.println(new String(buf,0,ch)); // 解码
            System.out.println(ch);
        }


            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


~~~





### 注意 read 方法无参/有参

对于返回值来说：

无参 ：返回这个具体的==十进制==是多少 如 a 就是97 

有参 ：返回实际读取的==字节数==

**上述为字节流**

**如果是字符流的话就是返回实际读取的字节数**



### 字符输出流

~~~java
package iodemo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharOutput {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("testdemo/src/iodemo/test1",true);
        Scanner sc = new Scanner(System.in);
        fw.write(97);
        fw.write("hello world");
        String str = sc.nextLine();
        char [] ch = new char[10];
        fw.write(str,0,5);
//        fw.write(ch);


        fw.close();

    }
}

~~~

**| 文件路径也可使用file对象**

## 缓冲流

![image-20241120190738913](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120190738913.png)

### 字节缓冲流

![image-20241120190819311](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120190819311.png)



![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120191612892.png)

**就是上了一层==包装==而已**



### 字符缓冲流



![image-20241120191733755](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120191733755.png)

![image-20241120195349269](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120195349269.png)

~~~java
package iodemo;

import java.io.*;
import java.util.Scanner;

public class BufferedCharStream {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new FileReader("testdemo/src/iodemo/test1"));
       BufferedWriter bw = new BufferedWriter(new FileWriter("testdemo/src/iodemo/test2",true));
       bw.write("hello");
       Scanner sc = new Scanner(System.in);
       String str = sc.nextLine();
        bw.newLine();
        
       bw.write(str);


       bw.close();
    }
}

~~~



# 多线程

![image-20241112194700498](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241112194700498.png)

## 继承Thread 类

线程休眠 ： Thread.sleep(1000) **(ms)**

**线程休眠**   指**一个正在执行的线程**在调用 `Thread.sleep(milliseconds)` 方法后，暂停执行一段时间。在这段时间内，**线程不会占用CPU资源，操作系统会将其状态设置为“休眠”，然后调度其他线程运行**。休眠的时间是由传入的参数（以毫秒为单位）决定的。

注意：

CPU核心数：

- 如果您的机器是**单核**的，那么在任一时刻只能执行一个线程，这可能导致主线程先执行完毕。
- 操作系统会交替执行这两个线程，具体的执行顺序和时间片分配由操作系统决定。



```java
package com.Senjay;

public class MultiThreadDemo1 {
    public static void main(String[] args) {
        Dog cast1 = new Dog();
        System.out.println(Runtime.getRuntime().availableProcessors()); // 16核处理器
        cast1.start();// 开启线程 不会阻塞 
        

       // 操作系统会交替执行这两个线程，具体的执行顺序和时间片分配由操作系统决定。
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
class Dog extends Thread {
    // 继承线程就表明是一个线程了

    @Override
    public void run() {
        int cnt =0;
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);// ms单位
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(cnt==20){
                break;
            }
        }


    }
}
```

为何使用start方法而不是run方法

1. 使用 start() 方法：
   - start() 方法会创建一个新的线程，并让这个线程执行 run() 方法。
   - 这个新线程会与主线程**并行运行**。
   - **JVM** 会调用这个**新线程的 run()** 方法。
   - 这才是真正的多线程执行。
2. 直接调用 run() 方法：
   - 如果直接调用 run() 方法，它会在当前线程（通常是主线程）中执行。
   - 不会创建新的线程。
   - 这只是一个**普通的方法调用**，不会实现多线程。
   - 

##  实现Runnable接口

​	知识点：==静态代理==

link to [动态代理](#动态代理)


```java
package com.Senjay.threads;

public class MultiThreadsDemo2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread); // 把实现了Runnable接口的mythread 对象放入thread的构造函数中
        thread.start(); // thread 代理对象 myThread 表示被代理的对象
        
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

}
class MyThread implements Runnable { // 实现接口
    @Override
    public void run() {
        int cnt = 0;
        while(true)
        {
            System.out.println(Thread.currentThread().getName() + "第" + ++cnt  + "次调用"  );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
```

极简（不是实际）模拟静态代理的实现！！！

~~~java
// Runnable 接口（被代理的接口）
interface Runnable {
    void run();
}

// MyThread 类（被代理的对象）
class MyThread implements Runnable {
    public void run() {
        System.out.println("MyThread is running");
    }
}

// Thread 类（代理类）
class Thread {
    private Runnable target;

    public Thread(Runnable target) {
        this.target = target;
    }

    public void start() {
        // 模拟创建新线程的过程
        System.out.println("Thread is starting");
        // 在新线程中调用 run 方法
        run();
    }

    private void run() {
        if (target != null) {
            target.run();
        }
    }
}

// 客户端代码
public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}
~~~



## 两个方法的优缺点和使用场景

**实现Runnable 接口 方式更加适合多个线程==共享一个资源==的情况 ，并且避免了单继承的情况！**

这两种情况确实有重要的区别。让我们逐一分析：

**情况1：**



```java
T1 t1 = new T1();  // t1 对象实现了Runnable 接口
Thread mythread1 = new Thread(t1);
Thread mythread2 = new Thread(t1);
mythread1.start();
mythread2.start();
```

在这种情况下：

1. 只创建了一个 T1 类的实例 (t1)。
2. 两个 Thread 对象 (mythread1 和 mythread2) 共享同一个 Runnable 实例 (t1)。
3. 两个线程会执行相同的 T1 实例的 run() 方法。
4. 如果 T1 的 run() 方法中有任何共享状态（如实例变量），这些状态将被两个线程共享和修改。
5. 这可能导致线程安全问题，需要适当的同步机制来处理并发访问。



---



**情况2：**



```java
T1 t1 = new T1();  // t1 对象实现了Runnable 接口
T2 t2 = new T2();  // t2 对象实现了Runnable 接口
Thread mythread1 = new Thread(t1);
Thread mythread2 = new Thread(t2);
mythread1.start();
mythread2.start();
```

**在这种情况下：**

1. 创建了两个不同的 Runnable 实例：t1 (T1 类型) 和 t2 (T2 类型)。
2. 每个 Thread 对象都有自己独立的 Runnable 实例。
3. 两个线程将执行不同的 run() 方法（一个执行 T1 的 run()，另一个执行 T2 的 run()）。
4. 每个线程操作的是独立的对象状态，不会直接共享实例变量。
5. 这种方式通常不会导致直接的线程安全问题（除非 T1 和 T2 访问共享的静态变量或外部资源）。

主要区别：

1. 代码逻辑：情况1中两个线程执行相同的代码，情况2中可能执行不同的代码。
2. 状态共享：情况1中线程共享状态，情况2中线程有独立的状态。
3. 线程安全：情况1更容易出现线程安全问题，情况2相对独立。
4. 灵活性：情况2允许不同线程执行不同的任务，更加灵活。
5. 资源使用：情况1可能更节省内存（只有一个 Runnable 对象），但需要更谨慎的并发控制。

选择哪种方式取决于您的具体需求。如果需要多个线程执行相同的任务并共享状态，使用情况1。如果需要不同线程执行不同的任务，或者希望避免状态共享，使用情况2。

---

### exp

**可以在线程中设置一个loop变量控制while跳出 在其他类中执行这个线程的时候当满足某种条件时调用public方法修改loop的值**

**ctrl +  l 选中整行**

## 线程的方法

~~~java
package TankGame;

public class ThreadFunction {
    public static void main(String[] args) {

        Thread T1 = new Thread(new thread1());
        T1.setName("Luna Freya");// 线程改名
        System.out.println(T1.getName());
//        T1.start();
//        T1.setPriority(10);
        System.out.println(T1.getPriority());
        T1.interrupt(); // 线程中断
    }
}
class thread1 implements Runnable {
    int n=10;
    @Override
    public void run() {
        while(n-->0)
        {
            System.out.println(n);
        }
    }
}

~~~

## yield() 方法

**作用：**

`yield()` 方法是一个静态方法，它会**暂停当前正在执行的线程**，让出 CPU 的执行权，给其他具有相同优先级的线程一个执行的机会。但是，`yield()` 方法**不能保证**其他线程一定会被执行，因为线程调度器可能会再次选择当前线程继续执行。

**特点：**

- **让出 CPU，但不释放锁：** `yield()` 方法只是让出 CPU 的执行权，不会释放线程持有的锁。
- **不能保证其他线程执行：** 线程调度器可能会忽略 `yield()` 的请求，继续选择当前线程执行。
- **适用于调试和并发测试：** `yield()` 方法通常用于调试和并发测试，帮助发现潜在的并发问题。

## join() 方法

**作用：**

`join()` 方法用于**等待**调用 `join()` 方法的线程执行完成。例如，`thread1.join()` 表示当前线程（通常是主线程）会等待 `thread1` 线程执行完毕后，才会继续执行。

也就是thread1线程插队 执行完了再到当前的线程

**特点：**

- **阻塞当前线程：** 调用 `join()` 方法的线程会被**阻塞**，直到被等待的线程执行完成。
- **可以设置等待时间：** `join(long millis)`  方法可以设置等待的**最大时间**（插队时间），**如果在指定时间内被等待的线程没有执行完成**，当前线程会继续执行。
- **用于线程同步：** `join()` 方法通常用于**线程同步**，确保线程按照一定的顺序执行。



### interrupt

~~~java
当线程在休眠（调用 Thread.sleep() 方法）时执行了中断 (thread.interrupt())，会发生以下情况：

中断状态设置： 线程的中断状态会被设置为 true。
InterruptedException 异常抛出： sleep() 方法会立即抛出 InterruptedException 异常。
线程从休眠状态退出： 线程会立即从休眠状态中退出，进入 RUNNABLE 状态。
异常处理： 线程会进入 catch 块（如果存在）来处理 InterruptedException 异常。
~~~

## 守护线程和工作线程（用户线程）

在 Java 中，线程可以分为两种类型：守护线程和工作线程（也称为用户线程）。它们的主要区别在于 JVM 如何对待它们。

**工作线程 (User Thread)**

- **定义：** 工作线程是应用程序的主要线程，执行应用程序的核心任务。
- **生命周期：** 当所有工作线程都结束时，JVM 会退出。JVM 不会等待守护线程完成。
- **默认类型：** 通过 `new Thread()` 创建的线程默认是工作线程。

**守护线程 (Daemon Thread)**

- **定义：** 守护线程是为其他线程提供服务的线程，例如垃圾回收线程。
- **生命周期：** 当所有工作线程都结束时，JVM 会退出，即使还有守护线程在运行。
- **设置方式：** 通过调用 `thread.setDaemon(true)` 方法将线程设置为守护线程。这个方法必须在线程启动之前调用。

**区别总结**

| 特性         | 工作线程 (User Thread)       | 守护线程 (Daemon Thread)                                  |
| :----------- | :--------------------------- | :-------------------------------------------------------- |
| **目的**     | 执行应用程序的核心任务       | 为其他线程提供服务                                        |
| **JVM 退出** | 所有工作线程结束时，JVM 退出 | JVM会在所有非守护线程结束后,自动终止所有守护线程并关闭JVM |
| **默认类型** | 是                           | 否                                                        |
| **设置方式** | `new Thread()`               | `thread.setDaemon(true)`                                  |

使用案例：
垃圾回收机制

### 守护线程特点

~~~java
守护线程的特点：

非必要性
    
这些任务即使中断也不会影响核心功能
用户关闭应用时可以立即结束
    
辅助性
    
为主要功能提供支持服务
提升用户体验但非必需
    
资源友好
    
优先级通常较低
不会占用过多系统资源
~~~



## 线程的生命周期

1. **新建（New）**：
   - 当线程对象被创建时，它处于新建状态。在这个状态下，线程对象已经实例化，但还没有开始执行。
2. **就绪（Runnable）**：
   - 当调用线程对象的 `start()` 方法后，线程进入就绪状态。此时，线程已经准备好运行，等待 CPU 分配时间片。就绪状态的线程可能随时被线程调度器选中，进入运行状态。
3. **运行（Running）**：
   - 线程获得 CPU 时间片开始执行时，进入运行状态。在这个状态，线程的 `run()` 方法中的代码开始执行。运行状态是就绪状态的线程被线程调度器选中后进入的状态。
4. **阻塞（Blocked）**：
   - 当线程因为等待某个资源（如 I/O 操作完成）而被阻塞时，进入阻塞状态。阻塞状态的线程在资源变得可用后会重新进入就绪状态。
5. **等待（Waiting）**：
   - 线程进入等待状态是因为调用了 `Object.wait()` 方法或 `Thread.join()` 方法，等待某个条件发生。在等待状态下，线程不会主动占用 CPU 资源，只有在其他线程通知或中断时才会重新进入就绪状态。
6. **计时等待（Timed Waiting）**：
   - 线程因为调用了带超时参数的方法（如 `Thread.sleep(long millis)`、`Object.wait(long timeout)`、`Thread.join(long millis)` 等）而进入计时等待状态。在计时等待状态下，线程将在指定时间后自动返回就绪状态。
7. **终止（Terminated）**：
   - 线程完成执行或者因异常退出后，进入终止状态。进入终止状态的线程将不再执行，生命周期结束



![img](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/%7B3EBBE7DE-BC29-47F6-BCF8-BEECA03C4399%7D)

~~~jAVA
在Java中，线程的<超时等待状态>
(TIMED WAITING)指的是线程在等待
特定时间段内完成某个操作，然后继续执
行。如果等待 <时间到期> 而事件没有发生
线程会从 <超时等待状态> 中恢复并继续执
行。超时等待通常由一些方法触发，如
Thread.sleep()、0biect.wait()
Thread.join()带有 <超时参数>，以及
LockSupport.parkNanos()和
LockSupport.parkUntil()等。
举个例子:
//线程进入超时等待状态
Thread.sleep(1000);//等待1000毫秒


/<"因为不能无限制的等待啊 一定是有一个时间限制的！！！！">/
~~~

~~~java
Thread.yield()方法并不保证
成功让出当前线程的执行时间片。
yield()的作用是提示线程调度器当前
线程愿意让出CPU使用权，允许其他同优
先级或者更高优先级的线程获得CPU时
间。然而，具体是否会让出，完全取决于
底层操作系统的线程调度算法。
在某些情况下，调用yie1d()后，当前
线程可能会立刻重新获得CPU时间片，继
续执行，而不是让出给其他线程。这主要
是因为线程调度器对yield()请求的处
理方式不确定。
简单来说，yield()是一个建议而不是
强制命令，不能依赖它来进行线程同步或
控制程序逻辑。
如果需要更加可靠的线程调度控制，通常
可以考虑使用更为明确的线程同步机制。
比如wait()、notify()、join()或各
种并发包提供的工具类，
~~~







## 线程的同步机制



## 互斥锁







# Java API （类库）



## 包装类（wrapper class）

**定义：基本数据类型对应的引用数据类型**

![image-20241114191405766](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114191405766.png)

**为何要包装类？**
由于集合要使用对象 ，方法传参参数大都是对象

char （2字节 in java） -- Character

int -- Integer

byte - Byte (其余与此同)

~~~java
package com.Senjay.basicAPI;

public class WrapperClass {
    public static void main(String [] args) {
        {
            Integer a = new Integer(120);// 过时
            Integer c = new Integer(120);
            System.out.println(a==c ); // false
            // 双等号 比较地址
        }
        {
            Integer a = Integer.valueOf(127);
            Integer c = Integer.valueOf(127);/// 写这个或者自动装箱
            System.out.println(a==c ); // true
        }
        // -128 ~ 127 缓存（底层提前把这些范围的数的已经创建了对象，其他的 就是相当于new出来一个新的）
        {
            Integer a = Integer.valueOf(527);
            Integer c = Integer.valueOf(527);
            System.out.println(a==c ); // false
            
        }
    }
}

~~~

Integer 的equals 方法已经内置封装好了

###  Integer 的自动装/拆箱

~~~java
package com.Senjay.basicAPI;

import java.util.Scanner;

public class WrapperClass {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        {
            Integer i =10; // 自动装箱       // 底层使用valueof方法  Intefer.valueof(10)
            int copy_i=i; // 自动拆箱  // 底层解构
        }
        ————————————————————————————————————————————————————————————————————————————————————————

      String b = Integer.toBinaryString(25);// 返回字符串
        System.out.println(b);
      String o = Integer.toOctalString(25);
        System.out.println(o);
      String h = Integer.toHexString(25);
        System.out.println(h);
        {
            int i = Integer.parseInt("59482"); // 解析
            System.out.println(i);
        }
        // 8种包装类中，除了character 中都有parsexxx方法！！！
        {
            String s =  sc.nextLine(); // 遇到回车后停止
            System.out.println(s);
        }

    }
}

~~~











---



## Arrays

现用现查

## String 

![image-20241114214054606](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114214054606.png)



查看API文档即可

link to [API文档](https://github.com/kasahuki/Backend/blob/main/JavaSE/API.md)

### string 注意事项

**string a string b 保存在字符串常量池当中（在字符串常量池中指向同一对象）**

`a==b` -->true

## System 类

### exit



~~~java
package com.Senjay.basicAPI;

public class SystemTest {
    public static void main(String[] args)
    {
//        System.exit(0); 表示当前JVM虚拟机是正常停止

        // 参数：status（状态码）
        System.exit(1);
        // System.exit(1);
        //System.exit(1); 语句在 Java 程序中用于终止 Java 虚拟机（JVM），
        //并且返回一个状态码给操作系统。这个状态码帮助操作系统和其他程序判断程序的退出状态。
        // 状态码1通常表示程序是由于某种错误或异常情况而非正常结束。
        System.out.println("Hello World");

    }

}
~~~

### currentTimeMillis

~~~java
package com.Senjay.basicAPI;

import java.util.*;

public class Time {
    public static  void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long time_start = System.currentTimeMillis();
        int n = sc.nextInt();
        for(int i=2;i*i<=n;i++) // 注意如果不写方法里的话3 这个情况就没有考虑到了 return 的特性
        {
            if(n%i==0)
            {
                System.out.println("false");
                break;
            }
            System.out.println("True");

        }
        long time_end = System.currentTimeMillis();
        System.out.println(time_end - time_start);

//        long time1 = System.currentTimeMillis();
//        for(int i=0;i<1000;i++)
//        {
//            int cnt = 0;
//            cnt++;
//        }
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
    }
}

~~~



### arraycopy （拷贝数组）

~~~java
package com.Senjay.basicAPI;

public class ArrayCopy {
    public static void main(String[] args) {
        int arr[] = {1,5,8,9,8,4,6,5,7,7,2}; // length:11
        int copy_arr[] = new int[10];
        System.arraycopy(arr,1,copy_arr,2,arr.length-3);
                        //  数据源 数据源索引  数据承受者  数据目的地索引 拷贝个数
        // -----------------------------------------------------------------------------
//        System.out.println(copy_arr);
        for(int i=0;i<copy_arr.length;i++)
            System.out.print(copy_arr[i]);
        // Java 中数组是一个对象
        // 所以具有很多方法 现用现查即可


    }
}
~~~

### Runtime

**Runtime类主要与Java程序的运行时环境有关。它提供了与程序运行时环境交互的方法，包括 内存管理、系统属性访问、执行外部程序**(shell命令)

![image-20241114170256358](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114170256358.png)

~~~java
package com.Senjay.basicAPI;

import java.io.IOException;

public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
       Runtime r1 = Runtime.getRuntime();
       // 首先先创建Runtime 对象
        // Runtime 方法
        System.out.println(r1.availableProcessors());
        // 打印cpu核心数
        // 有关JVM的内存
        System.out.println(r1.freeMemory() );// 剩余可用
        System.out.println(r1.totalMemory());// 表示JVM当前实际向操作系统申请的内存量
        // 这是实际分配的内存，会随程序需求动态变化
        System.out.println(r1.maxMemory()); // 表示JVM能够使用的最大内存上限 固定值
        System.out.println(r1);
//        r1.exec("notepad");
        // 还可执行 shell指令 // 可用来延时关机类似的功能



    }
}

~~~



## Object

==顶级父类== 只有**无参构造方法**

对于**System.out.println**    ==打印== 会默认调用对象的**toString**方法！！！

**由于子类使用方法时会先找自身有无，没有就像上（父类中）查找**

![47080478a8620c0f8e383cfd86139b2](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/47080478a8620c0f8e383cfd86139b2.jpg)

**方法调用者不可为null**

**equals (obj (要先做非空判断) 	isNull（obj） nonNull（...）**

```tex
默认的equals比较（从Object类继承）

public class Object {
    public boolean equals(Object obj) {
        return (this == obj);
    }
}
默认比较对象的引用（内存地址）
等同于使用 == 运算符
```

==重写==

~~~java
public class Person {
    private String name;
    private int age;
    
    @Override
    public boolean equals(Object obj) {
        // 1. 判断是否是同一个对象（地址相同就是同一个）
        if (this == obj) return true;
        
        // 2. 判断是否为null
        if (obj == null) return false;
        
        // 3. 判断是否是同一个 类
        if (getClass() != obj.getClass()) return false;
         // 隐式调用 
        // 显式调用 this.getClass()
         
        // 4. 类型转换
        Person other = (Person) obj; // 注意要强转
        
        // 5. 比较属性值  （核心）
        return age == other.age && 
               Objects.equals(name, other.name);
    }
}
~~~

**静态方法是属于类而不是特定对象的方法**

# Pattern & Matcher （regex 正则）

~~~java
package com.Senjay.basicAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("adsf");
        Matcher m = p.matcher("adsf45sadsfd");
        while (m.find()) {
//            System.out.println(m.group(0));表示匹配内容 
            // 里头参数 表示捕获组 0 就表示全部捕获 i：第i个捕获组 
            System.out.println(m.start() + " " + m.end());
        }
    }
}

~~~

**拓展：可以去 爬 一 定格式（模板）的资源**





## BigInteger





---



# ==springboot web( p{ springboot } )==

## 浏览器打开程序

访问 `http://localhost:8080` 和在浏览器中打开一个以 `file://` 开头的本地 HTML 文件有几个关键区别，主要涉及到它们的运行环境和交互方式：

### 1. 运行环境

- **`http://localhost:8080`**:
  - **服务器环境**：这是通过一个在本地运行的 Web 服务器（如 Spring Boot 应用、Node.js 服务、Apache、Nginx 等）提供的访问地址。
  - **动态内容**：服务器可以处理动态请求，生成动态内容，处理后端逻辑和数据库交互。每次请求都可以返回不同的内容或数据。
  - **网络协议**：使用 HTTP 协议进行通信，支持各种 HTTP 方法（GET、POST、PUT、DELETE 等），并可以设置 HTTP 头、状态码和其他网络信息。
- **`file://` 协议**:
  - **本地文件系统**：直接从本地磁盘读取文件，不涉及任何服务器。
  - **静态内容**：只能提供静态 HTML、CSS、JS 文件。无法处理服务器端逻辑，也不能直接与数据库交互。
  - **协议限制**：受限于浏览器的安全策略，某些功能（如 AJAX 请求）可能受到限制，因为浏览器通常不允许从 `file://` 协议发起跨域请求。

### 2. 功能和灵活性

- **动态与交互能力**：
  - 使用 `http://localhost:8080`，你可以构建和测试完整的 Web 应用程序，包括表单处理、用户认证、数据存储、业务逻辑等复杂功能。
  - 本地文件访问通常用于查看静态 HTML 页面，仅支持基本的客户端功能（如 JavaScript 动画、简单的用户交互）。
- **开发和调试**：
  - 通过服务器地址运行的应用可以利用开发工具进行更复杂的调试和测试，如查看后端日志、监控 HTTP 请求/响应、模拟不同网络条件等。
  - 本地 HTML 文件调试主要限于前端部分，不能测试后端逻辑。

### 3. 示例应用场景

- **`http://localhost:8080`**：用于开发和测试完整的 Web 应用程序，在本地运行和调试服务器端代码。
- **`file://`**：通常用于查看静态网页文件，如打开一个简单的 HTML 文档，或在没有 Web 服务器的情况下查看本地存储的网页。

---



## project 和 module

### Project

- **概念**：一个 Project 是一个顶级的容器，代表一个完整的开发工作空间。它可以包含多个模块、配置文件、项目设置、库、和其他项目相关的文件。

- **用途**：Project 通常对应一个完整的应用程序或系统。它可以包括所有与该应用程序相关的代码、资源、配置、和依赖。

- 特点

  ：

  - 可以包含多个模块，每个模块可以有不同的配置和依赖。
  - 配置文件如 `*.iml`、`workspace.xml` 保存在 `.idea` 目录中。
  - 适用于管理大型应用程序或多个相关应用程序。

### Module

- **概念**：一个 Module 是 Project 的一个子单元，代表一个自包含的代码集及其配置。它包含源代码、资源文件、模块特定的配置和依赖项。

- **用途**：模块通常用于分解大型项目，将项目的不同部分独立出来，便于管理、编译和测试。例如，一个模块可以是一个 Java 库、一个 Web 应用程序、一个服务等。

- 特点

  ：

  - 每个模块可以有自己的 SDK 设置、依赖项、编译输出路径。
  - 支持不同类型的模块，如 Java 模块、Maven 模块、Gradle 模块等。
  - 有自己的配置文件（如 `module.iml`）来定义模块的设置。

### 区别

1. **层级结构**：Project 是顶级容器，包含多个模块。模块是 Project 的组成部分。
2. **独立性**：模块可以独立编译和运行，有自己的依赖和设置。Project 统一管理所有模块的全局设置和共享资源。
3. **用途**：Project 用于管理整个开发工作空间，而模块用于组织代码结构和配置，以支持更复杂的项目拆分和依赖管理。

---

## 创建

创建springboot项目 

注意springboot现在不支持jdk1.8了

所以要在server URL 中修改为阿里云镜像

![image-20241109170551143](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109170551143.png)

1. **Java 版本**：这是指 Java 平台和语言的版本，例如 **Java 8、Java 11、Java 17** 等。这些版本标志着 Java 语言及其标准库的特性和功能集。这些版本的发布通常伴随新的语言特性、API 增强、性能改进和安全修复。
2. **JDK 版本**：JDK（Java Development Kit）是用来开发和运行 Java **应用程序的工具包**。JDK 包含了 JRE（Java Runtime Environment）、编译器（javac）、调试工具、以及其他开发所需的工具。J**DK 版本号通常与 Java 版本号一致**，例如 JDK 8 对应 Java 8，JDK 11 对应 Java 11。

![image-20241109153915617](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109153915617.png)

1. **项目的基本信息**：
   - **Group**：项目的组织名称，通常是你的公司或团队的域名反转形式（例如 `com.example`）。
   - **Artifact**：项目名称，例如 `demo`。
   - **Type**：选择项目**打包类型**，通常选择 `Maven` 或 `Gradle`。
   - **Language**：选择项目语言，通常选择 `Java`。
   - **Spring Boot Version**：选择合适的 Spring Boot 版本，通常选择最新的稳定版本。

然后选择依赖项

[link to maven ]() 和maven有什么联系

~~~java
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.senjay(group)
│   │   │       └── DemoApplication.java  // 主启动类
│   │   ├── resources/
│   │   │   ├── static/                   // 静态资源文件夹（如HTML、CSS、JS）
│   │   │   ├── templates/                // 模板文件夹（如Thymeleaf、Freemarker）
│   │   │   ├── application.properties    // 应用配置文件
│   │   │   └── application.yml           // 可选的YAML格式的配置文件
│   └── test/
│       ├── java/
│       │   └── com/example/demo/
│       │       └── DemoApplicationTests.java  // 自动生成的测试类
├── .gitignore                             // Git忽略文件
├── HELP.md                                // 帮助文件
├── mvnw                                   // Maven wrapper的执行脚本 (Unix)
├── mvnw.cmd                               // Maven wrapper的执行脚本 (Windows)
├── pom.xml                                // Maven构建文件
└── README.md                              // 项目说明文件
~~~



![image-20241109153427932](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109153427932.png)

自带一个入口类 运行入口类启动项目 



## idea 快捷键

 **更改热键 ： ${}**  

~~~tex
1.查看类或者接口下的继承关系：ctrl + h，查看类下的所有方法：alt + 7，或者ctrl + f12。

2.生成构造、toString等方法：alt + insert  / alt + fn + insert

3.快速交换两行代码：CTRL + shitf + up/down

4.查找所以类或者接口：CTRL + n/双击shift

5.按住alt键+鼠标左键   或者鼠标滚轮  ： 可以整一列操作数据。	

6.CTRL + d:     将上一行语句复制一份到下一行。

7.ctrl + alt + L ：自动格式化代码   CTRL  + L 查找替换

8. 100.fori    : 快速生成 i < 100的 for循环语句。遍历数组用数组名.fori。遍历字符串字符串名.length().fori。forr是倒着遍历。选中内容.sout可以将其输出

9.ctrl + alt + v \ alt + 回车 ：自动生成左边

10.ctrl + p ： 查看函数参数

11. CTRL + alt + m ： 自动抽取方法

12. shift + f6 ：批量更改相同名字的变量、方法、类等。

13.ctrl + alt + t ：对选中内容加while、if 等包裹起来。

14.ctrl + shitf + u : 将选中内容变成大写（upper），再次按下将变回小写。

15.ctrl + alt + 左键 ： 进入方法后退出（回到原来的位置）

16.选择内容 ctrl + r ：然后在输入新的内容可以一键替换

17.ctrl + b 跟进，等同ctrl + 左键     ，ctrl + alt +  左键  ： 回退上一步查看（ctrl + alt + f7也行）

18. Ctrl + shift + R ，搜索所有位置内容，可以进行替换。

19.shift + f6 ,选中文件后按这个就可以进行重命名操作。

20.ctrl + shift + alt + u：选中类后按下，展示继承结构图。

21.shift + f4 打开独立小窗口

22. ${alt+r} rename

23. ${generate} ctrl + , 
24. ${run} alt + p(play)
25. alt + enter 快速纠错

26. alt + 1 打开项目栏界面
27. ctrl + tab 类似alt tab 对文件切换 
28.ctrl + shift +enter 跳过括号以及大括号的书写 while/if 这样需要括号的
29.ctrl + d 复制该行到下一行
30.shift + 滚轮 横向移动	
31.ctrl + g 跳转指定行
32.ctrl + y 删除整行
33.ctrl + shift + a 全局快捷搜索

~~~



# HTTP协议

## 特点： 

![image-20241109172609983](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109172609983.png)

~~~tex
HTTP协议的无状态（Stateless）是指服务器不会保存客户端的任何信息，每次请求都是独立和分离的。让我详细解释一下：

1. 无状态的含义：

- 每次请求都是独立的，互不关联
- 服务器不会记住之前的请求信息
- 每次请求都需要携带必要的信息

2. 无状态的优点：

- 服务器不需要存储客户端信息，减少服务器开销
- 服务器可以更快地处理大量请求
- 更容易实现服务器的水平扩展

3. 无状态带来的挑战：

- 难以实现用户登录状态的保持
- 购物车等功能实现较复杂
- 需要重复传输一些相同的信息

4. 解决方案：

- Cookie：在客户端保存状态信息
- Session：在服务器端保存状态信息
- Token：使用令牌机制维护状态
- LocalStorage：在浏览器本地存储数据
~~~

##  HTTP ——请求数据

![image-20241109173542257](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109173542257.png)

![image-20241109173551884](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109173551884.png)

![image-20241109173905741](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109173905741.png)

## HTTP ——响应数据

![image-20241109174613471](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109174613471.png)

![image-20241109174616983](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109174616983.png)

![image-20241109174631636](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109174631636.png)

**就是资源放在服务器的不同位置上或是另一台服务器上 所以要重定向找到资源**

## 响应状态码



![image-20241109175006354](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109175006354.png)

# JDBC (了解即可)

| ------->[link to Mybatis in SSM(include Springboot)]()<----------- |
| ------------------------------------------------------------ |

![image-20241122212501773](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122212501773.png)

## JDBC 架构



1. 核心组件

a) JDBC API（java.sql包）

- Connection：数据库连接
- Statement：SQL语句执行
- ResultSet：结果集
- DriverManager：驱动管理

b) JDBC驱动管理器

- 管理数据库驱动程序
- 建立数据库连接
- 处理多个驱动程序

c) JDBC驱动程序

- 实现JDBC接口
- ==与具体数据库交互==
- 处理数据转换

包含各种数据的驱动程序 e.g. mysql 驱动程序

**datagirp就是java程序编写的所以在创建mysql时要安装jdbc （mysql）驱动程序 负责充当java应用程序和数据库协议之间的翻译桥梁！**



## JDBC 组件（API）



![image-20241122212510122](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122212510122.png)

![image-20241122212517500](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122212517500.png)



## CRUD

**（其余的增删改同理）** 代码了解即可

~~~java
package net.senjay;

import java.sql.*;

public class JDBCdemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. 加载驱动 jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立连接
            String url = "jdbc:mysql://localhost:3306/test"; // sql地址
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);// 连接对象

            // 3. 创建PreparedStatement
            String sql = "SELECT * FROM test1 WHERE id = ?"; // 创建sql语句
            pstmt = conn.prepareStatement(sql);

            // 4. 设置参数并执行
            pstmt.setInt(1, 1);
            rs = pstmt.executeQuery(); // 使用结果集存储


            // 5. 处理结果集
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
                // 从结果集中提取参数
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭资源
            // 防止空指针异常
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
~~~





 # Tomcat & Servlet



**version complicable  extension** 版本兼容异常  （tomcat11 与jdk1.8 不兼容！！！）<font color=red size=6>(VC)</font> ****

**可推广：可延申思考** ==Extendable==  <font color=red size=6>(E+)</font>

**指涉判空！！：** Referrance  Empty **<font color=red size=6>(RE)</font>**

安装tomcat 配置环境变量 JAVA_HOME value=jdk目录

如何找jdk --> where java 命令

**打开bin/startup.bat 启动tomcat服务器 或者在idea中run启动**

## Tomcat 的目录结构（可推广）

**Extendable: 编译器中的功能分类树**





![image-20241123150104197](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123150104197.png)





## JSP 和html

~~~ceylon
JSP和HTML有以下主要区别：

动态与静态
JSP是动态网页，可以包含Java代码，能够动态生成内容
HTML是静态网页，内容是固定的，不能动态生成
    
执行方式
JSP需要在服务器端执行，经过编译后转换为Servlet
HTML直接在浏览器中解释执行
    
功能特点
JSP可以访问数据库、执行业务逻辑
JSP可以使用内置对象(request、response等)
HTML只能显示静态内容和超链接
    
语法结构
JSP包含Java代码、JSP标签、HTML标签
HTML只包含HTML标签

    
运行环境
JSP需要Web服务器支持(如Tomcat)
HTML只需要浏览器即可运行

   
    
    
应用场景
JSP适合开发动态网站，需要与数据库交互的场景
HTML适合制作静态网页，展示固定内容
性能
JSP需要服务器解析，性能相对较低
HTML直接显示，性能较好
维护性
JSP可以实现业务逻辑与显示的分离，便于维护
HTML将内容和显示混在一起，维护相对困难
~~~

请求（request）：客户端发送数据给服务端

响应（response）：服务端返回数据给客户端

![image-20241123135208955](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123135208955.png)

~~~css
Tomcat服务器：

定义：
Tomcat是一个开源的Java Servlet容器和Web服务器，由Apache软件基金会开发。
软件性质：
Tomcat是一个软件应用程序，不是物理硬件。
主要用途：
运行Java Servlets和JavaServer Pages (JSP)  也就是一个<--容器-->
作为Web应用服务器，处理HTTP请求
托管Java Web应用程序
使用场景：
开发和测试Java Web应用
部署小到中型的Java Web项目
作为轻量级的应用服务器
特点：
轻量级，易于配置和使用
支持Java EE规范的一个子集
可以独立运行，也可以集成到其他应用服务器中
~~~

**客户端axios可与服务端servelet & JSP 协作**

## 基本写法

~~~java
    @WebServlet("/sr1") //        一定不要忘记写 '/'
    public class Servlet01 extends HttpServlet {
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("hello Lunafreya !");
            Writer writer = response.getWriter();
            writer.write("hello Lunafreya !");
        }
    }

~~~

了解即可：

![image-20241123151418786](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123151418786.png)

**URL：http://localhost:8080/myArtifact1/sr1 本机服务器 项目 项目下的资源**

Tomcat服务器下可有多个项目

## Servlet 生命周期



![image-20241123152020822](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123152020822.png)

**销毁：服务器关闭或应用程序停止**

**service 方法可以执行多次**

![image-20241123152222238](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123152222238.png)

## HttpServletRequest对象

![image-20241123153102809](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123153102809.png)、

### 常用方法

~~~java
package com.senjay.vc_javaee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/sr2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURI()); // /myArtifact1/sr2
        System.out.println(request.getRequestURL()); // http://localhost:8080/myArtifact1/sr2
        System.out.println(request.getQueryString()); // name=Senjay
        System.out.println(request.getMethod()); // GET
        System.out.println(request.getProtocol()); // HTTP/1.1
        System.out.println(request.getContextPath()); //获取webapp名字 // /myArtifact1
    }
}

~~~

### 获取请求参数

~~~java

@WebServlet("/sr3")
public class servlet3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("name"));
        String [] grades = request.getParameterValues("grade");// 使用grades数组存贮 键为grade的多个值
        System.out.println(Arrays.toString(grades));
        // 注意如果拿不到数组的话访问（如:遍历）数组就会空指针异常！！！
        // 指涉判空！！

    }
}
~~~

![image-20241123160145280](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123160145280.png)

### 请求乱码（Messey code）问题

编码格式 ≠ 解码格式 -->==“乱码”==

![image-20241123160825056](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123160825056.png)

![image-20241123160827925](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123160827925.png)

![image-20241123160830293](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123160830293.png)





## 请求转发和request作用域

![image-20241123161259247](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123161259247.png)

![image-20241123171400601](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123171400601.png)

![image-20241123171416989](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123171416989.png)



![image-20241123180713283](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123180713283.png)

### 域对象



**s05跳转s06**

![image-20241123181029124](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123181029124.png)

![image-20241123180934795](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123180934795.png)





| form 的 action 属性                                          | 表单元素的 name 属性                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `action` 属性定义了表单提交时,数据应该发送到哪个URL进行处理。它指定了处理表单数据的服务器脚本的路径。 | `name` 属性为表单元素提供一个名称,这个名称会在表单提交时与该元素的值一起发送到服务器。它用于在服务器端识别不同的表单数据。 |

~~~java
@WebServlet("/test1")
public class MessyCode extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("name"));
        // get 不会messycode
        // 试试post
        System.out.println(request.getParameter("age")); // 表单提交本质和get里请求行一样 只是和数据的敏感性以及数据的数量有关
        System.out.println(request.getParameter("grade"));

    }
}
~~~

~~~html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="test1" method="post">
    <input type="text" name="age">
    <br>
    <input type="text" name="grade">
    <button>点击提交</button>
</form>
</body>
</html>
~~~



## HttpServletReponse对象

`HttpServletResponse` 是 Java Servlet API 中的一个接口，专门用于处理 HTTP 响应。它提供了一系列方法，使开发人员能够控制响应头、响应状态以及响应体的内容。以下是关于 `HttpServletResponse` 的详细解释，包括它的作用、主要内容和常用方法。

### 1. 定义和作用

- **定义**：`HttpServletResponse` 是 `javax.servlet.http` 包中的一个接口，继承自 `ServletResponse` 接口。它提供了对 HTTP 响应的访问和操作。
- **作用**：`HttpServletResponse` 的主要作用是向客户端（如浏览器）发送响应数据。这包括设置响应状态码、内容类型、响应头，以及将响应体中的数据写回客户端。

### 2. 主要内容

`HttpServletResponse` 的内容可以分为几个部分：

1. **响应状态**：
   - HTTP 响应状态码，指示请求的结果。例如，200 表示成功，404 表示未找到，500 表示服务器错误等。
2. **响应头**：
   - 包含关于响应的信息，例如内容类型（`Content-Type`）、内容长度（`Content-Length`）、缓存控制（`Cache-Control`）、设置 Cookie 等。
   - ==Content-Type: text/html; charset=UTF-8==
3. **响应体**：
   - 实际返回给客户端的数据内容，例如 HTML 文档、JSON 数据、图像、文件下载等。

### 3. 常用方法

`HttpServletResponse` 提供了多种方法来设置响应的各个部分。以下是一些常用方法的说明：

#### 1. 设置响应状态

- **`setStatus(int sc)`**：设置响应状态码。

  

  ```java
  response.setStatus(HttpServletResponse.SC_OK); // 设置状态码为 200
  ```

- **`sendError(int sc, String msg)`**：发送错误响应。

  

  ```java
  response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found"); // 设置状态码为 404
  ```

#### 2. 设置响应头

- **`setHeader(String name, String value)`**：设置指定名称的响应头。

  

  ```java
  response.setHeader("Cache-Control", "no-cache"); // 设置缓存控制
  ```

- **`addHeader(String name, String value)`**：添加一个响应头。

  

  ```java
  response.addHeader("Set-Cookie", "sessionId=abc123; Path=/; HttpOnly");
  ```

- **`setContentType(String type)`**：设置响应的内容类型（MIME 类型）。

  

  ```java
  response.setContentType("text/html; charset=UTF-8"); // 设置内容类型为 HTML
  ```

#### 3. 获取输出流

- **`getWriter()`**：获取一个 `PrintWriter` 对象，用于向响应体写入字符数据。

  

  ```java
  PrintWriter out = response.getWriter();
  out.println("<html><body><h1>Hello, World!</h1></body></html>");
  ```

- **`getOutputStream()`**：获取一个 `ServletOutputStream` 对象，用于向响应体写入二进制数据。

  

  ```java
  ServletOutputStream out = response.getOutputStream();
  out.write(...); // 写入二进制数据
  ```

![image-20241123220948364](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123220948364.png)

| 特性         | `getWriter()`                                                | `getOutputStream()`                                          |
| :----------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| 类型         | 返回 `PrintWriter`，用于字符输出                             | 返回 `ServletOutputStream`，用于二进制输出                   |
| 用途         | 适用于输出文本（HTML、JSON、XML等）                          | 适用于输出二进制数据（图像、文件等）                         |
| 字符编码     | 需要设置字符编码（如 UTF-8）                                 | 不涉及字符编码                                               |
| 不能混合使用 | 如果调用了 `getWriter()`，则不能再调用 `getOutputStream()`，反之亦然 | 如果调用了 `getOutputStream()`，则不能再调用 `getWriter()`，反之亦然 |

**与io流网络编程流类似** 结合复习

**这里是通过HttpservletResponse 对象的方法获取字节输出流或者字符输出流**

---

==联系文件/网络IO流==

| 特性         | **PrintWriter**                          | **ServletOutputStream**              |
| :----------- | :--------------------------------------- | :----------------------------------- |
| **数据类型** | 主要用于字符流（文本数据）               | 主要用于二进制流（如图像、文件等）   |
| **适用场景** | 生成 HTML、JSON、XML 和纯文本响应        | 发送压缩数据、图像、PDF 等二进制内容 |
| **输出方式** | 使用 `println()`、`print()` 方法输出字符 | 使用 `write()` 方法输出字节          |
| **性能**     | 对于文本数据，性能较好，但不适合大文件   | 对于大文件和流式传输，性能更佳       |
| **互斥性**   | 不能与 `getOutputStream()` 一起使用      | 不能与 `getWriter()` 一起使用        |

~~~java
@WebServlet("/wr1")
public class WriterDemo1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.write("Hello world");
        // 不能同时使用
        ServletOutputStream sos = response.getOutputStream();
        sos.write("<h1 style=\"color:red;\">Hello World</h1>".getBytes());

    }
~~~





## 响应乱码问题

### MIME类型

| MIME 类型                           | 描述                           |
| :---------------------------------- | :----------------------------- |
| `text/plain`                        | 普通文本文件                   |
| `text/html`                         | HTML 文件                      |
| `text/css`                          | CSS 文件                       |
| `text/javascript`                   | JavaScript 文件                |
| `application/json`                  | JSON 数据                      |
| `application/xml`                   | XML 数据                       |
| `application/pdf`                   | PDF 文件                       |
| `application/octet-stream`          | 二进制数据（一般用于下载文件） |
| `image/jpeg`                        | JPEG 图像                      |
| `image/png`                         | PNG 图像                       |
| `image/gif`                         | GIF 图像                       |
| `audio/mpeg`                        | MPEG 音频                      |
| `video/mp4`                         | MP4 视频                       |
| `application/zip`                   | ZIP 压缩文件                   |
| `application/x-www-form-urlencoded` | 表单数据编码类型               |





![image-20241123221524023](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123221524023.png)

==**响应头发送信息告知这是一个什么样的数据**==

**encode 编码**



## 重定向

![image-20241123231650371](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123231650371.png)

**也可重定向到其他servlet去**

## 请求转发和重定向的区别

![image-20241123231926410](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123231926410.png)





![image-20241123223731618](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123223731618.png)

## cookie

![image-20241123232412728](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241123232412728.png)

### HTTP 的无状态性与 Cookie 的关系





| 无状态性：                                                   | Cookie 的作用：                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 由于 HTTP 是无状态的，服务器在处理每个请求时并不知道前一个请求的任何信息。这种设计使得 HTTP 协议更简单，但也导致了需要一种机制来管理用户会话和状态。 | Cookie 用于克服 HTTP 的无状态性。通过在客户端存储一些信息（如用户 ID、会话 ID 等），服务器可以在后续请求中识别用户并维持会话状态。每当客户端发起请求时，浏览器会自动将相关的 Cookie 发送给服务器，从而使服务器能够识别用户。 |







## session







## Token

[cookie 、sesson、Token 区别](https://www.bilibili.com/video/BV1ob4y1Y7Ep/?spm_id_from=333.337.search-card.all.click&vd_source=9570fc9c9829e70449f020506364bf36)







## 文件上传和下载







