package SY_11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MP3_Copy {
	public static void main(String[] args) {
	//建立字节数组
	byte[] b=new byte[20];	
	try {
//建立文件对象，作为文件输入流的源
File sourceFile=new File("e:\\java","小情歌.mp3");
//建立文件对象，作为文件输入出流的源
File targetFile=new File("e:","小情歌1.mp3");
if(!targetFile.exists())	targetFile.createNewFile();
//建立字节输入输出流
FileInputStream in=new FileInputStream(sourceFile);
FileOutputStream out=new FileOutputStream(targetFile);
//从文件中以字节为单位读数据，未读出字节返回-1
long begin=System.currentTimeMillis();
		while(in.read(b)!=-1)
		{
//通过输出流对目标文件进行写操作
out.write(b);				
		}
		long end=System.currentTimeMillis();
		System.out.println("复制时间:"+(end-begin));
		out.close();
		in.close();
	} catch (IOException e) {		
		e.printStackTrace();
	}	
	}
}

