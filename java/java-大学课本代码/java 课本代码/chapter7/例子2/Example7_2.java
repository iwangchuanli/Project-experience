public class Example7_2 {
   public static void main(String args[]) {
       ShowBoard board=new ShowBoard();
       board.showMess(new OutputEnglish());  //���������OutputAlphabet���������
       board.showMess(new OutputAlphabet()  
                                                            //���������OutputAlphabet�������������
                       {  public void output() 
                          {  for(char c='��';c<='��';c++)  //���ϣ����ĸ
                               System.out.printf("%3c",c); 
                          }  
                       }
                     );
   } 
}


