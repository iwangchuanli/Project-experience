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
Workbook.getWorkbook(new File("����.xls")); 
//��õ�һ����������� 
Sheet sheet=book.getSheet(0); 
//�õ���һ�е�һ�еĵ�Ԫ�� 
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