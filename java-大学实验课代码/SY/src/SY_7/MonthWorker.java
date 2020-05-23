package SY_7;

public class MonthWorker implements ITotalSalary{
	//模仿Weekworker
	int day;
    float salary=80;
    public  MonthWorker( int day)
    {    	this.day=day;    	
    }	
	public double totalSalary() {		
		return day*salary;
	}
}
