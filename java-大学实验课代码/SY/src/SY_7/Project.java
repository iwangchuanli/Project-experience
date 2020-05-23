package SY_7;

public class Project {
    Programer people;
    public  void setProgramer( Programer people){    	       
    	this.people=people; 
    }
    void showProgramer()
    {    	 
    	people.writeCode();//调用接口的writeCode
    }
}
