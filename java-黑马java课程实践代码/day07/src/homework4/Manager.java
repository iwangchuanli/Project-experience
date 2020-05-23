package homework4;

public class Manager extends Worker{

	private double bouns;

	public Manager(String id,String name,double bouns) {
		super.setId(id);
		super.setName(name);
		this.bouns = bouns;
	}
	public void workWay(){
		System.out.println(super.getName()+"管理员工");
	}
	
	
}
