import java.util.*;
public class Example15_5 {
    public static void main(String args[ ]) { 
       List<Integer> list = new LinkedList<Integer>();
       for(int i=10;i<=50;i=i+10)
           list.add(new Integer(i));
       System.out.println("ϴ��ǰ,�����е�����");
       Iterator<Integer> iter=list.iterator();
       while(iter.hasNext()){
          Integer n=iter.next();
          System.out.printf("%d\t",n.intValue());
       }
       Collections.shuffle(list);
       System.out.printf("\nϴ�ƺ�,�����е�����\n");
       iter=list.iterator();
       while(iter.hasNext()){
          Integer n=iter.next();
          System.out.printf("%d\t",n.intValue());
       }
       System.out.printf("\n��������ת1�κ�,�����е�����\n");
       Collections.rotate(list,1);
       iter=list.iterator();
       while(iter.hasNext()){
          Integer n=iter.next();
          System.out.printf("%d\t",n.intValue());
       }
      
    }
}

