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
		System.out.println("����Ϊ"+id+"��������Ϊ"+salary+"����Ϊ"+bouns+"����Ŀ����"+name+"����Ŭ�������Ź�����,��������,���Ա���ύ�����Ĵ���.....");
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
