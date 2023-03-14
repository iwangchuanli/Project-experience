
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
protected static String driverName = "com.mysql.jdbc.Driver";           //�������ݿ��������
protected static String dbName = "jdbc:mysql://localhost/score_manage_system"; //���ݿ�ӳ��·��
protected static String dbUser = "root";                                       //���ݿ�ĵ�¼��
protected static String dbPwd = "123456";                                      //���ݿ�ĵ�¼����
private static Connection conn = null ;                    //����һ�����ݿ�����                    


public DBConn(){                 //װ��ʱ�������ݿ�����
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


public static ResultSet executeQuery(String sql){      //��̬����executeQuery,ִ�в�ѯ����,���ؽ����ResultSet
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

public static boolean executeUpdate(String sql){      //��̬����executeUpdate,ִ�и��²���,���ؽ��������ֵ,true��ʾ���³ɹ�,�������ʧ��
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

public static String getNewString(String string){                 //Ϊ������ݿ���ʾ���������д�ķ���
  try {
   return ( new String(string.getBytes("ISO-8859-1"),"gb2312"));
  } catch (UnsupportedEncodingException e) {
   // TODO �Զ����� catch ��
   e.printStackTrace();
  }
  return null;
}
 
public static boolean studentChangePassword(String id,String password){      //ѧ���޸�����ķ���������߼��ж������ϲ㴦��
  String sql = "update STUDENT set PASSWORD='"+password+"'where SNO='"+id+"';";
  return DBConn.executeUpdate(sql); 
}
public static boolean teacherChangePassword(String id,String password){      //��ʦ�޸�����ķ���
  String sql = "update TEACHER set PASSWORD='"+password+"'where TNO='"+id+"';";
  return DBConn.executeUpdate(sql); 
}
public static boolean teacherSetScoreOfStudent(String studentid,String courseid,double score){       //��ʦ��������ķ���
  String sql = "update STUDY set SCORE='"+score+"' where SNO='"+studentid+"' and CNO='"+courseid+"';";
  return DBConn.executeUpdate(sql);
}
public static void close(){                       //�ر����ݿ����ӵķ���
  try{
   conn.close();
  }catch(SQLException e){
   e.printStackTrace();
  }finally{
   conn = null;
  }
}
}
