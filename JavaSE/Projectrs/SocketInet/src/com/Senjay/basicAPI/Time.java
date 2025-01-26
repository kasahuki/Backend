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
