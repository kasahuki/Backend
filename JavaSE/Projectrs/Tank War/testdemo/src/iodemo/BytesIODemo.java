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
