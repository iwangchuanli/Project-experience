import java.io.*;
import java.util.*;
public class Example11_13  {
    public static void main(String args[]) {
      ReadExaminationPaper  reader; //负责读入试题
      reader = new  ReadExaminationPaper();
      reader.setSourceName("test");
      reader.setTableName("testForm");
      Scanner scanner = new Scanner(System.in);
      int amount=reader.getAmount();   //获取试题数目
      if(amount==0) {
         System.out.printf("没有试题,无法考试");
         System.exit(0);              //退出程序
      }  
      System.out.printf("试卷共有%d道题目\n",amount); 
      System.out.printf("输入题号:如1,2...开始考试: ");
      while(scanner.hasNextInt()) {
          int number=scanner.nextInt();
          String huiche=scanner.nextLine(); //消耗调用户输入题号后的回车
          if(number>=1&&number<=amount) {
             String timu[] = reader.getExamQuestion(number);
             for(int i=0;i<timu.length-1;i++) 
                 System.out.println(timu[i]);  //输出试题和选择
             System.out.printf("输入选择的答案:");
             String answer=scanner.nextLine();
             if(answer.compareToIgnoreCase(timu[5])==0)
                  System.out.printf("第"+number+"题做对了\t");
             else 
                  System.out.printf("第"+number+"题做错了\t");
             System.out.printf("输入任意字母结束考试\t,输入题号继续考试: ");
          }
          else {
            System.out.printf("题号不合理\n");
            System.out.printf("输入任意字母结束考试,输入题号继续考试");
          }
       }
   }
}
