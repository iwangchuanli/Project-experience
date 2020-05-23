package SY_7;

public  class MP3Player extends MobileStorage{
	String info;
	public  void read() {
		System.out.println("读"+info);
	}
	public  void write(String info) {
		this.info=info;
	}
	public void PlayMusic(){
		System.out.println("PlayMusic");
	}
	
}
