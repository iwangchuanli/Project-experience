package homework;

public class prjMain01 {

	public static void main(String[] args) {
		Manager mag = new Manager("һ��","123", 15000, 6000);
		Coder cod = new Coder("����", "135", 10000);
		mag.work();
		cod.work();
	}
}
