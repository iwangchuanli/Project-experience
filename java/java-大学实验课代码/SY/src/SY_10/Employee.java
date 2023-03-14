package SY_10;

public class Employee {
    private String number;
    private String name;
    private int age;
    private int salary;
    private String idCard;
    int totalEmployee=1;
    int totalSalary=1;
    public Employee() {
		// TODO Auto-generated constructor stub
	}
    
   //设置编号，姓名，年龄
    public Employee(String number, String name, int age) throws NullException, LowAgeException, HeightAgeException {
       this.number = number;
       this.name = name;
       this.age = age;
       if(name == ""){
           throw new NullException("空异常！");
       }
       //System.out.println("renshu"+(totalEmployee++));
       if(age < 18){
           throw new LowAgeException("年龄太小！");
       }
       
       if(age > 60){
           throw new HeightAgeException("年龄太大！");
       }
     //  totalEmployee++;
    }
    //设置工资，身份证号
    public Employee(int salary, String idCard) throws LowSalaryException {
       this.salary = salary;
       this.idCard = idCard;
       if(salary < 600){
           throw new LowSalaryException("工资太低！");
       }
    }
   
    //所有属性的set和get方法
    public String getNumber() {
       return number;
    }
    public void setNumber(String number) {
       this.number = number;
    }
    public String getName() {
       return name;
    }
    public void setName(String name) {
       this.name = name;
    }
    public int getAge() {
       return age;
    }
    public void setAge(int age) {
       this.age = age;
    }
    public int getSalary() {
       return salary;
    }
    public void setSalary(int salary) {
       this.salary = salary;
    }
    public String getidCard() {
       return idCard;
    }
    public void setidCard(String idCard) {
    	this.idCard = idCard;
    }
    public int getTotalEmployee() {
       return totalEmployee;
    }
    public void setTotalEmployee(int totalEmployee) {
       this.totalEmployee = totalEmployee;
    }
    public int getTotalSalary() {
       return totalSalary;
    }
    public void setTotalSalary(int totalSalary) {
       this.totalSalary = totalSalary;
    }
   //异常处理
    public double addSalary(double addSalary) throws HeightAgeException{
       if(addSalary+salary > totalSalary){
           throw new HeightAgeException("工资太高！");
       }
       return addSalary+salary;
    }
    
    public double minusSalary(double minusSalary) throws LowSalaryException{
       if(salary-minusSalary < 600){
           throw new LowSalaryException("工资太低！");
       }
       return salary-minusSalary; 
    }
    
    public void showTotalSalary() throws NullException{
       if(totalSalary == 0){
           throw new NullException("空异常！");
       }
       System.out.println("工资总额:"+totalSalary);
    }
    
    public void showTotalEmployee() throws NullException{
       if(totalEmployee == 0){
           throw new NullException("空异常！");
       }
       System.out.println("员工人数:"+totalEmployee);
    }
   
}