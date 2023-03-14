import java.net.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
public class Client {
  public static void main(String args[]) {
       new QueryClient();
  }
}
class QueryClient extends JFrame implements Runnable,ActionListener {
   JButton connection,sendNumber,sendName;
   JTextField inputNumber,inputName;
   JTextArea showResult;
   Socket socket=null;
   DataInputStream in=null;
   DataOutputStream out=null;
   Thread thread; 
   QueryClient() {
      socket=new Socket();
      JPanel p=new JPanel();
      connection=new JButton("连接服务器");
      sendNumber=new JButton("发送车号");
      sendNumber.setEnabled(false);
      sendName=new JButton("发送车名");
      sendName.setEnabled(false);
      inputNumber=new JTextField(5);
      inputName  =new JTextField(5);
      showResult=new JTextArea(6,42);
      p.add(connection);
      p.add(new JLabel("车号:"));
      p.add(inputNumber);
      p.add(sendNumber);
      p.add(new JLabel("车名:"));
      p.add(inputName);
      p.add(sendName);
      add(p,BorderLayout.NORTH);
      add(showResult,BorderLayout.CENTER);
      connection.addActionListener(this);
      sendName.addActionListener(this);
      sendNumber.addActionListener(this); 
      thread=new Thread(this); 
      setBounds(10,30,350,400);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      validate();
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==connection) {
         try{   
            if(socket.isConnected()) {} 
            else {
                 InetAddress  address=InetAddress.getByName("127.0.0.1");
                 InetSocketAddress socketAddress=new InetSocketAddress(address,4331);
                 socket.connect(socketAddress); 
                 in =new DataInputStream(socket.getInputStream());
                 out = new DataOutputStream(socket.getOutputStream());
                 sendName.setEnabled(true);
                 sendNumber.setEnabled(true);
                 thread.start();
            }
         } 
         catch (IOException ee){
            socket=new Socket();
         }
      }
      else if(e.getSource()==sendNumber) {
         String s=inputNumber.getText();
         if(s!=null) {
             try { out.writeUTF("number:"+s);
             }
             catch(IOException e1){} 
         }               
      }
      else if(e.getSource()==sendName) {
         String s=inputName.getText();
         if(s!=null) {
             try { out.writeUTF("name:"+s);
             }
             catch(IOException e1){} 
         }               
      }
   }
   public void run() {
      String s=null;
      while(true) {
         try{  s=in.readUTF();
               showResult.append("\n"+s);
         }
         catch(IOException e) {
               showResult.setText("与服务器已断开");
               break;
         }   
     }
  }
}
