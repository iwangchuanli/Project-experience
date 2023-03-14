package SY_6;
//4）有黄山人，该类继承了中国人，重写了住功能，该功能为“黄山房价在8000以上”；添加新功能游，该功能为“黄山到处是美景，黄山欢迎您”；

public class 黄山人 extends 中国人{
	public void buy() {
		System.out.println("黄山房价在8000以上!");
	}
	public void travel() {
		System.out.println("黄山到处是美景，黄山欢迎您！");
	}

}
