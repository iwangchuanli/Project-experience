package SY_10;

public class Worker {
	int age;
	static int wNum=0;
	String name,num,id;
	double wage;
	static double sWage=0;
	public Worker(String num,int age,String name) throws AgehighException,AgelowException,KongException{
		if(age<18){
			throw new AgelowException();
		}
		else if(age>60){
			throw new AgehighException();
		}
		else if(name==null||name==" "){
			throw new KongException();
		}
	}
	public Worker(){
		wNum++;
		
	}
	public Worker(double wage,String id) throws MoneylowException{
		if(wage<600){
			throw new MoneylowException();
		}
		sWage=sWage+wage;
	}
	public void addSalary(double addSalary) throws MoneyhighException{
		wage=wage+addSalary;
		 if(wage>sWage){
			throw new MoneyhighException();
		}
		 sWage=sWage+addSalary;
	}
	 public void minusSalary(double minusSalary) throws MoneylowException{
		wage=wage-minusSalary;
		if(wage<600){
			throw new MoneylowException();
		}
		sWage=sWage-minusSalary;
	}
	public void showTotalSalary() throws KongException{
	    if(sWage==0){
			throw new KongException();
		}
	    System.out.printf("员工总工资\n", sWage);
	}
	public void showTotalEmployee() throws KongException{
		if(wNum==0){
			throw new KongException();
		}
		System.out.printf("员工总人数\n", wNum);
	}

}
