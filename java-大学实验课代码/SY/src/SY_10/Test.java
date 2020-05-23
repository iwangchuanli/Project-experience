package SY_10;

import java.util.Scanner;

public class Test{
 
    public static void main(String[] args) {
    		int totalEmployee;
    		int totalSalary;

    		 System.out.println("设置公司员工人数和员工总工资：");
        	 Scanner input=new Scanner(System.in);
        	 totalEmployee=input.nextInt();
        	 totalSalary=input.nextInt();
        	 Employee employees=new Employee();
        	 employees.setTotalEmployee(totalEmployee);
        	 employees.setTotalSalary(totalSalary);
        	 input.close();
		
       try {
           Employee employee1 = new Employee("001", "赵钱", 19);
           Employee employee2 = new Employee(5000, "215001");
          // totalEmployee=totalEmployee+1;
           
           employee1.showTotalEmployee();
           employee2.showTotalSalary();
           employee2.addSalary(300.0);
         //  employee2.minusSalary(100);
       } 
       catch (NullException | LowAgeException | HeightAgeException |LowSalaryException e) {
           e.printStackTrace();
       }
       
       try {
           Employee employee3 = new Employee("002", "孙李", 20);
           Employee employee4 = new Employee(7000, "215002");
           //totalEmployee=totalEmployee+1;
           employee3.showTotalEmployee();
           employee4.showTotalSalary();
           employee4.addSalary(400.0);
          // employee2.minusSalary(200);
       } 
       catch (NullException | LowAgeException | HeightAgeException |LowSalaryException e) {
           e.printStackTrace();
       }
       try {
           Employee employee5 = new Employee("003", "孙李", 21);
           Employee employee6 = new Employee(9000, "215003");
           employee5.showTotalEmployee();
           employee6.showTotalSalary();
           employee6.addSalary(500.0);
           employee6.minusSalary(300);
       } 
       catch (NullException | LowAgeException | HeightAgeException |LowSalaryException e) {
           e.printStackTrace();
       }
       
    }
}
