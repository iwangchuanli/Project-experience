package SY_7;
//定义厨房类，该类由接口的对象数组组成，包含厨房电器一天投币数计算方法；
public class kitchen {
	Coin [] coins;
    void setcoin(Coin [] coins)
    {
  	  this.coins=coins;
    }
    int countCoin()
    {   
    	int sum=0;
    	for(Coin p:coins)//遍历接口的对象数组，计算总工资
  		  sum+=p.countCoin();
    	return sum;
    }
}
