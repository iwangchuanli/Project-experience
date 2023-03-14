
/*
*@author ougaoyan ,date:2008-9-26
*/
package com.system.dbconnection;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConn {
protected static String driverName = "com.mysql.jdbc.Driver";           //连接数据库的驱动名
protected static String dbName = "jdbc:mysql://localhost/score_manage_system"; //数据库映射路径
protected static String dbUser = "root";                                       //数据库的登录名
protected static String dbPwd = "123456";                                      //数据库的登录密码
private static Connection conn = null ;                    //申明一个数据库连接                    


public DBConn(){                 //装载时建立数据库链接
  try{
   if(conn == null){
    Class.forName(driverName).newInstance(); 
    //System.out.println("Success loading Mysql Driver!");
    conn = DriverManager.getConnection(dbName,dbUser,dbPwd);
   }
   else
    return;
  }catch(Exception e){
   e.printStackTrace();
   }
}


public static ResultSet executeQuery(String sql){      //静态方法executeQuery,执行查询操作,返回结果集ResultSet
  try{
   if(conn == null){
    new DBConn();
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery(sql);
    return rs;
   }
  }catch(SQLException e){
    e.printStackTrace();
    return null;
  }finally{
  
  }
  return null;
}

public static boolean executeUpdate(String sql){      //静态方法executeUpdate,执行更新操作,返回结果集布尔值,true表示更新成功,否则更新失败
  try{
   if(conn == null){
    new DBConn();
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    stmt.executeUpdate(sql);
    return true;
   }
  }catch(SQLException e){
    e.printStackTrace();
    return false;
  }finally{
  
  }
  return false;
}

public static String getNewString(String string){                 //为解决数据库显示中文问题而写的方法
  try {
   return ( new String(string.getBytes("ISO-8859-1"),"gb2312"));
  } catch (UnsupportedEncodingException e) {
   // TODO 自动生成 catch 块
   e.printStackTrace();
  }
  return null;
}
 
public static boolean studentChangePassword(String id,String password){      //学生修改密码的方法具体的逻辑判断推至上层处理
  String sql = "update STUDENT set PASSWORD='"+password+"'where SNO='"+id+"';";
  return DBConn.executeUpdate(sql); 
}
public static boolean teacherChangePassword(String id,String password){      //教师修改密码的方法
  String sql = "update TEACHER set PASSWORD='"+password+"'where TNO='"+id+"';";
  return DBConn.executeUpdate(sql); 
}
public static boolean teacherSetScoreOfStudent(String studentid,String courseid,double score){       //老师输入分数的方法
  String sql = "update STUDY set SCORE='"+score+"' where SNO='"+studentid+"' and CNO='"+courseid+"';";
  return DBConn.executeUpdate(sql);
}
public static void close(){                       //关闭数据库连接的方法
  try{
   conn.close();
  }catch(SQLException e){
   e.printStackTrace();
  }finally{
   conn = null;
  }
}
}
