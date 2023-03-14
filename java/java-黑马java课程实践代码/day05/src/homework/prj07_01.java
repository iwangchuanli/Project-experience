package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class prj07_01 {
public static void main(String args[]){
int [] arr = {1,2,2,2,3,3,4,4,4,4};
testaaa(arr);
}


public static void testaaa(int [] str) {
   Map<Integer, Integer> map=new HashMap<>();
   for (int i = 0; i < str.length; i++) {
       if(map.containsKey(str[i])) {
           map.put(str[i], map.get(str[i])+1);//原来有则+1
       }else {
           map.put(str[i], 1); //原来没有则放一个
       }
   }

   for (Entry<Integer, Integer> entry : map.entrySet()) {
       System.out.println("数字"+entry.getKey()+"出现了"+entry.getValue()+"次");
   }
}

}