package Homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test02 {

	public static void main(String[] args) throws IOException {
		//数据源封装
		BufferedReader bufR = new BufferedReader(new FileReader("E:/黑马上课材料/day14/resource/test02.txt"));
		//目的文件封装
		BufferedWriter bufW = new BufferedWriter(new FileWriter("E:/黑马上课材料/day14/resource/test02copy.txt"));
		
		//数据读写
		String line;
		while ((line = bufR.readLine()) != null) {
			bufW.write(line);
			bufW.newLine();
			bufW.flush();
		}
		//释放资源
		bufR.close();
		bufW.close();
		
	}
}
