package com.classical;
/***
 * ��Ŀ����ҵ���ŵĽ������������ɡ�
 * ����(I)���ڻ����10��Ԫʱ���������10%��
 * �������10��Ԫ������20��Ԫʱ������10��Ԫ�Ĳ��ְ�10%��ɣ�����10��Ԫ�Ĳ��֣��ɿ����7.5%��
 * 20��40��֮��ʱ������20��Ԫ�Ĳ��֣������5%��
 * 40��60��֮��ʱ����40��Ԫ�Ĳ��֣������3%��
 * 60��100��֮��ʱ������60��Ԫ�Ĳ��֣������1.5%��
 * ����100��Ԫʱ������100��Ԫ�Ĳ��ְ�1%��ɣ�
 * �Ӽ������뵱������I����Ӧ���Ž��������� 
����������������������ֽ磬��λ��ע�ⶨ��ʱ��ѽ������   ������ ��long
 */
import java.io.*;
public class Prog12{
	public static void main(String[] args){
		System.out.print("�����뵱ǰ����");
		long profit = Long.parseLong(key_Input());//���÷������ճ�����
		System.out.println("Ӧ������"+bonus(profit));//���÷�������Ӧ������
	}
	//���ܴӼ������������
	private static String key_Input(){
		String str = null;
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
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
		return str;
	}
	//���㽱��
	private static long bonus(long profit){
		long prize = 0;
		long profit_sub = profit;
		if(profit>1000000){
			profit = profit_sub-1000000;//����߳��Ĳ���
			profit_sub = 1000000;//ʣ����������ڼ�����������Ľ���
			prize += profit*0.01;//��һ���ֵĽ���
		}
		if(profit>600000){
			profit = profit_sub-600000;
			profit_sub = 600000;
			prize += profit*0.015; 
		}
		if(profit>400000){
			profit = profit_sub-400000;
			profit_sub = 400000;
			prize += profit*0.03;
		}
		if(profit>200000){
			profit = profit_sub-200000;
			profit_sub = 200000;
			prize += prize*0.05;
		}
		if(profit>100000){
			profit = profit_sub-100000;
			profit_sub = 100000;
			prize += profit*0.075;
		}
		prize += profit_sub*0.1;//�����ϵ���100000�Ľ�����ý����ܶ�
		return prize;//����
	}
}

