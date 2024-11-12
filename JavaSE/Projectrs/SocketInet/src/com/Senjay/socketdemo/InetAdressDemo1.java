package com.Senjay.socketdemo;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class InetAdressDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress addr =InetAddress.getByName("senjay");
        System.out.println(addr);
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

    }
}
