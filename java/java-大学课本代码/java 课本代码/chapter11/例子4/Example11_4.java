import java.sql.*;
import java.util.*;
public class Example11_4 {
   public static void main(String args[]) {
     Query query=new Query();
     String dataSource="myData";
     String tableName="goods";
     query.setDatasourceName(dataSource);
     query.setTableName(tableName);
     String number = "A001";
     String SQL = "SELECT * FROM "+tableName+" WHERE number ='"+number+"'";
     query.setSQL(SQL);
     System.out.println(tableName+"������Ʒ����"+number+"�ļ�¼");
     query.inputQueryResult();
     double max = 300000,min=260000;
     SQL= "SELECT * FROM "+tableName+" WHERE price >="+min+" AND price <="+max;
     query.setSQL(SQL);
     System.out.println(tableName+"���м۸���"+min+"��"+max+"֮��ļ�¼:");
     query.inputQueryResult();
   }
}
