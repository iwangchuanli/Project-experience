public class Daemon implements Runnable {
   Thread A,B;
   Daemon() {
       A=new Thread(this);
       B=new Thread(this);
   }
   public void run() {
      if(Thread.currentThread()==A) {
          for(int i=0;i<8;i++) {
             System.out.println("i="+i) ;
             try{  Thread.sleep(1000);    
             }
             catch(InterruptedException e) {}
          } 
      }
      else if(Thread.currentThread()==B) {
          while(true) {
             System.out.println("�߳�B���ػ��߳� "); 
             try{  Thread.sleep(1000);    
             }
             catch(InterruptedException e){}
          }
      }
   }    
}
