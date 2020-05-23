package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class prj04 {

	   public static void main(String[] args) {
	        //创建键盘输入对象
	        Scanner sc=new Scanner(System.in);
	        System.out.println("请输入一串字符串：");
	        String s = sc.nextLine();
	        
	        //转为字符数组
	        char[] charArray = s.toCharArray();
	        
	        //创建一个map对象（char  次数）用来存放
	        Map<Character,Integer> map=new HashMap<Character,Integer>();
	        
	        //取出每一个字符C，  并进行对比map中是否有，没有额话 先赋值1.有的话会去重 并且对应的value的次数值进行加1
	        for (int i = 0; i < charArray.length; i++) {
	            char c=charArray[i];
	            if(map.containsKey(c)){
	                map.put(c,map.get(c)+1);
	            }else{
	                map.put(c,1);
	            }
	        }
	        
	        
	        //创建一个SufferBUider 对象，用与存储字符串  result 用来输出结果
	        StringBuffer sb=new StringBuffer();
	        StringBuffer result = null;
	        
	        //遍历map对象就行了
	        Set<Character> keySet = map.keySet();
	        
	        for (Character cs : keySet) {    //cs为每一个key值
	            //根据key值找次数
	            Integer cishu = map.get(cs);
	            result = sb.append(cs).append("(").append(cishu).append(")");
	            //转为String
	        }
	        System.out.println(result);
	    }
	}

