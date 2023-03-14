package com.view;

import javax.swing.*;

public class test {
    private JButton button1;
    private JPanel panel1;
    private JButton button2;

    public test() {
        button1 = new JButton("按钮1");
        button2 = new JButton("按钮2");
        panel1.add(button1);
        panel1.add(button2);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
