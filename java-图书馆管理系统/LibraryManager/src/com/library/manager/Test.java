package com.library.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Test {

	public static void main(String[] args) throws Exception {
		String sql = "select * from user where username='admin'";
		ResultSet rs = SqlCon.executeQuery(sql);
		while (rs.next()) {
			String is_admin = rs.getString("is_admin");
			System.out.println(rs + is_admin);
		}

	}
}
