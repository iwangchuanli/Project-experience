import java.util.*;
public class Example15_3 {
    public static void main(String args[]){
        LinkedList mylist=new LinkedList();
        mylist.add("��");                 //�����еĵ�һ���ڵ�
        mylist.add("��");                 //�����еĵڶ����ڵ�
        int number=mylist.size();         //��ȡ����ĳ���
        for(int i=0;i<number;i++){
          String temp=(String)mylist.get(i); //����ǿ��ת��ȡ��������
          System.out.println("��"+i+"�ڵ��е�����:"+temp);
        } 
        Iterator iter=mylist.iterator();
        while(iter.hasNext()) {
          String te=(String)iter.next();  //����ǿ��ת��ȡ��������
          System.out.println(te);
        }
   }
}
