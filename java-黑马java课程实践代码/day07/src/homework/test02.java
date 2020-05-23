package homework;

public class test02 {

	public static void main(String[] args) {
		Manager mang = new Manager("项目经理", "123", 9999, 6000);
		Coder coder = new Coder("程序员", "456", 10000);
		
		mang.work();
		coder.work();
	}
}
