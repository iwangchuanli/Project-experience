package homework;

public class test01 {

	public static void main(String[] args) {
		Student stu = new Student();
		Worker worker = new Worker();
		StudentLeader stuled = new StudentLeader();
		
		stu.work();
		worker.work();
		stuled.meeting();
	}
}
