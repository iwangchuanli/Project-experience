public class Cone<E> { 
   double height;
   E bottom;           //�÷�����E��������bottom
   public Cone (E b) {
      bottom=b;   
   }
   public void setHeight(double h) {
      height=h;
   }
   public double computerVolume() {
      String s=bottom.toString();//���ͱ���ֻ�ܵ��ô�Object��̳еĻ���д�ķ���
      double area=Double.parseDouble(s); 
      return 1.0/3.0*area*height; 
   }
}
