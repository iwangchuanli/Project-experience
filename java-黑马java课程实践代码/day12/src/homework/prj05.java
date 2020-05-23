package homework;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class prj05 {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        Map<String,Integer> wordMap=new HashMap<String,Integer>();
        System.out.println("Please input a String:");
        String testString=sc.nextLine();
        sc.close();
        
        testString.replaceAll("\\s{1,}"," ");
        String[] stringArray=testString.split(" ");
        for(String e:stringArray){
            if(wordMap.containsKey(e)){
                wordMap.put(e, wordMap.get(e)+1);
            }else{
                wordMap.put(e,1);
            }
        }
        
        for(String e:wordMap.keySet()){
            System.out.printf("word %s = %s times.\n",e,wordMap.get(e));
        }
    }
}