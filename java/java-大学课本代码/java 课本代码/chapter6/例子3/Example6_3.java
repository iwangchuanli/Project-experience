interface  ShowMessage {
   void ��ʾ�̱�(String s);
}
class TV implements ShowMessage {
   public void ��ʾ�̱�(String s) {
      System.out.println(s);
   }
}
class PC implements ShowMessage {
   public void ��ʾ�̱�(String s) { 
     System.out.println(s);
   }
}
public class Example6_3 {
   public static void main(String args[]) {
      ShowMessage sm;                  //�����ӿڱ���
      sm=new TV();                     //�ӿڱ����д�Ŷ��������
      sm.��ʾ�̱�("�����Ƶ��ӻ�");      //�ӿڻص���
      sm=new PC();                     //�ӿڱ����д�Ŷ��������
      sm.��ʾ�̱�("���뱼��5008PC��"); //�ӿڻص�
   } 
}
