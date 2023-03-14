class A {
    Object get() {
       return null; //返回一个空对象
    }
}
class B extends A {
    Integer get() {
       return new Integer(100);//返回一个Integer对象
    }
}
public class Example5_6 {
    public static void main(String args[]) {
       B b=new B();
       Integer t=b.get();
       System.out.println(t.intValue());   
    } 
}
