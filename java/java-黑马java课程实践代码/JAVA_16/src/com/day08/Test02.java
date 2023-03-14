package com.day08;

import java.io.File;

/**
 * 第三题：使用文件过滤器来打印出D盘下的所有.txt文件名称
 * 
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		File dir = new File("E:/黑马上课材料");

		File[] files = dir.listFiles();
		directory(dir);
	}

	public static void file(File fil) {
		String name = fil.getName();
		if (name.endsWith(".txt")) {
			System.out.println(fil.getAbsolutePath() + "  是一个TXT文件");
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