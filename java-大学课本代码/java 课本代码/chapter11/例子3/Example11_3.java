import java.sql.*; 
public class Example11_3 {
   public static void main(String args[]) {
      Connection con;
      Statement sql; 
      ResultSet rs;
      try{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      }
      catch(ClassNotFoundException e) {
         System.out.print(e);
      }
      try { 
          con=DriverManager.getConnection("jdbc:odbc:myData","","");
          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
          rs=sql.executeQuery("SELECT * FROM goods ");
          rs.last();
          int rows = rs.getRow();
          System.out.println("goods表共有"+rows+"条记录");
          rs.afterLast();
          System.out.println("倒序输出goods表中的记录:");
          while(rs.previous()) {
             String number=rs.getString(1);
             String name=rs.getString(2);
             Date date=rs.getDate("madeTime");
             double price=rs.getDouble("price");
             System.out.printf("%-4s",number);
             System.out.printf("%-6s",name);
             System.out.printf("%-15s",date.toString()); 
             System.out.printf("%6s\n",price);
          }
          con.close();
      }
      catch(SQLException e) { 
         System.out.println(e);
      }
  }
}
