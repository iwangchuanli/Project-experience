package com.day01;
/**
 * ʹ��SimpleDateFormat��,��2018-03-04ת��Ϊ2018��03��04�ա�
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
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy��mm��dd��");
		String s = sdf1.format(date);
		System.out.println(s);
	}
}
