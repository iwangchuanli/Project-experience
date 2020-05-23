package SY_11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.FilterWriter;
import java.io.IOException;

public class sy11_5 implements FilenameFilter {
public static void main(String[] args) {
	File tempFile=new File("e:/java/mylog.txt");
	try {
		FileWriter tofile = new FileWriter(tempFile);
		BufferedWriter out=new BufferedWriter(tofile);
	} 
	
	catch (IOException e) {
		e.printStackTrace();
	}
	
}

@Override
public boolean accept(File arg0, String arg1) {
	// TODO Auto-generated method stub
	return false;
}
}
