package SY_11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//将MP3_Copy.java中的内容 copy 到 e:/java/11_3_copy.txt
public class sy11_3 {
public static void main(String[] args) {
	File file=new File("e:/eclipse/java/workpeace/SY/src/SY_11/MP3_Copy.java");
	File tempfile=new File("e:/java/11_3_copy.txt");
	try {
		FileReader in=new FileReader(file);
		BufferedReader inOne=new BufferedReader(in);
		FileWriter tofile=new FileWriter(tempfile);
		BufferedWriter out=new BufferedWriter(tofile);
		String str=null;
         while((str=inOne.readLine())!=null)
         {
             out.write(" "+str);
             out.newLine();
             str=inOne.readLine();//inTwo读取一行。
         }
         in.close();
         inOne.close();
         out.flush();
         out.close();
         tofile.close();
	} catch (Exception e) {
		 System.out.println(e);
	}
}
}
