package homework_3;

public class Person {

	private String name;
	private int age;
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(String name,int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	public void keepPet(Dog dog,String something){
//		System.out.println("����Ϊ"+age+"���"+name+"����һֻ"+dog.getColor()+"��"+dog.getAge()+"��ĳ���");
//		dog.eat(something);
//		dog.lookHome();
//	}
//	
//	public void keepPet(Cat cat,String something){
//		System.out.println("����Ϊ"+age+"���"+name+"����һֻ"+cat.getColor()+"��"+cat.getAge()+"��ĳ���");
//		cat.eat(something);
//		cat.catchMouse();
//	}
	public void keepPet(Animals obj){
		if (obj instanceof Cat) {
			System.out.println("����Ϊ"+age+"���"+name+"����һֻ"+obj.getColor()+"��"+obj.getAge()+"��ĳ���");
			((Cat) obj).catchMouse();
			obj.eat(obj.getSomething());
		}
		if (obj instanceof Dog) {
			System.out.println("����Ϊ"+age+"���"+name+"����һֻ"+obj.getColor()+"��"+obj.getAge()+"��ĳ���");
			((Dog) obj).lookHome();
			obj.eat(obj.getSomething());
		}
	}
}
