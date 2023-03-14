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
            con.setAutoCommit(false);       //�ر��Զ��ύģʽ
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='A001'");
            rs.next();
            double priceOne = rs.getDouble("price");
            System.out.println("�������֮ǰA001��price:"+priceOne); 
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='B002'");
            rs.next();
            double priceTwo = rs.getDouble("price");
            System.out.println("�������֮ǰB002��price:"+priceTwo); 
            priceOne = priceOne-n;
            priceTwo = priceTwo+n;
            sql.executeUpdate
               ("UPDATE goods SET price ="+priceOne+" WHERE number='A001'");
            sql.executeUpdate
               ("UPDATE goods SET price="+priceTwo+" WHERE number='B002'");
            con.commit(); //��ʼ������,��������쳣ֱ��ִ��catch��
            con.setAutoCommit(true);
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='A001'");
            rs.next();
            priceOne = rs.getDouble("price");
            System.out.println("�������֮��A001��price:"+priceOne); 
            rs = sql.executeQuery("SELECT * FROM goods WHERE number='B002'");
            rs.next();
            priceTwo = rs.getDouble("price");
            System.out.println("�������֮��B002��price:"+priceTwo); 
            con.close();
         }
         catch(SQLException e){
            try{ con.rollback();          //�������������Ĳ���
            }
            catch(SQLException exp){}
            System.out.println(e);
         }
    }
}
