package SY_5;

public class Line {
	InputScore one;
	DelScore two;
	ComputerAver three;
	 Line() {
		 three=new ComputerAver();
		 two=new DelScore(three);
		 one=new InputScore(two);
		// TODO Auto-generated constructor stub
	}
	 public void givePersonScore() {
		one.inputScore();
	}
	

}
