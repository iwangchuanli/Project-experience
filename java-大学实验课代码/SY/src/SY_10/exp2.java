package SY_10;

public class exp2
{
    public static void main(String args[])
    {
        int a=4,b=0;
        
        try {
			System.out.println(a+"/"+b+"="+a/b);
		} catch (Exception e) {
			e.setStackTrace(null);
		}
    }
}

