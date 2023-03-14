package com.wordcount.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileRename {
	//Main
	public static void main(String[] args) {
		String filePath = "E:\\demo\\file";
		if (isFile(filePath)) {
			System.out.println("����һ���ļ���");
			rename(filePath, newPath(filePath));
		} else {
			System.out.println("����һ���ļ��У�");
			reName(filePath);
		}
	}
	//���û����������ļ���д
	public static void rename(String filePath, String newPath) {
		BufferedReader bufR = null;
		BufferedWriter bufW = null;
		try {
			bufR = new BufferedReader(new FileReader(new File(filePath)));
			bufW = new BufferedWriter(new FileWriter(new File(newPath)));
			String line;
			while ((line = bufR.readLine()) != null) {
				bufW.write(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufW.close();
				bufR.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//�ж��ǲ���һ���ļ�
	public static boolean isFile(String filePath) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			return false;
		} else if (file.isFile()) {
			return true;
		} else {
			return false;
		}
	}
	//�и�������·��
	public static String newPath(String filePath) {
		int temp = filePath.lastIndexOf('.');
		int length = filePath.length();
		String newPath = filePath.substring(0, temp) + "2" + filePath.substring(temp, length);
		return newPath;
	}
	//���и�����renameTo����������
	public static void reName(String filePath) {
		String newPath = filePath + "2";
		boolean flag = new File(filePath).renameTo(new File(newPath));
		System.out.println(flag);
	}
}
