package com.network.basic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    public static void main(String[] args) throws IOException {

        int port = 7000;
        int clientNo = 1;

        ServerSocket serverSocket = new ServerSocket(port);//服务器socket对象创建

        // 创建线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        try {
            while (true) {
                Socket socket = serverSocket.accept();//服务器对象接收到客户端对象   accept 侦听并接收socket套接字
                exec.execute(new SingleServer(socket, clientNo));//创建一个新的连接线程
                clientNo++;
            }
        } finally {
            serverSocket.close();
        }
    }
}

class SingleServer implements Runnable {//服务器线程

    private Socket socket;
    private int clientNo;

    public SingleServer(Socket socket, int clientNo) {
        this.socket = socket;
        this.clientNo = clientNo;
    }

    @Override
    public void run() {
        try {
            //在线程池方法中已经连接好套接字并进行接收
            DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));//文件读写建立
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));

            do {//循环处理客户端信息
                double length = dis.readDouble();//读客户端传来的数据
                System.out.println("从客户端" + clientNo + "接收到的边长数据为：" + length);
                double result = length * length;
                dos.writeDouble(result);//写回去
                dos.flush();
            } while (dis.readInt() != 0);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("与客户端" + clientNo + "通信结束");//循环接收，无数据接收，连接断开
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
