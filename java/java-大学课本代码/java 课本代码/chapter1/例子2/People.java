public class People {
    int height; 
    String ear;
    void speak(String s) { 
       System.out.println(s);
    }
}
class A { 
    public static void main(String args[]) {
      People zhubajie;
      zhubajie = new People();
      zhubajie.height = 170;
      zhubajie.ear = "��ֻ�����";
      System.out.println("���:"+zhubajie.height);
      System.out.println(zhubajie.ear);
      zhubajie.speak("ʦ��,���Ǳ�ȥ������,��ȥ�¹���");
    }
}
