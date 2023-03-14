package DataBase;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBcon {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver" ;
	// ����MySQL���ݿ�����ӵ�ַ
	public static final String DBURL = "jdbc:mysql://localhost:3306/studentdb?useUnocode=true&characterEncodeing=UTF-8" ;
	// MySQL���ݿ�������û���
	public static final String DBUSER = "root" ;
	// MySQL���ݿ����������
	public static final String DBPASS = "123456" ;
	java.sql.Connection con=null;
	Statement stmt;
	public DBcon(){
		try{
			jbInit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con =DriverManager.getConnection(DBURL,DBUSER,DBPASS);//�������ݿ�
		stmt= con.createStatement();//����������
	}
	public Statement getStatement(){
		return stmt;
	}
	//����ִ�в�ѯ��SQL���
	public ResultSet query(String SELECT_USER_SQL)throws Exception{
		return stmt.executeQuery(SELECT_USER_SQL);
	}
	//����ִ�и��µ�SQL���
	public void update(String UPDATE_SQL)throws Exception{
		 stmt.executeUpdate(UPDATE_SQL);
	}
	//����ִ�����ӵ�SQL���
	public void add(String ADD_SQL)throws Exception{
		 stmt.executeUpdate(ADD_SQL);
	}
	//����ִ��ɾ����SQL���
	public void delete(String DELETE_SQL)throws Exception{
		 stmt.executeUpdate(DELETE_SQL);
	}
}
