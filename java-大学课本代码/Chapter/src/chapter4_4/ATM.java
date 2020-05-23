package chapter4_4;

public class ATM {
	int num;
	int pwd;
	float account=0;
	public void toString(){
		System.out.println("账户:"+account+'\n'+"余额:"+account);
	}
	public void saveAccount(float money){
		account=account+money;
	}
	public void drawAccount(float money){
		
	}
	public void getAccount(){
		
	}
	
}
