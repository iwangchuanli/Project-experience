import java.net.*;
import java.util.*;
public class ZhangSan  {
   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      Thread readData ; 
      ReceiveLetterForZhang receiver = new ReceiveLetterForZhang();
      try{ readData = new Thread(receiver);
            readData.start();           //���������Ϣ���߳� 
            byte [] buffer=new byte[1];
            InetAddress address=InetAddress.getByName("127.0.0.1");
            DatagramPacket dataPack=
            new DatagramPacket(buffer,buffer.length, address,666);
            DatagramSocket postman=new DatagramSocket();
            System.out.print("���뷢�͸����ĵ���Ϣ:");
            while(scanner.hasNext()) {
                String mess = scanner.nextLine();
                buffer=mess.getBytes();
                if(mess.length()==0) 
                    System.exit(0);
                buffer=mess.getBytes();
                dataPack.setData(buffer);
                postman.send(dataPack);  
                System.out.print("�������뷢�͸����ĵ���Ϣ:");
            }
       }
       catch(Exception e) {
            System.out.println(e);
       }
   }
}
