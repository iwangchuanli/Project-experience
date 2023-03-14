package Demo;


import java.util.HashMap;
import java.util.Map;

/*
 * V put(K key,V value):���Ԫ��
 * V remove(Object key):���ݼ�ɾ����ֵ��Ԫ��
 * void clear():�Ƴ����еļ�ֵ��Ԫ��
 * boolean containsKey(Object key)���жϼ����Ƿ����ָ���ļ�
 * boolean containsValue(Object value):�жϼ����Ƿ����ָ����ֵ
 * boolean isEmpty()���жϼ����Ƿ�Ϊ��
 * int size()�����ؼ����еļ�ֵ�ԵĶ���
 * 
 * Map�����е�ʵ��������ݽṹֻ��Լ���Ч��
 */
public class MapDemo2 {
	public static void main(String[] args) {
		//�������϶���
		Map<String,String> map = new HashMap<String,String>();
		
		//V put(K key,V value):���Ԫ��
		//������ǵ�һ�δ洢����ֱ�Ӵ洢Ԫ�أ�����null
		//��������ǵ�һ�δ洢������ֵ����ǰ��ֵ�滻��������ǰ��ֵ
//		System.out.println("put:"+map.put("���޼�", "������"));
//		System.out.println("put:"+map.put("���޼�", "����"));
		map.put("���޼�", "����");
		map.put("����", "����");
		map.put("���", "С��Ů");
		
		//V remove(Object key):���ݼ�ɾ����ֵ��Ԫ��
//		System.out.println("remove:"+map.remove("����"));
//		System.out.println("remove:"+map.remove("����"));
		
		//void clear():�Ƴ����еļ�ֵ��Ԫ��
		//map.clear();
		
		//boolean containsKey(Object key)���жϼ����Ƿ����ָ���ļ�
//		System.out.println("containsKey:"+map.containsKey("����"));
//		System.out.println("containsKey:"+map.containsKey("����"));
		
		//boolean containsValue(Object value):�жϼ����Ƿ����ָ����ֵ �Լ���ϰ
		
		//boolean isEmpty()���жϼ����Ƿ�Ϊ��
		//System.out.println("isEmpty:"+map.isEmpty());
		
		//int size()�����ؼ����еļ�ֵ�ԵĶ���
		System.out.println("size:"+map.size());
		
		//������϶���
		System.out.println(map);
	}
}

