import java.sql.*;
import java.util.*;
public class Example11_2 {
   public static void main(String args[]) {
     Query query=new Query();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     query.setSQL("SELECT * FROM "+tableName);
     query.inputQueryResult();
   }
}
