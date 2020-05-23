package SY_7;

import java.util.Scanner;

public class Computer {
	MP3Player mPlayer=new MP3Player() ;
	FlashDisk flashDisk=new FlashDisk();
	MobileHardDisk moDisk=new MobileHardDisk();
	Scanner input=new Scanner(System.in);
	String info=input.nextLine();
	
	//为FlashDisk写ReadFromFlashDisk、WriteToFlashDisk两个方法。总共六个方法。
	
	
	
	//mobileStorage=mPlayer;
	public void ReadData(){
		System.out.println("读");
	}
	public void WriteData(String info){
		
	}
}
