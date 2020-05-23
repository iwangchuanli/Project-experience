public class Example12_13 {
   public static void main(String args[]) {
      Daemon  a=new Daemon ();
      a.A.start();
      a.B.setDaemon(true);
      a.B.start();
   }
}
