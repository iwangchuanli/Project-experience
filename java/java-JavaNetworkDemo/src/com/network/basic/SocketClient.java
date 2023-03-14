package com.network.basic;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws UnknownHostException, IOException {
        int port = 7000;
        String host = "localhost";
        // 创建一个套接字并将其连接到指定端口号
        Socket socket = new Socket(host, port);

        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));//文件读写，通过socket对象
        DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));

        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        while (!flag) {

            System.out.println("请输入正方形的边长:");
            double length = sc.nextDouble();
            dos.writeDouble(length);//通过socket对象写
            dos.flush();//缓冲刷新
            double area = dis.readDouble();//读

            System.out.println("服务器返回的计算面积为:" + area);

            while (true) {

                System.out.println("继续计算？(Y/N)");
                String str = sc.next();
                if (str.equalsIgnoreCase("N")) {
                    dos.writeInt(0);
                    dos.flush();
                    flag = true;
                    break;
                } else if (str.equalsIgnoreCase("Y")) {
                    dos.writeInt(1);
                    dos.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}