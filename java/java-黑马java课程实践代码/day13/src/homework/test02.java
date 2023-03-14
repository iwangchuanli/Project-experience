package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test02 {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/黑马上课材料/day13/resource/窗里窗外.txt");
		File targeFile = new File("E:/黑马上课材料/day13/resource/targeFile.txt");
		FileInputStream fileIn = new FileInputStream(file);
		FileOutputStream fileOut = new FileOutputStream(targeFile);
		byte [] fio = new byte[1024];
		int len;
		while ((len = fileIn.read(fio)) != -1) {
			fileOut.write(fio, 0, len);
		}
		fileIn.close();fileOut.close();
		System.out.println("文件拷贝完毕！");
	}
}
