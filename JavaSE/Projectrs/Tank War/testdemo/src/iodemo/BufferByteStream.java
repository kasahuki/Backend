package iodemo;

import java.io.*;

public class BufferByteStream {
    public static void main(String[] args) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("testdemo/src/iodemo/test1"));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("testdemo/src/iodemo/test2"));
        byte [] buf = new byte[1024];
        int len;
        while ((len = bis.read(buf))!=-1) {
//            System.out.println(new String(buf, 0, len));
            System.out.println(len);
        }
        try {
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
