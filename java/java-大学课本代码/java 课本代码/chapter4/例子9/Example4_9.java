public class Example4_9 {
   public static void main(String args[]) {
       SIM simOne = new SIM(13889776509L);
       MobileTelephone mobile = new MobileTelephone();
       mobile.setSIM(simOne);
       System.out.println("手机号码:"+mobile.lookNumber()); 
       SIM simTwo = new SIM(15967563567L);
       mobile.setSIM(simTwo);
       System.out.println("手机号码:"+mobile.lookNumber());  
    }
}