public class Example4_7 {
   public static void main(String args[]) {
      Battery nanfu = new Battery(100);
      System.out.println("���ڵ�صĴ�������:"+nanfu.electricityAmount);
      Radio radio = new Radio();
      System.out.println("��������ʼʹ�����ڵ��");
      radio.openRadio(nanfu);
      System.out.println("Ŀǰ���ڵ�صĴ�������:"+nanfu.electricityAmount);
   }
}
