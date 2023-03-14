package sqldemo;
import java.io.*; 
import jxl.*; 
public class ReadXLS 
{ 
public static void main(String args[]) 
{ 
try 
{ 
Workbook book= 
Workbook.getWorkbook(new File("测试.xls")); 
//获得第一个工作表对象 
Sheet sheet=book.getSheet(0); 
//得到第一列第一行的单元格 
Cell cell1=sheet.getCell(0,0); 
String result=cell1.getContents(); 
System.out.println(result); 
book.close(); 
}catch(Exception e) 
{ 
System.out.println(e); 
} 
} 
} 