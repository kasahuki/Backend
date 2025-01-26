package com.Senjay.basicAPI;

public class ArrayCopy {
    public static void main(String[] args) {
        int arr[] = {1,5,8,9,8,4,6,5,7,7,2}; // length:11
        int copy_arr[] = new int[10];
        System.arraycopy(arr,1,copy_arr,2,arr.length-3);
                        //  数据源 数据源索引  数据承受者  数据目的地索引 拷贝个数
//        System.out.println(copy_arr);
        for(int i=0;i<copy_arr.length;i++)
            System.out.print(copy_arr[i]);
        // Java 中数组是一个对象
        // 所以具有很多方法 现用现查即可


    }
}