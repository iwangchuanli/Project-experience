import java.io.*;
import java.util.*;
public class Example11_13  {
    public static void main(String args[]) {
      ReadExaminationPaper  reader; //�����������
      reader = new  ReadExaminationPaper();
      reader.setSourceName("test");
      reader.setTableName("testForm");
      Scanner scanner = new Scanner(System.in);
      int amount=reader.getAmount();   //��ȡ������Ŀ
      if(amount==0) {
         System.out.printf("û������,�޷�����");
         System.exit(0);              //�˳�����
      }  
      System.out.printf("�Ծ���%d����Ŀ\n",amount); 
      System.out.printf("�������:��1,2...��ʼ����: ");
      while(scanner.hasNextInt()) {
          int number=scanner.nextInt();
          String huiche=scanner.nextLine(); //���ĵ��û�������ź�Ļس�
          if(number>=1&&number<=amount) {
             String timu[] = reader.getExamQuestion(number);
             for(int i=0;i<timu.length-1;i++) 
                 System.out.println(timu[i]);  //��������ѡ��
             System.out.printf("����ѡ��Ĵ�:");
             String answer=scanner.nextLine();
             if(answer.compareToIgnoreCase(timu[5])==0)
                  System.out.printf("��"+number+"��������\t");
             else 
                  System.out.printf("��"+number+"��������\t");
             System.out.printf("����������ĸ��������\t,������ż�������: ");
          }
          else {
            System.out.printf("��Ų�����\n");
            System.out.printf("����������ĸ��������,������ż�������");
          }
       }
   }
}
