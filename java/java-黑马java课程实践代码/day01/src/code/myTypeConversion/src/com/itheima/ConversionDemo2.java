package code.myTypeConversion.src.com.itheima;
/*
 * ǿ��ת����
 * 		Ŀ������ ������= (Ŀ������)(��ת��������);
 * 
 * 		��Ȼ������ǿ��ת�������ǲ����顣��Ϊǿ��ת�����ܻ������ݵĶ�ʧ��
 */
public class ConversionDemo2 {
	public static void main(String[] args) {
		//��������������һ��int���ͣ�һ��byte����
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
