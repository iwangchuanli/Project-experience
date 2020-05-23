import java.rmi.*;
public class BindRemoteObject{
   public static void main(String args[]){
      try{
        RemoteConcreteSubject  remoteObject=new RemoteConcreteSubject();  
        Naming.rebind("rmi://127.0.0.1/rect",remoteObject);
        System.out.println("be ready for client server...");
      }
      catch(Exception exp){ 
               System.out.println(exp);
      }
   }
}
