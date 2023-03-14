package SY_5;
//定义学生比较类StudentCompare,该类包含方法成员有
//1）求最重学生方法：Student  maxWeightStu( Student [] ),该方法处理的是学生对象数组，返回的是学生对象
//2）求最高学生方法：maxHeightStu(Student []),该方法处理的是学生对象数组，返回的是学生对象

public class StudentCompare {
	
	Student maxWeightStu(Student []w){
		double x=w[0].weight;
		int y=0;
		for(int i=0;i<w.length;i++){
			if(x<=w[i].weight){
				x=w[i].weight;
				y=i;
			}
		}
		return w[y];
	}
	Student maxHeightStu(Student []h){
		double x=h[0].height;
		int y=0;
		for(int i=0;i<h.length;i++){
			if(x<=h[i].height)
				x=h[i].height;
				y=i;
		}
		return h[y];
	}

}
