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

alpe：**举例子都分别有什么用
并且给我 使用这个操作和不使用的区别 让我体会到这个操作的必要性**

### 实际类型 & 引用类型 

### 实际类型（Actual Type）：
实际类型是指对象在内存中真正的类型。
它是在运行时确定的，是对象实例化时使用的具体类。
<font size=10 color=red>实际类型</font>决定了对象可以使用哪些<font size=10 color=red>方法</font>

### 引用类型（Reference Type）：
引用类型是变量声明时使用的类型。
它是在编译时确定的，可以是实际类型的本身，也可以是其父类或实现的接口。
<font size=10 color=red>引用类型</font>决定了通过该引用可以访问哪些<font size=10 color=red>成员变量</font>

parent A = new sons()

parent B = new daughters()

**引用类型 			实际类型**

**多态本质：**

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

==学会查看方法参数列表不要死记硬背==



#### 发送数据：

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

        os.write(conversation.getBytes());
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
            // 如果读取成功，它返回一个 0 到 255 之间的整数，表示读取的字节值。
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



#### 通信流

![image-20241111192316713](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192316713.png)

#### 三次握手&四次挥手

![image-20241111192252760](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192252760.png)

![image-20241111192256195](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192256195.png)



# 异常

## 异常类型

### 编译时异常（受检异常）

编译时异常是在编译时由编译器强制检查的异常。程序员必须显式地处理这些异常，否则程序将无法编译。

### 运行时异常（非受检异常）

 运行时异常是在程序运行过程中可能出现的异常，编译器不强制要求程序员进行处理。这些异常通常是由程序中的逻辑错误引起的

### 错误（error）

- 错误是指应用程序无法处理的严重问题。这些通常是由运行时环境导致的，程序员不应试图捕获这些错误。：错误通常表示 JVM 的内部错误或资源耗尽，例如内存不足、栈溢出等。

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







# I/O 流





# 多线程



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

7.ctrl + alt + L ：自动格式化代码

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



 

