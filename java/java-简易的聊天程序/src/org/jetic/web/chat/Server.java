package org.jetic.web.chat;

/**
 * Title:        经天网络
 * Description:
 * Copyright:    Copyright (c) 2000<br>
 * Company:      www.jetic.com  经天<br>
 * @author hover
 * @version 1.0
 */

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Server {
    private ServerSocket serverSocket = null;
    public static int port = 4444;
    private boolean listening = true;
    Vector clientSockets = new Vector(10);

    public Server() throws IOException {
        try {
            serverSocket = new ServerSocket(port);
        }
        catch (Exception ex) {
            System.err.println("不能监听端口：" + port);
            ex.printStackTrace(System.err);
            System.exit(-1);
        }

        System.out.println("成功监听端口：" + port);

        while (listening)
            addClient(serverSocket.accept());

        serverSocket.close();
    }

    public void addClient(Socket socket) throws IOException {
        new ServerThread(socket, this).start();
        clientSockets.add(socket);
        send("欢迎 " + socket.getInetAddress().getHostName() + " 来到这里！");
        System.out.println("聊天室共有 " + clientSockets.size() + " 人");
    }

    public void removeClient(Socket socket) throws IOException {
        send("欢送 " + socket.getInetAddress().getHostName() + " 的离去");
        clientSockets.remove(socket);
        System.out.println("聊天室共有 " + clientSockets.size() + " 人");
    }

    public void send(String msg) throws IOException {
        Socket socket = null;
        for (int I = 0; I < clientSockets.size(); I++) {
            socket = (Socket)(clientSockets.get(I));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}