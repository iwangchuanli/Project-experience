package prj01;

public class StudentTool {

	public void listStudents(Student[] arr){
	//遍历打印学生信息
		for (int i = 0; i < arr.length; i++) {
			System.out.println("姓名："+arr[i].getName()+'\t'+"年龄："+arr[i].getAge()+'\t'+"成绩："+arr[i].getScore());
		}
	}
	public int getMaxScore(Student[] arr){
	//获取学生成绩的最高分
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			
			if (max < arr[i].getScore()) {
				max = arr[i].getScore();
			}
		}
		return max;
	}
	public Student getMaxStudent(Student[] arr){
		//获取成绩最高的学员
		int index = 0;
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = arr[i].getScore();
			if (max < arr[i].getScore()) {
				max = arr[i].getScore();
				index = i;
			}
		}
		return arr[index];
	}
	public int getAverageScore(Student[] arr){
	//获取学生成绩的平均值
		int sum = 0;
		int avg = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i].getScore();
		}
		avg = sum / arr.length;
		return avg;
	}
	public int getCount(Student[] arr){
	//获取不及格的学员数量
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getScore() < 60) {
				count ++;
			}
		}
		return count;
	}
	
}
