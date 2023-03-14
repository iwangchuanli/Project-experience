package SY_10;

public class MoneyhighException extends Exception{
	final String message="工资太高。";
	public String warnMess(){
		return message;
	}

}
