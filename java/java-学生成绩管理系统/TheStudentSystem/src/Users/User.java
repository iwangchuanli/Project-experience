package Users;

public class User {
	public  String userNo;//�û�ID�ţ�ϵͳ��Ψһ��
	public String name;//�û�����
	public String sexy;//�Ա�
	public String classGrade;//�༶
	public String coursename;//�γ�����
	public String score;//�γ̷���;
	public String item;//ѧ��
	public User(String userNo,String name,String sexy,String classGrade,String coursename,String score,String item){
		this.userNo = userNo;
		this.name = name;
		this.sexy = sexy;
		this.classGrade = classGrade;
		this.coursename = coursename;
		this.score = score;
		this.item = item;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getItem(){
		return item;
	}
	public void setItem(String item){
		this.item = item;
	}
	public String getSexy(){
		return sexy;
	}
	public void setSexy(String sexy){
		this.sexy = sexy;
	}
	public String getClassGrade(){
		return classGrade;
	}
	public void setClassGrade(String classGrade){
		this.classGrade = classGrade;
	}

	
}
