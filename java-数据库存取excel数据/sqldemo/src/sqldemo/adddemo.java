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
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/sqltestdb";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            PreparedStatement psql;
            
            ResultSet res = null;
            //要执行的SQL语句
          //预处理添加数据，其中有两个参数--“？”
            psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
                    + "values(?,?,?,?,?)");
            psql.setInt(1, 3212);              //设置参数1，创建id为3212的数据
            psql.setString(2, "王刚");      //设置参数2，name 为王刚
            psql.setString(3, "总裁");
            
            //3.ResultSet类，用来存放获取的结果集！！
        //    ResultSet rs = statement.executeQuery(psql);
            System.out.println("-----------------");

            String job = null;
            String id = null;
           // while(rs.next()){


DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date myDate2 = dateFormat2.parse("2010-09-13");
psql.setDate(4,new java.sql.Date(myDate2.getTime()));
psql.setFloat(5, (float) 2000.3);
psql.executeUpdate();           //执行更新

          //  }
            res.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

}
//
  


 