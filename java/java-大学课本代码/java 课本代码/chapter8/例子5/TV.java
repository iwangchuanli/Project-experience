public class TV {
   String name;
   public TV() {
   } 
   public TV(String s) {
      name=s;
   }
   public String toString() {
      String oldStr=super.toString(); 
      return oldStr+"\n���ǵ��ӻ���Ʒ����:"+name;
   }
}
