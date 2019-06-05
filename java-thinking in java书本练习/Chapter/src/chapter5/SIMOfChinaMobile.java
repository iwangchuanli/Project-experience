package chapter5;

public class SIMOfChinaMobile extends SIM{
	String number;
	public void  setNumber(String n) {
		number=n;
	}
	public String giveNumber() {
		return number;
	}
	public String giveCorpName() {
		return "中国移动";
	}

}
