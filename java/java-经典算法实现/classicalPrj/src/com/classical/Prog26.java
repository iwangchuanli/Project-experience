package com.classical;

/**
 * ��Ŀ�����������ڼ��ĵ�һ����ĸ���ж�һ�������ڼ��������һ����ĸһ��������� �жϵڶ�����ĸ��
�����������������ȽϺã������һ����ĸһ�������ж����������if����жϵڶ�����ĸ��

 * @author Administrator
 *
 */
import java.io.*;
public class Prog26{
	public static void main(String[] args){
		String str = new String();
	  BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
	  System.out.print("���������ڵ�Ӣ�ĵ���ǰ�����ĸ���ĸ����");
	  try{
		  str = bufIn.readLine();
	  }catch(IOException e){
		  e.printStackTrace();
	  }finally{
		  try{
			  bufIn.close();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
	  }
	  week(str);
	}
	
	
	private static void week(String str){
		int n = -1;
		if(str.trim().equalsIgnoreCase("Mo") || str.trim().equalsIgnoreCase("Mon") || str.trim().equalsIgnoreCase("Mond"))
		  n = 1;
		if(str.trim().equalsIgnoreCase("Tu") || str.trim().equalsIgnoreCase("Tue") || str.trim().equalsIgnoreCase("Tues"))
		  n = 2; 
		if(str.trim().equalsIgnoreCase("We") || str.trim().equalsIgnoreCase("Wed") || str.trim().equalsIgnoreCase("Wedn"))
		  n = 3;
		if(str.trim().equalsIgnoreCase("Th") || str.trim().equalsIgnoreCase("Thu") || str.trim().equalsIgnoreCase("Thur"))
		  n = 4; 
		if(str.trim().equalsIgnoreCase("Fr") || str.trim().equalsIgnoreCase("Fri") || str.trim().equalsIgnoreCase("Frid"))
		  n = 5;
		if(str.trim().equalsIgnoreCase("Sa") || str.trim().equalsIgnoreCase("Sat") || str.trim().equalsIgnoreCase("Satu"))
		  n = 2; 
		if(str.trim().equalsIgnoreCase("Su") || str.trim().equalsIgnoreCase("Sun") || str.trim().equalsIgnoreCase("Sund"))
		  n = 0; 
		switch(n){
			case 1:
			  System.out.println("����һ");
			  break;
			case 2:
			  System.out.println("���ڶ�");
			  break;
			case 3:
			  System.out.println("������");
			  break;
			case 4:
			  System.out.println("������");
			  break;
			case 5:
			  System.out.println("������");
			  break;
			case 6:
			  System.out.println("������");
			  break;
			case 0:
			  System.out.println("������");
			  break;
			default:
			  System.out.println("��������");
			  break;
		}
	}
}
