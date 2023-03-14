package com.orcl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleTest {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;

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
			}
		} catch (Exception e) {
			System.out.println("failed to connection:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
