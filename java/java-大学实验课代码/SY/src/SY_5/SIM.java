package SY_5;
//定义SIM卡类，该类包含数据成员手机号、姓名、身份证，该类方法成员有
//1）有参构造方法；
//2）对应上述数据成员的get/set方法；

public class SIM {
	String phoneNum;
	String name;
	String idCard;
	public SIM(String pN,String n,String iC) {
		this.phoneNum=pN;
		this.name=n;
		this.idCard=iC;
	}
	public SIM(){
		
	}
	void setInfo(String pN,String n,String iC){
		this.phoneNum=pN;
		this.name=n;
		this.idCard=iC;
	}
	void getInfo(){
		System.out.println("手机号："+phoneNum
										+"，姓名："+name
										+"，身份证："+idCard);
	}

}
