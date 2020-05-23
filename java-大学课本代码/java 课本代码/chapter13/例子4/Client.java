import java.io.*;
import java.net.*;
import java.util.*;
public class Client  {
   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      Socket mysocket=null;
      DataInputStream in=null;
      DataOutputStream out=null;
      Thread readData ; 
      Read read=null;
      try{  mysocket=new Socket();
            read = new Read();
            readData = new Thread(read);
            System.out.print("输入服务器的IP:");
            String IP = scanner.nextLine();
            System.out.print("输入端口号:");
            int port = scanner.nextInt();
            if(mysocket.isConnected()){}
            else{
              InetAddress  address=InetAddress.getByName(IP);
              InetSocketAddress socketAddress=new InetSocketAddress(address,port);
              mysocket.connect(socketAddress); 
              in =new DataInputStream(mysocket.getInputStream());
              out = new DataOutputStream(mysocket.getOutputStream());
              read.setDataInputStream(in);
              readData.start();
            }
       }
       catch(Exception e) {
            System.out.println("服务器已断开"+e);
       }
       System.out.print("输入园的半径(放弃请输入N):");
       while(scanner.hasNext()) {
           double radius=0; 
           try {
               radius = scanner.nextDouble();
           }
           catch(InputMismatchException exp){
              System.exit(0);
           }   
           try { 
               out.writeDouble(radius);
           }
           catch(Exception e) {}
       } 
   }
}
