package fruit;

/**
 * 
 * һ����������ʵ�ִ��� 1.����һ��Fruitˮ���� ��Ա������stock��� ���췽����set��get
 * 2.����һ�������߳��ࣺNetShop��ʵ��Runnable�ӿ� �������ˮ���Ķ���
 * 3.����һ��ʵ����߳��ࣺFrontShop��ʵ��Runnable�ӿ� �������ˮ���Ķ��� 4.ʹ�õȴ����ѻ����������100�ݼ���Ĺ��� ���磺
 * ��������������1�ݣ���ʣ��99�� ʵ�������������2�ݣ���ʣ��98�� ��������������3�ݣ���ʣ��97�� ʵ�������������4�ݣ���ʣ��96�� ...
 * 
 * @author Administrator
 *
 */
//��main
public class Test01 {
	public static void main(String[] args) {
		Fruit fr = new Fruit();
		NetShop ns = new NetShop(fr);
		FrontShop fs = new FrontShop(fr);
		ns.start();
		fs.start();
	}
}

// ˮ������
class Fruit {
	int stock;
	boolean flag = false;
}

// �����̶߳���
class NetShop extends Thread {
	private Fruit fr;

	public NetShop(Fruit fr) {
		this.fr = fr;
	}
	public void run() {
		while (true) {
			synchronized (fr) {
				if (fr.flag == false) {
					try {
						fr.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("������������");
				}
					fr.flag = false;
					fr.notify();
				
			}
		}
	}
}

// ʵ����߳�
class FrontShop extends Thread {
	private Fruit fr;
	public FrontShop(Fruit fr) {
		// TODO Auto-generated constructor stub
		this.fr = fr;
	}
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (fr) {
				if (fr.flag == true) {
					try {
						fr.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					System.out.println("ʵ�����������");
				}
					fr.flag = true;
					fr.notify();
			}
		}

	}
}