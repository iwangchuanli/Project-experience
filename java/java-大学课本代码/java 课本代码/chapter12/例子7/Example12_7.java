public class Example12_7 {
   public static void main(String args[]) {
      Bank bank = new Bank();
      bank.setMoney(200);
      Thread accountant,    //���
             cashier;      //����
      accountant = new Thread(bank);
      cashier = new Thread(bank);
      accountant.setName("���");
      cashier.setName("����");
      accountant.start();
      cashier.start(); 
   }
}

