package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class prj04 {

	   public static void main(String[] args) {
	        //���������������
	        Scanner sc=new Scanner(System.in);
	        System.out.println("������һ���ַ�����");
	        String s = sc.nextLine();
	        
	        //תΪ�ַ�����
	        char[] charArray = s.toCharArray();
	        
	        //����һ��map����char  �������������
	        Map<Character,Integer> map=new HashMap<Character,Integer>();
	        
	        //ȡ��ÿһ���ַ�C��  �����жԱ�map���Ƿ��У�û�ж �ȸ�ֵ1.�еĻ���ȥ�� ���Ҷ�Ӧ��value�Ĵ���ֵ���м�1
	        for (int i = 0; i < charArray.length; i++) {
	            char c=charArray[i];
	            if(map.containsKey(c)){
	                map.put(c,map.get(c)+1);
	            }else{
	                map.put(c,1);
	            }
	        }
	        
	        
	        //����һ��SufferBUider ��������洢�ַ���  result ����������
	        StringBuffer sb=new StringBuffer();
	        StringBuffer result = null;
	        
	        //����map���������
	        Set<Character> keySet = map.keySet();
	        
	        for (Character cs : keySet) {    //csΪÿһ��keyֵ
	            //����keyֵ�Ҵ���
	            Integer cishu = map.get(cs);
	            result = sb.append(cs).append("(").append(cishu).append(")");
	            //תΪString
	        }
	        System.out.println(result);
	    }
	}

