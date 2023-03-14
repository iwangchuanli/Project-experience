package homework;

public class Manager extends codeWorker{

	private double bouns;
	
	public Manager(String name,String id,double salary,double bouns) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.bouns = bouns;
	}
	public void work(){
		System.out.println("我是项目经理！"+"我叫:"+name);
	}
	
}
