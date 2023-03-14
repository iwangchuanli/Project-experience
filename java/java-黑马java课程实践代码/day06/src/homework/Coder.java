package homework;

public class Coder {

	private String name;
	private String id;
	private double salary;

	public Coder() {
		// TODO Auto-generated constructor stub
	}
	public Coder(String name,String id,double salary) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	
	public void work(){
		System.out.println("工号为"+id+"基本工资为"+salary+"的程序员"+name+"正在努力的写着代码......操作步骤描述");
	}
	
	private void setter(String name,String id,double salary) {
		// TODO Auto-generated method stub
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	private void gettet() {
		// TODO Auto-generated method stub

	}
}
