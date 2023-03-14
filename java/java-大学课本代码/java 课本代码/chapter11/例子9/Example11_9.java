import java.sql.*;
public class Example11_9 {
   public static void main(String args[]) {
     AddRecord insertRecord=new AddRecord();
     String datasorce="myData";
     String tableName="goods";
     insertRecord.setDatasourceName(datasorce);
     insertRecord.setTableName(tableName);
     insertRecord.setNumber("D001");
     insertRecord.setName("ÁªÏëµçÄÔ");
     insertRecord.setMadeTime("2010-12-10");
     insertRecord.setPrice(7000);
     String backMess=insertRecord.addRecord();
     System.out.println(backMess);
   }
}

