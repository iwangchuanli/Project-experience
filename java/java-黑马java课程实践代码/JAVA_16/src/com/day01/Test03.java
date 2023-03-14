package com.day01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test03{

	public static void main(String[] args) throws Exception {
		String s = "2018��02��14��";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��mm��dd��");
		Date date = sdf.parse(s);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.MONTH,cal.MONTH-1);
		int day = cal.get(cal.DAY_OF_WEEK);
		char [] ch = {'��','һ','��','��','��','��','��'};
		System.out.println(sdf.format(date)+"������"+ch[day-1]);
	}
}
