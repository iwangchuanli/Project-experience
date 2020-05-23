package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Prj02 {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/黑马上课材料/day13/resource/info2.txt");
		FileInputStream fileIn = new FileInputStream(file);//文件字节流读取
		
		HashMap<Character, Integer> hsm = new HashMap<>();
		//hashmap存放
		int len;
		while ((len = fileIn.read()) != -1) {
			if (hsm.containsKey((char)len)) {
				hsm.put((char) len, hsm.get((char)len)+1);
			}else{
				hsm.put((char) len, 1);
			}
		}
		
		//创建一个SufferBUider 对象，用与存储字符串  result 用来输出结果
        StringBuffer sb=new StringBuffer();
        StringBuffer result = null;
        
        //遍历map对象
        Set<Character> keySet = hsm.keySet();
        for (Character cs : keySet) {    //cs为每一个key值
            //根据key值找次数
            Integer cishu = hsm.get(cs);
            result = sb.append(cs).append("(").append(cishu).append(")");
            //转为String
        }
        System.out.println(result);
	}
}
