class Computer{  
    int add(int x,int y){
       return x+y;
    }
}
public class Example4_6 {
    public static void main(String args[]){
       Computer com = new Computer();
       int m = 100;
       int n = 200;
       int result = com.add(m,n);
       System.out.println(result); 
    }
}


