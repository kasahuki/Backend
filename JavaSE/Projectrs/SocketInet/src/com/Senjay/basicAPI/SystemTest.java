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
