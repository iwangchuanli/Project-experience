public class Example15_1 {
   public static void main(String args[]) {
      Circle circle=new Circle(10);
      Cone<Circle> coneOne=new Cone<Circle>(circle);//����һ����Բ��׶����  
      coneOne.setHeight(16);
      System.out.println(coneOne.computerVolume());
      Rect rect=new Rect(15,23);
      Cone<Rect> coneTwo=new Cone<Rect>(rect);//����һ��������׶����
      coneTwo.setHeight(98); 
      System.out.println(coneTwo.computerVolume());
  }
}