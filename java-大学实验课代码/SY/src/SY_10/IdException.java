package SY_10;

public class IdException extends Exception{
	final String message="身份证不正确。";
	public String warnMess(){
		return message;
	}

}
