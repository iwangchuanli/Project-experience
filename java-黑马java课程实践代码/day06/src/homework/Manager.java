package homework;

public class Manager {

	private String name;
	private String id;;
	private double salary;
	private double bouns;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}
	public Manager(String name,String id,double salary,double bouns) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.bouns = bouns;
	}
	
	public void work() {
		System.out.println("工号为"+id+"基本工资为"+salary+"奖金为"+bouns+"的项目经理"+name+"正在努力的做着管理工作,分配任务,检查员工提交上来的代码.....");
	}
	
	private void seter(String name,String id,double salary,double bouns) {
		// TODO Auto-generated method stub
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.bouns = bouns;
	}
	private void getter() {
		// TODO Auto-generated method stub

	}
}
