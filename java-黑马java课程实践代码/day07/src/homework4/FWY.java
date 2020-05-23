package homework4;

public class FWY extends Worker{

	
	public FWY(String id,String name) {
		super.setId(id);
		super.setName(name);
		// TODO Auto-generated constructor stub
	}

	public void workWay(){
		System.out.println(super.getName()+"为客户服务！");
	}
}
