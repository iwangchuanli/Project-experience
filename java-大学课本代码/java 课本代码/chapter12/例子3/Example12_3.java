public class Example12_3 {
   public static void main(String args[ ]) {
      House house = new House();
      house.setWater(10);
      Thread dog,cat;
      dog=new Thread(house); 
      cat=new Thread(house);  //cat和dog的目标对象相同 
      dog.setName("狗");
      cat.setName("猫"); 
      dog.start();
      cat.start();
   }
}
