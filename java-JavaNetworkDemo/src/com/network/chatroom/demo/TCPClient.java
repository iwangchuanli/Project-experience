package com.network.chatroom.demo;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TCPClient {

    static private Socket clientSocket;

    public TCPClient() {}

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String serverIP;

        System.out.println("请设置服务器IP：");
        serverIP = scanner.next();
        clientSocket = new Socket(serverIP, 6789);
        TCPClient client = new TCPClient();
        client.start();
    }

    public void start() {
        try {
            Scanner scanner = new Scanner(System.in);
            setName(scanner);

            // 接收服务器端发送过来的信息的线程启动
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ListenrServser());

            // 建立输出流，给服务端发信息
            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);

            while(true) {
                pw.println(scanner.nextLine());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setName(Scanner scan) throws Exception {
        String name;
        //创建输出流
        PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"),true);
        //创建输入流
        BufferedReader br = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));

        while(true) {
            System.out.println("请创建您的昵称：");
            name = scan.nextLine();
            if (name.trim().equals("")) {
                System.out.println("昵称不得为空");
            } else {
                pw.println(name);
                String pass = br.readLine();
                if (pass != null && (!pass.equals("O K"))) {
                    System.out.println("昵称已经被占用，请重新输入：");
                } else {
                    System.out.println("昵称“"+name+"”已设置成功，可以开始聊天了");
                    break;
                }
            }
        }
    }

    // 循环读取服务端发送过来的信息并输出到客户端的控制台
    class ListenrServser implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                String msgString;
                while((msgString = br.readLine())!= null) {
                    System.out.println(msgString);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}

