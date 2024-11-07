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

无论子类是否显式调用父类的构造方法，父类的构造方法总会在子类的构造方法中执行。这是因为 Java 需要确保在创建子类对象时，父类的部分能够被正确地初始化。

- 如果子类的构造方法没有显式调用父类的构造方法，那么 Java 编译器会**隐式**插入一个对父类**无参构造方法**的调用（`super()`）。
- 如果父类没有无参的构造方法，而子类没有显式调用父类的有参构造方法，那么编译会失败。

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

# springboot web( p{ springboot } )





 

