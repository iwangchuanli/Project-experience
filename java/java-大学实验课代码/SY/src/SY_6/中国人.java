package SY_6;
//2）有中国人，该类继承了原始人类，重写吃和说功能（自己设置内容），添加新的功能住，该功能为“中国人喜欢买房”；
public class 中国人 extends 原始人{
	public void eat() {
		System.out.println("吃中国菜！");
	}
	public void speak() {
		System.out.println("说中国语!");
	}
	public void buy() {
		System.out.println("中国人喜欢买房!");
	}

}
