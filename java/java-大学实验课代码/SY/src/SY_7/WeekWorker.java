package SY_7;

public class WeekWorker implements ITotalSalary{
    int hour;
    float salary=10;	
	public WeekWorker( int hour) {
		this.hour=hour;   
	}
	public double totalSalary() {		
		return hour*salary;
	}
}
