public class MobileTelephone {
   SIM card;
   public void useSIM(SIM card) {
      this.card=card;
   }
   public void showMess() {
      System.out.println("使用的卡是:"+card.giveCorpName()+"提供的");
      System.out.println("手机号码是:"+card.giveNumber());
   }
}
