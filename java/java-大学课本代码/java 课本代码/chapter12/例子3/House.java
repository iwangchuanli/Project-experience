public class House implements Runnable {
   int waterAmount;       //��int����ģ��ˮ��
   public void setWater(int w) {
      waterAmount = w;
   }
   public void run() {     
      while(true) {
         String name=Thread.currentThread().getName();
         if(name.equals("��")) { 
              System.out.println(name+"��ˮ") ; 
              waterAmount=waterAmount-2;  //���ȵĶ�
         }
         else if(name.equals("è")){
              System.out.println(name+"��ˮ") ;   
              waterAmount=waterAmount-1;  //è�ȵ���
         }
         System.out.println(" ʣ "+waterAmount);
         try{  Thread.sleep(2000);  //���ʱ��
         }
         catch(InterruptedException e){}  
         if(waterAmount<=0) {
                 return;    
         }  
     }
   }
}
