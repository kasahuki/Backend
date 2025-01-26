package com.Senjay.io_modules;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IoDemo {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos2 = new FileOutputStream("SocketInet/src/com/Senjay/io_modules/test1");// 在当前工程目录下 相对路径
        FileOutputStream fos = new FileOutputStream("D:\\桌面\\Backend\\JavaSE\\Projectrs\\SocketInet\\src\\com\\Senjay\\io_modules\\test2");
        // 绝对路径
        try {
            fos.write(58);
            fos2.write(97);
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

