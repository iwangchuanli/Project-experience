package homework;

public class Course {

	private String courseId;
	private String name;
	private String date;
	private String kcms;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	public Course(String courseId,String name,String date,String kcms) {
		this.courseId = courseId;
		this.name = name;
		this.date = date;
		this.kcms = kcms;
	}
	
	public void set(String courseId,String name,String date,String kcms){
		this.courseId = courseId;
		this.name = name;
		this.date = date;
		this.kcms = kcms;
	}
	
	public String getId(){
		return courseId;
	}
}
