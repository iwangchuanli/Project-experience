class People {
    private int averHeight = 166;
    public int getAverHeight() {
       return averHeight;
    }
}
class ChinaPeople extends People {
     int height;
     public void setHeight(int h) {
          //height = h+averHeiht; // �Ƿ�������û�м̳�averHeiht
          height = h;
     }
     public int getHeight() {
        return height;
     }
}
public class Example5_2 {
  public static void main(String args[]) {
      ChinaPeople zhangSan = new ChinaPeople();
      System.out.println("�������δ�̳е�averageHeight��ֵ��:"+zhangSan.getAverHeight());
      zhangSan.setHeight(178);
      System.out.println("��������ʵ������height��ֵ��:"+zhangSan.getHeight());
  }  
}
