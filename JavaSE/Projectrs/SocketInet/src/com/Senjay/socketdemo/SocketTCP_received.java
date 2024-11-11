package com.Senjay.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP_received {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086); // 指名接收的端口号
        Socket socket = ss.accept();// 监听客户端的连接 等待并接受客户端的连接请求。这个方法会阻塞，直到有客户端连接。
        InputStream is = socket.getInputStream();

        InputStreamReader isr = new InputStreamReader(is); // 将字节流转化为字符流
        BufferedReader br = new BufferedReader(isr);

        // 获取通道的输入流
        int b;
        while ((b = br.read()) != -1) {
            // 对于字节流
            // is.read() 方法从输入流中读取一个字节的数据。
            // 如果读取成功，它返回一个 0 到 255 之间的整数，表示读取的字节值。
            // 如果到达流的末尾，它返回 -1。

            // 对于字符流
            // 这是一个 while 循环，它会持续执行，直到从流中读取到 EOF（End of File）标记。
            // br.read() 方法：
            // 从 BufferedReader 中读取一个字符。
            // 返回值是该字符的 Unicode 编码点（一个整数），范围是 0 到 65535。
            // 如果到达流的末尾，返回 -1。

            System.out.print((char) b); // 一个字节一个字节地获取 如果是中文的要怎么办
        }
//        is.close();
        socket.close();
    }
}
