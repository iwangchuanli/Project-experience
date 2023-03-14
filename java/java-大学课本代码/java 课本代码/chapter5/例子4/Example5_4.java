public class Example5_4 {
    public static void main(String args[]) {
       double math=64,english=76.5,chinese=66;
       ImportantUniversity univer = new  ImportantUniversity();
       univer.enterRule(math,english,chinese); //调用重写的方法
       math=89;
       english=80;
       chinese=86;
       univer = new  ImportantUniversity();
       univer.enterRule(math,english,chinese); //调用重写的方法
    }
}