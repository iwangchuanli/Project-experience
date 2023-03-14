package com.library.manager;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class SqlCon {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/bookdb";
	private static String username = "root";
	private static String password = "123456";
	private static Connection con = null;

	private SqlCon() {
		try {
			if (con == null) {
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库未能打开！");
			System.out.println(e.getMessage());
		}
	}

	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			if (con == null) {
				new SqlCon();
			}
			rs = con.createStatement().executeQuery(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据查询失败！");
			rs = null;
		}
		return rs;
	}

	public static int executeUpdate(String sql) {
		int a = 0;
		try {
			if (con == null) {
				new SqlCon();
			}
			a = con.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库更新失败！");
			a = -1;
		}
		return a;
	}

	public static void close() {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库未打开！");
		}
	}
}
