package fruit;

/**
 * 
 * 一、根据需求实现代码 1.定义一个Fruit水果类 成员变量：stock库存 构造方法、set和get
 * 2.定义一个官网线程类：NetShop，实现Runnable接口 完成卖出水果的动作
 * 3.定义一个实体店线程类：FrontShop，实现Runnable接口 完成卖出水果的动作 4.使用等待唤醒机制完成卖出100份坚果的功能 例如：
 * 官网正在卖出第1份，还剩余99份 实体店正在卖出第2份，还剩余98份 官网正在卖出第3份，还剩余97份 实体店正在卖出第4份，还剩余96份 ...
 * 
 * @author Administrator
 *
 */
//主main
public class Test01 {
	public static void main(String[] args) {
		Fruit fr = new Fruit();
		NetShop ns = new NetShop(fr);
		FrontShop fs = new FrontShop(fr);
		ns.start();
		fs.start();
	}
}

// 水果对象
class Fruit {
	int stock;
	boolean flag = false;
}

// 官网线程对象
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
					System.out.println("官网在售卖！");
				}
					fr.flag = false;
					fr.notify();
				
			}
		}
	}
}

// 实体店线程
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
					System.out.println("实体店在售卖！");
				}
					fr.flag = true;
					fr.notify();
			}
		}

	}
}