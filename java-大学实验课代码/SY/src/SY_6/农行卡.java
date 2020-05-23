package SY_6;

public class 农行卡 {
	String card;
	String name;
	String idCard;
	String phoneNum;
	double money;
	农行卡(){
		
	}
	农行卡(String card,String name,String idCard,String phoneNum,double money){
		this.card=card;
		this.name=name;
		this.idCard=idCard;
		this.phoneNum=phoneNum;
		this.money=money;
	}
	public void setInfo(){
		
	}
	public void getInfo(){
		System.out.println("农行卡信息："+"'\n'卡号"+card+"'\n'姓名："+name+"'\n'身份证号："+idCard+"'\n'联系方式："+phoneNum+"'\n'余额："+money);
	}
	public void menu(){
		System.out.println("功能：'\n'1、注册信息'\n'2、设置信息'\n'3、存钱'\n'4、取钱'\n'5、理财所得'\n'6、");
	}
	public void savemoney(double m){//存钱
		money=money+m;
	}
	public void usemoney(double m){//取钱
		money=money-m;
	}
	public void licai() {//理财
		money=money+money*(0.03d);
	}
	//信用
}
