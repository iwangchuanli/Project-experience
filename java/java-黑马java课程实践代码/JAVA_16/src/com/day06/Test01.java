package com.day06;
/**
 * ��һ�⣺�����������󣬲��ô���ʵ��
	һ����1000�ŵ�ӰƱ,����������������ȡ,����ÿ����ȡ��ʱ��Ϊ3000����,
	Ҫ��:���ö��߳�ģ����Ʊ���̲���ӡʣ���ӰƱ������
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		sellTicket st = new sellTicket("���ν��", 1000);
		st.start();
	}
}
class sellTicket extends Thread{
	private int count;
	public sellTicket(String name,int count) {
		// TODO Auto-generated constructor stub
		super(name);
		this.count = count;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (count > 0) {
			try {
				sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			count --;
			System.out.println(super.getName()+"����ȥ1�ţ���ʣ��"+count+"�š�");
		}
	}
}
