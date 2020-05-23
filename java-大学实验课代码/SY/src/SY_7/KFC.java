package SY_7;

public class KFC {
    ITotalSalary[] peoples;
    void setPeople(ITotalSalary[] peoples)
    {
  	  this.peoples=peoples;
    }
    double totalSalarys()
    {   
    	double sum=0;
    	for(ITotalSalary p:peoples)//遍历接口的对象数组，计算总工资
  		  sum+=p.totalSalary();
    	return sum;
    }
}
