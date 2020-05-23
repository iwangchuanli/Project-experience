package SY_6;
//3）有韩国人，该类继承了原始人类，重写吃和说功能（自己设置内容），添加新的功能美容，该功能为“韩国人只要好看就行”；
public class 韩国人 extends 原始人{
	public void eat() {
		System.out.println("吃韩国食物！");
	}
	public void speak() {
		System.out.println("说韩语!");
	}
	public void mei() {
		System.out.println("韩国人只要好看就行!");
	}
}
