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
		
		BufferedReader bufR = new BufferedReader(new FileReader("E:/�����Ͽβ���/day14/resource/user.txt"));
		
		HashMap<String, String> hsM = new HashMap<>();
		boolean flag = false;
		//����Ϣ¼��map����
		String line;
		while ((line = bufR.readLine()) != null) {
			String userName = line.split(",")[0];
			String pwd = line.split(",")[1];
			hsM.put(userName, pwd);
		}
		System.out.println(hsM);
		
		System.out.print("������ע���û�����");
		String zuName = input.nextLine();
		System.out.print("������ע���û����룺");
		String zuPwd = input.nextLine();
		
		//����set�������Ͻ��жԱ�
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
		
		//����flag�ж� ����
		if (flag == false) {
			BufferedWriter bufW = new BufferedWriter(new FileWriter("E:/�����Ͽβ���/day14/resource/user.txt",true));
			StringBuilder str = new StringBuilder();
			/*FileWriter(String fileName, boolean append) 
          		���ݸ������ļ����Լ�ָʾ�Ƿ񸽼�д�����ݵ� boolean ֵ������ FileWriter ���� 
			 */
			
//			for (String name : set) {
//				str.delete(0, str.length());
//				String pwd = hsM.get(name);
//				str = str.append(name).append(",").append(pwd);
//				bufW.write(str.toString());
//				bufW.flush();
//			}

			//���û���Ϣע��
			str = str.append(zuName).append(",").append(zuPwd);
			bufW.write(str.toString());
			bufW.flush();
			bufW.close();
			System.out.println("�û���Ϣע����ɣ���");
		}else {
			System.out.println("�û����Ѵ��ڣ���");
		}
		
		
		bufR.close();
		
		
		
	}
}
