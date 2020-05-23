package com.network.basic;

import java.io.*;
import java.net.*;

/**
 * 以上类URLReader 功能为网络资源的读取器，首先传入域名来创建URL对象来表示网络资源，接着构造一个缓冲输入流，
 * 需要在其构造方法中传入参数，
 * 此参数就是URL对象的openStream方法返回的资源流，
 * 这样缓冲输入流就可获取到资源信息，接着一行行去读取并输出来。
 *
 * 读取新浪网页 源码
 */
public class URLReader {

    public static void main(String args[]) throws Exception{
        URL url = new URL("http://www.sina.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.print(inputLine);
        in.close();
    }
}