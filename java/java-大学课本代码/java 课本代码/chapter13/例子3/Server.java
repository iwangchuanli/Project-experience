import java.io.*;
import java.net.*;
public class Server {
   public static void main(String args[]) {
      String [] answer ={"南非","进入世界杯了","哈哈...问题真逗!"};
      ServerSocket serverForClient=null;
      Socket socketOnServer=null;
      DataOutputStream out=null;
      DataInputStream  in=null;
      try { serverForClient = new ServerSocket(2010);
      }
      catch(IOException e1) {
            System.out.println(e1);
      } 
      try{ System.out.println("等待客户呼叫");
           socketOnServer = serverForClient.accept(); //堵塞状态，除非有客户呼叫
           out=new DataOutputStream(socketOnServer.getOutputStream());
           in=new DataInputStream(socketOnServer.getInputStream());
           for(int i=0;i<answer.length;i++) {
              String s=in.readUTF(); // in读取信息，堵塞状态
              System.out.println("服务器收到客户的提问:"+s);
              out.writeUTF(answer[i]);
              Thread.sleep(500);
           }
      }
      catch(Exception e) {
          System.out.println("客户已断开"+e);
      }
   }
}
