package code.myTypeConversion.src.com.itheima;
/*
 * 强制转换：
 * 		目标类型 变量名= (目标类型)(被转换的数据);
 * 
 * 		虽然可以做强制转换，但是不建议。因为强制转换可能会有数据的丢失。
 */
public class ConversionDemo2 {
	public static void main(String[] args) {
		//定义两个变量，一个int类型，一个byte类型
		int a = 10;
		byte b = 20;
		int c = a + b;
		System.out.println(c);
		
		@SuppressWarnings("unused")
		byte d = 30;
		byte e = (byte)(a + b);
		System.out.println(e);
	}
}
