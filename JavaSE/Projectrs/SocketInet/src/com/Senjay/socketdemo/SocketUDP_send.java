package com.Senjay.socketdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
// 192.168.101.116
public class SocketUDP_send {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();

        while (true) {
            String str =sc.nextLine();
            if("404".equals(str))
                break;
            byte [] data = str.getBytes();
            InetAddress address = InetAddress.getByName("192.168.101.116");
            int port =10086;

            DatagramPacket dp = new DatagramPacket(data,data.length,address,port);

            ds.send(dp);
        }


    }
}
