/*
*@author ougaoyan ,date:2008-9-26
*/
package com.system.model;
import java.sql.ResultSet;
import com.system.dbconnection.DBConn;
public class Course {                  //�γ���
public String id;                   //�γ̺�
public String name;                //�γ���
public String year;                 //ѧ��
public String term;                //ѧ��
public int xueshi;                //ѧʱ
public int xuefen;                //ѧ��

public Course(String id){         //������,�ɿγ̺žͿɻ�������γ̵������Ϣ
   String sql = "select * from COURSE where CNO='"+id+"';";
   ResultSet rs = DBConn.executeQuery(sql);
   try{
    while(rs.next()){
        setId(rs.getString("CNO"));
        setName(DBConn.getNewString(rs.getString("CNAME")));
        setYear(rs.getString("YEAR"));
        setTerm(rs.getString("TERM"));
        setXueshi(rs.getInt("XUEFEN")*12);
        setXuefen(rs.getInt("XUEFEN")); 
    }
   }catch(Exception e){
    e.printStackTrace();
   }
   DBConn.close();
}
public Course(){                     //һ���յĹ��캯��
  
}
public void setId(String id){         //������һЩ���úͻ�ȡ�γ����Եķ���
   this.id = id;
}

public String getId(){
   return id;
}

    public void setName(String name){
   this.name = name;
}
    
public String getName(){
   return name;
}

    public void setYear(String year){
   this.year = year;
}
    
public String getYear(){
   return year;
}
    public void setTerm(String term){
   this.term = term;
}
public String getTerm(){
   return term;
}
    public void setXueshi(int xueshi){
   this.xueshi = xueshi;
}
public int getXueshi(){
   return xueshi;
}
   public void setXuefen(int xuefen){
   this.xuefen = xuefen;
}
public int getXuefen(){
   return xuefen;
}
}
