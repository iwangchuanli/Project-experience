public class People{
    int leg,hand;
    String name;
    People(String s){
        name=s;
        this.init();   //可以省略this，即将this.init();写成init();
    }
    void init(){
       leg=2;
       hand=2;
       System.out.println(name+"有"+hand+"只手"+leg+"条腿");
    }
    public static void main(String args[]){
      People boshi=new People("布什"); //创建boshi时，构造方法中的this就是对象boshi
    }
} 


