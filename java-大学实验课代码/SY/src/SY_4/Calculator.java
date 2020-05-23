package SY_4;

import java.util.Scanner;


public class Calculator {
	double a,b;
	void add(){
		System.out.println("a+b的值："+(a+b));
	}
	void sub(){
		System.out.println("a-b的值："+(a-b));
	}
	void mul(){
		System.out.println("a*b的值："+(a*b));
	}
	void div(){
		System.out.println("a/b的值："+(a/b));
	}
	public static void main(String[] args) {
		Calculator test=new Calculator();
		Scanner input=new Scanner(System.in);
		System.out.println("请输入a和b的值：");
		test.a=input.nextDouble();
		test.b=input.nextDouble();
		test.add();
		test.sub();
		test.mul();
		test.div();
		input.close();
	}

}
