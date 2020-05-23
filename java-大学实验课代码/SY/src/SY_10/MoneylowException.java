package SY_10;

public class MoneylowException extends Exception{
	final String message = "工资太低。";
	public String warnMess(){
		return message;
	}

}
