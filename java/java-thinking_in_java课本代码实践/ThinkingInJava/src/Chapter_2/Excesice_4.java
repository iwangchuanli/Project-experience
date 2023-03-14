package Chapter_2;



public class Excesice_4 {
public static void main(String[] args) {
	DataOnly data=new DataOnly();
	data.i=47;
	data.d=1.1;
	data.b=false;
	data.cData.c='a';
	System.out.println(data.i+data.d+data.cData.c);
}
}
class DataOnly{
	OtherData cData=new OtherData();
	int i;
	double d;
	boolean b;
}
class OtherData{
	char c;
}
