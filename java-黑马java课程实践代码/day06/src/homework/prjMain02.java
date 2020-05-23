package homework;

public class prjMain02 {

	
	public static void main(String[] args) {
		prjTeacher teac1 = new prjTeacher("周志鹏", 30, "Java基础中面向对象");
		prjStudent stu1 = new prjStudent("韩光", 18, "面向对象");
		
		teac1.eat();
		teac1.teach();
		
		stu1.eat();
		stu1.study();
	}
}
