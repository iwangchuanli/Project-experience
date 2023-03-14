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

public class Client {
    Socket socket = null;
    private String host;
    private boolean connected = false;

    public boolean isConnected() { return connected; }

    public Client(ChatApplet applet) {
        try {
            host = applet.getDocumentBase().getHost();
            //host = "202.115.4.246";
            socket = new Socket(host, Server.port);
            connected = true;
            new ClientRecieveThread(socket, applet).start();
        }
        catch (Exception ex) {
            applet.appendMessage(ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    public PrintWriter getOutputStream() throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
}