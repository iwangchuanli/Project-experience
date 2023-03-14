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

public class ServerThread extends Thread {
    private Server server = null;
    private Socket socket = null;
    private Protocol jcp = null;
    private String userid;

    public ServerThread(Socket socket, Server server) {
        super("jetic Chat Server");
        this.socket = socket;
        this.server = server;
        userid = socket.getInetAddress().getHostName();
        jcp = new Protocol(userid);
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String inputLine, outputLine;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));

            out.println("你可以开始你的闲聊了 ：）");

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("quit")) break;
                outputLine = jcp.processInput(inputLine);
                server.send(outputLine);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            Close();
        }
        finally {
            Close();
        }
    }

    private void Close() {
        try {
            server.removeClient(socket);
            socket.close();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}