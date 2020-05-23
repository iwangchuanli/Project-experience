public class Example15_1 {
   public static void main(String args[]) {
      Circle circle=new Circle(10);
      Cone<Circle> coneOne=new Cone<Circle>(circle);//创建一个（圆）锥对象  
      coneOne.setHeight(16);
      System.out.println(coneOne.computerVolume());
      Rect rect=new Rect(15,23);
      Cone<Rect> coneTwo=new Cone<Rect>(rect);//创建一个（方）锥对象
      coneTwo.setHeight(98); 
      System.out.println(coneTwo.computerVolume());
  }
}