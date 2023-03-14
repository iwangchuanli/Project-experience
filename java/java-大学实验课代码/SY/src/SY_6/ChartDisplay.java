package SY_6;

public class ChartDisplay {
	private AbstractChat chart;
	public void setChart( AbstractChat chart){
		this.chart=chart;
	}
	public void display(){
		chart.display();
	}

}
