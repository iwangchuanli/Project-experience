package homework_1;

public class Test {

	public static void main(String[] args) {
		OldPhone oldphone = new OldPhone("诺基亚");
		NewPhone newphone = new NewPhone("APPLE");
		
		oldphone.call("旧手机");
		oldphone.sendMessage("旧手机");
		
		newphone.call("新手机");
		newphone.sendMessage("新手机");
		newphone.palyGame();
	}
}
