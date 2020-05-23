package SY_4;

public class MainClass {  
	   public static void main(String args[]) {
	       FamilyPerson.surname="李"; //用类名FamilyPerson访问surname,并为surname赋值:"李"
	       FamilyPerson father,sonOne,sonTwo;
	       father = new  FamilyPerson();
	       sonOne = new  FamilyPerson();
	       sonTwo = new  FamilyPerson();
	       father.setName("向阳"); //father调用setName(String s),并向s传递"向阳"
	       sonOne.setName("抗日");
	       sonTwo.setName("抗战"); 
	       System.out.println("父亲:"+father.surname+father.name);
	       System.out.println("大儿子:"+sonOne.surname+sonOne.name);
	       System.out.println("二儿子:"+sonTwo.surname+sonTwo.name);  
	      father.setName("张");// father调用setSurName(String s),并向s传递"张"
	       System.out.println("父亲:"+father.surname+father.name);
	       System.out.println("大儿子:"+sonOne.surname+sonOne.name);
	       System.out.println("二儿子:"+sonTwo.surname+sonTwo.name);  
	    }
	}
