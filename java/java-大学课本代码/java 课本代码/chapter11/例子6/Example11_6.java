import java.sql.*;
public class Example11_6 {
   public static void main(String args[]) {
     Query query=new Query();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     String SQL = "SELECT * FROM "+tableName+" WHERE name LIKE '%[T宝]%'";
     query.setSQL(SQL);
     System.out.println(tableName+"表中商品名称含有“T”或“宝”的记录:");
     query.inputQueryResult();
   }
}
