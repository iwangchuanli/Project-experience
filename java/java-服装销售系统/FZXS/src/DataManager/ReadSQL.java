package DataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadSQL {

    public static void main(String[] args) {
        //����Connection����
        Connection con;
        //����������
        String driver = "com.mysql.jdbc.Driver";
        //URLָ��Ҫ���ʵ����ݿ���mydata
        String url = "jdbc:mysql://localhost:3306/fyzhuangmanger";
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
            //Ҫִ�е�SQL���
            String sql = "select * from users";
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("ִ�н��������ʾ:");  
            System.out.println("-----------------");   
            String name = null;
            String pwd = null;
            while(rs.next()){
                //��ȡstuname��������
                name = rs.getString("name");
                //��ȡstuid��������
                pwd = rs.getString("pwd");
                //������
                System.out.println(name + "\t" + pwd);
            }
            rs.close();
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