import java.sql.*;
public class ReadExaminationPaper {
   String sourceName,tableName;
   public ReadExaminationPaper() {
      try{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      }
      catch(ClassNotFoundException e) {
         System.out.print(e);
      }
   }
   public int getAmount(){
      Connection con;
      Statement sql;
      ResultSet rs;
      try { 
           String uri="jdbc:odbc:"+sourceName; 
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
   public String[] getExamQuestion(int number) {
      Connection con;
      Statement sql;
      ResultSet rs;
      String [] examinationPaper = new String[6];
      try { 
           String uri="jdbc:odbc:"+sourceName; 
           String id="";
           String password="";
           con=DriverManager.getConnection(uri,id,password);
           sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery("SELECT * FROM testForm");
           rs.absolute(number);
           examinationPaper[0] = rs.getString(1);//��Ŀ
           examinationPaper[1] = rs.getString(2);//ѡ��A
           examinationPaper[2] = rs.getString(3);//ѡ��B
           examinationPaper[3] = rs.getString(4);//ѡ��C
           examinationPaper[4] = rs.getString(5);//ѡ��D
           examinationPaper[5] = rs.getString(6);//��
           con.close();
       }
       catch(SQLException e) {
           System.out.println("�޷��������"+e);
       }
       return examinationPaper;
   }
   public void setTableName(String s){
      tableName=s;
   }  
   public void setSourceName(String s) {
      sourceName=s; 
   }  
}
