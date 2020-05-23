package homework_3;

public class Test {

	public static void main(String[] args) {
		
		Person per = new Person("老王",30);
		Cat cat = new Cat(3, "灰颜色","鱼");
		Dog dog = new Dog(2, "黑颜色","骨头");
		//per.keepPet(cat, "鱼");
		//per.keepPet(dog, "骨头");
		
		per.keepPet(cat);
		per.keepPet(dog);
		
	}
}
