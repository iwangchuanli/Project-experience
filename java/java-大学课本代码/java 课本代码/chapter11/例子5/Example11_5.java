import java.sql.*;
public class Example11_5 {
   public static void main(String args[]) {
     Query query=new Query();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     String SQL = "SELECT * FROM "+tableName+" ORDER BY name";
     query.setSQL(SQL);
     System.out.println(tableName+"���¼����Ʒ��������:");
     query.inputQueryResult();
     SQL= "SELECT * FROM "+tableName+" ORDER BY price";
     query.setSQL(SQL);
     System.out.println(tableName+"���¼����Ʒ�۸�����:");
     query.inputQueryResult();
   }
}
