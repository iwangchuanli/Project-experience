public class Example12_7 {
   public static void main(String args[]) {
      Bank bank = new Bank();
      bank.setMoney(200);
      Thread accountant,    //会计
             cashier;      //出纳
      accountant = new Thread(bank);
      cashier = new Thread(bank);
      accountant.setName("会计");
      cashier.setName("出纳");
      accountant.start();
      cashier.start(); 
   }
}

