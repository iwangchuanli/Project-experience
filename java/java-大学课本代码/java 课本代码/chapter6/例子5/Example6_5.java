interface SpeakHello {
    void speakHello();
}
class Chinese implements SpeakHello {
   public  void speakHello() {
       System.out.println("�й���ϰ���ʺ�����,�Է�����? ");
   }
}
class English implements SpeakHello {
    public  void speakHello() {
       System.out.println("Ӣ����ϰ���ʺ���:���,�������� ");
    }
}
class KindHello {
   public void lookHello(SpeakHello hello) { //�ӿ����Ͳ���
       hello.speakHello();                    //�ӿڻص�
   }
}
public class Example6_5 {
   public static void main(String args[]) {
       KindHello kindHello=new KindHello();
       kindHello.lookHello(new Chinese());
       kindHello.lookHello(new English());
   }
}
