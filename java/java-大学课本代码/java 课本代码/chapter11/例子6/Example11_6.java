import java.sql.*;
public class Example11_6 {
   public static void main(String args[]) {
     Query query=new Query();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     String SQL = "SELECT * FROM "+tableName+" WHERE name LIKE '%[T��]%'";
     query.setSQL(SQL);
     System.out.println(tableName+"������Ʒ���ƺ��С�T���򡰱����ļ�¼:");
     query.inputQueryResult();
   }
}
