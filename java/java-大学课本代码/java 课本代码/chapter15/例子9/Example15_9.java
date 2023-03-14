import java.util.*;
class StudentKey implements Comparable { 
   double d=0; 
   StudentKey (double d) {
     this.d=d;
   }
   public int compareTo(Object b) {
     StudentKey st=(StudentKey)b;
     if((this.d-st.d)==0)
        return -1;
     else
        return (int)((this.d-st.d)*1000);
  }
}
class Student { 
    String name=null;
    double math,english;
    Student(String s,double m,double e) {
       name=s; 
       math=m;
       english=e;
    }
}
public class Example15_9 {
   public static void main(String args[ ]) {
      TreeMap<StudentKey,Student>  treemap= new TreeMap<StudentKey,Student>();
      String str[]={"��һ","Ǯ��","����","����"};
      double math[]={89,45,78,76};
      double english[]={67,66,90,56};
      Student student[]=new Student[4];
      for(int k=0;k<student.length;k++) {
         student[k]=new Student(str[k],math[k],english[k]);
      }
      StudentKey key[]=new StudentKey[4] ;
      for(int k=0;k<key.length;k++) {
         key[k]=new StudentKey(student[k].math); //�ؼ��ְ���ѧ�ɼ����д�С
      }
      for(int k=0;k<student.length;k++) {
         treemap.put(key[k],student[k]);          
      }
      int number=treemap.size();
      System.out.println("��ӳ������"+number+"������,����ѧ�ɼ�����:");
      Collection<Student> collection=treemap.values();
      Iterator<Student> iter=collection.iterator();
      while(iter.hasNext()) {
         Student stu=iter.next();
         System.out.println("���� "+stu.name+" ��ѧ "+stu.math);
      }
      treemap.clear();
      for(int k=0;k<key.length;k++) {
         key[k]=new StudentKey(student[k].english);//�ؼ��ְ�Ӣ��ɼ����д�С
      }
      for(int k=0;k<student.length;k++) {
         treemap.put(key[k],student[k]);
      }
      number=treemap.size();
      System.out.println("��ӳ������"+number+"������:��Ӣ��ɼ�����:");
      collection=treemap.values();
      iter=collection.iterator();
      while(iter.hasNext()) {
         Student stu=(Student)iter.next();
         System.out.println("���� "+stu.name+" Ӣ�� "+stu.english);
      }
    }
}
