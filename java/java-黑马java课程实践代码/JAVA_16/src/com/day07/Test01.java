package com.day07;
/**
 * 
 * һ����������ʵ�ִ���
	1.����һ��Fruitˮ����
		��Ա������stock���
		���췽����set��get
	2.����һ�������߳��ࣺNetShop��ʵ��Runnable�ӿ�
		�������ˮ���Ķ���
	3.����һ��ʵ����߳��ࣺFrontShop��ʵ��Runnable�ӿ�
		�������ˮ���Ķ���
	4.ʹ�õȴ����ѻ����������100�ݼ���Ĺ���
		���磺
			��������������1�ݣ���ʣ��99��
			ʵ�������������2�ݣ���ʣ��98��
			��������������3�ݣ���ʣ��97��
			ʵ�������������4�ݣ���ʣ��96��
			...
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		Fruit fruit = new Fruit();
		
		NetShop ns = new NetShop("����", fruit);
		FrontShop fs = new FrontShop("ʵ���", fruit);
		
		ns.start();
		fs.start();
	}
}
