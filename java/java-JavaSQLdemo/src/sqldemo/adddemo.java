package sqldemo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class adddemo {

    public static void main(String[] args) {
        //����Connection����
        Connection con;
        //����������
        String driver = "com.mysql.jdbc.Driver";
        //URLָ��Ҫ���ʵ����ݿ���mydata
        String url = "jdbc:mysql://localhost:3306/sqltestdb";
        //MySQL����ʱ���û���
        String user = "root";
        //MySQL����ʱ������
        String password = "123456";
        //������ѯ�����
        try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = con.createStatement();
            PreparedStatement psql;
            
            ResultSet res = null;
            //Ҫִ�е�SQL���
          //Ԥ����������ݣ���������������--������
            psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
                    + "values(?,?,?,?,?)");
            psql.setInt(1, 3212);              //���ò���1������idΪ3212������
            psql.setString(2, "����");      //���ò���2��name Ϊ����
            psql.setString(3, "�ܲ�");
            
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
        //    ResultSet rs = statement.executeQuery(psql);
            System.out.println("-----------------");

            String job = null;
            String id = null;
           // while(rs.next()){


DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date myDate2 = dateFormat2.parse("2010-09-13");
psql.setDate(4,new java.sql.Date(myDate2.getTime()));
psql.setFloat(5, (float) 2000.3);
psql.executeUpdate();           //ִ�и���

          //  }
            res.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //���ݿ��������쳣����
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("���ݿ����ݳɹ���ȡ����");
        }
    }

}
//
  


 