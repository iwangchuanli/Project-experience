package com.chatroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientView extends JFrame {
    JTextArea textArea = new JTextArea(20,40);
    JTextField textField = new JTextField(20);
    JButton btn = new JButton("发送");
    JPanel panel = new JPanel();
    //static Socket clientSocket;

    public ClientView(){
        panel.add(textArea);
        panel.add(textField);
        panel.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatClient client = new ChatClient();
                String serverIP;
                textArea.setText("请设置服务器IP：");
                serverIP = textField.getText();
                try {
                    client.clientSocket = new Socket(serverIP, 6789);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                client.start();
            }
        });
        getContentPane().add(panel,BorderLayout.CENTER);
    }
//    public static void main(String[] args) {
//        ClientView cv = new ClientView();
//        cv.setVisible(true);
//        cv.setSize(500,600);
//        cv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}