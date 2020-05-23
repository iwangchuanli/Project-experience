package Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 把项目目录下的a.txt内容复制到项目目录下的b.txt中
 * 
 * 数据源：
 * 		a.txt---读数据---字符流---InputStreamReader---FileReader---BufferedReader
 * 目的地：
 * 		b.txt---写数据---字符流---OutputStreamWriter---FileWriter---BufferedWriter
 */
public class CopyTxtTest {
	public static void main(String[] args) throws IOException {
		//封装数据源
		BufferedReader br = new BufferedReader(new FileReader("a.txt"));
		//封装目的地
		BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));
		
		//读写数据
		//一次读写一个字符数组
		char[] chs = new char[1024];
		int len;
		while((len=br.read(chs))!=-1) {
			bw.write(chs, 0, len);
		}
		
		//释放资源
		bw.close();
		br.close();
	}
}

