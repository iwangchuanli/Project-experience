 /*
*@author ougaoyan ,date:2008-9-29
*/
package com.system.model;
import java.sql.ResultSet;
import com.system.dbconnection.DBConn;
public class StudentCourse extends Course {
public double score;
public String teacher;
public StudentCourse(String studentid,String courseid){      //以学生学号和课程号为参数的构造函数     
   super();
   String sid = studentid;
   String cid = courseid;
   try{
    String sql ="select * from STUDENT,COURSE,STUDY,TEACHER,TEACH where STUDENT.SNO=STUDY.SNO and STUDY.CNO=COURSE.CNO and COURSE.CNO = TEACH.CNO and TEACH.TNO= TEACHER.TNO and STUDY.SNO='"+sid+"'and STUDY.CNO='"+cid+"';" ;
    ResultSet rs = DBConn.executeQuery(sql);
    //if(rs != null){
       while(rs.next()){
        setId(rs.getString("STUDY.CNO"));
        setName(DBConn.getNewString(rs.getString("COURSE.CNAME")));
        setYear(rs.getString("COURSE.YEAR"));
           setTerm(rs.getString("COURSE.TERM"));
           setXueshi(rs.getInt("COURSE.XUEFEN")*12);
           setXuefen(rs.getInt("COURSE.XUEFEN"));
           setScore(rs.getDouble("STUDY.SCORE"));
           setTeacher(DBConn.getNewString(rs.getString("TNAME")));
       }
    //}
    DBConn.close();                                                   //关闭数据库连接 
   }catch(Exception e){
    e.printStackTrace();
   }
}
   public void setScore(double score){
    this.score = score;
   }
   public void setTeacher(String teachername){
    teacher = teachername;
   }
}
