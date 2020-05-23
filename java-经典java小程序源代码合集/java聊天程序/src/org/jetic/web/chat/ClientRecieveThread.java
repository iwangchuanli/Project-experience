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

public class ClientRecieveThread extends Thread {
    private ChatApplet applet = null;
    private Socket socket = null;

    public ClientRecieveThread(Socket socket, ChatApplet applet) {
        this.socket = socket;
        this.applet = applet;
    }

    public void run() {
        BufferedReader in = null;
        String inputLine;
        try {
            in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                //if (inputLine.equalsIgnoreCase("quit")) break;
                applet.appendMessage(inputLine + "\n");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        finally {
            Close();
        }
    }

    void Close() {
        try {
            socket.close();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}