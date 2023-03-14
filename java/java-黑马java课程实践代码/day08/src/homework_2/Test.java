package homework_2;

public class Test {

	public static void animal(Animals obj){
		obj.eat();
		
		//obj = new Animals();
		if(obj instanceof Cat){
			((Cat) obj).catchMouse();
		}
		if(obj instanceof Dog){
			((Dog) obj).lookHome();
		}
		
		
	}
	public static void main(String[] args) {
		Animals a = new Animals();
		
		a = new Cat("black");
		animal(a);
		
		a = new Dog("white");
		animal(a);
//		Animals a1 = new Animals();
//		a1.eat();
//		
//		Animals a = new Cat("black");
//		a.eat();
//		
//		Cat c = (Cat)a;
//		c.eat();
//		c.catchMouse();
//		
//		Dog d = new Dog("white");
//		d.eat();
//		d.lookHome();
		
	}
}
