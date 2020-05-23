package com.view;

import javax.swing.*;

public class chatroom {
    private JTextArea out;
    private JTextField inText;
    private JButton btnok;
    private JPanel panel;
    //private JScrollPane scoPanel;

    public chatroom() {
        //panel.add(scoPanel);
        //scoPanel.add(out);
        panel.add(out);
        panel.add(inText);
        panel.add(btnok);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("chatroom");
        frame.setContentPane(new chatroom().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

