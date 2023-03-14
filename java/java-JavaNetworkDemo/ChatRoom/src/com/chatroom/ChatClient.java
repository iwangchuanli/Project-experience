package com.chatroom;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端
 */
public class ChatClient extends Thread {
    //static private Socket clientSocket;
    static Socket clientSocket;
    static Scanner scanner = new Scanner(System.in);

    //main
    public static void main(String[] args) throws Exception {
        String serverIP;
        System.out.println("请设置服务器IP：");
        serverIP = scanner.next();
        clientSocket = new Socket(serverIP, 6789);
        ChatClient client = new ChatClient();
        client.start();
    }

    //用户线程
    public void start() {
        try {
            BufferedReader br = null;
            PrintWriter pw = null;
            setName(scanner);
            // 接收服务器端发送过来的信息的线程启动
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ListenServer());
            write(pw);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //设置用户昵称
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
            name = scan.next();
            if (name.trim().equals("")) {
                System.out.println("昵称不得为空 ");
            } else {
                pw.println(name);
                String pass = br.readLine();
                if (pass != null && (!pass.equals("OK"))) {
                    System.out.println("昵称已经被占用，请重新输入：");
                } else {
                    System.out.println("昵称“"+name+"”已设置成功，可以开始聊天了");
                    break;
                }
            }
        }
    }
    //选定用户发送信息
    //向服务器循环写信息
    public void write(PrintWriter pw) throws IOException {
        pw = new PrintWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        while (true) {
            pw.println(scanner.next());
        }
    }

    //循环读取服务器端的信息
    class  ListenServer implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                String msgString;
                while ((msgString = br.readLine()) != null) {
                    System.out.println(msgString);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}



