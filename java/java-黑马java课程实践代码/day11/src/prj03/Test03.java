package prj03;

import java.util.ArrayList;



public class Test03 {

	public static Student maxStu(ArrayList<Student> stu){
		int maxScore = 0,index = 0;
		for (int i = 0; i < stu.size(); i++) {
			if (maxScore < stu.get(i).getScore()) {
				maxScore = stu.get(i).getScore();
				index = i;
			}
		}
		return stu.get(index);
	}
	public static int sumScore(ArrayList<Student> stu){
		int sum = 0;
		for (int i = 0; i < stu.size() ; i++) {
			sum += stu.get(i).getScore();
		}
		return sum;
		//System.out.println("�༶ͬѧ���гɼ�Ϊ��"+sum);
	}
	public static double avgScore(ArrayList<Student> stu){
		double avg = 0;
		avg = (double) sumScore(stu) / stu.size();
		return avg;
	}
	public static void countLowScore(ArrayList<Student> stu){
		int count = 0;
		for (int i = 0; i < stu.size(); i++) {
			if (stu.get(i).getScore() < 60) {
				count ++;
				System.out.println("����"+stu.get(i).getName()+"  ����"+stu.get(i).getAge()+"  ����"+stu.get(i).getScore());
			}
		}
		System.out.println("�༶������ѧԱ����:"+count);
		//return count;
	}
	public static void main(String[] args) {
		ArrayList<Student> listStu = new ArrayList<>();
		Student stu1 = new Student(" java ", 1, 90);
		Student stu2 = new Student("phython", 2, 80);
		Student stu3 = new Student("  C#  ", 3, 70);
		Student stu4 = new Student("Android", 4, 60);
		Student stu5 = new Student("  IOS  ", 5, 50);
		
		listStu.add(stu1);listStu.add(stu2);listStu.add(stu3);
		listStu.add(stu4);listStu.add(stu5);
		
		System.out.println("�༶���ѧԱ��������"+maxStu(listStu).getName()+"   ����:"+maxStu(listStu).getAge()+"  �ɼ�:"+maxStu(listStu).getScore());
		System.out.println("------------------------");
		System.out.println("�༶�ܳɼ�Ϊ��"+sumScore(listStu)+"   �༶ƽ����Ϊ��"+avgScore(listStu));
		System.out.println("------------------------");
		countLowScore(listStu);
		//System.out.println("�༶������ѧԱ����"+countLowScore(listStu));
	}
}
