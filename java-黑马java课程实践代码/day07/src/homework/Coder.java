package homework;

public class Coder extends codeWorker{

	public Coder(String name,String id,double salary) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	public void work(){
		System.out.println("我是程序员！"+"我叫:"+name);
	}
}
