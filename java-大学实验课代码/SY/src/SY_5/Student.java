package SY_5;
//定义学生类Student，该类包含数据成员学号、姓名、身高和体重，该类方法成员有
//1）有参构造方法；
//2）对应上述数据成员的get/set方法；

public class Student {
	String num;
	String name;
	double height;
	double weight;
	public Student(String num,String name,double h,double w){
		this.num=num;
		this.name=name;
		this.height=h;
		this.weight=w;
	}
	public Student(){
	}
	void setInfo(String num,String name,double h,double w){
		this.num=num;
		this.name=name;
		this.height=h;
		this.weight=w;
	}
	void getInfo(){
		System.out.println("学号："+num+"，姓名："+name+"，身高："+height+"，体重："+weight);
	}
}
