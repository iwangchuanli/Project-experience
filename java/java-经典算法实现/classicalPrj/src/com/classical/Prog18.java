package com.classical;

import java.util.HashSet;

/**
 * 
 * @author Administrator
 *��Ŀ������ƹ����ӽ��б������������ˡ��׶�Ϊa,b,c���ˣ��Ҷ�Ϊx,y,z���ˡ�
 *�ѳ�ǩ���������������������Ա����������������
 *a˵������x�ȣ�c˵������x,z�ȣ�
 *�������ҳ��������ֵ������� 
 */
/**�ٷ��ⷨ ���ö���
import java.util.ArrayList;
public class Prog18{
	String a,b,c;//�׶ӳ�Ա
	public static void main(String[] args){
		String[] racer = {"x","y","z"};//�Ҷӳ�Ա
		ArrayList<Prog18> arrayList = new ArrayList<Prog18>();
		for(int i=0;i<3;i++)
		  for(int j=0;j<3;j++)
		    for(int k=0;k<3;k++){
		    	Prog18 prog18 = new Prog18(racer[i],racer[j],racer[k]);
		    	if(!prog18.a.equals(prog18.b) && !prog18.a.equals(prog18.c) && !prog18.b.equals(prog18.c) &&
		    	   !prog18.a.equals("x") && !prog18.c.equals("x") && !prog18.c.equals("z"))
		    	   arrayList.add(prog18);
		    }
		  for(Object obj:arrayList)
		    System.out.println(obj);
	}
	//���췽��
	private Prog18(String a,String b,String c){
		this.a = a;
		this.b = b ;
		this.c = c;
	}
	public String toString(){
		return "a�Ķ�����"+a+"  "+"b�Ķ�����"+b+"  "+"c�Ķ�����"+c;
	}
}
*/

public class Prog18{
	public static void main(String[] args) {
		HashSet<String> haSet = new HashSet<>();
		StringBuilder str = new StringBuilder();
		String dy[] = {"x","y","z"};
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int m = 0; m < 3; m++) {
					if (!dy[i].equals("x") && !dy[m].equals("x") && !dy[m].equals("z") &&
							!dy[i].equals(dy[m]) && !dy[i].equals(dy[j]) && !dy[j].equals(dy[m])) {
						str = str.append(dy[i]).append(",").append(dy[j]).append(",").append(dy[m]);
						//System.out.println(str);
						haSet.add(str.toString());
					}
				}
			}
		}
		System.out.println("a�Ķ����ǣ�"+str.toString().split(",")[0]);
		System.out.println("b�Ķ����ǣ�"+str.toString().split(",")[1]);
		System.out.println("c�Ķ����ǣ�"+str.toString().split(",")[2]);
		//System.out.println(haSet);
	}
}