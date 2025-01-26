<center> <font size=10> JavaSE  & Frame introduction </font> </center>

# Java 语言特性

``Java 实现了“Write Once, Run Anywhere”（一次编写，随处运行）的理念。源代码编译成字节码后，字节码可以在任何安装了 Java 虚拟机（JVM）的设备上运行，而不需要修改代码`  --> 意味着 java 的平台无关姓/可移植性

## JVM （Java Virtual Machine）

- **作用**：JVM 是 Java 程序的运行环境，负责将编译后的 **Java 字节码（.class 文件）** 转换为机器码，并在特定平台上执行。

- 特点

  ：

  - JVM 是平台相关的（不同操作系统有不同的 JVM 实现）。
  - 提供跨平台支持：通过运行字节码实现“Write Once, Run Anywhere”（一次编写，到处运行）。
  - 提供垃圾回收（GC）机制、内存管理、多线程支持等。

## JRE （Java Runtime Environment）

- 组成

  ：

  - JVM：用于执行 Java 字节码。
  - 核心类库（如 `java.lang`、`java.util`、`java.io` 等）：为 Java 程序提供基础功能。
  - 运行时工具（如垃圾回收器、内存管理器等）。

- 特点

  ：

  - JRE 只 ==提供运行 Java 程序的环境==，**不能用于开发** Java 程序。



## JDK （Java Development Kit）

- **作用**：JDK 是 Java 程序的开发工具包，用于 **	**。

- 组成

  ：

  - **JRE**：JDK 包含了一个完整的 JRE 环境，因此可以运行 Java 程序。
  - **开发工具**：如 Java 编译器（`javac`）、调试工具（`jdb`）、打包工具（`jar`）等。
  - **附加库**：提供额外的库和 API（例如 JavaFX、工具类等），支持 Java 应用开发。

- 特点

  ：

  - JDK 是 Java 开发者使用的工具集，**既可以开发 Java 程序，也可以运行 Java 程序**。



![image-20241225211902574](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241225211902574.png)

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

~~~ceylon
在 Java 中，编译、解释和运行分别由不同的组件和工具完成：

1. **编译（Compilation）**：
   - 编译是将 Java 源代码（.java 文件）转换为字节码（.class 文件）的过程。这个过程由 Java 编译器（javac）完成。编译器检查代码语法和语义是否符合 Java 语言规范，并将源代码转换为可以在 Java 虚拟机（JVM）上运行的中间代码（字节码）。

2. **解释（Interpretation）**：
   - Java 程序的解释是指 Java 虚拟机（JVM）在运行时解释字节码并将其转换为特定的机器代码，以便计算机能够执行。JVM 负责将字节码翻译成本地机器代码，这种解释过程是在程序运行时动态进行的。

3. **运行（Execution）**：
   - 运行阶段是指 Java 虚拟机（JVM）执行已经编译和解释后的字节码。JVM 负责管理内存、线程和其他运行时的资源，以确保 Java 程序按照预期执行。

因此，编译的任务是由 Java 编译器完成，解释和运行的任务则由 Java 虚拟机（JVM）负责。  
~~~



# 执行方式：



### 1. **C 语言的执行方式**

**C 语言** 是一种 **编译型语言**，其执行方式与 Java 和 Python 都不同：

- 静态编译

  ：

  - C 代码在执行之前，会通过编译器（如 `gcc`）编译成 **机器码**，即特定平台的二进制可执行文件。这个过程是一次性的。
  - 编译后的机器码可以直接运行在操作系统上，不需要任何解释或虚拟机的支持。
  - 由于编译生成的文件是与操作系统和硬件密切相关的，C 是一种 **高效** 的语言，执行速度非常快。

**执行流程**：

- 编写 C 代码（`.c` 文件）。
- 使用编译器（如 `gcc`）将 `.c` 文件编译成目标系统的机器码（`.exe`，`.out` 等可执行文件）。
- 直接在操作系统上运行生成的可执行文件，整个程序已经是机器码，不需要解释器。

**总结**：C 语言经过编译后，生成的机器码直接在硬件上运行，执行速度非常快。这是一个典型的 **一次编译，直接运行** 的过程。

### 2. **Python 的执行方式**

**Python** 是一种 **解释型语言**，其执行方式与 C 和 Java 都不同：

- 逐行解释执行

  ：

  - Python 代码在运行时由 **解释器**（如 CPython）逐行解释执行，不需要事先编译成机器码。
  - Python 是 **动态类型语言**，因此在运行时才会确定类型和做相应的检查。
  - 这种逐行解释的方式使得开发和调试更加灵活，但是运行速度相对较慢，因为每次执行时都需要通过解释器逐行解析代码并执行。

**执行流程**：

- 编写 Python 代码（`.py` 文件）。
- 运行 Python 解释器（如 `python`），解释器逐行解释执行代码。
- Python 解释器会将 `.py` 文件编译为 **字节码**（`.pyc` 文件）并存储在缓存中。
- **字节码** 会被 Python 虚拟机解释执行，但仍然是逐行解释的方式，无即时编译优化。

**总结**：Python 是逐行解释执行的语言，每次运行代码时都通过解释器逐行执行，灵活性很高，但性能相对较低。Python 也有一些优化（如字节码缓存），但其核心仍然是解释执行。

### == 3. **Java 的执行方式 == **

**Java** 是一种结合了 **编译型** 和 **解释型** 的语言，使用的是 **字节码解释 + JIT 编译** 的混合模式：

- **编译成字节码**：
  - Java 代码首先通过 `javac` 编译器编译成与平台无关的 **字节码**（`.class` 文件）。
  - 字节码不是直接在硬件上运行的，而是由 ==Java 虚拟机（JVM）解释执行。==
- **解释执行 + JIT 编译**：
  - 当 Java 程序运行时，==JVM 会先 **解释执行** 字节码，将字节码逐行解释为机器码并执行==。
  - 为了提高性能，JVM 会通过 **即时编译（JIT）\**的方式，将一些\** 热点字节码**（频繁执行的代码）编译为 **本地机器码**，下次再遇到这些代码时就直接用编译好的机器码执行，不再解释。
- **JIT 编译的优势** 在于：程序运行时可以动态优化，将经常执行的代码编译为机器码，从而提升性能。

#### 注意点

 **Java 源程序文件的名字与 main()方法所在的类名相同，且扩展名必须是 .java**

**= 编译: javac（complied） HelloJava.java, 生成 HelloJava.class （字节码文件）**

**= 运行: java HelloJava**

# 面向对象三大特性



## 访问权限 ： pubilc 、protected、default 、 private

![说明: a](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/clip_image002.jpg)



```java
// private 只是说明子类无法访问到父类的不代表自己不能用父类具有的属性
```

## 数据类型

​	![说明: a](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/clip_image002.gif)

**java 的 char 是两个字节！！！**

### 数据类型的转化



![image-20250103132405087](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250103132405087.png)

~~~java

public class Main{
    public static void main(String [] args)
    {
        int x = 5/2.0; // 错误的 不能使用 int
        System.out.print(x);
    }
}



public class Main{
    public static void main(String [] args)
    {
        int a = 5;
        byte x = a; // 错误的  只能从弱到强不能从强到弱 
        System.out.print(x);
    }
}
~~~



![image-20241225213849536](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241225213849536.png)

key：数据空间和引用空间（堆栈） 自动初始化！！！

特别地，对于 String (**引用数据类型**) 

直接赋值的字符串会进入 ==字符串常量池==



### Final 关键字

| **修饰目标** | **特性**                       |
| :----------- | :----------------------------- |
| **变量**     | 变量值不可变，必须初始化。     |
| **方法**     | 方法不能被子类重写。           |
| **类**       | 类不能被继承。                 |
| **参数**     | 参数值在方法内部不可变。       |
| **引用类型** | 引用不可变，但对象内容可以修改 |

接口成员变量和成员函数默认的修饰符：

变量： public static final 

函数： public abstract 

#  <font size= 6 color =red > 函数传参问题 </font>  

![image-20250103140715989](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250103140715989.png)

~~~ceylon
解释和理解：
实参和形参指向同一个对象： 在 main 方法中，创建了一个 Integer 对象 number，其值为 10。当调用 modifyInteger(number) 时，实参 num 和 number 都指向同一个 Integer 对象，即值为 10 的对象。

方法内部操作： 在 modifyInteger 方法内部，执行了 num = num + 100; 这个操作实际上创建了一个新的 Integer 对象，其值为 110。然后将这个新的对象赋给了 num。在这里，num 的重新赋值只是修改了 num 这个局部变量的引用，使它指向了新创建的 Integer 对象。

原始对象不可变性： Integer 类是不可变的，意味着一旦创建了 Integer 对象，它的值就不可更改。所以，即使在 modifyInteger 方法内部创建了新的 Integer 对象，也不会影响到原始的 number 对象。在 Java 中，所有的基本包装类（如 Integer、Double 等）都是不可变的，这意味着你不能修改它们的值，只能通过创建新的对象来表达新的值。

传递方式是按值传递： 尽管实参和形参指向同一个对象，但 Java 中的参数传递是按值传递的。这意味着传递给方法的是实际参数的副本，而不是参数本身。在方法内部对参数的任何修改都只是修改了副本，不会影响到原始的调用者变量。
~~~



### 解释和理解：

1. **实参和形参指向同一个对象：** 在 `main` 方法中，创建了一个 `Integer` 对象 `number`，其值为 `10`。当调用 `modifyInteger(number)` 时，实参 `num` 和 `number` 都指向同一个 `Integer` 对象，即值为 `10` 的对象。
2. **方法内部操作：** 在 `modifyInteger` 方法内部，执行了 `num = num + 100;` 这个操作实际上创建了一个新的 `Integer` 对象，其值为 `110`。然后将这个新的对象赋给了 `num`。在这里，`num` 的重新赋值只是修改了 `num` 这个局部变量的引用，使它指向了新创建的 `Integer` 对象。
3. **原始对象不可变性：** `Integer` 类是不可变的，意味着一旦创建了 `Integer` 对象，它的值就不可更改。所以，即使在 `modifyInteger` 方法内部创建了新的 `Integer` 对象，也不会影响到原始的 `number` 对象。在 Java 中，所有的基本包装类（如 `Integer`、`Double` 等）都是不可变的，这意味着你不能修改它们的值，只能通过创建新的对象来表达新的值。
4. **传递方式是按值传递：** 尽管实参和形参指向同一个对象，但 Java 中的参数传递是按值传递的。这意味着传递给方法的是实际参数的副本，而不是参数本身。在方法内部对参数的任何修改都只是修改了副本，不会影响到原始的调用者变量。

---



**将数据用数组返回数组接收赋值即可**

**只能使用数组其余对象或是基本类型都只是创建了一个副本而已**

~~~java
private static StringBuffer [] swap(StringBuffer a, StringBuffer b) {
    StringBuffer temp = new StringBuffer(a);
    a = b;
    b = temp;
    return new StringBuffer [] { a, b };
}

public static void main(String [] args) {
    StringBuffer a = new StringBuffer("senjay");
    StringBuffer b = new StringBuffer("keiko_takahashi");
    StringBuffer [] result = swap(a, b);
    a = result [0];
    b = result [1];
    System.out.println(a + " " + b); // 输出结果为 keiko_takahashi senjay
}

~~~



### 注意

###   **对于引用数据类型，实参和形参指向同一个对象**。

当我们将引用类型的变量作为参数传递给方法时，实际上传递的是对象的引用（内存地址），而不是对象本身的副本。这意味着方法内的形参和方法外的实参指向内存中的同一个对象。因此，在方法内对这个对象进行的修改会影响到方法外的对象。

---



**不可变性：Integer 是不可变的，这意味着一旦创建，其值就不能被改变。**
**新对象创建：任何修改 Integer 值的操作都会创建一个新的 Integer 对象。**
**引用更新：在方法内部，当我们执行 n = n + 5 时，我们实际上是将 n 指向一个新的 Integer 对象，而不是修改原有对象。**
**原对象不变：原始的 Integer 对象保持不变，主方法中的引用 num 仍然指向这个未被修改的对象。**

--->

！ 不仅适用于 Integer，也适用于其他包装类（如 Boolean, Character, Float 等）和 String 类，因为它们都是不可变（immutable ）的

---

## 方法重载

**重载方法识别标志:** **参数的个数、类型、数据类型的排列顺序 (** **也就是只和参数列表有关)**

**返回值（返回类型）不能做为识别的标志。构造方法重载**





## Static 静态变量和静态方法

### 静态导入

==常规导入== 保持了代码的清晰度，明确显示了成员来自哪个类。

~~~java
  import java.lang.Math;
~~~



=== 静态导入== 可以在适当的情况下使代码更 ==简洁==

```java
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
```



**1.代码长度略微减少**

在频繁使用某个类的静态成员时，静态导入可以减少重复的类名：



```java
// 不使用静态导入
double result = Math.sin(Math.PI / 2) + Math.cos(Math.PI / 4);

// 使用静态导入
import static java.lang.Math.*;
double result = sin(PI / 2) + cos(PI / 4);
```

**2.特定领域的代码可读性**

在某些特定领域，如数学计算或物理模拟，去掉类名前缀可能使公式看起来更接近其数学表达：



```java
// 使用静态导入
import static java.lang.Math.*;
double distance = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
```



### 作用 & 注意点

- 静态变量和方法属于类，而不是实例。
- 可以 **通过类名直接访问，无需创建对象。**
- 静态方法只能直接访问静态成员，不能直接访问非静态成员。
- **静态成员在内存中只有一份，被所有实例共享。**
- **适用于不依赖对象状态、需要被(==类的实例也就是对象==)共享的场景。**

！：

静态方法里可以使用类的其他静态成员，但是不能直接使用非静态成员，如要使用非静态成员可以通过对象名引用(就是创建一个对象实例然后引用成员（**成员变量** **、** **成员方法**）)， 静态方法里可以定义自己的局部变量但是不能定义 static 变量

### **静态方法的特点**

1. **属于类而不是对象**：
   - 静态方法是属于类的，而不是某个具体对象。换句话说，静态方法可以通过类名直接调用，而不需要创建类的实例。
   - 例如，`ClassName.methodName()`，无需实例化对象即可调用。
2. **不能访问实例变量和实例方法**：
   - 静态方法不能直接访问实例（非静态）变量和实例方法，因为静态方法是类级别的，而实例变量和实例方法是对象级别的。
   - 如果要在静态方法中访问实例变量或方法，必须通过对象引用来访问。
3. **可以访问静态变量和静态方法**：
   - 静态方法可以直接访问类中的静态变量和静态方法。因为静态成员属于类本身，在类加载时即会被分配内存，所以静态方法可以直接访问。
4. **没有 `this` 引用**：
   - 静态方法没有 `this` 引用，因为 `this` 代表的是当前对象，而静态方法不依赖于对象实例，因此没有 `this` 引用。
5. **可以通过类名调用**：
   - 静态方法通常通过类名来调用，不需要创建对象。你也可以通过对象引用来调用静态方法，但这种做法不推荐，因为这容易给人误导，可能会让人以为该方法是对象的方法。
6. **不能被重写（Override）**：
   - 静态方法不能被重写。尽管子类可以继承父类的静态方法，但子类无法对父类的静态方法进行重写（Override）。静态方法是与类关联的，不是与对象关联的，所以它遵循编译时绑定（静态绑定）。
7. **静态方法可以被重载（Overload）**：
   - 与实例方法一样，静态方法也可以被重载。即可以在同一个类中定义多个相同名字但参数不同的静态方法。

##  封装

**封装**（Encapsulation）是面向对象编程（OOP）中的基本概念之一。封装的核心思想是将对象的 **状态（字段/属性）\**和\** 行为（方法）\**封装在一个类中，并通过提供\** 访问控制** 来限制外部对这些数据的直接访问和修改，从而实现数据的隐藏和保护。

在封装中，类的内部实现细节对外部是不可见的，外部只能通过暴露的 **公共接口**（即公开的方法）与对象进行交互，而不能直接操作对象的内部数据。这样做的目的是为了保证对象的完整性和安全性，避免外部程序随意修改对象的内部状态。

### 封装的关键点

1. **属性私有化**：类的属性（成员变量）通常使用 `private` 关键字进行修饰，使其在类的外部不可直接访问。
2. **提供公共访问方法**：通过 `public` 关键字提供公共的 **getter** 和 **setter** 方法，允许外部代码通过这些方法安全地访问和修改类的私有属性。
3. **隐藏实现细节**：类的内部实现细节对外部不可见，外部只需要关心接口和功能，而不需要了解具体是如何实现的。
4. **控制访问权限**：通过访问控制符（如 `private`、`protected`、`public`）控制类成员的可见性，确保类的内部数据不会被外部误修改或滥用。

### 封装的作用

1. **保护数据的完整性**：通过将属性私有化，防止外部随意修改类的内部状态，确保数据的正确性和一致性。例如，某个属性应该是正数，可以在 `setter` 方法中加入校验逻辑，避免不合法的数据被赋值。
2. **隐藏实现细节，降低复杂度**：封装的另一个重要作用是 **隐藏实现细节**。外部用户不需要了解类的内部实现，只需要通过公开的方法来使用类的功能。这降低了系统的复杂度，提高了代码的可维护性。
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

## 接口成员默认修饰符



![image-20241229173649537](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229173649537.png)

![image-20241229173738080](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229173738080.png)

## 内部类 与 匿名内部类  

<span style="color:#FF3333;">匿名内部类通常用于以下场景：</span>

1. **实现接口**：快速实现一个接口，并创建其实例。
2. **继承抽象类**：快速继承一个抽象类，并创建其实例。
3. **覆盖具体类的方法**：快速创建一个具体类的子类，并覆盖其方法。

![image-20241229173856876](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229173856876.png)



![image-20241229173952983](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229173952983.png)

### 匿名内部类和 事件处理 结合

![image-20241229213230085](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229213230085.png)

![image-20241229213236569](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229213236569.png)



**这个是使用了匿名内部类**

```java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventExample {
public static void main(String[] args) {
    // 创建 JFrame 作为窗口
    JFrame frame = new JFrame("事件源、事件对象、事件监听器示例");

    // 创建按钮（事件源）
    JButton button = new JButton("点击我");

    // 事件源注册事件监听器（事件监听器） --->就是实现接口
    button.addActionListener(new ActionListener() {
        // 实现 actionPerformed 方法，处理事件
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取事件对象（事件对象）
            Object source = e.getSource();  // 获取事件源
            System.out.println("按钮被点击，事件源是：" + source);
            System.out.println("事件的源是： " + source.getClass().getName());
        }
    });

    // 设置窗口的布局和行为
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLayout(null);

    // 设置按钮的位置和大小
    button.setBounds(100, 70, 100, 40);

    // 将按钮添加到窗口
    frame.add(button);

    // 显示窗口
    frame.setVisible(true);
}
```
![image-20241230010339702](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241230010339702.png)

---

**联系 tank war** 



**1.给事件源添加事件监听器（对象）**

**2.创建一个类 实现这个监听器接口 重写方法 书写事件触发后的逻辑 这个类的实例化就是上面的对象**

---





特点： 无需创建类的声明只需要创建实现这个接口的对象（实现方法） 然后 ==**赋值** 到这个 **接口**== 上去

~~~java
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String [] args) {
        // 使用匿名内部类实现接口
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello, world!");
            }
        };
        
        greeting.sayHello();  // 输出 Hello, world!
    }
}

~~~

## 枚举类

**枚举常量通常使用全大写字母并用下划线分隔（如果有多个单词）**



~~~java
enum Day {
    MONDAY("Start of the week"),
    TUESDAY("Second day of the week"),
    WEDNESDAY("Mid-week"),
    THURSDAY("Almost there"),
    FRIDAY("Last day of work"),
    SATURDAY("Weekend"),
    SUNDAY("Rest day");

    private final String description;

    // 枚举的构造函数
    Day(String description) {
        this.description = description;
    }

    // 获取枚举常量的描述
    public String getDescription() {
        return description;
    }
}

public class Main {
    public static void main(String [] args) {
        for (Day day : Day.values()) {
            System.out.println(day + ": " + day.getDescription());
        }
        // sout(Day.MONDAY  + " " + Day.MONADY.getDescription())
    }
}

~~~



## 继承 & 多态

![image-20241120155553520](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120155553520.png)



## 重载（overload）和重写（override）的区别

==方法重载== 是指在 ==同一个类== 中，方法名相同但参数不同（参数类型、数量、顺序不同），与返回类型无关。重载发生在同一个类中，允许我们使用相同的方法名处理 ==不同类型== 或 ==不同数量== 的参数。 ==**只和参数列表有关**==

==方法重写== 是指在 **子类** 中定义一个与 **父类** 中 ==方法签== 名相同（==方法名、参数列表、返回类型相同==）的方法，以改变父类方法的实现。重写发生在继承关系中，目的是让子类提供一个特定的实现来代替父类的默认实现。

**静态的多态性  重载 (overload) 编译时多态**

![image-20241229171400506](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20241229171400506.png)



**动态的多态性 覆盖/重写 (override) 运行时多态**

## 之所以有静态方法和 final 类的原因

不用创建对象 就可以调用方法 

final 类不能被继承 免得 ==被继承重写== 破坏原有科学严密性

![image-20241229172627277](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229172627277.png)



### 实际类型 & 引用类型 

### 实际类型（Actual Type）：

==运行时类型==

实际类型是指对象在 **内存中真正的类型**。
它是在 **运行时** 确定的，是对象实例化时使用的具体类。
<font size=10 color=red> 实际类型 </font> 决定了对象可以使用哪些 <font size=10 color=red> 方法 </font>

### 引用类型（Reference Type）：

==编译时类型==



引用类型是 **变量声明时使用** 的类型。
它是在编译时确定的，可以是实际类型的本身，也可以是 **其父类或实现的接口。**
<font size=10 color=red> 引用类型 </font> 决定了通过该引用可以访问哪些 <font size=10 color=red> 成员变量 </font>

parent A = new sons()

parent B = new daughters()

---



**引用类型 			实际类型**

引用类型的对象数据存储在栈内存中 ，实际类型的对象数据存储在堆内存中

**new 就是把实际类型的对象数据存储在堆内存了**

也就是 A 只可使用父类的字段然后在运行的时候调用方法指向堆内存中子类的方法

（**但是变量还是父类的**）

---

**so ： 子类重写的方法限不能小于父类的方法** （维护了 ==多态性==）

**多态本质：**

new 返回的是一个对象的引用

返回的是一个对 sons 对象的引用 

sons 本身是自己的实际类型

引用类型 = new 实际类型 （返回对象的引用）

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

在 Java 中，**父类不能直接赋值给子类**，这是因为子类是父类的扩展，子类可能包含父类中没有的属性和方法。如果允许将父类对象直接赋值给子类，可能会导致运行时错误，因为子类可能访问父类中不存在的成员。----影响==多态性==

1. 0 这样没有1.0f的就是默认double型

   5这样整型 没有5L 就是默认int型

   ~~~java
    boolean b  = 1 ; // error 
   ~~~

   一个A类继承了另一个B类那么A就拥有了B类所有的方法和变量（在访问权限内的！！！）

类型强的不能赋值给类型弱的 int强类型 boolean弱类型

<span style="color:#CC0000; font-weight:bold; font-size:2em;">继承问题：</span>

 ~~~java
 import java.util.*;
 
 public class Main
 {
     public static void main(String args [])
     {
         A a = new A();
         a.test();
     }
 }
 
  class B
 {
     public void test() {
         System.out.print("B");
     }
 }
  class A extends B 
 {
     // public void test() {
     //     System.out.print("A");
     // }   
 }
 // out : B 
 // 取消注释后 out：A
 ~~~



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
    public static void main(String [] args) {
        AudioPlayer player1 = new MP3Player();
        AudioPlayer player2 = new WAVPlayer();
        AudioPlayer player3 = new FLACPlayer();

        // 播放不同格式的文件
        player1.play("song1.mp3");
        player2.play("song2.wav");
        player3.play("song3.flac");

        // 可以轻松添加新的音频格式, 只需实现 AudioPlayer 接口
    }
}
~~~

在 Java 中，将实现了某个接口的对象赋值给该接口类型的变量，是 **面向对象编程** 中 **多态性**（Polymorphism）的一个体现。简单来说，就是你可以通过接口类型的引用来操作实现该接口的对象，而不必关心对象的具体实现细节。

### 实现类赋值给接口 の 详细解释：

#### 1. **接口和实现类的关系**：

- **接口**（Interface）在 Java 中定义了一组方法，这些方法是没有具体实现的。接口是行为的抽象，定义了实现类必须遵守的契约（规则）。
- **实现类**（Implementing Class）是接口的具体实现，必须实现接口中定义的所有方法。

#### 2. **接口类型的变量可以指向实现类的对象**：

当一个类实现了某个接口时，我们可以将这个类的对象赋值给该接口类型的变量。这样做的好处是，代码变得更加灵活，因为你可以针对接口编程，而不必依赖某个具体的实现类。

---



 **灵活性**

- 可以随时更换实现类，而不需要修改代码的其他部分。例如：

  ```java
  List<String> list = new ArrayList<>();  // 使用 ArrayList
  list = new LinkedList<>();              // 切换为 LinkedList
  ```

 **可维护性**

- 代码依赖于接口，而不是具体的实现类，降低了代码的耦合度，更容易维护和扩展。

 **可测试性**

- 在单元测试中，可以使用模拟对象（Mock Object）替换具体的实现类，方便测试。

**符合设计原则**

- 这种写法符合 **面向对象设计原则** 中的 **依赖倒置原则**（Dependence Inversion Principle），即高层模块不应该依赖低层模块，二者都应该依赖抽象。



---



 **问题背景：**



假设我们有一个简单的订单处理系统，需要将订单保存到数据库中。最初的实现可能是这样的：

**不符合依赖倒置原则的写法**



```java
// 低层模块：数据库操作
class DatabaseService {
    public void saveOrder(String order) {
        System.out.println("Saving order to database: " + order);
    }
}

// 高层模块：订单处理
class OrderService {
    private DatabaseService databaseService = new DatabaseService();

    public void processOrder(String order) {
        // 处理订单逻辑
        System.out.println("Processing order: " + order);
        // 保存订单到数据库
        databaseService.saveOrder(order);
    }
}

// 使用
public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.processOrder("Order #123");
    }
}
```

**问题**：

- 高层模块 `OrderService` 直接依赖低层模块 `DatabaseService`。
- 如果将来需要更换存储方式（如保存到文件或发送到消息队列），必须修改 `OrderService` 的代码。

---



**引入依赖倒置原则**

通过引入依赖倒置原则，我们可以将 `OrderService` 与具体的存储实现解耦。

改进后的代码：



```java
// 抽象：存储接口
interface StorageService {
    void save(String data);
}

// 低层模块：数据库存储
class DatabaseService implements StorageService {
    @Override
    public void save(String data) {
        System.out.println("Saving to database: " + data);
    }
}

// 低层模块：文件存储
class FileService implements StorageService {
    @Override
    public void save(String data) {
        System.out.println("Saving to file: " + data);
    }
}

// 高层模块：订单处理
class OrderService {
    private StorageService storageService;

    // 通过构造函数注入依赖
    public OrderService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void processOrder(String order) {
        // 处理订单逻辑
        System.out.println("Processing order: " + order);
        // 保存订单
        storageService.save(order);
    }
}

// 使用
public class Main {
    public static void main(String[] args) {
        // 使用数据库存储
        StorageService databaseService = new DatabaseService();
        OrderService orderService1 = new OrderService(databaseService);
        orderService1.processOrder("Order #123");

        // 使用文件存储
        StorageService fileService = new FileService();
        OrderService orderService2 = new OrderService(fileService);
        orderService2.processOrder("Order #456");
    }
}
```



---



例子：

假设有一个接口 `Animal` 和两个实现类 `Dog` 和 `Cat`：



```java
public interface Animal {
    void sound();
}

public class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Woof");
    }
}

public class Cat implements Animal {
    @Override
    public void sound() {
        System.out.println("Meow");
    }
}
```

我们可以将 `Dog` 和 `Cat` 的对象赋值给 `Animal` 接口类型的变量：



```java
Animal myDog = new Dog();  // myDog 是 Animal 类型的引用，指向了 Dog 对象
Animal myCat = new Cat();  // myCat 是 Animal 类型的引用，指向了 Cat 对象
```

#### 3. **多态性**：

多态性允许我们通过接口的引用来调用实现类中的方法，且具体调用哪个实现类的方法是在运行时决定的。比如：



```java
myDog.sound();  // 输出 "Woof"
myCat.sound();  // 输出 "Meow"
```

在这段代码中，虽然 `myDog` 和 `myCat` 的类型都是 `Animal`，但它们分别指向了 `Dog` 和 `Cat` 对象。当调用 `sound()` 方法时，程序会根据它们实际指向的对象来执行对应的实现方法。

#### 4. **优点**：

将实现类的对象赋值给接口类型的变量有以下几个优点：

- **解耦合**：代码依赖于接口，而不是实现类。这样当需要切换实现时，不需要修改使用该接口的代码。例如，你可以很轻松地用另一个实现类来替换 `Dog` 或 `Cat`，而不必改动使用接口的代码。

- **灵活性**：你可以编写更加通用的代码，针对接口编程，而不是针对具体类编程。例如：

  

  ```java
  public void makeSound(Animal animal) {
      animal.sound();  // 这里不用关心 animal 是 Dog 还是 Cat，只要它实现了 Animal 接口
  }
  
  // 调用
  makeSound(new Dog());  // 输出 "Woof"
  makeSound(new Cat());  // 输出 "Meow"
  ```

- **扩展性**：如果将来有新的类实现 `Animal` 接口（比如 `Bird`），你可以很轻松地将它们整合进现有的代码，而不需要修改现有代码：

  

  ```java
  public class Bird implements Animal {
      @Override
      public void sound() {
          System.out.println("Tweet");
      }
  }
  
  // 调用
  makeSound(new Bird());  // 输出 "Tweet"
  ```

#### 5. **实际应用场景**：

在实际开发中，使用接口编程可以让代码更具有扩展性和维护性。例如，很多框架和库（如 Spring、MyBatis）都依赖于接口来实现松耦合的设计。开发者只需要面向接口编程，而具体的实现可以根据需要在运行时动态替换。

比如在 MyBatis 这样的 ORM 框架中，`UserMapper` 是一个接口，MyBatis 会在运行时生成该接口的实现类，并将其对象赋值给接口类型的变量：



```java
UserMapper userMapper = session.getMapper(UserMapper.class);
```

在这段代码中，`userMapper` 是 `UserMapper` 接口类型的变量，而它实际上指向了 MyBatis 动态生成的一个实现类的对象。通过这种方式，你可以调用接口

## 赋值的本质 堆区和栈区存储

在 Java 中，**将父类对象赋值给子类变量** 是不允许的，会导致编译错误。这是因为子类是父类的扩展，子类可能包含父类中没有的成员（属性和方法），而父类对象无法保证这些额外的成员存在。

------

### **为什么不能将父类赋值给子类？**

1. **类型不匹配**：

   - 子类是父类的扩展，子类对象可以向上转型为父类类型（父类引用指向子类对象），但父类对象不能向下转型为子类类型（子类引用指向父类对象）。
   - 父类对象可能缺少子类中定义的成员，直接赋值会导致类型不安全。

2. **编译错误**：

   - 如果尝试将父类对象直接赋值给子类变量，编译器会报错：

     java

     复制

     ```
     Parent obj = new Parent();
     Child child = obj; // 编译错误：不兼容的类型
     ```

------

### **如何实现父类到子类的转换？**

如果确实需要 **将父类对象转换为子类类型**，必须 ==都要== 满足以下条件：

1. **父类引用实际指向子类对象**：
   - 父类引用必须是通过向上转型得到的，即它实际指向的是一个子类对象。
2. **显式类型转换**：
   - 使用强制类型转换（`(Child)`）将父类引用转换为子类类型。 



![c9984a60daa638547b81088a318a972](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/c9984a60daa638547b81088a318a972.jpg)

**对象赋值的本质：**

1. **引用传递：**
   - **在 Java 中，对象变量存储的是对象的引用（内存地址），而不是对象本身。**
   - **当进行对象赋值时，实际上是复制这个引用，而不是复制整个对象。**
2. **共享对象：**
   - **赋值后，两个引用指向同一个对象。**
   - **通过任何一个引用修改对象，都会影响到其他引用所看到的对象状态。**
   
   





![image-20241107082239273](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082239273.png)

![image-20241107082343542](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082343542.png)

![image-20241107082354980](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082354980.png)

**多态具体运用： 对象数组**

![image-20241107082144419](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082144419.png)

**<font color=red size=6> java 导包：</font> Java 中的导包是指为了在源码中使用某个包中的类或接口而引入相应的包。当我们使用 Java 中的类或接口时，需要用到这个类或接口的全限定名，如果没有引入相应的包，我们在代码中就需要使用完整的类名来访问这个类或接口。而导包可以帮助我们简化代码，提高代码的可读性和可维护性。**

**java 默认（默认级别）** 包级别访问机制  （不包括子类）

---

final 关键字 ： **修饰类/方法 变量**

一旦被修饰 类 ： **无法被继承** 

方法：**不能被子类重写** 

变量：**不可以被改变**

---

# 构造方法：

**构造方法不能被继承**：子类不能继	承父类的构造方法。每个类都有自己的构造方法，即使父类构造方法存在，子类也不能 ==直接使用==（意思就是不能直接调用（使用））它。子类只能通过 `super()` 来调用父类的构造方法

每当创建对象时，构造方法都会被调用， **不能返回任何值**， 如果在继承的状态下， 

！！！核心： 子类的构造方法必须隐式（==无参构造！！！==）地或者显式地调用父类的构造方法 

**无论子类是否显式调用父类的构造方法，父类的构造方法总会在子类的构造方法中执行。这是因为 Java 需要确保在创建子类对象时，父类的部分能够被正确地初始化。**

- <span style="color:#FF3333;">如果子类的构造方法 **没有显式调用父类** 的构造方法，那么 Java 编译器会 **隐式** 插入一个对父类 **无参构造方法** 的调用（`super()`）。</span>

- <span style="color:#FF3333;">如果父类没有无参的构造方法，而子类 **没有显式调用父类** 的有参构造方法，那么编译会失败。</span>

- ~~~java
  package demo1; // 父子类
  
  public class Student {
      private String name;
      private int age;
      public Student(){
          System.out.println("父类构造函数被调用了");
      }
      public Student(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }
  
  class Formal extends Student{
      private String name;
      private int age;
      private int position;
      public Formal(String name, int age, int position){
          this.name = name;
          this.age = age;
          this.position = position;
      }
  //   if 子类的构造方法没有显式调用父类的构造方法: 隐式的插入一个无参的构造函数
      public Formal(){
          System.out.println("子类无参构造被调用了");
      }
  
  }
  
  class Ordinary extends Student {
  
  
  }
  
  ~~~

- ~~~java
  package demo1;  // 测试类
  
  public class Test {
      public static void main(String [] args)
      {
          Formal Senjay = new Formal();
  //        输出内容：  
  //        父类构造函数被调用了
  //        子类无参构造被调用了
      }
  
  }
  
  ~~~



==注意点== ：为什么 子类的成员方法比父类具有相同或者更广的访问方式

**子类的方法访问权限要比父类的大（或至少相等）是 Java 语言设计的一个重要原则**

---

**方法重载 ： 同名方法 ， （形式参数）参数类型 数量 顺序不同**

**和修饰符、返回类型无关 ，只和参数列表有关**

---



![image-20241107083820942](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107083820942.png)



# 访问权限和 static 关键字

![image-20241107082551761](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082551761.png)

# 类 抽象类

![image-20241107082807147](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082807147.png)

![image-20241107082814982](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082814982.png)

![image-20241107082821386](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241107082821386.png)

# 接口



# Java 集合操作





# java 基础

## Tips

**1.边看视频边写代码。** **先看视频了解一个大概** 模拟出流程后 然后自己手写 最后回过头看哪里有缺或是哪里不够好（总结方法论）

**2.在没有充分了解 Java 这个行业之前贸然开始 自信，破釜沉舟 **  

学习一个技能技术前 要懂得 it  **who （谁发明）  when（什么时候发明） whether （这个技术是否过时 是否可替代）what（这个技术是什么） why（为什么要发明这个技术） how（这个技术长什么样，如何学习，如何了解学习渠道）**

学东西光学知识点没 p 用 一定要有应用场景的实现！！！

 **3.学习不专注**  起码得保证 45min 不被打扰专注时间

 **4.不写测试代码 **  判空指涉

**5.拒绝学习代码规范，展示代码**   不要害怕别人说自己菜

**6.碰见 bug 不思考就发问**   foremost : 学会自己看，这样下次碰到了就会解决了，实在不行就上网解决，贵在坚持，不要轻易放弃。最后实在不行了在问别人！

**7.手懒不想动 **   **写程序不能光想和光临摹别人的代码 要自己实现 先有个全局观 然后再从细节出发**

 **8.没有极致的求知精神** 

**9.没有目的的去写代码，不刻意的练习**   要知道自己在干嘛

**10.急于求成想快速精通。** 让自己痛苦才是学习的最好 buff

**多个类在一个文件中的规则：**

- 在 Java 中，一个源文件可以包含多个类
- 但只能有一个 public 类，且 public 类的名字必须与文件名相同
- 其他类（如 Mypanel）是包访问级别的类，只能在同一个包内访问



核心：

java 一切皆对象

**main 方法是函数的入口方法 **  执行这个类 一定会先从主方法开始执行！！！ 

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
    public static void main(String [] args) { // 主方法
        new DrawCircle(); // 一 new （创建）就会调用构造方法

    }
}
class Mypanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10,10,100,100); // 起始点从圆的外切正方形的左上角顶点开始！
        g.drawLine(10,10,100,100);
        g.setColor(Color.red); //设置画笔颜色
//        g.setFont(new Font("隶书", Font.BOLD,50)); // 设置画笔字体

//        g.drawString("lunaFreya",100,100);
//        Image img = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("image/Freya.png")); src: 引入图片写法
//        g.drawImage(img, 10, 10, 500,500, this);



    }
}
~~~

**查文档能力 ：java 8 以及各种框架！！** Graphics 类用法

**类比能力 ：java 画图体系类似 css 中 position 的定位坐标系**



**hero 继承了 tank 类 ---》封装思想**

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
    Vector <enemyTank> enemyTanks = new Vector <>();
    // 创建一个集合
    int enemyTankSize = (int)(10*(Math.random()));

    //变量 默认是包访问权限
    public Mypanel() {
        hero = new Hero(100,100);
        // 初始化坦克对象
        hero.setDirection(2);
        // 没有必要封装到 hero 里 , 直接调用方法改变/获取值
        for(int i = 0; i < enemyTankSize; i++)
        {
            enemyTank e = new enemyTank(100*(i+1),0);
            e.setDirection((int)(Math.random()*4));// 随机方向
            enemyTanks.add(e);

        } // 初始化坦克集合 2


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
        drawTank(hero.getX(), hero.getY(), hero.getDirection(), g,0);
        for(int i = 0; i < enemyTankSize; i++)
        {
            enemyTank e = enemyTanks.get(i);
            // 集合方法
            drawTank(e.getX(), e.getY(), e.getDirection(), g,1);
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
            // 继承了 tank 就可以直接使用 tank 的方法了
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
    public static void main(String [] args) {

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

## 继承 特性（Tank --> Hero）

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

**128 位分成八组**

## 局域网和公网

~~~tex
192.168 开头的 IP 地址是私有 IP 地址范围中最常见的一类。具体来说：

1. 地址范围：
   192.168.0.0 到 192.168.255.255
2. 子网掩码：
   255.255.0.0 （也写作 /16 in CIDR notation）
3. 分类：
   这属于 C 类私有地址范围
4. 可用 IP 数量：
   65,536 个（2^16）
5. 常见用途：
   - 家庭网络
   - 小型办公室网络
   - 无线路由器默认设置
   - 虚拟机网络
6. 特点：
   - 不可直接在互联网上路由
   - 可在不同的局域网中重复使用
   - 需要通过 NAT（网络地址转换）才能访问互联网
7. 配置示例：
   - 路由器 IP：192.168.1.1
   - DHCP 范围：192.168.1.2 到 192.168.1.254
8. 安全性：
   提供了一定程度的网络隔离，因为这些地址不能直接从互联网访问
9. 标准：
   定义在 RFC 1918 中
10. 其他常见配置：
    - 192.168.0.x
    - 192.168.1.x
    - 192.168.2.x 等

这个地址范围非常流行，因为它提供了足够多的地址用于大多数小型到中型网络，同时又易于记忆和配置。然而，在大型企业网络中，可能会选择使用 10.0.0.0/8 范围，因为它提供了更多的地址空间。

// 重要提示：虽然 192.168.x.x 地址在不同的局域网中可以重复使用，但在同一个网络中，每个设备的 IP 地址必须是唯一的。
~~~



## 特殊 IP 地址

​	![image-20241109235358709](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109235358709.png)

**不同的局域网下 ip 地址不一样**

而 127.0.0.1 一定是本机 ip



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
    public static void main(String [] args) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("senjay");
   //   InetAddress addr = InetAddress.getByName("192.168.0.9"); 
        // 主机名/ip 地址
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

### UDP 协议

**Socket 即插座**

==学会查看方法参数列表不要死记硬背==

data **gram** 数据包（**报**）

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
    public static void main(String [] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();// no parameter ：send data by random port

        String str = "hello world!";
        byte [] datas = new byte [1000];
        datas = str.getBytes();
        InetAddress address = InetAddress.getByName("192.168.101.116");
        int port = 10086;
        // 指定发送的地址和端口号
        // 打包数据和发送地址
        DatagramPacket dp = new DatagramPacket(datas, datas.length, address, port); // parameters link to cue so that show
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
    public static void main(String [] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);
        byte [] temp = new byte [1024];
        DatagramPacket dp = new DatagramPacket(temp, temp.length);
        ds.receive(dp);
        // 接收 将数据放入 dp 中

        // 解构数据
        byte [] data = dp.getData();
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

==**在 send 中指名发送的组名**==

![image-20241111192453774](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192453774.png)

==**在 receive 中加入指名的组中**==

在接收方创建 `MultiSocket ` 对象！

##### 区别注意：

**`DatagramSocket`** 是用于 **点对点** 的 UDP 通信。它可以发送/接收独立的数据包到/从指定的接收者/发送者。

**`MulticastSocket`** 是用于多播 UDP 通信。它可以将数 **据发送到一个多播组中的多个接收者**，允许多个主机同时 **接收** 到同一数据包。



![image-20241111192514964](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241111192514964.png)

`ms.join(InetAddress对象)`

#### 广播

**直接将发送的 ip 地址改为 255.255.255.255!!**



### TCP 协议

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
    public static void main(String [] args) throws IOException {
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
    public static void main(String [] args) throws IOException {
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

**当语句有异常时直接进入 catch 语句块 不会执行异常语句下面的语句了**



## 异常类型

### 编译时异常（受检异常）

编译时异常是在编译时由 ==编译器强制检查== 的异常。程序员必须 ==显式== 地处理（两种方法）这些异常，否则程序将 ==无法编译==。

### 运行时异常（非受检异常）

 运行时异常是在程序运行过程中可能出现的异常，编译器不强制要求程序员进行处理。这些异常通常是由程序中的 ==逻辑错误== 引起的

### 错误（error）

- 错误是指应用程序无法处理的严重问题。这些通常是由 ==运行时环境== 导致的，程序员不应试图捕获这些错误。：错误通常表示 JVM 的内部错误或资源耗尽，例如 ==内存不足、栈溢出== 等。

## 异常的处理方式：

对于异常的处理 ： 直接在本层函数中处理 ，或者抛出到调用本层函数的上层函数去处理这个异常，如果 main 函数还去抛出就抛到了 jvm 中 。

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

    public static void main(String [] args) {
        try {
            checkAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获到异常: " + e.getMessage());
        }
    }
}
~~~

**注意：throws 和 throw 关键字**

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
            throw new CustomException("数字不能大于 100");
        }
        System.out.println("数字是: " + number);
    }

    public static void main(String [] args) {
        try {
            validate(150);
        } catch (CustomException e) {
            System.out.println("捕获到自定义异常: " + e.getMessage());
        }
    }
}
~~~





# 注解（结合反射才有用）

java 中的字段 : 成员变量和成员方法



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

ElementType, CONSTRUCTOR		  	针对构造器	

ElementType.PACKAGE					     针对包

ElementType.ANNOTATION TYPE	   针对注解


## @Retention 指定注解的保留域

RetentionPolicy:
**RetentionPolicy.SOURCE** 源代码级别，由编译器处理，处理之后就不再保留

**RetentionPolicy.CLASS** 注解信息保留到类对应的 class 文件中

**RetentionPolicy.RUNTIME** 由 JM 读取，运行时使用



## 自定义注解

![bbc1a7eb9d907fd73c64971b1ba414b](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/bbc1a7eb9d907fd73c64971b1ba414b.jpg)



# 反射

# overview

`机器人，它能在运行时看见自己的零件(字段、方法)并----根据需要----进行调整或操作。反射就是这个机器人用来查看和操作自身的方法`

## 反射的应用场景
### 1.动态创建对象:
**概念:** 在运行时根据需要创建新的对象，而不是提前写死。

**例子:** 想象你在玩一个游戏，随着游戏进展，动态生成新的角色或道具。

### 2.依赖注入(Dependency Injection)
**概念:** 在运行时把所需的对象注入到另一个对象中，让它们可以协同工作。
**例子:** 假设你有一个工厂，需要不同的零件来组装产品。依赖注入就像是自动把这些零件分配给工厂。

---



**不使用反射直接拿到成员属性 の 局限性:**
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

**所以底层就实现了几个子类继承 InputStream 以此来实现父类的方法**

![image-20241114221334670](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114221334670.png)

## 路径问题

~~~java
package com.Senjay.io_modules;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoDemo {
    public static void main(String [] args) throws FileNotFoundException {
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

**一个项目中有多个模块 每个模块都会有一个 src 注意**

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
    public static void main(String [] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("testdemo/src/iodemo/test1", false);// 续写开关 (默认为 false)
//            fos.write(98);  (一个字节大小的数据) -- 一次写一个字节数据
        byte [] bytes = {97,98,99,100};// 每个元素都是一个字节的数据
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
        byte [] datas = str.getBytes();// 将字符串转化为字节流
        // 为什么输入中文后不会乱码 中文不是以三字节存储的吗
        try {
            fos.write(datas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(datas));
//        System.out.println(Charset.defaultCharset().name());// java 系统默认编码格式
        // getbyte() 的编码标准就是以 java 默认编码格式


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



![image-20250101214503388](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101214503388.png)

### 对于换行书写

![image-20241120164235989](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120164235989.png)



回车\r

换行\n 



## 文件输入流

![image-20241120164531650](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120164531650.png)

**读入的时候指针也会移动的**

~~~java
   while((b = fis.read())!=-1) // 返回实际 字节数据
        {
            fos.write(b);
            //  Byte by byte 读取/写入
        }
~~~

​	

~~~java


public class InputStream2 {
    public static void main(String [] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("testdemo/src/iodemo/test2");
        FileOutputStream fos = new FileOutputStream("testdemo/src/iodemo/test1");
        byte [] buf = new byte [3]; // 不能有空字节被写入进去！！！
        // 开一定大小的字节数组
        int len;
        try {
           while((len = fis.read(buf))!=-1) // 返回实际读入的字节数 使用 buf 这个容器接收
           {
               fos.write(buf,0, len); 
               // off --> 当前指针位置 offset
           }
            System.out.println(len); // 返回字节数 汉字 *3 英文* 1（byte
            // 中文三个字节   src：哈哈
            System.out.println(Arrays.toString(buf)); // 输出-> [-27, -109, -120] [-27, -109, -120] 

			System.out.print(new String(src,0, len) + " "); // 重要 将字节数组转化为字符串的形式
            
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
read 方法的读取位置通常不会因为遇到换行符而停止，除非你指定了特定的读取长度或使用了 readline()这样的方法。
如果你想要按行读取，可以使用 readline()方法，它会在遇到  换行符 时停止读取
~~~





![image-20241120165732058](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120165732058.png)

### 解码简单实例

字节流 `{ -28, -72, -83, -27, -101, -67 }`（UTF-8 编码的 "你好"）：

- 第一个字节 `-28`（`0xE4`）的高位是 `1110`，表示这是一个 3 字节字符。
- 读取接下来的两个字节 `-72` 和 `-83`，解码为字符 "你"。
- 第二个字节 `-27`（`0xE5`）的高位是 `1110`，表示这是一个 3 字节字符。
- 读取接下来的两个字节 `-101` 和 `-67`，解码为字符 "好"。

### 读写问题注意

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

**字符流和字节流 read()  （无参 --> 字节流就不用说了 字符流见上）和 write() 函数 读取和写入规则 `fr.read()` 读取 'H'，返回 `72`，`fw.write(72)` 写入 'H'**

 <font size=5 color =red > **SUM : ==读入== 的时候会先 ==编码== 然后 ==写入== 的时候就会 ==解码==** </font>



~~~java
package iodemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CharInput {
    public static void main(String [] args) throws IOException {
        FileReader fr = new FileReader("testdemo/src/iodemo/test2");
//        int c;
//       while((c = fr.read())!=-1)
//       {
//           System.out.print((char)c);
//       }
        int ch; 
        char [] buf = new char [50]; //字符数组
        while ((ch = fr.read(buf)) != -1) { // 读入数据由 char 数组存储 返回字符数 （字节流就是字节数）
            System.out.println(new String(buf,0, ch)); // 解码操作
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

无参 ：返回这个具体的 ==十进制== 是多少 如 a 就是 97 

有参 ：返回实际读取的 ==字节数==

**上述为字节流**

**如果是字符流的话就是返回实际读取的字节数**



### 字符输出流

~~~java
package iodemo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharOutput {
    public static void main(String [] args) throws IOException {
        FileWriter fw = new FileWriter("testdemo/src/iodemo/test1", true);
        Scanner sc = new Scanner(System.in);

        fw.write(97);
        fw.write("hello world");
        char [] ch = new char [10];
       String str = sc.nextLine();
        fw.write(str,0,5);
//        fw.write(ch);


        fw.close();

    }
}

~~~



![image-20250101214429432](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101214429432.png)

**| 文件路径也可使用 file 对象**

## 缓冲流

![image-20241120190738913](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120190738913.png)

### 字节缓冲流

![image-20241120190819311](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120190819311.png)



![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120191612892.png)

**就是上了一层 ==包装== 而已**



### 字符 ==缓冲流== （也就是过滤流 ==》过滤流就是对流进行处理的流 也就是包装流）

#### readline (输入)  & newLine （输出）





![image-20241120191733755](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120191733755.png)

![image-20241120195349269](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241120195349269.png)

~~~java
package demo1.IO 流测试;

import java.io.*;
import java.util.Scanner;

public class CharStream {
    public static void main(String [] args) throws IOException {
        FileReader fr = new FileReader("testDemo/sources/test1");
        FileWriter fw = new FileWriter("testDemo/sources/copytest1");
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);

        // 包装
        String line;
        while((line = br.readLine())!= null) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();
        // 一定要关闭缓冲流 不然读入/写出的数据会丢失


    }
}

~~~

![image-20250101211232802](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101211232802.png)

**输出\r\n 就直接执行了这个 `回车`**

无论是输出到终端还是文件中

---

![image-20250101211910897](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101211910897.png)

![image-20250101211931707](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101211931707.png)



### 数据流 （DataOutputStream） 也是过滤流

### 1. **`writeInt` 的二进制格式**

`writeInt` 将一个 `int` 类型的整数（32 位）写入文件时，会将其拆分为 **4 个字节**，并按照 **大端序**（Big-Endian）存储。大端序（符合人类阅读习惯）是指 **高位字节存储在低地址**，低位字节存储在高地址。

例如，整数 `100` 的二进制表示为：

# :# ： 越左地址越低

```
00000000 00000000 00000000 01100100
```

`writeInt` 会将其拆分为 4 个字节并写入文件：



```
00000000 (字节 1)
00000000 (字节 2)
00000000 (字节 3)
01100100 (字节 4)
```

------

### 2. **`readInt` 的读取规则**

`readInt` 从文件中读取 4 个字节，并将它们按照 **大端序** 重新组合为一个 `int` 类型的整数。

例如，从文件中读取的 4 个字节为：

复制

```
00000000 (字节 1)
00000000 (字节 2)
00000000 (字节 3)
01100100 (字节 4)
```

`readInt` 会将它们重新组合为：

复制

```
00000000 00000000 00000000 01100100
```

对应的整数是 `100`。

```java
package demo1.IO流测试;

import java.io.*;

public class AsciiTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream ("testDemo/sources/test1");
        // 以二进制格式写入文件 所以打不开文件
        FileInputStream fis = new FileInputStream ("testDemo/sources/test1");
        DataOutputStream dos = new DataOutputStream(fos);
        DataInputStream dis = new DataInputStream(fis);


        int src[] = {58,23,56,89};
        for (int i = 0 ; i<src.length ;i++) {
            dos.writeInt(src[i]);
        }
        // 验证 以二进制格式读取
        for(int i = 0 ;i<src.length ;i++) {
            System.out.println(dis.readInt()+ " ");
        }

        dos.close();



    }
}
```

### RandomAccessFile 类

`RandomAccessFile` 是 Java 中一个非常强大的类，它允许 **随机访问文件**，即可以从文件的任意位置读取或写入数据。与 `FileInputStream` 和 `FileOutputStream` 不同，`RandomAccessFile` 既可以读取文件，也可以写入文件，并且支持文件的随机访问。

#### 特点



1. **随机访问**：

   - 可以通过 `seek()` 方法将文件指针移动到文件的任意位置，然后进行读取或写入。

2. **读写模式**：

   - 支持只读模式（`"r"`）、读写模式（`"rw"`）等。

3. **多功能**：

   - 可以读取和写入基本数据类型（如 `int`、`double` 等）以及字节数据。

4. **文件指针**：

   - 通过 `getFilePointer()` 方法获取当前文件指针的位置。
   - 通过 `length()` 方法获取文件的长度。

   ==读取的过程中文件指针也会移动的！！！==

   ==移动的单位是==

   - **例如，`seek(0)` 将文件指针移动到文件的开头。（也就是第一个）**
   - **`seek(10)` 将文件指针移动到文件的第 11 个字节（从 0 开始计数）。**

# 泛型

可接收多个泛型参数



    public class Pair <K, V> {
    	private K key;
     	private V value;
    // 构造方法
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    // Getter 和 Setter 方法
    public K getKey() {
        return key;
    }
    
    public void setKey(K key) {
        this.key = key;
    }
    
    public V getValue() {
        return value;
    }
    
    public void setValue(V value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Pair{" +
                "key =" + key +
                ", value =" + value +
                '}';
    }
}



![image-20250101231029945](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101231029945.png)



### 泛型接口 

![	](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101231125808.png)



### 通配泛型参数

![image-20250101231443338](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101231443338.png)



---



![image-20250101231511321](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101231511321.png)



---

### 泛型方法

![image-20250101231803720](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101231803720.png)



### 泛型使用限制

**类型参数只能是引用类型不能是基本类型！！**

![image-20250101232226483](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101232226483.png)

## 集合类

如果一个类实现了 **子接口**，那么它 **必须** 实现 **父接口** 的所有抽象方法。这是因为 **子接口** 继承了父接口的方法，而类实现子接口时，就需要实现子接口及其父接口的所有方法。

### 1. **集合框架的层次结构**

Java 集合框架的核心接口和类位于 `java.util` 包中，主要分为以下几类：

#### （1）**Collection 接口**

**Collection 接口定义了十多个方法，用于增加、删除和查询数据元素实现了这个接口的所有数据容器就有了相同的操作方式。**

![image-20250101234522653](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250101234522653.png)

- 表示一组对象的集合。
- 主要子接口：
  - `List`：有序且 ==允许== 重复元素的集合。
  - `Set`：无序且 ==不允许== 重复元素的集合。
  - `Queue`：队列，支持先进先出（FIFO）或优先级排序。

#### （2）**Map 接口**

- 表示键值对的集合。
- 主要实现类：
  - `HashMap`：基于 **哈希表** 的键值对集合。
  - `TreeMap`：基于 **红黑树** 的键值对集合， 按 **键（key）** ==排序==。

------

### 2. **常用集合类及其用法**

#### （1）**List（列表）**

- **特点**：有序且允许重复元素。

- **常用实现类**：

  - `ArrayList`：基于动态数组 实现，支持快速随机访问。 类似 vector 动态数组

    

  - `LinkedList`：基于 ==双向链表== 实现，支持快速插入和删除。

##### 示例：

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println(list); // 输出：[Apple, Banana, Cherry]
        System.out.println(list.get(1)); // 输出：Banana
    }
}
```

------

#### （2）**Set（集合）**

- **特点**：无序且不允许重复元素。
- **常用实现类**：
  - `HashSet`：基于 ==哈希表== 实现，支持快速查找。
  - `TreeSet`：基于 ==红黑树== 实现，元素按自然顺序排序。

##### 示例：



```java
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple"); // 重复元素，不会被添加

        System.out.println(set); // 输出：[Apple, Banana]
    }
}
```

------

#### （3）**Queue（队列）**

- **特点**：支持先进先出（FIFO）或优先级排序。
- **常用实现类**：
  - **`LinkedList`：可以用作 ==队列== 或 ==双端队列==。**
  - **`PriorityQueue`：基于 ==优先级堆== 实现，元素按优先级排序。**

##### 示例：

```java
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Apple");
        queue.offer("Banana");
        queue.offer("Cherry");

        System.out.println(queue.poll()); // 输出：Apple
        System.out.println(queue.poll()); // 输出：Banana
    }
}
```

------

#### （4）**Map（映射）**

- **特点**：存储键值对 **，键不允许重复。**
- **常用实现类**：
  - `HashMap`：基于哈希表实现，**支持快速查找。**
  - `TreeMap`：基于红黑树实现，**按键排序。**

##### 示例：

```java
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        System.out.println(map.get("Banana")); // 输出：2
        System.out.println(map.containsKey("Apple")); // 输出：true
    }
}
```

------

### 3. **集合类的选择**

- **需要有序且允许重复元素**：使用 `List`（如 `ArrayList`、`LinkedList`）。
- **需要无序且不允许重复元素**：使用 `Set`（如 `HashSet`、`TreeSet`）。
- **需要先进先出或优先级排序**：使用 `Queue`（如 `LinkedList`、`PriorityQueue`）。
- **需要存储键值对**：使用 `Map`（如 `HashMap`、`TreeMap`）。

------

### 4. **集合类的常用操作**

#### （1）**遍历集合**

- 使用 `for-each` 循环：

  ```
  for (String item : list) {
      System.out.println(item);
  }
  ```

- 使用迭代器：

  ```
  Iterator<String> iterator = list.iterator();
  while (iterator.hasNext()) {
      System.out.println(iterator.next());
  }
  ```

#### （2）**排序集合**



- 使用 `Collections.sort()` 对 `List` 排序：

  ```
  Collections.sort(list);
  ```

- 使用 `TreeSet` 或 `TreeMap` 对元素自动排序。

- ###  **`Collections`**

  - **定义**：
    `Collections` 是一个 **工具类**，位于 `java.util` 包中。
  - **作用**：
    - 提供了一组静态方法，用于操作或返回集合。
    - 主要用于对集合进行排序、查找、反转、同步等操作。
  - **特点**：
    - 不能被实例化（构造方法是私有的）。
    - 所有方法都是静态的，可以直接通过类名调用。
  - **常用方法**：
    - `sort(List<T> list)`：对列表进行排序。
    - `reverse(List<?> list)`：反转列表中的元素。
    - `shuffle(List<?> list)`：随机打乱列表中的元素。
    - `binarySearch(List<? extends Comparable<? super T>> list, T key)`：在有序列表中查找元素。

#### （3）**查找元素**

- 使用 `contains()` 方法检查 **集合** 是否包含某个元素：

  

  ```
  boolean contains = list.contains("Apple");
  ```

#### （4）**删除元素**

- 使用 `remove()` 方法删除元素：

  

  ```
  list.remove("Apple");
  ```

------

### 5. **集合类的性能比较**

| 集合类       | 实现方式 | 插入性能 | 查找性能 | 删除性能 | 是否有序 | 是否允许重复 |
| :----------- | :------- | :------- | :------- | :------- | :------- | :----------- |
| `ArrayList`  | 动态数组 | O(1)     | O(1)     | O(n)     | 是       | 是           |
| `LinkedList` | 双向链表 | O(1)     | O(n)     | O(1)     | 是       | 是           |
| `HashSet`    | 哈希表   | O(1)     | O(1)     | O(1)     | 否       | 否           |
| `TreeSet`    | 红黑树   | O(log n) | O(log n) | O(log n) | 是       | 否           |
| `HashMap`    | 哈希表   | O(1)     | O(1)     | O(1)     | 否       | 键不允许重复 |
| `TreeMap`    | 红黑树   | O(log n) | O(log n) | O(log n) | 是       | 键不允许重复 |

`LinkedList` 和 `ArrayList` 是 **有序的**，但这种有序是指 **插入顺序**，即元素在 `LinkedList` 中的排列顺序与元素被插入的顺序保持一致。

正正自然排序或者自定义顺序的是 **TreeMap 和 PriorityQueue**



### 集合类方法

| 操作     | `ArrayList`/`LinkedList` | `HashSet`/`TreeSet`  | `HashMap`/`TreeMap`   |
| :------- | :----------------------- | :------------------- | :-------------------- |
| **增**   | `add(E e)`               | `add(E e)`           | `put(K key, V value)` |
| **删**   | `remove(Object o)`       | `remove(Object o)`   | `remove(Object key)`  |
| **改**   | `set(int index, E e)`    | 先删后增             | `put(K key, V value)` |
| **查**   | `get(int index)`         | `contains(Object o)` | `get(Object key)`     |
| **长度** | `size()`                 | `size()`             | `size()`              |

# 多线程

**线程时 CPU 调度的最小单位**

![image-20241229190018877](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229190018877.png)





​	![image-20241229181630649](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229181630649.png)

![image-20241112194700498](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241112194700498.png)

## 继承 Thread 类

线程休眠 ： Thread.sleep(1000) **(ms)**

**线程休眠**   指 **一个正在执行的线程** 在调用 `Thread.sleep(milliseconds)` 方法后，暂停执行一段时间。在这段时间内，**线程不会占用 CPU 资源，操作系统会将其状态设置为“休眠”，然后调度其他线程运行**。休眠的时间是由传入的参数（以毫秒为单位）决定的。

注意：

CPU 核心数：

- 如果您的机器是 **单核** 的，那么在任一时刻只能执行一个线程，这可能导致主线程先执行完毕。
- 操作系统会 ==交替执行== 这两个线程，具体的执行顺序和时间片分配由操作系统决定。
- ，在多核处理器的现代计算机中，这两个线程确实可能是同时进行的。让我解释一下这个概念：

  1. 并发 vs 并行：

     - 并发（Concurrency）：多个任务交替执行，给人一种同时进行的感觉。
     - 并行（Parallelism）：多个任务真正同时执行。

  2. 线程调度：

     即使在单核处理器上，操作系统也会通过快速切换线程来创造 "同时进行" 的错觉。这就是并发。

  3. 多核处理器：

     在多核处理器上，确实可能实现真正的并行执行。两个线程可能在不同的核心上同时运行。

  4. 不确定性：

     尽管线程可能并行执行，但它们的精确执行顺序和时间仍然是不确定的，取决于多种因素，如系统负载、线程优先级等。

  5. 示例解释：

     在之前的例子中，当我们调用 `t1.start()` 和 `t2.start()` 时，我们只是告诉操作系统这两个线程已准备好运行。操作系统会决定如何调度这些线程。

     ```java
     t1.start();
     t2.start();
     ```

     这两行代码之后，t1 和 t2 可能：
     - 在多核系统上并行执行
     - 在单核系统上交替执行
     - 一个线程可能在另一个开始之前就结束

  6. 观察并行：

     要观察真正的并行执行，可以让线程执行耗时的操作：

     ```java
     Thread t1 = new Thread(() -> {
         for (int i = 0; i < 5; i++) {
             System.out.println("Thread 1: " + i);
             try {
                 Thread.sleep(100);  // 模拟耗时操作
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });
     
     Thread t2 = new Thread(() -> {
         for (int i = 0; i < 5; i++) {
             System.out.println("Thread 2: " + i);
             try {
                 Thread.sleep(100);  // 模拟耗时操作
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });
     
     t1.start();
     t2.start();
     ```

     在这个例子中，你可能会看到两个线程的输出交错，这表明它们确实在 "同时" 执行。

  总结：虽然线程可能同时进行，但我们通常无法精确控制它们的执行顺序。这就是为什么在处理多线程时，我们需要使用同步机制来协调线程间的交互，以确保程序的正确性。



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

为何使用 start 方法而不是 run 方法

1. 使用 start() 方法：
   - start() 方法会 **创建一个新的线程** （底层中 不是代码创建） ，并让这个线程执行 run() 方法。
   - 这个新线程会与主线程 **并行运行**。
   - **JVM** 会调用这个 **新线程的 run()** 方法。
   - 这才是真正的 ==多线程执行。==
2. 直接调用 run() 方法：
   - 如果直接调用 run() 方法，它会在当前线程（通常是主线程）中执行。
   - 不会创建新的线程。
   - 这只是一个 **普通的方法调用**，不会实现多线程。
   - 

##  实现 Runnable 接口

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

// 客户端代码 client
public class Main {
    public static void main(String [] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}
~~~



## 两个方法的优缺点和使用场景

**实现 Runnable 接口 方式 更加适合多个线程 ==共享一个资源== 的情况 ，并且避免了单继承（一个类只能有一个父类）的情况！**

这两种情况确实有重要的区别。让我们逐一分析：

**情况 1：**



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
5. 这可能导致线程安全问题，需要适当的 **同步机制来处理并发访问。**



---



**情况 2：**



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
5. 这种方式通常不会导致直接的线程安全问题（除非 T1 和 T2 访问 **共享的静态变量** 或 **外部资源**）。

主要区别：

1. 代码逻辑：情况 1 中两个线程执行相同的代码，情况 2 中可能执行不同的代码。
2. 状态共享：情况 1 中线程共享状态，情况 2 中线程有独立的状态。
3. 线程安全：情况 1 更容易出现线程安全问题，情况 2 相对独立。
4. 灵活性：情况 2 允许不同线程执行不同的任务，更加灵活。
5. 资源使用：情况 1 可能更节省内存（只有一个 Runnable 对象），但需要更谨慎的并发控制。

选择哪种方式取决于您的具体需求。如果需要多个线程执行相同的任务并共享状态，使用情况 1。如果需要不同线程执行不同的任务，或者希望避免状态共享，使用情况 2。

---

### exp

**可以在线程中设置一个 loop 变量控制 while 跳出 在其他类中执行这个线程的时候当满足某种条件时调用 public 方法修改 loop 的值**

这样就可以让这个线程停下来了

## 线程的方法

### 线程优先级



![image-20241229190152084](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229190152084.png)

~~~java
package TankGame;

public class ThreadFunction {
    public static void main(String [] args) {

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
    int n = 10;
    @Override
    public void run() {
        while(n--> 0)
        {
            System.out.println(n);
        }
    }
}

~~~

## yield() 方法

**作用：**

`yield()` 方法是一个静态方法，它会 **暂停当前正在执行的线程**，让出 CPU 的执行权，给其他具有相同优先级的线程一个执行的机会。但是，`yield()` 方法 **不能保证** 其他线程一定会被执行，因为线程调度器可能会再次选择当前线程继续执行。

**特点：**

- **让出 CPU，但不释放锁：** `yield()` 方法只是让出 CPU 的执行权，不会释放线程持有的锁。
- **不能保证其他线程执行：** 线程调度器可能会忽略 `yield()` 的请求，继续选择当前线程执行。
- **适用于调试和并发测试：** `yield()` 方法通常用于调试和并发测试，帮助发现潜在的并发问题。

## join() 方法

**作用：**

`join()` 方法用于 **等待** 调用 `join()` 方法的线程执行完成。例如，`thread1.join()` 表示当前线程（通常是主线程）会等待 `thread1` 线程执行完毕后，才会继续执行。

也就是 thread1 线程插队 执行完了再到当前的线程

**特点：**

- **阻塞当前线程：** 调用 `join()` 方法的线程会被 **阻塞**，直到被等待的线程执行完成。
- **可以设置等待时间：** `join(long millis)`  方法可以设置等待的 **最大时间**（插队时间），**如果在指定时间内被等待的线程没有执行完成**，当前线程会继续执行。
- **用于线程同步：** `join()` 方法通常用于 **线程同步**，确保线程按照一定的顺序执行。



### interrupt

~~~java
当线程在休眠（调用 Thread.sleep() 方法）时执行了中断 (thread.interrupt())，会发生以下情况：

中断状态设置： 线程的中断状态会被设置为 true。
InterruptedException 异常抛出： sleep() 方法会立即抛出 InterruptedException 异常。
线程从休眠状态退出： 线程会立即从休眠状态中退出，进入 RUNNABLE 状态。
异常处理： 线程会进入 catch 块（如果存在）来处理 InterruptedException 异常。
~~~

## 守护进程

Windows 守护进程(Windows Service)是在后台运行的应用程序，它们通常在 **系统启动时自动运行**，**不需要用户交互**，即使用户未登录也能持续运行。

以下是一些常见的 Windows 守护进程示例：

**系统内置服务：**
Windows Update (自动更新服务)
Windows Defender (防病毒服务)
Print Spooler (打印机后台处理服务)
Task Scheduler (任务计划程序)
Windows Firewall (防火墙服务)

**第三方应用服务：**
MySQL Server (数据库服务)
Apache/Nginx (网页服务器)
Antivirus Software (杀毒软件服务)
Remote Desktop Services (远程桌面服务)
TeamViewer Service (远程控制服务)

**守护进程的特点：**

后台运行，无界面
开机自启动
可以通过服务管理器管理(启动/停止/重启)
具有系统权限
可以设置恢复操作(如崩溃后自动重启)

**创建和管理服务的方法：**

通过服务管理器(services.msc)
使用命令行工具(sc.exe)
通过编程方式(.NET Framework 等)
使用第三方工具(NSSM 等)

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
| **JVM 退出** | 所有工作线程结束时，JVM 退出 | JVM 会在所有非守护线程结束后, 自动终止所有守护线程并关闭 JVM |
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

## 线程的控制

![image-20241229190342981](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229190342981.png)

## 线程的生命周期

1. **新建（New）**：
   - 当线程对象被创建时，它处于新建状态。在这个状态下，线程对象已经 ==实例化==，但还没有开始执行。
2. **就绪（Runnable）**：
   - 当调用线程对象的 `start()` 方法后，线程进入 ==就绪状态==。此时，线程已经准备好运行，**等待 CPU 分配时间片**。就绪状态的线程可能随时被 ==**线程调度器**== 选中，进入运行状态。
3. **运行（Running）**：
   - **线程获得 CPU 时间片开始执行时，进入运行状态**。在这个状态，线程的 `run()` 方法中的代码开始执行。运行状态是就绪状态的线程被线程调度器选中后进入的状态。
4. **阻塞（Blocked）**：
   - 当线程因为等待某个资源（如 I/O 操作完成）而被阻塞时，进入阻塞状态。阻塞状态的线程在资源变得可用后会重新进入就绪状态。
5. **等待（Waiting）**：
   - 线程进入等待状态是因为调用了 `Object.wait()` 方法或 `Thread.join()` 方法，**等待某个条件发生**。在等待状态下，线程不会主动占用 CPU 资源，**只有在其他线程通知或中断时才会重新进入就绪状态**。
6. **计时等待（Timed Waiting）**：  (==包含了等待时间的==)
   - 线程因为调用了带超时参数的方法（如 `Thread.sleep(long millis)`、`Object.wait(long timeout)`、`Thread.join(long millis)` 等）而进入计时等待状态。在计时等待状态下，线程将在指定时间后自动返回就绪状态。
7. **终止（Terminated）**：
   - 线程完成执行或者因异常退出后，进入终止状态。进入终止状态的线程将不再执行，**生命周期结束**



![img](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/%7B3EBBE7DE-BC29-47F6-BCF8-BEECA03C4399%7D)

~~~jAVA
在 Java 中，线程的 <超时等待状态>
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
Thread.sleep(1000);//等待 1000 毫秒


/<"因为不能无限制的等待啊 一定是有一个时间限制的！！！！">/
~~~

~~~java
Thread.yield()方法并不保证
成功让出当前线程的执行时间片。
yield()的作用是提示线程调度器当前
线程愿意让出 CPU 使用权，允许其他同优
先级或者更高优先级的线程获得 CPU 时
间。然而，具体是否会让出，完全取决于
底层操作系统的线程调度算法。
在某些情况下，调用 yie1d()后，当前
线程可能会立刻重新获得 CPU 时间片，继
续执行，而不是让出给其他线程。这主要
是因为线程调度器对 yield()请求的处
理方式不确定。
简单来说，yield()是一个建议而不是
强制命令，不能依赖它来进行线程同步或
控制程序逻辑。
如果需要更加可靠的线程调度控制，通常
可以考虑使用更为明确的线程同步机制。
比如 wait()、notify()、join()或各
种并发包提供的工具类，
~~~







## 线程的同步机制 与 互斥锁



<font color = red size = 6> 同步并不是“同时进行”的意思，而是“协同步调”一一协调指令运行的先后顺序 </font>

**<font size =5  color  =  grape> Java 则允许使用 synchronized 关键字来对某个函数、对象或者代码块上锁 </font>**

Java 则允许使用 synchronized 关键字来对某个函数、对象或者代码块上锁

**key：同步并不是“同时进行”的意思，而是“协同步调”一一协调指令运行的先后顺序**

线程既可以是 **并行执行** 的，也可以是 **并发执行** 的，取决于硬件和操作系统的支持。

- **并发执行**（==Concurrency==）是指多个线程在同一时间段内交替执行，但不一定同时执行。在单核 CPU 上，线程是通过操作系统调度器快速切换执行的，看起来像是并行执行，但实际上是串行的，只是高效地切换任务，使得它们能在短时间内互相交替执行。

- **并行执行**（==Parallelism==）是指多个线程同时在不同的处理器核心上执行，通常发生在多核 CPU 上。每个线程在不同的处理器核心上运行，真正实现了同时执行。

所以，如果你有一个单核 CPU，线程执行通常是并发的；如果是多核 CPU，且操作系统和硬件允许，线程则可以并行执行。

总的来说，**并发** 是为了让多个任务看起来像是同时发生，而 **并行** 则是指真正的同时发生。



## ![image-20241229192803292](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229192803292.png)

![image-20241229192810346](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229192810346.png)

### Synchronized 关键字 和 锁（Lock）

`synchronized* 关键字和 ` **Lock** ` 接口（特别是通过 ` ReentrantLock` 实现的）都是 Java 中用来实现线程同步的机制，它们的作用是确保多个线程在访问共享资源时互斥，以避免数据竞态条件（race condition）。尽管它们都实现了类似的功能，但在使用方式和灵活性上有一些显著的区别。

### 1. **`synchronized` 关键字**
`synchronized` 是一种隐式的同步机制，用来标记方法或代码块，在一个时间内，只允许一个线程访问被 `synchronized` 修饰的资源。

#### 用法：

- **同步方法**：
  
  ```java
  public synchronized void increment() {
      counter++;
  }
  ```
  这里的 `increment()` 方法会在每次调用时自动加锁，确保同一时刻只有一个线程可以执行它。
  
- **同步代码块**：
  ```java
  public void increment() {
      synchronized(this) {
          counter++;
      }
  }
  ```
  `synchronized(this)` 表示锁定当前对象 `this`，确保只有一个线程可以进入这段代码块进行操作。

#### 特点：
- **隐式加锁和释放锁**：当进入同步方法或同步代码块时，自动加锁；当执行完同步代码块或方法后，自动释放锁。
- **不可中断**：`synchronized` 是由 JVM 管理的锁机制，不能被手动中断。
- **锁粒度固定**：`synchronized` 锁定的是一个对象或类，适用于方法或者代码块，且锁粒度不够灵活。

#### 示例：使用 `synchronized` 解决计数器问题
```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // 创建两个线程，分别执行 increment 操作
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
```

### 2. **`Lock` 接口（`ReentrantLock`）**
`Lock` 接口提供了一种比 `synchronized` ==更加灵活== 的同步机制，主要通过 ==显式== 地加锁和解锁来控制线程的访问。

#### 用法：
- `Lock` 接口通过 `lock()` 方法手动加锁，`unlock()` 方法手动释放锁。

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private final Lock lock = new ReentrantLock(); // 创建一个 ReentrantLock

    public void increment() {
        lock.lock();  // 手动加锁
        try {
            count++; // 访问共享资源时
        } finally {
            lock.unlock();  // 确保解锁
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // 创建两个线程，分别执行 increment 操作
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
```

#### 特点：
- **手动加锁与解锁**：必须显式调用 `lock()` 方法加锁，调用 `unlock()` 方法解锁。由于锁的释放是显式的，它比 `synchronized` 更灵活。
- **可中断**：`Lock` 提供了 `lockInterruptibly()` 方法，允许线程在等待锁的时候被中断。
- **锁的公平性**：`ReentrantLock` 可以通过构造函数设置公平锁（`new ReentrantLock(true)`），确保线程按照请求锁的顺序获得锁，这对于某些应用程序非常重要。
- **死锁避免**：`Lock` 提供了更好的死锁控制，能够尝试获取锁（`tryLock()`）而不是一直等待。

### 3. **`synchronized` 和 `Lock` 的主要区别**

| 特性             | `synchronized`                               | `Lock`（如 `ReentrantLock`）                              |
| ---------------- | -------------------------------------------- | --------------------------------------------------------- |
| **加锁和释放锁** | 自动加锁，自动释放锁（通过方法或代码块退出） | 手动加锁，通过 `lock()` 方法加锁，`unlock()` 方法释放锁   |
| **可中断性**     | 不支持中断                                   | 支持中断（`lockInterruptibly()`）                         |
| **锁的公平性**   | 不支持公平性（随机选择线程）                 | 支持公平锁，可以选择是否公平（`new ReentrantLock(true)`） |
| **锁的重入性**   | 支持重入锁（同一线程可以多次进入）           | 支持重入锁（同一线程可以多次进入）                        |
| **死锁避免**     | 无法直接控制死锁                             | 提供了 `tryLock()` 方法，避免死锁                         |
| **适用场景**     | 简单的同步操作，适用于单一的锁粒度           | 复杂的同步场景，尤其是需要灵活控制锁时                    |
| **性能**         | 性能较低，尤其在竞争激烈的情况下             | 通常性能较好，尤其是在复杂场景下                          |

### 4. **使用场景**
- **`synchronized`**：适用于简单的同步场景，不需要灵活控制锁的场合。适合小范围的同步操作。
- **`Lock`**：适用于需要更多控制的场景，尤其是复杂的同步逻辑。比如当你需要：
  - 可中断的锁（防止线程长时间等待）。
  - 公平锁（按照线程请求锁的顺序来分配）。
  - 尝试获取锁（防止死锁）。
  
### 总结
- **`synchronized`** 简单且易于使用，但它的灵活性较差，无法提供像 `Lock` 那样丰富的控制方法。
- **`Lock`** 提供了更多功能和灵活性，尤其在需要复杂同步策略或要求中断锁等待时更为适用。

在选择使用 `synchronized` 还是 `Lock` 时，通常会根据应用程序的复杂性和对锁管理的需求来决定。如果同步逻辑简单，`synchronized` 已经足够；如果需要 **更高的灵活性和控制**，则推荐使用 `Lock`。

### 死锁

**死锁**（Deadlock）是指在并发程序中，两个或多个线程在执行过程中，由于争夺资源而造成一种相互等待的状态，从而导致这些线程都无法继续执行。

### 互斥锁

![](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250111175929914.png)

![image-20250111180534664](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250111180534664.png)

### 

**如果是使用继承实现的线程的话 就不能用this了 因为这样每个实例都是new一个都是不同的实例 所以要用class为锁对象**

#### **1. 使用 `this` 作为锁对象的行为**

代码：

```java
class Counter implements Runnable {
    private int count = 0; // 每个实例有自己的计数器

    @Override
    public void run() {
        synchronized (this) { // 锁当前实例
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
        }
    }
}

public class SynchronizedThisExample {
    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        // 两个线程共享 counter1
        Thread t1 = new Thread(counter1, "Thread 1");
        Thread t2 = new Thread(counter1, "Thread 2");

        // 两个线程共享 counter2
        Thread t3 = new Thread(counter2, "Thread 3");
        Thread t4 = new Thread(counter2, "Thread 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
```

行为分析：

Thread t1 和 Thread t2 共享锁 counter1 的实例锁。线程会依次进入同步块，count 是 counter1 独有的。

Thread t3 和 Thread t4 共享锁 counter2 的实例锁。线程会依次进入同步块，count 是 counter2 独有的。

互不干扰：counter1 和 counter2 是不同实例，各自的锁互相独立，count 变量也不共享。





（）里也可以放个object因为同一个实例成员一样



![image-20250111184739922](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250111184739922.png)

![image-20250111184752081](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250111184752081.png)

#### **2. 使用 `Class.class` 作为锁对象的行为**



```java
class Counter implements Runnable {
    private static int count = 0; // 所有实例共享静态计数器

    @Override
    public void run() {
        synchronized (Counter.class) { // 锁整个类
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
        }
    }
}

public class SynchronizedClassExample {
    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();


        Thread t1 = new Thread(counter1, "Thread 1");
        Thread t2 = new Thread(counter1, "Thread 2");

   
        Thread t3 = new Thread(counter2, "Thread 3");
        Thread t4 = new Thread(counter2, "Thread 4");
        // 所有不同实例 counter1 counter2 都共享一个类级别对象锁

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
```

**行为分析**：

- 所有**线程**都竞争 `Counter.class` 的类级锁

  ：

  - 即使**线程属于不同实例**（如 `counter1` 和 `counter2`），它们都需要等待锁 `Counter.class` 释放。
  - 计数器 `count` 是静态变量，所有实例共享。

- **全局互斥**：线程需要按顺序进入同步块。



![image-20250111184954441](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20250111184954441.png)

### 释放锁

在 Java 中，对于 `synchronized` 同步块或方法，锁的释放与获取有其特定的行为和规则：

### 会释放锁的情况：

1. **正常执行完成**：
   - 当同步代码块或方法内的代码正常执行完成时，线程会自动释放锁。
2. **抛出异常**：
   - 如果同步代码块或方法内抛出异常（或是error），Java 虚拟机会确保锁被释放，以避免死锁等问题 （**或者break return** ）
3. **调用 `wait()` 方法**：
   - 当线程在同步块或方法内调用 `wait()` 方法时，它会释放锁并进入等待状态，直到其他线程调用相同对象的 `notify()` 或 `notifyAll()` 方法来唤醒它。
4. **执行 `Thread.sleep()` 或 `Thread.yield()`**：
   - 虽然 `Thread.sleep()` 和 `Thread.yield()` ==不会释放锁==，但它们能够==允许其他线程执行==，从而==间接==释放了锁的竞争。

---



# Java API （类库）



## 包装类（wrapper class）

**定义：基本数据类型对应的引用数据类型**

![image-20241114191405766](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114191405766.png)

**为何要包装类？**
由于集合要使用对象 ，方法传参参数大都是对象

char （2 字节 in java） -- Character

int -- Integer

byte - Byte (其余与此同)

~~~java
package com.Senjay.basicAPI;

public class WrapperClass {
    public static void main(String [] args) {
        {
            Integer a = new Integer(120);// 过时
            Integer c = new Integer(120);
            System.out.println(a == c ); // false
            // 双等号 比较地址
        }
        {
            Integer a = Integer.valueOf(127);
            Integer c = Integer.valueOf(127);/// 写这个或者自动装箱
            System.out.println(a == c ); // true
        }
        // -128 ~ 127 缓存（底层提前把这些范围的数的已经创建了对象，其他的 就是相当于 new 出来一个新的）
        {
            Integer a = Integer.valueOf(527);
            Integer c = Integer.valueOf(527);
            System.out.println(a == c ); // false
            
        }
    }
}

~~~

Integer 的 equals 方法已经内置封装好了

###  Integer 的自动装/拆箱

~~~java
package com.Senjay.basicAPI;

import java.util.Scanner;

public class WrapperClass {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        {
            Integer i = 10; // 自动装箱       // 底层使用 valueof 方法  Intefer.valueof(10)
            int copy_i = i; // 自动拆箱  // 底层解构
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
        // 8 种包装类中，除了 character 中都有 parsexxx 方法！！！
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

# StringBuffer

![image-20241229180231103](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241229180231103.png)

## String 

![image-20241114214054606](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114214054606.png)

![image-20241229180844139](C:/Users/33813/AppData/Roaming/Typora/typora-user-images/image-20241229180844139.png)

查看 API 文档即可

link to [API 文档](https://github.com/kasahuki/Backend/blob/main/JavaSE/API.md)

### string 注意事项

**string a string b 保存在字符串常量池当中（在字符串常量池中指向同一对象）**

intern方法也和字符串常量池有关！！

== 对于引用数据类型就是比较引用地址 ！！ 

**equals 如果没有重写就是和== 一样！！**

对于Stingbuffered 类 .append 方法无论加什么 == 原来 都是 True ！ 



`a==b` --> true

## System 类

### exit



~~~java
package com.Senjay.basicAPI;

public class SystemTest {
    public static void main(String [] args)
    {
//        System.exit(0); 表示当前 JVM 虚拟机是正常停止

        // 参数：status（状态码）
        System.exit(1);
        // System.exit(1);
        //System.exit(1); 语句在 Java 程序中用于终止 Java 虚拟机（JVM），
        //并且返回一个状态码给操作系统。这个状态码帮助操作系统和其他程序判断程序的退出状态。
        // 状态码 1 通常表示程序是由于某种错误或异常情况而非正常结束。
        System.out.println("Hello World");

    }

}
~~~

### currentTimeMillis

~~~java
package com.Senjay.basicAPI;

import java.util.*;

public class Time {
    public static  void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        long time_start = System.currentTimeMillis();
        int n = sc.nextInt();
        for(int i = 2; i*i <= n; i++) // 注意如果不写方法里的话 3 这个情况就没有考虑到了 return 的特性
        {
            if(n%i == 0)
            {
                System.out.println("false");
                break;
            }
            System.out.println("True");

        }
        long time_end = System.currentTimeMillis();
        System.out.println(time_end - time_start);

//        long time1 = System.currentTimeMillis();
//        for(int i = 0; i < 1000; i++)
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
    public static void main(String [] args) {
        int arr [] = {1,5,8,9,8,4,6,5,7,7,2}; // length: 11
        int copy_arr [] = new int [10];
        System.arraycopy(arr,1, copy_arr,2, arr.length-3);
                        //  数据源 数据源索引  数据承受者  数据目的地索引 拷贝个数
        // -----------------------------------------------------------------------------
//        System.out.println(copy_arr);
        for(int i = 0; i < copy_arr.length; i++)
            System.out.print(copy_arr [i]);
        // Java 中数组是一个对象
        // 所以具有很多方法 现用现查即可


    }
}
~~~

### Runtime

**Runtime 类主要与 Java 程序的运行时环境有关。它提供了与程序运行时环境交互的方法，包括 内存管理、系统属性访问、执行外部程序**(shell 命令)

![image-20241114170256358](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241114170256358.png)

~~~java
package com.Senjay.basicAPI;

import java.io.IOException;

public class RuntimeDemo {
    public static void main(String [] args) throws IOException {
       Runtime r1 = Runtime.getRuntime();
       // 首先先创建 Runtime 对象
        // Runtime 方法
        System.out.println(r1.availableProcessors());
        // 打印 cpu 核心数
        // 有关 JVM 的内存
        System.out.println(r1.freeMemory() );// 剩余可用
        System.out.println(r1.totalMemory());// 表示 JVM 当前实际向操作系统申请的内存量
        // 这是实际分配的内存，会随程序需求动态变化
        System.out.println(r1.maxMemory()); // 表示 JVM 能够使用的最大内存上限 固定值
        System.out.println(r1);
//        r1.exec("notepad");
        // 还可执行 shell 指令 // 可用来延时关机类似的功能



    }
}

~~~



## Object

==顶级父类== 只有 **无参构造方法**

对于 **System.out.println**    ==打印== 会默认调用对象的 **toString** 方法！！！

**由于子类使用方法时会先找自身有无，没有就像上（父类中）查找**

![47080478a8620c0f8e383cfd86139b2](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/47080478a8620c0f8e383cfd86139b2.jpg)

**方法调用者不可为 null**

**equals (obj (要先做非空判断) 	isNull（obj） nonNull（...）**

```tex
默认(没有重写)的equals比较（从Object类继承）

public class Object {
    public boolean equals(Object obj) {
        return (this == obj);
    }
}
默认比较对象的引用（内存地址）
"等同于"使用 == 运算符
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
        
        // 2. 判断是否为 null
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
    public static void main(String [] args) {
        Pattern p = Pattern.compile("adsf"); // 模式串
        Matcher m = p.matcher("adsf45sadsfd"); // 去和这个模式串匹配的匹配串
        while (m.find()) {
//            System.out.println(m.group(0)); 表示匹配内容 
            // 里头参数 表示捕获组 0 就表示全部捕获 i：第 i 个捕获组 
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

创建 springboot 项目 

注意 springboot 现在不支持 jdk1.8 了

所以要在 server URL 中修改为阿里云镜像

![image-20241109170551143](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109170551143.png)

1. **Java 版本**：这是指 Java 平台和语言的版本，例如 **Java 8、Java 11、Java 17** 等。这些版本标志着 Java 语言及其标准库的特性和功能集。这些版本的发布通常伴随新的语言特性、API 增强、性能改进和安全修复。
2. **JDK 版本**：JDK（Java Development Kit）是用来开发和运行 Java **应用程序的工具包**。JDK 包含了 JRE（Java Runtime Environment）、编译器（javac）、调试工具、以及其他开发所需的工具。J **DK 版本号通常与 Java 版本号一致**，例如 JDK 8 对应 Java 8，JDK 11 对应 Java 11。

![image-20241109153915617](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109153915617.png)

1. **项目的基本信息**：
   - **Group**：项目的组织名称，通常是你的公司或团队的域名反转形式（例如 `com.example`）。
   - **Artifact**：项目名称，例如 `demo`。
   - **Type**：选择项目 **打包类型**，通常选择 `Maven` 或 `Gradle`。
   - **Language**：选择项目语言，通常选择 `Java`。
   - **Spring Boot Version**：选择合适的 Spring Boot 版本，通常选择最新的稳定版本。

然后选择依赖项

[link to maven ]() 和 maven 有什么联系

~~~java
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.senjay(group)
│   │   │       └── DemoApplication.java  // 主启动类
│   │   ├── resources/
│   │   │   ├── static/                   // 静态资源文件夹（如 HTML、CSS、JS）
│   │   │   ├── templates/                // 模板文件夹（如 Thymeleaf、Freemarker）
│   │   │   ├── application.properties    // 应用配置文件
│   │   │   └── application.yml           // 可选的 YAML 格式的配置文件
│   └── test/
│       ├── java/
│       │   └── com/example/demo/
│       │       └── DemoApplicationTests.java  // 自动生成的测试类
├── .gitignore                             // Git 忽略文件
├── HELP.md                                // 帮助文件
├── mvnw                                   // Maven wrapper 的执行脚本 (Unix)
├── mvnw.cmd                               // Maven wrapper 的执行脚本 (Windows)
├── pom.xml                                // Maven 构建文件
└── README.md                              // 项目说明文件
~~~



![image-20241109153427932](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241109153427932.png)

自带一个入口类 运行入口类启动项目 



## idea 快捷键

 **更改热键 ： ${}**  

~~~tex
1.查看类或者接口下的继承关系：ctrl + h，查看类下的所有方法：alt + 7，或者 ctrl + f12。

2.生成构造、toString 等方法：alt + insert  / alt + fn + insert

3.快速交换两行代码：CTRL + shitf + up/down

4.查找所以类或者接口：CTRL + n/双击 shift

5.按住 alt 键+鼠标左键   或者鼠标滚轮  ： 可以整一列操作数据。	

6.CTRL + d:     将上一行语句复制一份到下一行。

7.ctrl + alt + L ：自动格式化代码   CTRL  + L 查找替换

8. 100.fori    : 快速生成 i < 100 的 for 循环语句。遍历数组用数组名.fori。遍历字符串字符串名.length().fori。forr 是倒着遍历。选中内容.sout 可以将其输出

9.ctrl + alt + v \ alt + 回车 ：自动生成左边

10.ctrl + p ： 查看函数参数

11. CTRL + alt + m ： 自动抽取方法

12. shift + f6 ：批量更改相同名字的变量、方法、类等。

13.ctrl + alt + t ：对选中内容加 while、if 等包裹起来。

14.ctrl + shitf + u : 将选中内容变成大写（upper），再次按下将变回小写。

15.ctrl + alt + 左键 ： 进入方法后退出（回到原来的位置）

16.选择内容 ctrl + r ：然后在输入新的内容可以一键替换

17.ctrl + b 跟进，等同 ctrl + 左键     ，ctrl + alt +  左键  ： 回退上一步查看（ctrl + alt + f7 也行）

18. Ctrl + shift + R ，搜索所有位置内容，可以进行替换。

19.shift + f6 , 选中文件后按这个就可以进行重命名操作。

20.ctrl + shift + alt + u：选中类后按下，展示继承结构图。

21.shift + f4 打开独立小窗口

22. ${alt+r} rename

23. ${generate} ctrl + , 
24. ${run} alt + p(play)
25. alt + enter 快速纠错

26. alt + 1 打开项目栏界面
27. ctrl + tab 类似 alt tab 对文件切换 
28.ctrl + shift +enter 跳过括号以及大括号的书写 while/if 这样需要括号的
29.ctrl + d 复制该行到下一行
30.shift + 滚轮 横向移动	
31.ctrl + g 跳转指定行
32.ctrl + y 删除整行
33.ctrl + shift + a 全局快捷搜索

~~~





# JDBC (了解即可)

| -------> [link to Mybatis in SSM(include Springboot)]() <----------- |
| ------------------------------------------------------------ |

![image-20241122212501773](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122212501773.png)

## JDBC 架构



1. 核心组件

a) JDBC API（java.sql 包）

- Connection：数据库连接
- Statement：SQL 语句执行
- ResultSet：结果集
- DriverManager：驱动管理

b) JDBC 驱动管理器

- 管理数据库驱动程序
- 建立数据库连接
- 处理多个驱动程序

c) JDBC 驱动程序

- 实现 JDBC 接口
- ==与具体数据库交互==
- 处理数据转换

包含各种数据的驱动程序 e.g. mysql 驱动程序

**datagirp 就是 java 程序编写的所以在创建 mysql 时要安装 jdbc （mysql）驱动程序 负责充当 java 应用程序和数据库协议之间的翻译桥梁！**



## JDBC 组件（API）



![image-20241122212510122](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122212510122.png)

![image-20241122212517500](https://cdn.jsdelivr.net/gh/kasahuki/os_test@main/img/image-20241122212517500.png)



## CRUD

**（其余的增删改同理）** 代码了解即可

~~~java
package net.senjay;

import java.sql.*;

public class JDBCdemo {
    public static void main(String [] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. 加载驱动 jdbc 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立连接
            String url = "jdbc:mysql://localhost: 3306/test"; // sql 地址
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);// 连接对象

            // 3. 创建 PreparedStatement
            String sql = " SELECT * FROM test1 WHERE id = ?"; // 创建 sql 语句
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





## 



# java 路径问题

在 Java 或 Maven 项目中，当你直接使用文件名（不包含路径）来访问文件时，Java 会从当前工作目录（Current Working Directory）开始查找文件。这个行为适用于使用 FileInputStream、FileOutputStream 或 File 类等直接操作文件系统的情况。以下是详细解释：



不同运行环境下的当前工作目录：

 

a. 在 IDE（如 Eclipse 或 IntelliJ IDEA）中运行：

- 通常是项目的 ==根目录==（包含 src 文件夹和 pom.xml 的目录）。



b. 通过命令行运行 JAR 文件：

- 是执行 java -jar 命令的目录。

c. 在 Web 应用服务器中运行：

- 可能是服务器的某个特定目录，取决于服务器配置。

1. ==Maven 项目结构的影响：==
   - Maven 的标准目录结构（如 src/main/resources）主要影响构建过程和类路径资源的加载。
   - 它不直接影响文件系统操作中的相对路径解析。

|                       在 Maven 项目中                        |      |
| :----------------------------------------------------------: | ---- |
| src/main/java 中的源代码编译后会放在 target/classes。==src/main/resources== 中的资源文件也会复制到 target/classes。target/classes 成为运行时的类路径根。 |      |



| Maven 目录位置                    | 包含的文件类型                                               |
| :-------------------------------- | :----------------------------------------------------------- |
| 项目根目录                        | - pom.xml (Maven 项目文件) - README.md - .gitignore - Dockerfile - docker-compose.yml - 构建脚本 (.sh, .bat) - 日志文件 (.log) |
| src/main/java                     | - Java 源代码文件 (.java)                                    |
| src/main/resources                | - 配置文件 (.properties, .xml, .yml, .json) - 日志配置文件 - 国际化资源文件 - 静态资源 (对于非 Web 项目) - 数据文件 (CSV, JSON 用于应用) - 数据库迁移脚本 - API 文档文件 (如 Swagger) |
| src/main/webapp (仅用于 Web 项目) | - HTML 文件 - JSP 文件 - CSS 文件 - JavaScript 文件 - 图片文件 - web.xml (部署描述符) - 其他 Web 资源 |
| src/test/java                     | - 测试 Java 源代码文件 - 单元测试文件                        |
| src/test/resources                | - 测试配置文件 - 测试数据文件 - 测试特定的资源文件           |
| target                            | - 编译后的 .class 文件 - 打包后的 JAR/WAR 文件 - 生成的报告和文档 |
| target/classes                    | - 编译后的主要类文件 - 从 src/main/resources 复制的资源文件  |
| target/test-classes               | - 编译后的测试类文件 - 从 src/test/resources 复制的测试资源文件 |

[Maven link to 项目结构管理](Maven.md)

