import java.util.*;
public class Example15_3 {
    public static void main(String args[]){
        LinkedList mylist=new LinkedList();
        mylist.add("你");                 //链表中的第一个节点
        mylist.add("好");                 //链表中的第二个节点
        int number=mylist.size();         //获取链表的长度
        for(int i=0;i<number;i++){
          String temp=(String)mylist.get(i); //必须强制转换取出的数据
          System.out.println("第"+i+"节点中的数据:"+temp);
        } 
        Iterator iter=mylist.iterator();
        while(iter.hasNext()) {
          String te=(String)iter.next();  //必须强制转换取出的数据
          System.out.println(te);
        }
   }
}
