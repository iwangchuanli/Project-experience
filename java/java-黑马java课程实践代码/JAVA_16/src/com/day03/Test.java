package com.day03;
/**
 * 
 * @author Administrator
 *第一题：练习今日的代码

第二题：分析以下需求并实现
	1.功能描述:给定月份，输出对应的季节
	2.要求:
		(1)定义一个月份，例如：int month = 5;
		(2)输出该月份对应的季节
			3,4,5春季
			6,7,8夏季
			9,10,11秋季
			12,1,2冬季
		(3)演示格式如下:
			定义的月份:5
			控制台输出:5月份是春季
		public class Test2 {
		public static void main(String[] args) {
			season(8);
		}
		
		public static void season(int a){
		switch(a){
			case 3:
				System.out.print(a+"月份是春季");
				break;
			case 4:
				System.out.print(a+"月份是春季");
				break;
			case 5:
				System.out.print(a+"月份是春季");
				break;
			case 6:
				System.out.print(a+"月份是夏季");
				break;
			case 7:
				System.out.print(a+"月份是夏季");
				break;
			case 8:
				System.out.print(a+"月份是夏季");
				break;
			case 9:
				System.out.print(a+"月份是秋季");
				break;
			case 10:
				System.out.print(a+"月份是秋季");
				break;
			case 11:
				System.out.print(a+"月份是秋季");
				break;
			case 12:
				System.out.print(a+"月份是冬季");
				break;
			case 1:
				System.out.print(a+"月份是冬季");
				break;
			case 2:
				System.out.print(a+"月份是冬季");
				break;
			default:
				System.out.print("输入有误");
				break;
			}
		}
	}

第三题：分析以下需求并实现
	1.打印1到100之内的整数，但数字中包含9的要跳过
	2.每行输出5个满足条件的数，之间用空格分隔
	3.如：1 2 3 4 5 
	public class Test2 {
		public static void main(String[] args) {
			print();
		}
		
		public static void print(){
			/*1.打印1到100之内的整数，但数字中包含9的要跳过
			2.每行输出5个满足条件的数，之间用空格分隔
			3.如：1 2 3 4 5 
			public class Test2{
		public static void main (String []args){
				int count =0;
        for (int i = 1; i<=100; i++){
            if(i/10!=9&&(i%10!=9)){
                System.out.print(i+" ");
                count++;
                if(count%5==0){
                    System.out.println();
                }
            }
        }
		}
	}
	
第四题：分析以下需求并实现
	public class Test11 {
		public static void main(String[] args) {
			for(int x=1; x<=10; x++) {
				if(x%3==0) {	3  6	9
					//()在此处填写代码
				}
				System.out.println("Java基础班");
			}
		}
	}
	问题:
		1.想在控制台输出2次:"Java基础班"   括号()里面应该填写什么?  break;
		2.想在控制台输出7次:"Java基础班"   括号()里面应该填写什么? continue;
		3.想在控制台输出13次:"Java基础班"   括号()里面应该填写什么? System.out.println("Java基础班");

第五题：分析以下需求并实现
	1.使用嵌套循环完成99乘法表的打印
		1*1=1
		1*2=2	2*2=4
		1*3=3	2*3=6	3*3=9
		...
	public class Test2 {
		public static void main(String[] args) {
			//正三角
			for(int i=1; i<=9; i++) {
				//for(int j=1; j<=9; j++) { 全屏99表
				for(int j=1; j<=i; j++) {
					System.out.print(i+"*"+j+"="+(i*j)+"\t");
				}
				System.out.println();
				}
				
			}
		}
	
	public class Test_9x9 {  
  
    public static void main(String[] args) {  
        //倒三角  
        for (int i=1; i<=9; i++) {  
            for (int j=i; j<=9; j++) {  
                System.out.print(j+"*"+i+"="+(i*j)+"\t");  
            }  
            System.out.println();  
        }  
    }  
      
}  
	
	
 */
public class Test {

}
