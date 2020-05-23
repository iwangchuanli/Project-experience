import java.sql.*;
public class Example11_8 {
   public static void main(String args[]) {
     PrepareQuery query=new PrepareQuery();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     query.setSQL("SELECT * FROM "+tableName);
     query.inputQueryResult();
   }
}
