public class Example12_2 { 
   public static void main(String args[]) { 
       Thread speakElephant;            //��Thread�����߳�
       Thread speakCar;                 //��Thread�����߳�
       ElephantTarget elephant;         //speakElephant�̵߳�Ŀ�����
       CarTarget car;                   //speakCar�̵߳�Ŀ�����
       elephant = new ElephantTarget();
       car = new CarTarget();
       speakElephant = new Thread(elephant) ;   //�����߳�
       speakCar = new Thread(car);              //�����߳�
       speakElephant.start();                    //�����߳� 
       speakCar.start();                        //�����߳�
       for(int i=1;i<=15;i++) {
          System.out.print("����"+i+"  ");
       }  
   }
}
