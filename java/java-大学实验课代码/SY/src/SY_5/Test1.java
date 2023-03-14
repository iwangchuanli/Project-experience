package SY_5;

import java.util.Scanner;
import SY_5.StudentCompare;
public class Test1 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		Student stu[]=new Student[10];
		for(int i=0;i<stu.length;i++){
			stu[i]=new Student();
			stu[i].num=input.nextLine();
			stu[i].name=input.nextLine();
			stu[i].height=input.nextDouble();
			stu[i].weight=input.nextDouble();
		}
		Student maxHeight=new Student();
		StudentCompare sch=new 	StudentCompare();
		maxHeight=sch.maxHeightStu(stu);
		Student maxWeight=new Student();
		StudentCompare scw=new 	StudentCompare();
		maxWeight=scw.maxHeightStu(stu);
		maxHeight.getInfo();
		maxWeight.getInfo();
		
		
		input.close();
	}

}
