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
        r1.exit(0);// 等效于System.exit(0)
        System.out.println(r1);
//        r1.exec("notepad");
        // 还可执行 shell指令 // 可用来延时关机类似的功能




    }
}
