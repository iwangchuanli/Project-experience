package Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyJavaTest {
	public static void main(String[] args) throws IOException {
		//封装数据源
		BufferedReader br = new BufferedReader(new FileReader("BufferedStreamDemo.java"));
		//封装目的地
		BufferedWriter bw = new BufferedWriter(new FileWriter("Copy.java"));
		
		//读写数据
		String line;
		while((line=br.readLine())!=null) {
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		
		//释放资源
		bw.close();
		br.close();
		
	}
}

