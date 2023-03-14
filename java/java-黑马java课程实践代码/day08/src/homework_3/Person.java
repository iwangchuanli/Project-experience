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
//		System.out.println("年龄为"+age+"岁的"+name+"养了一只"+dog.getColor()+"的"+dog.getAge()+"岁的宠物");
//		dog.eat(something);
//		dog.lookHome();
//	}
//	
//	public void keepPet(Cat cat,String something){
//		System.out.println("年龄为"+age+"岁的"+name+"养了一只"+cat.getColor()+"的"+cat.getAge()+"岁的宠物");
//		cat.eat(something);
//		cat.catchMouse();
//	}
	public void keepPet(Animals obj){
		if (obj instanceof Cat) {
			System.out.println("年龄为"+age+"岁的"+name+"养了一只"+obj.getColor()+"的"+obj.getAge()+"岁的宠物");
			((Cat) obj).catchMouse();
			obj.eat(obj.getSomething());
		}
		if (obj instanceof Dog) {
			System.out.println("年龄为"+age+"岁的"+name+"养了一只"+obj.getColor()+"的"+obj.getAge()+"岁的宠物");
			((Dog) obj).lookHome();
			obj.eat(obj.getSomething());
		}
	}
}
