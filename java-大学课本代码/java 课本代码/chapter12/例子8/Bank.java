public class Bank implements Runnable {
   int money=200;
   public void setMoney(int n) {
      money=n;
   }
   public void run() {
      if(Thread.currentThread().getName().equals("会计")) 
         saveOrTake(300);
      else if(Thread.currentThread().getName().equals("出纳"))
         saveOrTake(150);;
   }
    public synchronized void saveOrTake(int amount) { //存取方法
      if(Thread.currentThread().getName().equals("会计")) {
         for(int i=1;i<=3;i++) { 
             money=money+amount/3;      //每存入amount/3，稍歇一下
             System.out.println(Thread.currentThread().getName()+
                               "存入"+amount/3+",帐上有"+money+"万,休息一会再存");
             try { Thread.sleep(1000);  //这时出纳仍不能使用saveOrTake方法 
             }                       
             catch(InterruptedException e){}
         }
      }
      else if(Thread.currentThread().getName().equals("出纳")) {
         for(int i=1;i<=3;i++) { //出纳使用存取方法取出60
             money=money-amount/3;   //每取出amount/3，稍歇一下
             System.out.println(Thread.currentThread().getName()+
                               "取出"+amount/3+"帐上有"+money+"万,休息一会再取");
             try { Thread.sleep(1000);       //这时会计仍不能使用saveOrTake方法
             }                         
             catch(InterruptedException e){}
         }
      }
   }
}
