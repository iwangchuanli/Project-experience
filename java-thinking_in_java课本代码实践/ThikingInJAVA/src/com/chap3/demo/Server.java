package com.chap3.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8080);
        //DatagramPacket(byte[] buf, int length)
        //          构造 DatagramPacket，用来接收长度为 length 的数据包。

        DatagramPacket packet = new DatagramPacket(new byte[1024],1024);
        socket.receive(packet);

        byte[] data = packet.getData();
        int len = packet.getLength();
        String str = new String(data,0,len);
        System.out.println(str);
        socket.close();
    }

}
