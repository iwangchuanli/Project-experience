
package Demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedStreamDemo {

	public static void main(String[] args) throws IOException {
				// BufferedOutputStream(OutputStream out)FileOutputStream fos = new FileOutputStream("a.txt");
				// BufferedOutputStream bos = new BufferedOutputStream(fos);
				// 上面的两句等价于下面的这一句
				// BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("a.txt"));
				
				// bos.write("hello".getBytes());
				// bos.close();

				// BufferedInputStream(InputStream in)
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:/黑马上课材料/day14/resource/a.txt"));
				//方式1：一次读取一个字节
//				int by;
//				while((by=bis.read())!=-1) {
//					System.out.print((char)by);
//				}
//				
				//方式2：一次读取一个字节数组
				byte[] bys = new byte[1024];
				int len;
				while((len=bis.read(bys))!=-1) {
					System.out.print(new String(bys,0,len));
				}
				
				bis.close();

	}
}
