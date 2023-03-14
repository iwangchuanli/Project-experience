package SY_4;

public class User {
	   public static void main(String args[]) {
	      CD dataCD = new CD();
	      int b[] ={1,2,3,4,5,6,7,8};
	      dataCD.setSize(b.length);
	      dataCD.setContent(b);
	      int a[]=dataCD.getContent();
	      System.out.println("dataCD上的内容：");
	      for(int i=0;i<a.length;i++)//< DIV> 
	         System.out.printf("%3d",a[i]); 
	      Computer computerIMB = new Computer();
	      computerIMB.putCD(dataCD);//computerIMB调用putCD(CD cd)方法,将dataCD的引用传递给cd
	      System.out.println(" 将dataCD的数据复制到计算机:computerIMB.");
	      computerIMB.copyToComputer();//computerIMB调用copyToComputer()方法
	      System.out.println("computerIMB上的内容：");
	      computerIMB.showData();
	      int m=12; 
	      System.out.println(" computerIMB将每个数据增加"+m);
	      computerIMB.addData(m);
	      System.out.println("computerIMB将增值后的数据复制到CD:dataCD"); 
	     computerIMB.copyToCD();//computerIMB调用copyToCD()方法
	      System.out.println("dataCD上的内容：");
	      a=dataCD.getContent();
	      for(int i=0;i<a.length;i++)//< DIV> 
	         System.out.printf("%3d",a[i]);   
	   }
	 
	}
