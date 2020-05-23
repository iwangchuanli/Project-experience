package com.classical;

import java.util.HashSet;

/**
 * 
 * @author Administrator
 *题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。
 *已抽签决定比赛名单。有人向队员打听比赛的名单。
 *a说他不和x比，c说他不和x,z比，
 *请编程序找出三队赛手的名单。 
 */
/**官方解法 利用对象
import java.util.ArrayList;
public class Prog18{
	String a,b,c;//甲队成员
	public static void main(String[] args){
		String[] racer = {"x","y","z"};//乙队成员
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
	//构造方法
	private Prog18(String a,String b,String c){
		this.a = a;
		this.b = b ;
		this.c = c;
	}
	public String toString(){
		return "a的对手是"+a+"  "+"b的对手是"+b+"  "+"c的对手是"+c;
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
		System.out.println("a的对手是："+str.toString().split(",")[0]);
		System.out.println("b的对手是："+str.toString().split(",")[1]);
		System.out.println("c的对手是："+str.toString().split(",")[2]);
		//System.out.println(haSet);
	}
}