package com.day08;

import java.io.File;

/**
 * �����⣺ʹ���ļ�����������ӡ��D���µ�����.txt�ļ�����
 * 
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		File dir = new File("E:/�����Ͽβ���");

		File[] files = dir.listFiles();
		directory(dir);
	}

	public static void file(File fil) {
		String name = fil.getName();
		if (name.endsWith(".txt")) {
			System.out.println(fil.getAbsolutePath() + "  ��һ��TXT�ļ�");
		}
	}

	public static void directory(File fil) {
		File[] files = fil.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				directory(file);
			}
			if (file.isFile()) {
				file(file);
			}
		}
	}
}