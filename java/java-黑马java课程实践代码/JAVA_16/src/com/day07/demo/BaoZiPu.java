package com.day07.demo;

public class BaoZiPu extends Thread{

	private baozi bz;

	public BaoZiPu(String name,baozi bz) {
		super(name);
		this.bz = bz;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count =0;
		while (true) {//�����
			synchronized (bz) {//ͬ��
				if (bz.flag == true) {//���ڰ���
					try {
						bz.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				//û�а���  ��ʼ��
				System.out.println("�����̿�ʼ�����ӣ�");
				if (count % 2 == 0) {
					bz.pier = "��Ƥ";
					bz.xianer = "����";
				}else{
					bz.pier = "��Ƥ";
					bz.xianer = "ţ����";
				}
				count++;
				
				bz.flag = true;
				System.out.println("���������"+bz.pier+bz.xianer);
				System.out.println("�Ի����԰�");
				bz.notify();
			}
		}
	}
}
