public class TV {
   String name;
   public TV() {
   } 
   public TV(String s) {
      name=s;
   }
   public String toString() {
      String oldStr=super.toString(); 
      return oldStr+"\n这是电视机，品牌是:"+name;
   }
}
