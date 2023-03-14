package SY_7;

public class tv implements Coin{
	int coin;
	public void inCoin(int x) {
		coin=coin+x*1;
	}
	public int countCoin() {
		return coin;
	}
}