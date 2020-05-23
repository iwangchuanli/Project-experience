package SY_3;

public class Register {
	String name;
	String pwd,pwd2;
	String idCard;
	String phoneNum;
	String youx;
	void setInfo(String name,String pwd,String pwd2,String idCard,String phoneNum,String youx){
		this.name=name;
		this.pwd=pwd;
		this.pwd2=pwd2;
		this.idCard=idCard;
		this.phoneNum=phoneNum;
		this.youx=youx;
	}
	void verify(){
		if(pwd==pwd2&&name.length()>=3&&pwd.length()>=6)
			System.out.println("验证通过！");
		else {
			System.out.println("注册失败！");
		}
		
	}


}
