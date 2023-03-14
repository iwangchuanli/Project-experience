import java.sql.*;
import com.sun.rowset.*; 
public class CachedQuery {
   String datasourceName="";        //����Դ��
   String tableName="";            //����
   String SQL;                     //SQL���
   CachedRowSetImpl rowSet;        //��������
   public CachedQuery() {
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
      Statement sql; 
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
           sql=con.createStatement();
           rs=sql.executeQuery(SQL);
           rowSet=new CachedRowSetImpl();
           rowSet.populate(rs);
           con.close();              //���ھͿ��Թر�������
           while(rowSet.next()) {
             for(int k=1;k<=�ֶθ���;k++) {
                 System.out.print(" "+rowSet.getString(k)+" ");
             }
             System.out.println("");
           }
       }
       catch(SQLException e) {
           System.out.println("��������ȷ�ı���"+e);
       }
   }    
}
