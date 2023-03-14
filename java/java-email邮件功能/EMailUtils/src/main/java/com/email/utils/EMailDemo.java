package com.email.utils;

import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class EMailDemo {

	public static void main(String[] args) {
		// EMailDemo.sendEmail();
		// EMailDemo.sendHtml(EMailDemo.getSession());
		EMailDemo.sendWithFile(getSession());
	}

	private static String to = null;
	private static String from = null;
	private static Scanner sc = new Scanner(System.in);

	private static Session getSession() {
		System.out.println("请输入收件人邮箱（任意）：");
		String to = sc.nextLine();// 收件人
		System.out.println("请输入发件人邮箱（QQ）：");
		String from = sc.nextLine();// 发件人
		System.out.println("请输入发件人邮箱的授权码：");
		String pop3 = sc.nextLine();// 发件人
		String host = "smtp.qq.com";// 指定发送邮件的QQ主机
		Properties properties = System.getProperties(); // 获取系统属性
		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pop3); // 发件人邮件用户名、授权码
			}
		});
		return session;
	}

	public static void sendEmail(Session session) {
		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: 头部头字段
			System.out.println("请设置邮件标题：");
			message.setSubject(sc.nextLine());
			// 设置消息体
			System.out.println("请设置邮件内容：");
			message.setText(sc.nextLine());
			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void sendHtml(Session session) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			System.out.println("请设置邮件标题：");
			message.setSubject(sc.nextLine());
			// 发送 HTML 消息, 可以插入html标签
			System.out.println("请设置邮件内容(可以插入html标签)：");
			message.setContent(sc.nextLine(), "text/html");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void sendWithFile(Session session) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			System.out.println("请设置邮件标题：");
			message.setSubject(sc.nextLine());

			// 创建消息部分
			BodyPart messageBodyPart = new MimeBodyPart();
			// 消息
			System.out.println("请设置邮件内容：");
			message.setText(sc.nextLine());
			// 创建多重消息
			Multipart multipart = new MimeMultipart();
			// 设置文本消息部分
			multipart.addBodyPart(messageBodyPart);
			// 附件部分
			messageBodyPart = new MimeBodyPart();
			System.out.println("请输入文件完整路径：");
			String filename = sc.nextLine();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			// 发送完整消息
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
