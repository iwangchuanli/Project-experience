package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Prj01 {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/�����Ͽβ���/day13/resource/qucong.txt");
		File newfile = new File("E:/�����Ͽβ���/day13/resource/quconghou.txt");
		//�ļ��ֽ���
		FileInputStream fileIn = new FileInputStream(file);
		FileOutputStream fileOut = new FileOutputStream(newfile);
		//FileReader fileR = new FileReader(file);
		
		int len;//hashset Ԫ��Ψһ
		HashSet<Byte > hsSet = new HashSet<>();
		while ((len = fileIn.read()) != -1) {
			hsSet.add((byte) len);
		}
		System.out.println(hsSet);

		for (Byte byt : hsSet) {
			fileOut.write(byt);
		}
		fileIn.close();
		fileOut.close();
	}	
}

