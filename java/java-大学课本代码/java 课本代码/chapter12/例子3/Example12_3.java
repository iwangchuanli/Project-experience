public class Example12_3 {
   public static void main(String args[ ]) {
      House house = new House();
      house.setWater(10);
      Thread dog,cat;
      dog=new Thread(house); 
      cat=new Thread(house);  //cat��dog��Ŀ�������ͬ 
      dog.setName("��");
      cat.setName("è"); 
      dog.start();
      cat.start();
   }
}
