package SY_5;
//定义电话本类PhoneBook，该类包含数据成员姓名、手机号，该类方法成员有
//1）有参构造方法；
//2）对应上述数据成员的get/set方法；

public class PhoneBook {
	String name;
	String phoneNum;
	public PhoneBook(String n,String pN) {
		this.name=n;
		this.phoneNum=pN;
	}
	public PhoneBook(){
		
	}
	void setInfo(String n,String pN){
		this.name=n;
		this.phoneNum=pN;
		
	}
	void getInfo(){
		System.out.println("姓名："+name+"，手机号："+phoneNum);
	}

}
