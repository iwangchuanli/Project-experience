package Homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Prj02 {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		BufferedReader bufR = new BufferedReader(new FileReader("E:/黑马上课材料/day14/resource/user.txt"));
		
		HashMap<String, String> hsM = new HashMap<>();
		boolean flag = false;
		//将信息录入map集合
		String line;
		while ((line = bufR.readLine()) != null) {
			String userName = line.split(",")[0];
			String pwd = line.split(",")[1];
			hsM.put(userName, pwd);
		}
		System.out.println(hsM);
		
		System.out.print("请输入注册用户名：");
		String zuName = input.nextLine();
		System.out.print("请输入注册用户密码：");
		String zuPwd = input.nextLine();
		
		//利用set遍历集合进行对比
		Set<String> set = hsM.keySet();
		for (String name : set) {
			String passW = hsM.get(name);
			//System.out.println(name+","+passW);
			if (name.equals(zuName)) {
				flag = true;
			}else {
				flag = false;
			}
		}
		
		//根据flag判断 插入
		if (flag == false) {
			BufferedWriter bufW = new BufferedWriter(new FileWriter("E:/黑马上课材料/day14/resource/user.txt",true));
			StringBuilder str = new StringBuilder();
			/*FileWriter(String fileName, boolean append) 
          		根据给定的文件名以及指示是否附加写入数据的 boolean 值来构造 FileWriter 对象 
			 */
			
//			for (String name : set) {
//				str.delete(0, str.length());
//				String pwd = hsM.get(name);
//				str = str.append(name).append(",").append(pwd);
//				bufW.write(str.toString());
//				bufW.flush();
//			}

			//新用户信息注册
			str = str.append(zuName).append(",").append(zuPwd);
			bufW.write(str.toString());
			bufW.flush();
			bufW.close();
			System.out.println("用户信息注册完成！！");
		}else {
			System.out.println("用户名已存在！！");
		}
		
		
		bufR.close();
		
		
		
	}
}
