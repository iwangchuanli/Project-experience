package homework;

import java.util.Scanner;

/***
 * @author Administrator
 *在控制台输出以下内容：
	姓名：王洋
	年龄：22
	性别：男
	职业：学生
	住址：北京市昌平区北七家镇宏福科技园修正大厦

 */
public class prj02 {
public static void main(String[] args) {
	Scanner input=new Scanner(System.in);
	String name,sex,work,address;
	int age;
	System.out.println("请输入个人信息：");
	name = input.nextLine();
	age = input.nextInt();
	sex = input.nextLine();
	work = input.nextLine();
	address = input.nextLine();
	System.out.println("您输入的信息内容为："+'\n'+
			"姓名："+name+'\n'+"年龄："+age+'\n'+"性别："+sex+
			'\n'+"职业："+work+'\n'+"地址："+address);
	
}
}
