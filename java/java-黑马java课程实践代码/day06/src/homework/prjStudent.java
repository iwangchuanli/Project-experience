package homework;

public class prjStudent {

	private String name;
	private int age;
	private String content;
	
	public prjStudent() {
		// TODO Auto-generated constructor stub
	}
	public prjStudent(String name,int age,String content) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.content = content;
	}
	
	public void eat(){
		System.out.println("����Ϊ"+age+"��"+name+"ͬѧ���ڳԷ�....");
	}
	
	public void study(){
		System.out.println("����Ϊ"+age+"��"+name+"ͬѧ����ר����־������"+content+"��֪ʶ....");
	}
	
	private void setter(String name,int age,String content) {
		// TODO Auto-generated method stub
		this.name = name;
		this.age = age;
		this.content = content;
	}
	private void getter() {
		// TODO Auto-generated method stub

	}
}
