package org.jetic.web.chat;

/**
 * Title:        ��������
 * Description:
 * Copyright:    Copyright (c) 2000<br>
 * Company:      www.jetic.com  ����<br>
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
            System.err.println("���ܼ����˿ڣ�" + port);
            ex.printStackTrace(System.err);
            System.exit(-1);
        }

        System.out.println("�ɹ������˿ڣ�" + port);

        while (listening)
            addClient(serverSocket.accept());

        serverSocket.close();
    }

    public void addClient(Socket socket) throws IOException {
        new ServerThread(socket, this).start();
        clientSockets.add(socket);
        send("��ӭ " + socket.getInetAddress().getHostName() + " �������");
        System.out.println("�����ҹ��� " + clientSockets.size() + " ��");
    }

    public void removeClient(Socket socket) throws IOException {
        send("���� " + socket.getInetAddress().getHostName() + " ����ȥ");
        clientSockets.remove(socket);
        System.out.println("�����ҹ��� " + clientSockets.size() + " ��");
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