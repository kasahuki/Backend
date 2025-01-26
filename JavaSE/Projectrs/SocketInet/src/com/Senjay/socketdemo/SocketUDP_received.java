package com.Senjay.socketdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketUDP_received {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086); // 接收的端口号
        byte [] temp = new byte[1024];
        DatagramPacket dp = new DatagramPacket(temp, temp.length);

        while (true) {
            ds.receive(dp);
            byte [] data = dp.getData();
            InetAddress address = dp.getAddress();
            int port = dp.getPort();
            int length = dp.getLength();
            String received = new String(data, 0, length);
            System.out.println("Address：" +address.getHostAddress() +" port:"+port+" length:"+length);
            System.out.println("received:"+received);
        }
    }

}
