package homework;

public class MainApp01 {

	public static void main(String[] args) {
		Teacher teac1 = new Teacher();
		Teacher teac2 = new Teacher("t002","�ű̳�","Ů",24,"IOS");
		Teacher teac3 = new Teacher();
		teac1.set("t001","Ѧ֮ǫ","��",26,"Java");
		teac3.set("t003","�Ž�","��",28,"Java");
		System.out.println("��ʦ�ı�ŷֱ�Ϊ��"+teac1.getId()+",  "+teac2.getId()+",  "+teac3.getId()+",  ");
		Course java = new Course("s001","Java","2007-02-08","Javaѧ�ƣ�����JavaSE��JavaEE");
		Course ios = new Course();
		ios.set("s002","IOS","2007-02-09","IOSϵͳ����");
		System.out.println("�γ̵ı�ŷֱ�Ϊ��"+java.getId()+",  "+ios.getId());
	}
}
