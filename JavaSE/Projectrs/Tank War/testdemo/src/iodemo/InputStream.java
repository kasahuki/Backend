package iodemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("testdemo/src/iodemo/test2");
        FileOutputStream fos = new FileOutputStream("testdemo/src/iodemo/test1");
        int b;
        while((b=fis.read())!=-1)
        {
            fos.write(b);
            //  Byte by byte 读取/写入
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
        // 注意开关顺序
    }
}
