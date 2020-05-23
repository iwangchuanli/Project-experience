package com.day01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test03{

	public static void main(String[] args) throws Exception {
		String s = "2018年02月14日";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
		Date date = sdf.parse(s);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.MONTH,cal.MONTH-1);
		int day = cal.get(cal.DAY_OF_WEEK);
		char [] ch = {'日','一','二','三','四','五','六'};
		System.out.println(sdf.format(date)+"是星期"+ch[day-1]);
	}
}
