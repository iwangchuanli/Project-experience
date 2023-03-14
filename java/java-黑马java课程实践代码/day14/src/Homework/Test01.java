package Homework;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test01 {

	public static void main(String[] args) throws IOException {
		//创建字符输出流对象
		OutputStreamWriter outWrite = new OutputStreamWriter(new FileOutputStream("E:/黑马上课材料/day14/resource/test01.txt"));
		String str = "你好";
		
		byte[] by = str.getBytes("UTF-8");
		String s = new String(by,"UTF-8");
		System.out.println(s);
		outWrite.write(s);
		
		InputStreamReader inRead = new InputStreamReader(new FileInputStream("E:/黑马上课材料/day14/resource/test01.txt"),"UTF-8");
		char[] ch = new char[1024];
		int len;
		while((len=inRead.read(ch))!=-1) {
			System.out.print(new String(ch,0,len));
		}

		outWrite.flush();
		outWrite.close();
		inRead.close();
	}
}
