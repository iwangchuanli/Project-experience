package SY_5;


import java.util.Scanner;

public class HuaWeiNote9 {
	SIM card=new SIM();
	PhoneBook [] pBook=new PhoneBook[10];
	double phonePwd;
	Scanner input=new Scanner(System.in);
	public void insertSIM(SIM card){ //定义方法insertSIM,功能为手机上放入SIM卡；

		this.card=card;
	}
	public void outSIM(SIM card){ //定义方法outSIM,功能为从手机上取出SIM卡；
		
	}
	public void addLinkMan(){//定义方法addLinkMan,功能为添加新的联系人到手机通讯录中；
		for(int i=0;i<pBook.length;i++){
			pBook[i]=new PhoneBook();
			pBook[i].name=input .nextLine();
			pBook[i].phoneNum=input .nextLine();
		}
	}
		
		public void deleLinkMan(int x) {//定义方法deleLinkMan,功能为删除手机通讯录中的联系人；
			for(int i=x;i<pBook.length;i++){
				pBook[i]=pBook[i+1];
			}
		}
	//	定义方法findLinkMan,功能为查找手机通讯录中的联系人，返回查找的结果；
		public int findLinkMan(){
			String name=input.nextLine();
			int x=0;
			for(int i=0;i<pBook.length;i++){
				if(name==pBook[i].name){
					x=i;
				}	
			}
			return x;
		}
//定义方法callTelephone,功能为拨打电话，可以直接拨打电话，也可以在通讯录中查找联系人，再拨打电话；
		public void callTelephone(){
			String num=input.nextLine();
			 receiveTelephone();
		}
//定义方法receiveTelephone,功能为接电话，可以先接听电话，若该电话未保存，可保存该联系人至通讯录；
		public void receiveTelephone(){
			
		}
//定义方法sendMessage,功能为发送消息，可以直接发送消息，也可以在通讯录中查找联系人，再发送消息；
		public void sendMessage(){
			String info=input.nextLine();
			
		}
//定义方法receiveMessage,功能为发送消息，可以接收消息，若该电话未保存，可保存该联系人至通讯录；
		public void receiveMessage(){
			
		}
//定义方法setPass,,功能为设置手机使用密码；
		public void  setPass() {
			
		}
//定义方法newMenu,,功能为手机菜单；
		public void newMenu() {
			System.out.print("1、放入SIM卡"
					+ "2、取出SIM卡"
					+ "3、添加新的联系人到手机通讯录中"
					+ "4、删除手机通讯录中的联系人"
					+ "5、查找手机通讯录中的联系人"
					+ "6、拨打电话"
					+ "7、接听电话"
					+ "8、发送消息"
					+ "9、接收消息"
					+ "10、设置手机使用密码"
					+ "11、手机菜单");
			
		}

	}
