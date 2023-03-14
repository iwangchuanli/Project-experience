package com.network.basic;

import java.net.Socket;

public class SocketClientDemo {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("192.168.12.46",12345);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
