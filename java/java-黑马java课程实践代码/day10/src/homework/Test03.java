package homework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test03 {

	public static void main(String[] args) throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入您的出生日期：");
		String date = input.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birdDate = sdf.parse(date);
		System.out.println(birdDate);
		
		Date nowDate = new Date();
		System.out.println(nowDate);
		
		long days = nowDate.getTime() - birdDate.getTime();
		System.out.println(days);
		System.out.println((days/3600000));
		
		Date liveDay = new Date(days);
		System.out.println(liveDay);
		
		String day = liveDay.toString();
		System.out.println(day);
//		Date d = new Date();
//		System.out.println(d);
//		System.out.println(d.getTime());
//		d.setTime(1000*60*60);
//		System.out.println(d.getTime());
		
		
	}
}
