import java.sql.*; 
public class Example11_10 {
    public static void main(String args[]){
       Connection con = null;
       Statement sql; 
       ResultSet rs;
       try { Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
       }
       catch(ClassNotFoundException e){
             System.out.println(""+e);
       }
       try{ double n = 1;
            con = DriverManager.getConnection("jdbc:odbc:myData","","");
            con.setAutoCommit(false);       //关闭自动提交模式
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='A001'");
            rs.next();
            double priceOne = rs.getDouble("price");
            System.out.println("事务操作之前A001的price:"+priceOne); 
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='B002'");
            rs.next();
            double priceTwo = rs.getDouble("price");
            System.out.println("事务操作之前B002的price:"+priceTwo); 
            priceOne = priceOne-n;
            priceTwo = priceTwo+n;
            sql.executeUpdate
               ("UPDATE goods SET price ="+priceOne+" WHERE number='A001'");
            sql.executeUpdate
               ("UPDATE goods SET price="+priceTwo+" WHERE number='B002'");
            con.commit(); //开始事务处理,如果发生异常直接执行catch块
            con.setAutoCommit(true);
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='A001'");
            rs.next();
            priceOne = rs.getDouble("price");
            System.out.println("事务操作之后A001的price:"+priceOne); 
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='B002'");
            rs.next();
            priceTwo = rs.getDouble("price");
            System.out.println("事务操作之后B002的price:"+priceTwo); 
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
