public class RedCowForm {
   static String formName;
   RedCow cow;  //�ڲ�����������
   RedCowForm() {
   }
   RedCowForm(String s) {
      cow = new RedCow(150,112,5000);
      formName = s;
   }
   public void showCowMess() {
      cow.speak();
   }
   class RedCow {  //�ڲ��������
      String cowName = "��ţ";
      int height,weight,price;
      RedCow(int h,int w,int p){
          height = h;
          weight = w;
          price = p;
      }
      void speak() {
         System.out.println("ż��"+cowName+",���:"+height+"cm ����:"+weight+"kg,������"+formName);
      }
   }    //�ڲ������
} 
