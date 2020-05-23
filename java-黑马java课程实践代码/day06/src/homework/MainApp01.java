package homework;

public class MainApp01 {

	public static void main(String[] args) {
		Teacher teac1 = new Teacher();
		Teacher teac2 = new Teacher("t002","张碧晨","女",24,"IOS");
		Teacher teac3 = new Teacher();
		teac1.set("t001","薛之谦","男",26,"Java");
		teac3.set("t003","张杰","男",28,"Java");
		System.out.println("老师的编号分别为："+teac1.getId()+",  "+teac2.getId()+",  "+teac3.getId()+",  ");
		Course java = new Course("s001","Java","2007-02-08","Java学科，包含JavaSE和JavaEE");
		Course ios = new Course();
		ios.set("s002","IOS","2007-02-09","IOS系统开发");
		System.out.println("课程的编号分别为："+java.getId()+",  "+ios.getId());
	}
}
