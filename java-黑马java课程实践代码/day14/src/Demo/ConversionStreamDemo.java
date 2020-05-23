package Demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 转换流中的编码和解码问题
2.4.1 转换流指的是?
	转换流其实就是一个字符流。
转换流 = 字节流 + 编码表
2.4.2 转换流的构造方法
OutputStreamWriter 字符输出流
  public OutputStreamWriter(OutputStream out)
  		根据默认编码把字节流的数据转换为字符流
  public OutputStreamWriter(OutputStream out,String charsetName)
  		根据指定编码把字节流数据转换为字符流
  
InputStreamReader 字符输入流
  public InputStreamReader(InputStream in)
  		用默认的编码读数据
  public InputStreamReader(InputStream in,String charsetName)
*/
public class ConversionStreamDemo {
	public static void main(String[] args) throws IOException {
		//public OutputStreamWriter(OutputStream out):默认编码GBK
		//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"));
		//public OutputStreamWriter(OutputStream out,String charsetName)
		//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"),"GBK");
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"),"UTF-8");
		//调用写数据的方法
		osw.write("你好");
		//释放资源
		osw.close();
		System.out.println("------------------------");
		
		//public InputStreamReader(InputStream in):默认编码GBK
		//InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"));
		//public InputStreamReader(InputStream in,String charsetName)
		//InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"),"GBK");
		InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"),"UTF-8");
		//读数据：一次读取一个字符数据
		int ch;
		while((ch=isr.read())!=-1) {
			System.out.print((char)ch);
		}
		//释放资源
		isr.close();
		
	}
}
