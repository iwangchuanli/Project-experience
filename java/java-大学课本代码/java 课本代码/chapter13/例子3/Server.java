import java.io.*;
import java.net.*;
public class Server {
   public static void main(String args[]) {
      String [] answer ={"�Ϸ�","�������籭��","����...�����涺!"};
      ServerSocket serverForClient=null;
      Socket socketOnServer=null;
      DataOutputStream out=null;
      DataInputStream  in=null;
      try { serverForClient = new ServerSocket(2010);
      }
      catch(IOException e1) {
            System.out.println(e1);
      } 
      try{ System.out.println("�ȴ��ͻ�����");
           socketOnServer = serverForClient.accept(); //����״̬�������пͻ�����
           out=new DataOutputStream(socketOnServer.getOutputStream());
           in=new DataInputStream(socketOnServer.getInputStream());
           for(int i=0;i<answer.length;i++) {
              String s=in.readUTF(); // in��ȡ��Ϣ������״̬
              System.out.println("�������յ��ͻ�������:"+s);
              out.writeUTF(answer[i]);
              Thread.sleep(500);
           }
      }
      catch(Exception e) {
          System.out.println("�ͻ��ѶϿ�"+e);
      }
   }
}
