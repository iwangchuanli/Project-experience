import java.sql.*;
public class AddRecord {
   String datasourceName="";        //����Դ��
   String tableName="";            //����
   String number="",      //��Ʒ�� 
          name="",        //����
          madeTime;       //��������
   double price;         //�۸�
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
                   str="�Ա������"+m+"����¼�ɹ�";
               else 
                  str="��Ӽ�¼ʧ��";
            }
            else {
               str="����Ҫ�й�Ա��";
            }
            con.close();
       }
       catch(SQLException e) { 
            str="û���ṩ��ӵ����ݻ�"+e;
       }
       return str;
    }
}   
