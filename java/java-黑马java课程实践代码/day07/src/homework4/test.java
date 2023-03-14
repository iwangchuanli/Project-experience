package homework4;

public class test {

	public static void main(String[] args) {
		Manager mang = new Manager("1", "经理", 1000);
		FWY fwy = new FWY("2", "服务员");
		
		mang.workWay();
		fwy.workWay();
	}
}
