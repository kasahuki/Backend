package com.Senjay.basicAPI;

import java.util.Scanner;

public class WrapperClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        {
            Integer i = 10; // 自动装箱       // 底层使用valueof方法  Intefer.valueof(10)
            int copy_i = i; // 自动拆箱  // 底层解构
        }

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
            String s = sc.nextLine(); // 遇到回车后停止
            System.out.println(s);
        }

    }
}
