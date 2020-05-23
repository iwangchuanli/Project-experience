package Demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterDemo {
	public static void main(String[] args) throws IOException {
		//创建字符输出流对象
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("b.txt"));
		
		//public void write(int c):写一个字符
//		osw.write(97);
//		osw.write('a');
		//写完数据后，没有发现数据，为什么呢?
		//1字符=2字节
		//文件中的数据存储的基本单位是字节
		
		//public void write(char[] cbuf):写一个字符数组
//		char[] chs = {'a','b','c','d','e'};
//		osw.write(chs);
		
		//public void write(char[] cbuf,int off,int len):写一个字符数组的一部分
//		char[] chs = {'a','b','c','d','e'};
//		osw.write(chs, 1, 3);
		
		//public void write(String str):写一个字符串
//		osw.write("hello");
		
		//public void write(String str,int off,int len):写一个字符串的一部分
		osw.write("hello", 0, 3);
		
//		//void flush():刷新该流的缓冲
//		osw.flush();
//		
//		//释放资源
		osw.close(); //关闭此流，但要先刷新它
	}
}

