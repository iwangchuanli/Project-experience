import java.net.*;
import java.util.*;
public class Receiver  {
   public static void main(String args[]) {
      int port = 5858;                               //�鲥�Ķ˿�
      InetAddress group=null;                        //�鲥��ĵ�ַ
      MulticastSocket socket=null;                   //���㲥�׽��� 
      try{
          group=InetAddress.getByName("239.255.8.0");//���ù㲥��ĵ�ַΪ239.255.8.0
          socket=new MulticastSocket(port);    //���㲥�׽��ֽ���port�˿ڹ㲥
          socket.joinGroup(group); //����group
      }                          
      catch(Exception e){} 
      while(true) {   
         byte data[]=new byte[8192];
         DatagramPacket packet=null;
         packet=new DatagramPacket(data,data.length,group,port); //�����յ����ݰ�
         try {  socket.receive(packet);
                String message=new String(packet.getData(),0,packet.getLength());
                System.out.println("���յ�����:\n"+message);
         }
         catch(Exception e) {}
      }
   }
}
