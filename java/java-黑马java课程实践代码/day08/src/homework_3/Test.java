package homework_3;

public class Test {

	public static void main(String[] args) {
		
		Person per = new Person("����",30);
		Cat cat = new Cat(3, "����ɫ","��");
		Dog dog = new Dog(2, "����ɫ","��ͷ");
		//per.keepPet(cat, "��");
		//per.keepPet(dog, "��ͷ");
		
		per.keepPet(cat);
		per.keepPet(dog);
		
	}
}
