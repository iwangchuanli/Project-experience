public class Example4_10 { 
   public static void main(String args[]) {
       Lader.�µ�=100;     //Lader���ֽ��뱻���ص��ڴ�,ͨ���������������
       Lader laderOne=new Lader();
       Lader laderTwo=new Lader();
       laderOne.�����ϵ�(28);
       laderTwo.�����ϵ�(66);
       System.out.println("laderOne���ϵ�:"+laderOne.��ȡ�ϵ�());
       System.out.println("laderOne���µ�:"+laderOne.��ȡ�µ�());
       System.out.println("laderTwo���ϵ�:"+laderTwo.��ȡ�ϵ�());
       System.out.println("laderTwo���µ�:"+laderTwo.��ȡ�µ�());
    } 
}
