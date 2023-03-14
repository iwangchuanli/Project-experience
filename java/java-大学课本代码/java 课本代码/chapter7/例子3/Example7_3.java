interface SpeakHello {
     void speak();
}
class  HelloMachine {
   public void turnOn(SpeakHello hello) {
       hello.speak();
   }
}
public class Example7_3 {
   public static void main(String args[]) {
      HelloMachine machine = new HelloMachine();
      machine.turnOn( new SpeakHello(){
                          public void speak() {
                             System.out.println("hello,you are welcome!");
                          }
                      } 
                    ); 
      machine.turnOn( new SpeakHello(){
                          public void speak() {
                              System.out.println("ƒ„∫√£¨ª∂”≠π‚¡Ÿ!");
                          }
                      } 
                    ); 
   } 
}
