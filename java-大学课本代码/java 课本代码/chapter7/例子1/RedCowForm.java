public class RedCowForm {
   static String formName;
   RedCow cow;  //内部类声明对象
   RedCowForm() {
   }
   RedCowForm(String s) {
      cow = new RedCow(150,112,5000);
      formName = s;
   }
   public void showCowMess() {
      cow.speak();
   }
   class RedCow {  //内部类的声明
      String cowName = "红牛";
      int height,weight,price;
      RedCow(int h,int w,int p){
          height = h;
          weight = w;
          price = p;
      }
      void speak() {
         System.out.println("偶是"+cowName+",身高:"+height+"cm 体重:"+weight+"kg,生活在"+formName);
      }
   }    //内部类结束
} 
