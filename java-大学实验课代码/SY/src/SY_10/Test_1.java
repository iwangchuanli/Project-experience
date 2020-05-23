package SY_10;

public class Test_1 {
	public static void main(String args[]){
		Worker w1=new Worker();
		Worker w2=new Worker();
		Worker w3=new Worker();
		try{
			 w1 = new Worker("001",17,"小王");
			 
		}
		catch(AgelowException e){
			System.out.println(e.warnMess());
		}
		catch(AgehighException e){
			System.out.println(e.warnMess());
		}
		catch(KongException e){
			System.out.println(e.warnMess());
		}
		try{
			w1 = new Worker(500,"111111111");
		}
		catch(MoneylowException e){
			System.out.println(e.warnMess());
		}
	  try{
			 w2 = new Worker("002",61,"小宋");
			 
		}
		catch(AgelowException e){
			System.out.println(e.warnMess());
		}
		catch(AgehighException e){
			System.out.println(e.warnMess());
		}
		catch(KongException e){
			System.out.println(e.warnMess());
		}
	  try{
			w2 = new Worker(800,"111111111");
			try{
				w2.addSalary(100000);
			}
			catch(MoneyhighException e){
				System.out.println(e.warnMess());
			}
		}
		catch(MoneylowException e){
			System.out.println(e.warnMess());
		}
		try{
			 w3 = new Worker("003",20," ");
			
			
		}
		catch(AgelowException e){
			System.out.println(e.warnMess());
		}
		catch(AgehighException e){
			System.out.println(e.warnMess());
		}
		catch(KongException e){
			System.out.println(e.warnMess());
		}
		try{
			w3 = new Worker(800,"111111111");
			try{
				w3.minusSalary(300);
			}
			catch(MoneylowException e){
				System.out.println(e.warnMess());
			}
		}
		catch(MoneylowException e){
			System.out.println(e.warnMess());
		}
		try{
			w3.showTotalEmployee();
			w3.showTotalSalary();
		}
		catch(KongException e){
			System.out.println(e.warnMess());
		}
		
	}

}
