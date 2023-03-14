package SY_5;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		SIM card=new SIM();
		PhoneBook [] pBook=new PhoneBook[10];
		HuaWeiNote9 hwNote9=new HuaWeiNote9();
		Scanner input=new Scanner(System.in);
		System.out.println("请输入手机卡信息:");
		hwNote9.card.phoneNum=input.nextLine();
		hwNote9.card.name=input.nextLine();
		hwNote9.card.idCard=input.nextLine();
		hwNote9.newMenu();
		System.out.println("请选择功能:");
		int key=input.nextInt();
		int x;
		switch (key) {
		case 1:
			hwNote9.insertSIM(card);
			break;
		case 2:
			hwNote9.outSIM(card);
			break;
		case 3:
			hwNote9.addLinkMan();
			break;
		case 4:
			x=hwNote9.findLinkMan();
			hwNote9.deleLinkMan(x);
			break;
		case 5:
			hwNote9.findLinkMan();
			break;
		case 6:
			hwNote9.callTelephone();
			break;
		case 7:
			hwNote9.receiveTelephone();
			break;
		case 8:
			hwNote9.sendMessage();
			break;
		case 9:
			hwNote9.receiveMessage();
			break;
		case 10:
			hwNote9.setPass();
			break;
		case 11:
			hwNote9.newMenu();
			break;
		default:
			break;
		}
	}

}
