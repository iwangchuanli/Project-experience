public class Example7_5 {
   public static void main(String args[]) {
      Bank bank=new Bank();
      try{  bank.income(200,-100);
            bank.income(300,-100);
            bank.income(400,-100);
            System.out.printf("银行目前有%d元\n",bank.getMoney());
            bank.income(200, 100);
            bank.income(99999,-100);
      }
      catch(BankException e) {
            System.out.println("计算收益的过程出现如下问题:");
            System.out.println(e.warnMess());        
      }
      System.out.printf("银行目前有%d元\n",bank.getMoney());
   }
}
