import java.sql.*;
public class ModifyTable {
   String datasourceName=""; 
   String SQL,message=""; 
   public ModifyTable() {
      try{  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
      catch(Exception e){ } 
   }
   public void setSQL(String SQL) {
      this.SQL=SQL;
   }
   public void setDatasourceName(String s) {
      datasourceName=s.trim();
   }
   public String modifyRecord() {
     Connection con=null;
     Statement sql=null; 
       try {  String uri="jdbc:odbc:"+datasourceName; 
              String id="";
              String password="";
              con=DriverManager.getConnection(uri,id,password);
              sql=con.createStatement(); 
              sql.execute(SQL);
              message="²Ù×÷³É¹¦";
              con.close();
       }
       catch(SQLException e) 
           {  message=e.toString();
           }
       return message;
    }
} 
