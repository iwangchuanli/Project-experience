import java.sql.*;
public class Example11_12 {
   public static void main(String args[]) {
     CachedQuery query=new CachedQuery();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     query.setSQL("SELECT * FROM "+tableName);
     query.inputQueryResult();
   }
}
