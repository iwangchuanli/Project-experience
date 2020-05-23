import java.io.*;
import java.util.*;
public class StandardExam {
   File testFile;
   public void setTestFile(File f) {
      testFile = f;
   }
   public void startExamine() {
      int score=0;
      Scanner scanner = new Scanner(System.in);
      try {
           FileReader inOne = new FileReader(testFile);
           BufferedReader inTwo = new BufferedReader(inOne);
           String s = null;
           while((s = inTwo.readLine())!=null){
              if(!s.startsWith("-"))
                  System.out.println(s);
              else {
                  s = s.replaceAll("-",""); System.out.println(s);
                  String correctAnswer = s;
                  System.out.printf("\n输入选择的答案:");
                  String answer=scanner.nextLine();
                  if(answer.compareToIgnoreCase(correctAnswer)==0)
                     score++;
              }
           }
           inTwo.close();
      } 
      catch(IOException exp){}
      System.out.printf("最后的得分:%d\n",score);
   }
}
