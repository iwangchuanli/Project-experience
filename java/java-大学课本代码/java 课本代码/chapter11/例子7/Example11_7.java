import java.sql.*;
public class Example11_7 {
   public static void main(String args[]) {
     ModifyTable modify=new ModifyTable();
     modify.setDatasourceName("myData");
     modify.setSQL("UPDATE goods SET price =3009 WHERE name='�������ӻ�'");
     String backMess=modify.modifyRecord();
     System.out.println(backMess); 
     modify.setSQL("INSERT INTO goods VALUES ('A009','�ֻ�','2010-12-20',3976)");
     backMess=modify.modifyRecord();
     System.out.println(backMess);
   } 
}

