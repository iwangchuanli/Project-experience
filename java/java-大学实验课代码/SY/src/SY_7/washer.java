package SY_7;

public class washer implements Coin{
	int coin;
	public void inCoin(int x) {
		coin=coin+x*3;
	}
	public int countCoin() {
		return coin;
	}
}