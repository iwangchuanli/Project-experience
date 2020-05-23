package com.day01;
/**
 * 使用SimpleDateFormat类,把2018-03-04转换为2018年03月04日。
 */
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test02 {

	public static void main(String[] args) throws Exception {
		String str = "2018-03-04";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = sdf.parse(str);
		System.out.println(date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年mm月dd日");
		String s = sdf1.format(date);
		System.out.println(s);
	}
}
