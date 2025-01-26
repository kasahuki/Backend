package com.Senjay.socketdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTCP_send {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("192.168.101.116",10086);// 创建对象的同时来连接服务端
        OutputStream os = socket.getOutputStream();// 获取输出流通道
        String conversation = sc.nextLine();

        os.write(conversation.getBytes());
//        os.close(); 没有必要 因为关闭通道这个也跟着关了
        socket.close(); // 断开连接


    }
}
