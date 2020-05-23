import java.sql.*; 
public class Example11_11 {
    public static void main(String args[]){
       Connection con=null;
       Statement sql; 
       ResultSet rs;
       try { Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
       }
       catch(ClassNotFoundException e){
             System.out.println(""+e);
       }
       try{ double n=500;
            con=DriverManager.getConnection("jdbc:odbc:myData","","");
            con.setAutoCommit(false);       //关闭自动提交模式
            sql=con.createStatement();
            sql.addBatch("UPDATE goods SET price =5555 WHERE number='A001'");//序号为1的SQL语句
            sql.addBatch("UPDATE goods SET name ='haierTV' WHERE number='A001'");//序号为2的SQL语句
            sql.addBatch("UPDATE goods SET price ='8765' WHERE number='B002'");  //序号为3的SQL语句
            sql.addBatch("INSERT INTO goods VALUES ('A777','北京电视机','2010-12-20',3976)");  //序号为4的SQL语句
            int [] number=sql.executeBatch();//开始批处理，返回被执行的SQL语句的序号
            con.commit();   //进行事务处理
            System.out.println("共有"+number.length+"条SQL语句被执行");
            sql.clearBatch();
            con.close();
         }
         catch(SQLException e){
            try{ con.rollback();          //撤消事务所做的操作
            }
            catch(SQLException exp){}
            System.out.println(e);
         }
    }
}
