package DataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDB {

    private String DBDriver = null;
    private String url = null;
    private String user = null;
    private String password = null;
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private static InitDB initDB_obj = null ;

    public InitDB() { // Java DB����ģʽ�ķ���
        this.DBDriver = "org.apache.derby.jdbc.ClientDriver";
        this.url = "jdbc:derby://localhost:1527/FzManager;create=true";
        this.user = "fuZ";
        this.password = "123" ;
        try {
            Class.forName(DBDriver); // ��������
            conn = DriverManager.getConnection(url, user, password);//��������
            stmt = conn.createStatement(); // ��ȡ���ʶ���
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ��Ҫ�������Ĺ��÷���
    public InitDB(String DBDriver,String url, String user,String password) {
        this.DBDriver = DBDriver ;
        this.url = url ;
        this.user = user ;
        this.password = password;
        try {
            Class.forName(DBDriver);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static InitDB getInitDB() {
        if(initDB_obj==null)
            initDB_obj = new InitDB();
        return initDB_obj ;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public ResultSet getRs(String sql) { // ��ȡ��ѯ�����
        if (sql.toLowerCase().indexOf("select") != -1) {
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rs;
    }

    public void closeDB() { // �ر����ӵȣ��ͷ���Դ
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
