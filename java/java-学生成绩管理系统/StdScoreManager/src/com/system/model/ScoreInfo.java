
package com.system.model;
import java.sql.ResultSet;
import com.system.dbconnection.DBConn;
public class ScoreInfo {
   public String studentName;    //����
   public String studentId;      //ѧ��
   public String studentClass;   //�༶
   public String studentDepartment;//Ժϵ
   public String couseId;         //�γ̴���
   public double score;          //����
   
   
   public ScoreInfo(String studentid,String courseid){
    String sql = "select * from STUDENT,STUDY where STUDENT.SNO = STUDY.SNO and STUDENT.SNO ='"+studentid+"'and STUDY.CNO='"+courseid+"';";
   ResultSet rs = DBConn.executeQuery(sql);
   try{
      while(rs.next()){
       setCouseId(rs.getString("CNO"));
       setScore(rs.getDouble("SCORE"));
       setStudentClass(rs.getString("CLASS"));
       setStudentDepartment(DBConn.getNewString(rs.getString("DEPARTMENT")));
       setStudentId(rs.getString("STUDENT.SNO"));
       setStudentName(DBConn.getNewString(rs.getString("SNAME")));
       }
   }catch(Exception e){
    e.printStackTrace();
     }
   DBConn.close();                //�ر����ݿ�����
    
   }/*construct over*/
   
   //public boolean changeScore( ){              //�������ݿ��޸ķ����ķ���
    
    //return 
// }
   private void setStudentName(String studentName ){
    this.studentName=studentName;
   }
   private void setStudentId(String studentId){
    this.studentId=studentId;
   }
   private void setStudentClass(String studentClass){
    this.studentClass=studentClass;
   }
   private void setStudentDepartment(String studentDepartment){
    this.studentDepartment=studentDepartment;
   }
   private void setCouseId(String couseId ){
    this.couseId=couseId;
   }
   private void setScore(double score){
    this.score=score;
}
}
