package org.jetic.web.chat;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;

public class ChatApplet extends JApplet {
    boolean isStandalone = false;
    BorderLayout borderLayout1 = new BorderLayout();
    Border border1;
    JPanel jPanel2 = new JPanel();
    Border border2;
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    JButton buttonSend = new JButton();
    BorderLayout borderLayout3 = new BorderLayout();
    JPanel jPanel3 = new JPanel();
    Border border3;
    BorderLayout borderLayout4 = new BorderLayout();
    JTextField textTalk = new JTextField();
    JPanel jPanel4 = new JPanel();
    Border border4;
    BorderLayout borderLayout5 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea textMessages = new JTextArea();

    PrintWriter out = null;

    /**Get a parameter value*/
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    /**Construct the applet*/
    public ChatApplet() {
    }
    /**Initialize the applet*/
    public void init() {
        try {
            jbInit();

            Client client = new Client(this);
            if (client.isConnected())
                out = client.getOutputStream();
            else
                appendMessage("大大的错误！！");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**Component initialization*/
    private void jbInit() throws Exception {
        border1 = BorderFactory.createEmptyBorder(9,9,9,9);
        border2 = BorderFactory.createEmptyBorder(9,9,9,9);
        border3 = BorderFactory.createEmptyBorder(2,0,2,5);
        border4 = BorderFactory.createEmptyBorder(0,0,5,0);
        this.setSize(new Dimension(400,300));
        this.getContentPane().setLayout(borderLayout1);
        jPanel2.setBorder(border2);
        jPanel2.setLayout(borderLayout2);
        buttonSend.setFocusPainted(false);
        buttonSend.setText("发  送");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSend_actionPerformed(e);
            }
        });
        jPanel1.setLayout(borderLayout3);
        jPanel3.setBorder(border3);
        jPanel3.setLayout(borderLayout4);
        jPanel4.setBorder(border4);
        jPanel4.setLayout(borderLayout5);
        textTalk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                textTalk_keyPressed(e);
            }
        });
        this.getContentPane().add(jPanel2, BorderLayout.CENTER);
        jPanel2.add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(buttonSend, BorderLayout.EAST);
        jPanel1.add(jPanel3, BorderLayout.CENTER);
        jPanel3.add(textTalk, BorderLayout.CENTER);
        jPanel2.add(jPanel4, BorderLayout.CENTER);
        jPanel4.add(jScrollPane1, BorderLayout.CENTER);
        jScrollPane1.getViewport().add(textMessages, null);
    }
    /**Get Applet information*/
    public String getAppletInfo() {
        return "Applet Information";
    }
    /**Get parameter info*/
    public String[][] getParameterInfo() {
        return null;
    }
    /**Main method*/
    public static void main(String[] args) {
        ChatApplet applet = new ChatApplet();
        applet.isStandalone = true;
        JFrame frame = new JFrame();
        //EXIT_ON_CLOSE == 3
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Applet Frame");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(400,320);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }

    //static initializer for setting look & feel
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch(Exception e) {
        }
    }

    public void appendMessage(String message) {
        textMessages.setText(message + textMessages.getText());
    }

    void buttonSend_actionPerformed(ActionEvent e) {
        String msg;
        msg = textTalk.getText().trim();
        if (msg.equals("") || msg == null)  return;

        out.println(textTalk.getText());
        textTalk.setText("");
    }

    void textTalk_keyPressed(KeyEvent e) {
        if (e.getKeyChar() != '\n') return;
        out.println(textTalk.getText());
        textTalk.setText("");
    }
}