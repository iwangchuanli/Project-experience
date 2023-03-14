package com.orcl.test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OracleController {
	private static Connection con = null;// oracle����
	private static PreparedStatement pre = null;// Ԥ������䴦��
	private static ResultSet result = null;// �����

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return
	 */
	public static Connection getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("start to connection!");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
			String user = "system";
			String password = "123456";
			long startTime = System.currentTimeMillis();
			con = DriverManager.getConnection(url, user, password);
			long endTime = System.currentTimeMillis();
			System.out.println("===Time--" + (endTime - startTime));
			System.out.println("===hashCode--" + con.hashCode());
			if (!con.isClosed()) {
				System.out.println("success to connection!");
				return con;
			}
		} catch (Exception e) {
			System.out.println("failed to connection:" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * �ر����ݿ�����
	 * 
	 * @param con
	 * @param pre
	 * @param result
	 */
	public static void closeCon(Connection con, PreparedStatement pre, ResultSet result) {
		try {
			if (result != null)
				result.close();
			if (pre != null)
				pre.close();
			if (con != null)
				con.close();
			System.out.println("���ݿ������ѹرգ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����sql��� ���ӡ�ɾ��������
	 * 
	 * @param con
	 * @param sql
	 */
	public static void execute(Connection con, String sql) {
		try {
			if (con == null) {
				return;
			}
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate(sql);
			System.out.println("update row:" + i);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("execute error!" + sql);
			e.printStackTrace();
		}
	}

	/**
	 * sql��ѯ��䴦��
	 * 
	 * @param con
	 * @param sql
	 */
	public static void query(Connection con, String sql) {
		try {
			if (con == null) {
				return;
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rmeta = rs.getMetaData();
			int numColumns = rmeta.getColumnCount();
			// System.out.println("Result row:"+numColumns);
			while (rs.next()) {
				for (int i = 0; i < numColumns; i++) {
					String sTemp = rs.getString(i + 1);
					System.out.print(sTemp + "  ");
				}
				System.out.println("  ");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Query error!" + sql);
			e.printStackTrace();
		}
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = getCon();
		String insertSql = "insert into users(id,userName,password) values(5,'test005','test005')";
		// execute(con, insertSql);
		String querySql = "select * from users";
		query(con, querySql);
		System.out.println("----------");
		String querySql02 = "select * from users where userName='test002'";
		query(con, querySql02);
		closeCon(con, pre, result);
	}
}
