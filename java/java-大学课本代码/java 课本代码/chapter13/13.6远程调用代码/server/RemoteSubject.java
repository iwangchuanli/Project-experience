import java.rmi.*;
public interface RemoteSubject extends Remote {
   public void setHeight(double height) throws RemoteException;
   public void setWidth(double width) throws RemoteException;
   public double getArea() throws RemoteException;
}
