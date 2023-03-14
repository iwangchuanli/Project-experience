import java.io.*;
import java.net.*;
import java.sql.*;
public class Target extends Thread { //implements Runnable {
   Socket socket;
   DataOutputStream out=null;
   DataInputStream  in=null;
   PreparedStatement sqlOne,sqlTwo;
   boolean boo=false;
   Target(Socket t, PreparedStatement sqlOne,PreparedStatement sqlTwo) {
      socket=t;
      this.sqlOne=sqlOne;
      this.sqlTwo=sqlTwo;  
      try {  out=new DataOutputStream(socket.getOutputStream());
             in=new DataInputStream(socket.getInputStream());
      }
      catch(IOException e){
          System.out.println(e);
      }
   }  
   public void run() {
      ResultSet rs = null;        
      while(true) {
        try{
            String str=in.readUTF();
            if(str.startsWith("number:")) {
               str = str.substring(str.indexOf(":")+1);
               sqlOne.setString(1,str);
               rs=sqlOne.executeQuery() ;
            }
            else if(str.startsWith("name")) {
               str = str.substring(str.indexOf(":")+1);
               sqlTwo.setString(1,str);
               rs=sqlTwo.executeQuery() ;
            }
            while(rs.next()) {
               boo=true;
               String number=rs.getString(1);
               String name=rs.getString(2);
               Date time=rs.getDate(3);
               double price=rs.getDouble(4);
               out.writeUTF("���ͺ�:"+number+"����:"+name+"��������:"+time+" �۸�:"+price); 
            }
            if(boo==false)
               out.writeUTF("û�и�ѧ�ţ�");
       }
       catch (IOException e) { 
           System.out.println("�ͻ��뿪"+e);
               return;
       }
       catch (SQLException e) { 
           System.out.println(e);
       }
     }
   } 
}