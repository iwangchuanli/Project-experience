package homework_1;

public class NewPhone extends Phone implements IPlay{

	public NewPhone(String brand) {
		// TODO Auto-generated constructor stub
		super.setBrand(brand);
	}
	@Override
	public void palyGame() {
		// TODO Auto-generated method stub
		System.out.println("��"+super.getBrand()+"��������Ϸ");
	}

	
}
