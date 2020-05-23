
/*
*@author ougaoyan ,date:2008-9-26
*/
package com.system.model;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import com.system.dbconnection.DBConn;
public class Student {
public static String id;
public static String name;
public static String sex;
public static String password;
public static String sclass;
public static String department;
public static String school;
public static List courseList = new ArrayList();
public Student(String id){
    String sql1 ="select * from STUDENT where SNO='"+id+"';";
    ResultSet rs1 = DBConn.executeQuery(sql1);
    try{
         while(rs1.next()){                                  //从数据库获得学生信息并初始化相关属性
       setId(rs1.getString("SNO"));
       setName(DBConn.getNewString(rs1.getString("SNAME")));
       setSex(DBConn.getNewString(rs1.getString("SEX")));
       setPassword(rs1.getString("PASSWORD"));
       setClass(rs1.getString("CLASS"));
       setDepartment(DBConn.getNewString(rs1.getString("DEPARTMENT")));
       setSchool(DBConn.getNewString(rs1.getString("SCHOOL")));
       DBConn.close();                                    //关闭数据库连接
         }
        }catch(Exception e){
          e.printStackTrace();
        }
        String sql2 = "select * from STUDENT,STUDY where STUDENT.SNO=STUDY.SNO and STUDY.SNO='"+id+"';";
        try{ 
        ResultSet rs2 = DBConn.executeQuery(sql2);
              
        while(rs2.next()){
           courseList.add(rs2.getString("CNO").trim());
        }
        
        }catch(Exception e){
      e.printStackTrace();
     }
        DBConn.close(); 
    
    }

public static List getScoreList(){              //返回学生各门课程的成绩信息
   List list = new ArrayList();
   Iterator i = Student.courseList.iterator(); 
   while(i.hasNext()){
     StudentCourse scourse = new StudentCourse(Student.id,(String)i.next());
     list.add(scourse);
    }
   return list;
}
public static List getYearScoreList(String year){
   List list = getScoreList();
   List yearList = new ArrayList();
   StudentCourse course = null;
   for(int i = 0;i <list.size();i++){
    course = (StudentCourse)list.get(i);
    if(course.year.equals(year)){
     yearList.add(course);
    }
   }
   return yearList; 
}
public static List getTermScoreLiat(String year,String term){
   List list = getScoreList();
   List termList = new ArrayList();
   StudentCourse course = null;
   for(int i = 0;i <list.size();i++){
    course = (StudentCourse)list.get(i);
    if(course.year.equals(year)&&course.term.equals(term)){
     termList.add(course);
    }
   }
   return termList; 
}
public static void setId(String sid){               //设置学号的方法
   id = sid;
}
public static void setName(String sname){            //设置密码的方法
   name = sname;
}
public static void setSex(String sex){              //设置性别的方法
   Student.sex = sex;
}
public static void setPassword(String spassword){     //设置密码的方法
   password = spassword;
}
public static void setClass(String sclass){
   Student.sclass= sclass;
}
public static void setDepartment(String dep){
   department = dep;
}
public static void setSchool(String sch){
   school = sch;
}

public static boolean changePassword(String newpassword){            //学生用户修改密码的方法
   return DBConn.studentChangePassword(Student.id, newpassword);
}
  
}
