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
		System.out.println("����Ϊ"+id+"��������Ϊ"+salary+"�ĳ���Ա"+name+"����Ŭ����д�Ŵ���......������������");
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
