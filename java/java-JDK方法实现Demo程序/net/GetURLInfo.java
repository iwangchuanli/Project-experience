/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.net;
import java.net.*;
import java.io.*;
import java.util.Date;

/**
 * A class that displays information about a URL.
 **/
public class GetURLInfo {
    /** Use the URLConnection class to get info about the URL */
    public static void printinfo(URL url) throws IOException {
        URLConnection c = url.openConnection();  // Get URLConnection from URL
        c.connect();                             // Open a connection to URL
	
        // Display some information about the URL contents
        System.out.println("  Content Type: " + c.getContentType());
        System.out.println("  Content Encoding: " + c.getContentEncoding());
        System.out.println("  Content Length: " + c.getContentLength());
        System.out.println("  Date: " + new Date(c.getDate()));
        System.out.println("  Last Modified: " +new Date(c.getLastModified()));
        System.out.println("  Expiration: " + new Date(c.getExpiration()));
	
        // If it is an HTTP connection, display some additional information.
        if (c instanceof HttpURLConnection) {
            HttpURLConnection h = (HttpURLConnection) c;
            System.out.println("  Request Method: " + h.getRequestMethod());
            System.out.println("  Response Message: " +h.getResponseMessage());
            System.out.println("  Response Code: " + h.getResponseCode());
        }
    }
    
    /** Create a URL, call printinfo() to display information about it. */
    public static void main(String[] args) {
        try { printinfo(new URL(args[0])); }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java GetURLInfo <url>");
        }
    }
}
