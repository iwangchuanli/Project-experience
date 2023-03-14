public class SortString {
   public static void sort(String a[]) {
      int count=0; 
      for(int i=0;i<a.length-1;i++) {
          for(int j=i+1;j<a.length;j++) { 
              if(a[j].compareTo(a[i])<0) {
                  String temp=a[i];
                  a[i]=a[j];
                  a[j]=temp;
              }
          } 
      }
   }
}

