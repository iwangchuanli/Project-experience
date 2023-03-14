public class Example12_5 {
   public static void main(String args[]) {
      Home home=new Home();
      Thread showTime=new Thread(home); 
      showTime.start();
   }
}

