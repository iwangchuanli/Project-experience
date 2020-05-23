public class Example12_9 {
   public static void main(String args[]) {
     ThreadJoin  a = new ThreadJoin();
     Thread customer = new Thread(a);
     Thread cakeMaker = new Thread(a);
     customer.setName("¹Ë¿Í");
     cakeMaker.setName("µ°¸âÊ¦");
     a.setJoinThread(cakeMaker);
     customer.start();
   }
}
