import java.sql.*;
public class PrepareQuery {
   String datasourceName="";        //����Դ��
   String tableName="";            //����
   String SQL;                     //SQL���
   public PrepareQuery() {
      try{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      }
      catch(ClassNotFoundException e) {
         System.out.print(e);
      }
   }
   public void setDatasourceName(String s) {
      datasourceName=s.trim();
   }
   public void setTableName(String s) {
      tableName=s.trim();
   }
   public void setSQL(String SQL) {
      this.SQL=SQL.trim();
   }
   public void inputQueryResult() {
      Connection con;
      PreparedStatement sql;
      ResultSet rs;
      try { 
           String uri="jdbc:odbc:"+datasourceName; 
           String id="";
           String password="";
           con=DriverManager.getConnection(uri,id,password);
           DatabaseMetaData metadata=con.getMetaData();
           ResultSet rs1=metadata.getColumns(null,null,tableName,null);
           int �ֶθ���=0;
           while(rs1.next()) {
              �ֶθ���++;
           }
           sql=con.prepareStatement(SQL);
           rs=sql.executeQuery();
           while(rs.next()) {
             for(int k=1;k<=�ֶθ���;k++) {
                 System.out.print(" "+rs.getString(k)+" ");
             }
             System.out.println("");
           }
           con.close();
       }
       catch(SQLException e) {
           System.out.println("��������ȷ�ı���"+e);
       }
   }    
}
