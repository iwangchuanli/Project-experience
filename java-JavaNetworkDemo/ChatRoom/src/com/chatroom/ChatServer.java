package com.chatroom;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务器端
 */
public class ChatServer {
    private ServerSocket serverSocket;//服务器Socket对象创建

    private ExecutorService exec;//线程池
    // 利用集合Map存放客户端之间私聊的信息
    private Map<String,PrintWriter> storeInfo;

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(6789);//服务器端口  设置服务器对象
            storeInfo = new HashMap<String, PrintWriter>();//用户信息集合
            exec = Executors.newCachedThreadPool();//线程池
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //main
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }

    //建立用户线程 存放线程池中 循环等待创建
    public void start(){
        try {
            while(true) {
                System.out.println("等待客户端连接... ... ");
                Socket socket = serverSocket.accept();
                // 获取客户端的ip地址
                InetAddress address = socket.getInetAddress();
                System.out.println("客户端：“" + address.getHostAddress() + "”连接成功！ ");
                /*
                 * 启动一个线程，由线程来处理客户端的请求，这样可以再次监听
                 * 下一个客户端的连接
                 */
                exec.execute(new ListenClient(socket)); //通过线程池来分配线程
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //存储用户线程信息  以Map形式存入集合中
    private void putIn(String key,PrintWriter value) {
        synchronized(this) {
            storeInfo.put(key, value);
        }
    }
    //删除用户信息
    private synchronized void remove(String  key) {
        storeInfo.remove(key);
        System.out.println("当前在线人数为："+ storeInfo.size());
    }

    //发送信息给指定的客户端
    private synchronized void sendToSomeone(String name,String message) {
        PrintWriter pw = storeInfo.get(name); //将对应客户端的聊天信息取出作为私聊内容发送出去
        if(pw != null) pw.println(message);
    }
    // 向所有客户端广播
    private synchronized void sendToAll(String message) {
        for(PrintWriter out: storeInfo.values()) {
            out.println(message);
        }
    }
    //用户线程单项创建
    class ListenClient implements Runnable{
        private Socket socket;
        private String name;
        public ListenClient(Socket socket) {
            this.socket = socket;
        }
        // 获取昵称
        private String getName() throws Exception {
            try {
                //服务端的输入流读取客户端发送来的昵称输出流
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8"));
                //服务端将昵称验证结果通过自身的输出流发送给客户端
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
                //读取客户端发来的昵称
                while(true) {
                    String nameString = br.readLine();
                    if ((nameString.trim().length() == 0) || storeInfo.containsKey(nameString)) {
                        pw.println("FAIL");
                    } else {
                        pw.println("OK");
                        return nameString;
                    }
                }
            } catch(Exception e) {
                throw e;
            }
        }
        @Override
        public void run() {
            try {
                //通过客户端的Socket获取客户端的输出流    用来将消息发送给客户端
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                //将客户昵称和其所说的内容存入共享集合HashMap中
                name = getName();
                putIn(name, pw);//用户名   用户输入的内容
                Thread.sleep(100);
                // 服务端通知所有客户端，某用户上线
                sendToAll("[系统通知] “" + name + "”已上线");
                // 通过客户端的Socket获取输入流    读取客户端发送来的信息
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8"));
                String msgString = null;
                while((msgString = bReader.readLine()) != null) {

                    // 检验是否为私聊（格式：@昵称：内容）
                    if(msgString.startsWith("@")) {
                        int index = msgString.indexOf(":");
                        if(index >= 0) {
                            //获取昵称
                            String theName = msgString.substring(1, index);
                            String info = msgString.substring(index+1, msgString.length());
                            info =  name + "： "+ info;
                            //将私聊信息发送出去
                            sendToSomeone(theName, info);
                            continue;
                        }
                    }

                    // 遍历所有输出流，将该客户端发送的信息转发给所有客户端
                    System.out.println(name + "： " + msgString);
                    sendToAll(name + "：" + msgString);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
