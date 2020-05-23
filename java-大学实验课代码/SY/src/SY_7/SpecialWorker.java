package SY_7;

public class SpecialWorker {
	int day;
    float salary=100;
    public  SpecialWorker( int day)
    {    	this.day=day;    	
    }	
	public double totalSalary() {		
		return day*salary;
	}

}
