import java.util.*;
public class Example15_2 {
   public static void main(String args[]){
      List<String> list=new LinkedList<String>();
      for(int i=0;i<=60096;i++){
             list.add("speed"+i);
      }
      Iterator<String> iter=list.iterator();
      long starttime=System.currentTimeMillis();
      while(iter.hasNext()){
           String te=iter.next();
      }
      long endTime=System.currentTimeMillis();
      long result=endTime-starttime;
      System.out.println("ʹ�õ�����������������ʱ��:"+result+"����");
      starttime=System.currentTimeMillis();
      for(int i=0;i<list.size();i++){
          String te=list.get(i);
      }
      endTime=System.currentTimeMillis();
      result=endTime-starttime;
      System.out.println("ʹ��get����������������ʱ��:"+result+"����");
    }
}
