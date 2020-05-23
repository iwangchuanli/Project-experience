package SY_3;

import java.util.Scanner;

public class sy3_5 {
	public static void main(String[] args) {
		User u1=new User();
		Scanner input=new Scanner(System.in);
		String name1=input.next();
		String pwd1=input.next();
		u1.login(name1, pwd1);
		input.close();
	}

}
