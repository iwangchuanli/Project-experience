import java.sql.*;
public class AddRecord {
   String datasourceName="";        //数据源名
   String tableName="";            //表名
   String number="",      //商品号 
          name="",        //名称
          madeTime;       //生产日期
   double price;         //价格
   public AddRecord() {
      try{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      }
      catch(ClassNotFoundException e) {
         System.out.print(e);
      } 
    }
   public void setDatasourceName(String s) {
      datasourceName=s.trim();
   }
   public void setTableName(String s) {
      tableName=s.trim();
   }
    public void setNumber(String s) {
       number=s.trim();
    }
    public void setName(String s) {
       name=s.trim();
    }
    public void setPrice(double n) {
       price=n;
    }
    public void setMadeTime(String b) {
        madeTime=b;
    }
    public String addRecord()
    {  String str="";
       Connection con;
       PreparedStatement sql; 
       try { String uri="jdbc:odbc:"+datasourceName; 
             String id="";
             String password="";
             con=DriverManager.getConnection(uri,id,password);
             String insertCondition="INSERT INTO goods VALUES (?,?,?,?)";
             sql=con.prepareStatement(insertCondition);
             if(number.length()>0) {
               sql.setString(1,number);
               sql.setString(2,name);
               sql.setString(3,madeTime);
               sql.setDouble(4,price);
               int m=sql.executeUpdate();
               if(m!=0) 
                   str="对表中添加"+m+"条记录成功";
               else 
                  str="添加记录失败";
            }
            else {
               str="必须要有雇员号";
            }
            con.close();
       }
       catch(SQLException e) { 
            str="没有提供添加的数据或"+e;
       }
       return str;
    }
}   
