
package com.system.model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.system.dbconnection.DBConn;
public class Teacher {
public static String id;
public static String name;
public static String sex;
public static String title;         //ְ��
public static String password;
public static String courseid;
public static List scoreInfoList = new ArrayList(); //����ѧ���γ���Ϣ���б�
public Teacher(String id){

   String sql1 = "select * from TEACH,TEACHER where TEACH.TNO = TEACHER.TNO and TEACHER.TNO ='"+id+"';";
	//String sql1 ="select * from TEACHER where TNO='"+id+"';";
	ResultSet rs1 = DBConn.executeQuery(sql1);
   try{
      while(rs1.next()){
       setId(rs1.getString("TEACHER.TNO"));
       setCourse(rs1.getString("TEACH.CNO"));
       setName(DBConn.getNewString(rs1.getString("TNAME")));
       setSex(DBConn.getNewString(rs1.getString("SEX")));
       setTitle(DBConn.getNewString(rs1.getString("TITLE")));
       setPassword(DBConn.getNewString(rs1.getString("PASSWORD")));
       }
   }catch(Exception e){
    e.printStackTrace();
     }
   DBConn.close();                //�ر����ݿ�����
  
  
   String sql2 ="select * from STUDENT,STUDY,TEACH where STUDY.SNO=STUDENT.SNO and STUDY.CNO=TEACH.CNO and TEACH.CNO='"+Teacher.courseid+"';";
   ResultSet rs2 = DBConn.executeQuery(sql2);
   try{
    while(rs2.next()){
     String[] ste = {rs2.getString("STUDENT.SNO").trim(),rs2.getString("TEACH.CNO").trim()};
     scoreInfoList.add(ste);
    }
   }catch(Exception e){
    e.printStackTrace();
   }
   DBConn.close(); 
  
}
public static List getScoreInfoList(){
   List scoreInfoList = new ArrayList();
   Iterator i = Teacher.scoreInfoList.iterator(); 
   while(i.hasNext()){
    String[] str2= (String[])i.next();
    ScoreInfo info = new ScoreInfo(str2[0],str2[1]);
    scoreInfoList.add(info);
    }
   return scoreInfoList;
  
}

public static void setId(String sid){               //���ý�ʦ����ķ���
   id = sid;
}
public static void setName(String sname){            //���������ķ���
   name = sname;
}
public static void setSex(String sex){              //�����Ա�ķ���
   Teacher.sex = sex;
}
public static void setTitle(String title){              //�����Ա�ķ���
   Teacher.title = title;
}
public static void setPassword(String spassword){     //��������ķ���
   password = spassword;
}
public static void setCourse(String courseid){     //�������̿γ̵ķ���
   Teacher.courseid=courseid;
}
public static boolean changePassword(String newpassword){            //��ʦ�û��޸�����ķ��� 
   return DBConn.teacherChangePassword(Teacher.id, newpassword);
}
    public static boolean setStudentScore(String studentid,double score){ //�޸�ѧ�������ķ���
    if(score>=0&&score<=100){
         return DBConn.teacherSetScoreOfStudent(studentid , courseid, score);
    }else {
       return false;
    }
    }
}
