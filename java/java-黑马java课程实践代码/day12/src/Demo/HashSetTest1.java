package Demo;


import java.util.HashSet;

/*
 * HashSet���ϴ洢�Զ�����󲢱���
 * Ҫ���������ĳ�Ա����ֵ��ͬ������Ϊ��ͬһ��Ԫ�ء�
 * ��ʾ���Զ���һ��ѧ���࣬������Ա����name��age���������ϵ�ʱ���ڿ���̨���ѧ������ĳ�Ա����ֵ��
 * ���ַ�ʽ����
 * 		������
 * 		��ǿfor
 * 
 * ��Ϊ���Ǵ洢��Ԫ����������û����дhashCode()��equals()���������Ա�֤����Ԫ�ص�Ψһ�ԡ�
 * �����뱣֤����ô����?��д�����������Ϳ����ˡ�
 * �����д��?�Զ����ɾͿ����ˡ�
 */
public class HashSetTest1 {
	public static void main(String[] args) {
		//�������϶���
		HashSet<Student> hs = new HashSet<Student>();
		
		//����Ԫ�ض���
		Student s1 = new Student("����ϼ",30);
		Student s2 = new Student("������",35);
		Student s3 = new Student("������",33);
		Student s4 = new Student("����ϼ",30);
		Student s5 = new Student("������",35);
		
		//��Ԫ����ӵ�����
		hs.add(s1);
		hs.add(s2);
		hs.add(s3);
		hs.add(s4);
		hs.add(s5);
		
		//��������
		//��ǿfor
		for(Student s : hs) {
			System.out.println(s.getName()+"---"+s.getAge());
		}
	}
}

