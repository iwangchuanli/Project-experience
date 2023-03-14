/*
*@author ougaoyan ,date:2008-9-26
*/
package com.system.model;
import java.sql.ResultSet;
import com.system.dbconnection.DBConn;
public class Course {                  //课程类
public String id;                   //课程号
public String name;                //课程名
public String year;                 //学年
public String term;                //学期
public int xueshi;                //学时
public int xuefen;                //学分

public Course(String id){         //构造器,由课程号就可获得整个课程的相关信息
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
public Course(){                     //一个空的构造函数
  
}
public void setId(String id){         //以下是一些设置和获取课程属性的方法
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
