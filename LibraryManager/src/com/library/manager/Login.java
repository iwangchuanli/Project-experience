package com.library.manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.awt.AWTUtilities;

public class Login extends JFrame {
	String url = "login.png";
	MyContentPane rp = new MyContentPane(url, 0.7f);
	TextField text_user, text_pass;

	public Login() {
		this.setTitle("Please Login");
		// setOpaque(true);//设置内容面板为透明
		rp.setOpaque(false);
		this.setUndecorated(true);// 边框
		// this.setSize(260, 170);
		this.setSize(rp.img.getIconWidth(), rp.img.getIconHeight());
		this.setContentPane(rp);
		// AWTUtilities.setWindowOpaque(this, false);// 设置为JFrame为透明
		this.setLayout(null);
		setResizable(false);
		Label userlb = new Label("UserName:");
		Label passlb = new Label("PassWord:");
		Button sure = new Button("Login");
		Button cancel = new Button("Cancel");
		text_user = new TextField();
		text_pass = new TextField();
		text_pass.setEchoChar('●');
		userlb.setBounds(30, 53, 70, 20);
		passlb.setBounds(30, 83, 70, 20);
		text_user.setBounds(110, 50, 120, 20);
		text_pass.setBounds(110, 80, 120, 20);
		sure.setBounds(42, 120, 80, 25);
		cancel.setBounds(135, 120, 80, 25);
		this.add(text_user);
		this.add(text_pass);
		this.add(sure);
		this.add(cancel);
		sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sureActionListener(e);

			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SqlCon.close();
				dispose();

			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SqlCon.close();
				dispose();
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		// AWTUtilities.setWindowOpaque(this, false);// 设置为JFrame为透明
	}

	public void sureActionListener(ActionEvent le) {
		String user = text_user.getText();
		String pass = text_pass.getText();
		String is_admin = "";
		if (user.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(null, "密码不能为空，请输入密码");
			return;
		}
		try {
			String sql = "select * from user where username=" + "'" + user + "'" + "and password=" + "'" + pass + "'";
			ResultSet rs = SqlCon.executeQuery(sql);
			if (rs.next()) {
				is_admin = rs.getString("is_admin");
			} else {
				JOptionPane.showMessageDialog(null, "Wrong that is UserNmae or Password ");
				return;
			}
			GlobalVar.login_user = user;
			ShowMain show = new ShowMain();
			show.setRights(is_admin);
			System.out.println("Successed");
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "the wrong from information");
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
