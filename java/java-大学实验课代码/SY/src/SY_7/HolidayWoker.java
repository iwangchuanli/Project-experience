package SY_7;

public class HolidayWoker implements ITotalSalary{
	//模仿Weekworker
	int month;
    float salary=3000;
    public  HolidayWoker( int month)
    {    	this.month=month;    
    }
	public double totalSalary() {		
		return month*salary;
	}
}