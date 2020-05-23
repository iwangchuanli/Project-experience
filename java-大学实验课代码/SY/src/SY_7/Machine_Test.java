package SY_7;

public class Machine_Test {
	public static void main(String[] args) {
		microwave microwave = new microwave();
		tv tv=new tv();
		washer washer=new washer();
		cooker cooker=new cooker();
		Coin [] coins=new Coin[4];
		//定义电器类对象，随机产生使用次数
		microwave.inCoin((int)(Math.random()*10)+1);
		tv.inCoin((int)(Math.random()*10)+1);
		washer.inCoin((int)(Math.random()*10)+1);
		cooker.inCoin((int)(Math.random()*10)+1);
		//coins[0]=microwave.countCoin();
		
		//定义厨房类的对象，输出一天的总币数
		kitchen kitchen=new kitchen();
		System.out.println("一天的总币数"+kitchen.countCoin()+"微波炉"+microwave.countCoin()+"电视"+tv.countCoin()+"洗衣机"+washer.countCoin()+"电饭锅"+cooker.countCoin());
		
	}
}
