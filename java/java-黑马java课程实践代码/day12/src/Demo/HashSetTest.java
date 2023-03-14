package Demo;

import java.util.HashSet;
import java.util.Iterator;

/*
 * HashSet���ϴ洢�Զ�����󲢱���
 * ��ʾ���Զ���һ��ѧ���࣬������Ա����name��age���������ϵ�ʱ���ڿ���̨���ѧ������ĳ�Ա����ֵ��
 * ���ַ�ʽ����
 * 		������
 * 		��ǿfor
 */
public class HashSetTest {
	public static void main(String[] args) {
		//�������϶���
		HashSet<Student> hs = new HashSet<Student>();
		
		//����Ԫ�ض���
		Student s1 = new Student("����ϼ",30);
		Student s2 = new Student("������",35);
		Student s3 = new Student("������",33);
		
		//��Ԫ����ӵ�����
		hs.add(s1);
		hs.add(s2);
		hs.add(s3);
		
		//����
		//������
		Iterator<Student> it = hs.iterator();
		while(it.hasNext()){
			Student s = it.next();
			System.out.println(s.getName()+"---"+s.getAge());
		}
		System.out.println("------------------");
		
		//��ǿfor
		for(Student s : hs) {
			System.out.println(s.getName()+"---"+s.getAge());
		}
	}
}
