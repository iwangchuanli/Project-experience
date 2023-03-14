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
            con.setAutoCommit(false);       //�ر��Զ��ύģʽ
            sql=con.createStatement();
            sql.addBatch("UPDATE goods SET price =5555 WHERE number='A001'");//���Ϊ1��SQL���
            sql.addBatch("UPDATE goods SET name ='haierTV' WHERE number='A001'");//���Ϊ2��SQL���
            sql.addBatch("UPDATE goods SET price ='8765' WHERE number='B002'");  //���Ϊ3��SQL���
            sql.addBatch("INSERT INTO goods VALUES ('A777','�������ӻ�','2010-12-20',3976)");  //���Ϊ4��SQL���
            int [] number=sql.executeBatch();//��ʼ���������ر�ִ�е�SQL�������
            con.commit();   //����������
            System.out.println("����"+number.length+"��SQL��䱻ִ��");
            sql.clearBatch();
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
