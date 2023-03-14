import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class RemoteConcreteSubject extends UnicastRemoteObject implements RemoteSubject{
     double width,height;
     public RemoteConcreteSubject() throws RemoteException {
     }
     public void setWidth(double width) throws RemoteException{
           this.width=width;
     }
     public void setHeight(double height) throws RemoteException{
           this.height=height;
     }
     public double getArea() throws RemoteException {
           return width*height;
     }
}
