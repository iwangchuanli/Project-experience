package SY_7;

public class FlashDisk extends MobileStorage{
	String info;
	public  void read() {
		System.out.println("读"+info);
	}
	public  void write(String info) {
		this.info=info;
	}
	
}
