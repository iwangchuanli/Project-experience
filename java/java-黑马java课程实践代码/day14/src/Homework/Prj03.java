package Homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Prj03 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufR = new BufferedReader(new FileReader("E:/黑马上课材料/day14/resource/text.txt"));
		
		List<String> colS = new ArrayList<String>();
		String line;
		while ((line = bufR.readLine()) != null) {
			colS.add(line);
		}
		System.out.println(colS);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < colS.size(); i++) {
			sb.delete(0, sb.length());
			sb = sb.append(colS.get(i)).reverse().append('\n');
			System.out.print(sb.toString()+"  ");
			colS.set(i, sb.toString());
		}
		//System.out.println(colS);
		//输出
		BufferedWriter bufW = new BufferedWriter(new FileWriter("E:/黑马上课材料/day14/resource/text.txt"));
		for (int i = colS.size()-1; i >= 0; i--) {
			System.out.print(colS.get(i)+"  ");
			bufW.write(colS.get(i));
			bufW.flush();
		}
		
		bufR.close();
		bufW.close();
	}
}
