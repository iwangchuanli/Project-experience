package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Prj02 {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/�����Ͽβ���/day13/resource/info2.txt");
		FileInputStream fileIn = new FileInputStream(file);//�ļ��ֽ�����ȡ
		
		HashMap<Character, Integer> hsm = new HashMap<>();
		//hashmap���
		int len;
		while ((len = fileIn.read()) != -1) {
			if (hsm.containsKey((char)len)) {
				hsm.put((char) len, hsm.get((char)len)+1);
			}else{
				hsm.put((char) len, 1);
			}
		}
		
		//����һ��SufferBUider ��������洢�ַ���  result ����������
        StringBuffer sb=new StringBuffer();
        StringBuffer result = null;
        
        //����map����
        Set<Character> keySet = hsm.keySet();
        for (Character cs : keySet) {    //csΪÿһ��keyֵ
            //����keyֵ�Ҵ���
            Integer cishu = hsm.get(cs);
            result = sb.append(cs).append("(").append(cishu).append(")");
            //תΪString
        }
        System.out.println(result);
	}
}
