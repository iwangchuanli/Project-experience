package homework_1;

public class Test {

	public static void main(String[] args) {
		OldPhone oldphone = new OldPhone("ŵ����");
		NewPhone newphone = new NewPhone("APPLE");
		
		oldphone.call("���ֻ�");
		oldphone.sendMessage("���ֻ�");
		
		newphone.call("���ֻ�");
		newphone.sendMessage("���ֻ�");
		newphone.palyGame();
	}
}
