package SY_7;

public class cooker implements Coin{
	int coin;
	public void inCoin(int x) {
		coin=coin+x*2;
	}
	public int countCoin() {
		return coin;
	}
}
