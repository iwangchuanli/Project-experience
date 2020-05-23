package Demo;


import java.util.HashMap;
import java.util.Map;

/*
 * V put(K key,V value):添加元素
 * V remove(Object key):根据键删除键值对元素
 * void clear():移除所有的键值对元素
 * boolean containsKey(Object key)：判断集合是否包含指定的键
 * boolean containsValue(Object value):判断集合是否包含指定的值
 * boolean isEmpty()：判断集合是否为空
 * int size()：返回集合中的键值对的对数
 * 
 * Map集合中的实现类的数据结构只针对键有效。
 */
public class MapDemo2 {
	public static void main(String[] args) {
		//创建集合对象
		Map<String,String> map = new HashMap<String,String>();
		
		//V put(K key,V value):添加元素
		//如果键是第一次存储，就直接存储元素，返回null
		//如果键不是第一次存储，就用值把以前的值替换，返回以前的值
//		System.out.println("put:"+map.put("张无忌", "周芷若"));
//		System.out.println("put:"+map.put("张无忌", "赵敏"));
		map.put("张无忌", "赵敏");
		map.put("郭靖", "黄蓉");
		map.put("杨过", "小龙女");
		
		//V remove(Object key):根据键删除键值对元素
//		System.out.println("remove:"+map.remove("郭靖"));
//		System.out.println("remove:"+map.remove("郭襄"));
		
		//void clear():移除所有的键值对元素
		//map.clear();
		
		//boolean containsKey(Object key)：判断集合是否包含指定的键
//		System.out.println("containsKey:"+map.containsKey("郭靖"));
//		System.out.println("containsKey:"+map.containsKey("郭襄"));
		
		//boolean containsValue(Object value):判断集合是否包含指定的值 自己练习
		
		//boolean isEmpty()：判断集合是否为空
		//System.out.println("isEmpty:"+map.isEmpty());
		
		//int size()：返回集合中的键值对的对数
		System.out.println("size:"+map.size());
		
		//输出集合对象
		System.out.println(map);
	}
}

