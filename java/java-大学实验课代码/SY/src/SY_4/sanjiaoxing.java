package SY_4;

public class sanjiaoxing {
	double a,b,c;
	double p=(a+b+c)/2;
	void 周长(){
		System.out.println("三角形的周长"+(a+b+c));
	}
	void 面积(){
		double result=Math.abs(p*(p-a)*(p-b)*(p-c));
		System.out.println("三角形的面积"+result);
	}

}
