package homework;

public class prjMain02 {

	
	public static void main(String[] args) {
		prjTeacher teac1 = new prjTeacher("��־��", 30, "Java�������������");
		prjStudent stu1 = new prjStudent("����", 18, "�������");
		
		teac1.eat();
		teac1.teach();
		
		stu1.eat();
		stu1.study();
	}
}
