package SY_3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sy3_6 {
	public static void main(String[] args) {
		Pattern p;
		Matcher m;
		String regex="\\s";
		String s="长亭外 古道边 芳草碧连天 晚风拂 柳声残 夕阳山外山";
		p=Pattern.compile(regex);
		System.out.println("原歌词："+s);
		m=p.matcher(s);
		
		System.out.println("拆分后：");
		String words[]=s.split(regex);//拆分split
		for(int i=0;i<words.length;i++){
			System.out.println(words[i]);
			
		}
		
		while(m.find()){//模式匹配
			String str=m.group();
			System.out.print(str);
		}
		System.out.println("剔除regex后：");
		String result=m.replaceAll("");
		System.out.println(result);
		
	}

}
