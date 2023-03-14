package SY_7;

public class KFCTest {
public static void main(String[] args) {
	KFC hsKFC=new KFC();
	ITotalSalary[] total=new ITotalSalary[8];
	total[0]=new WeekWorker(5);
	total[1]=new WeekWorker(15);
	total[2]=new WeekWorker(15);
	total[3]=new HolidayWoker(50);
	total[4]=new HolidayWoker(60);
	total[5]=new MonthWorker(2000);
	total[6]=new MonthWorker(3000);
	total[7]=new MonthWorker(5000);
	hsKFC.setPeople(total);
	System.out.println(hsKFC.totalSalarys());
}
}

