public class Bank implements Runnable {
   int money=200;
   public void setMoney(int n) {
      money=n;
   }
   public void run() {
      if(Thread.currentThread().getName().equals("���")) 
         saveOrTake(300);
      else if(Thread.currentThread().getName().equals("����"))
         saveOrTake(150);;
   }
    public synchronized void saveOrTake(int amount) { //��ȡ����
      if(Thread.currentThread().getName().equals("���")) {
         for(int i=1;i<=3;i++) { 
             money=money+amount/3;      //ÿ����amount/3����Ъһ��
             System.out.println(Thread.currentThread().getName()+
                               "����"+amount/3+",������"+money+"��,��Ϣһ���ٴ�");
             try { Thread.sleep(1000);  //��ʱ�����Բ���ʹ��saveOrTake���� 
             }                       
             catch(InterruptedException e){}
         }
      }
      else if(Thread.currentThread().getName().equals("����")) {
         for(int i=1;i<=3;i++) { //����ʹ�ô�ȡ����ȡ��60
             money=money-amount/3;   //ÿȡ��amount/3����Ъһ��
             System.out.println(Thread.currentThread().getName()+
                               "ȡ��"+amount/3+"������"+money+"��,��Ϣһ����ȡ");
             try { Thread.sleep(1000);       //��ʱ����Բ���ʹ��saveOrTake����
             }                         
             catch(InterruptedException e){}
         }
      }
   }
}
