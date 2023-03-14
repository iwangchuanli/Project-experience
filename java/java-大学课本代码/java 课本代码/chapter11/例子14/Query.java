import java.sql.*;
public class Query {
   String datasourceName="";        //����Դ��
   String tableName="";            //����
   Object a[][];
   public Query() {
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
   public Object[][] getRecord() {
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
           int n=getAmount();
           a = new Object[n][�ֶθ���];
           sql=con.prepareStatement("select * From "+tableName);
           rs=sql.executeQuery();
           int m=0;
           while(rs.next()) {
             for(int k=1;k<=�ֶθ���;k++) {
                 a[m][k-1] = rs.getString(k);
             }
             m++;
           }
           con.close();
       }
       catch(SQLException e) {
           System.out.println("��������ȷ�ı���"+e);
       }
       return a;
   }  
   public int getAmount(){
      Connection con;
      Statement sql;
      ResultSet rs;
      try { 
           String uri="jdbc:odbc:"+datasourceName; 
           String id="";
           String password="";
           con=DriverManager.getConnection(uri,id,password);
           sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery("SELECT * FROM "+tableName);
           rs.last();
           int rows = rs.getRow();
           return rows;
      }
      catch(SQLException exp){
           System.out.println(""+exp);
           return 0;
      }  
   }
}
