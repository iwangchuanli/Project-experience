import java.rmi.*;
public class ClientApplication{
   public static void main(String args[]){
      try{
         Remote  remoteObject=Naming.lookup("rmi://127.0.0.1/rect");
         RemoteSubject remoteSubject= (RemoteSubject)remoteObject;
         remoteSubject.setWidth(129);
         remoteSubject.setHeight(528);
         double area=remoteSubject.getArea(); 
         System.out.println("Ãæ»ý:"+area);
      }
      catch(Exception exp){
         System.out.println(exp.toString());
      }
   }
}
