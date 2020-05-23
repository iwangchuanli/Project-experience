import java.io.*;
import java.net.*;
import java.sql.*;
public class Server {
   public static void main(String args[]) {
      Connection con;
      PreparedStatement sqlOne=null,sqlTwo=null; 
      ResultSet rs;
      try{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      }
      catch(ClassNotFoundException e){}
      try{  con=DriverManager.getConnection("jdbc:odbc:myData","","");
            sqlOne=con.prepareStatement("SELECT * FROM goods WHERE number = ? ");
            sqlTwo=con.prepareStatement("SELECT * FROM goods WHERE name = ? ");
      }
      catch(SQLException e){}
      ServerSocket server=null;
      Runnable target;
      Thread threadForClient = null;
      Socket socketOnServer = null;
      while(true) {
           try{  server=new ServerSocket(4331);
           }
           catch(IOException e1) {
                System.out.println("正在监听"); 
           } 
           try{  System.out.println(" 等待客户呼叫");
                 socketOnServer = server.accept();
                 System.out.println("客户的地址:"+socketOnServer.getInetAddress());
           }
           catch(IOException e) {
                System.out.println("正在等待客户");
           }
           if(socketOnServer!=null) {
                target = new Target(socketOnServer,sqlOne,sqlTwo) ;
                threadForClient =new Thread(target); 
                threadForClient.start();  
           }
       }
   }
}


