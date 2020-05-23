package SY_6;

public class Test_2 {
	public static void main(String[] args) {
		AbstractChat chartDisplay;
		chartDisplay=new PieChart();
		chartDisplay.display();
		chartDisplay=new BarChart();
		chartDisplay.display();
		 chartDisplay=new LineChart();
		chartDisplay.display();
	}

}
