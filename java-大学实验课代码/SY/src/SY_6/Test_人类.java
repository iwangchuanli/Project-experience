package SY_6;
//5）定义测试类，在主方法里，定义以上类的对象，访问吃、说、住、美容、游等功能；
//6）在测试类的主方法中，通过定义上转型对象，实现多态。（同一个对象，访问相同的方法，达到不同的效果）。

public class Test_人类 {
	public static void main(String[] args) {
		原始人  people;
		people=new 原始人();
		people.eat();
		people.speak();
		people=new 中国人();
		people.eat();
		people.speak();
		韩国人 people1=new 韩国人();
		people1.eat();
		people1.speak();
		people1.mei();
		黄山人 people2=new 黄山人();
		people2.eat();
		people2.speak();
		people2.buy();
		people2.travel();
	}
	
	

}
