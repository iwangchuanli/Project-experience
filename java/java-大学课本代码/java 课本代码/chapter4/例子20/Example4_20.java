public class Example4_20 { 
   public static void main(String args[ ]) { 
      char a[]={'a','b','c','D','E','F'};
      for(int i=0;i<a.length;i++) { 
         if(Character.isLowerCase(a[i]))    
             a[i]=Character.toUpperCase(a[i]);
         else if(Character.isUpperCase(a[i]))
             a[i]=Character.toLowerCase(a[i]);
       }
      for(int i=0;i<a.length;i++)   
         System.out.print(" "+a[i]);
 
   } 
}




