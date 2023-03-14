package homework;
/***
 * @author Administrator
 *取值范围小的数据类型与取值范围大的数据类型进行运算,
 *会先将小的数据类型提升为大的,再运算
 */
public class prj01 {
public static void main(String[] args) {
	 @SuppressWarnings("unused")
	byte b1=3,b2=4,b;
		//b=b1+b2;
		b=(byte) (b1+b2);
		/*强制转换的格式
		* 目标类型 变量名=(目标类型)(被转换的数据); */
		
		
		/*
		 * 隐式数据类型转换
		 * int b;
		 * b=b1+b2;
		 * */
		b=3+4;

}
}
