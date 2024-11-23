package iodemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class InputStream2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("testdemo/src/iodemo/test2");
        FileOutputStream fos = new FileOutputStream("testdemo/src/iodemo/test1");
        byte[] buf = new byte[15]; // 不能有空字节被写入进去！！！
        // 开一定大小的字节数组
        int len;
        try {
            len = fis.read(buf);
            System.out.println(new String (buf, 0, len));
            System.out.println(len); // 返回字节数 汉字*3 英文*1（byte）
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
